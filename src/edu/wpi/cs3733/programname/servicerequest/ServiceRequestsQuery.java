package edu.wpi.cs3733.programname.servicerequest;

import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.database.DBConnection;
import edu.wpi.cs3733.programname.database.DatabaseQueryController;
import edu.wpi.cs3733.programname.servicerequest.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class ServiceRequestsQuery {
    DBConnection dbConnection;
    public ServiceRequestsQuery(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public ArrayList<ServiceRequest> queryServiceRequestsByType(String serviceType){
        ServiceRequest queryResult = null;
        ArrayList<ServiceRequest> resultList = new ArrayList<ServiceRequest>();
        try {
            String sql = "SELECT * FROM ServiceRequests WHERE type = " + serviceType;
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            int id;
            Employee requester;
            String requesterUsername;
            String type;
            NodeData location;
            String nodeID;
            String description;
            Timestamp createdTime;
            Timestamp handledTime;
            Timestamp completedTime;
            String status;
            Employee handler;
            String handlerUsername;

            while(result.next()) {
                id = result.getInt("id");
                requesterUsername = result.getString("requester");
                // todo: handle null exception
                requester = new EmployeesQuery(dbConnection).queryEmployeeByUsername(requesterUsername);
                type = result.getString("type");
                nodeID = result.getString("location");
                // todo: handle null exception
                location = new DatabaseQueryController(dbConnection).queryNodeById(nodeID);
                description = result.getString("description");
                createdTime = result.getTimestamp("createdTime");
                handledTime = result.getTimestamp("handledTime");
                completedTime = result.getTimestamp("completedTime");
                status = result.getString("status");
                handlerUsername = result.getString("handler");
                // todo: handle null exception
                handler = new EmployeesQuery(dbConnection).queryEmployeeByUsername(handlerUsername);
                queryResult = new ServiceRequest(id,requester, type, location, description, createdTime, handledTime, completedTime, status, handler);
                resultList.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Select Service Request Failed!");
            e.printStackTrace();
        }
        return resultList;
    }

    public void addServiceRequest(ServiceRequest serviceRequest){
        int id = serviceRequest.getId();
        Employee requester = serviceRequest.getRequester();
        String requesterUsername = requester.getUsername();
        String type = serviceRequest.getType();
        NodeData location = serviceRequest.getLocation();
        String nodeID = location.getId();
        String description = serviceRequest.getDescription();
        Timestamp createdTime = serviceRequest.getCreatedTime();
        Timestamp handledTime = serviceRequest.getHandledTime();
        Timestamp completedTime = serviceRequest.getCompletedTime();
        String status = serviceRequest.getStatus();
        Employee handler = serviceRequest.getHandler();
        // todo: handle null
        String handlerUsername = handler.getUsername();

        String str;
        try {
            str = "insert into ServiceRequests values( id +'" + requesterUsername + "', '" + type + "', '" + nodeID + "', '" + description +
                    "', '" + createdTime + "','" + handledTime + "', '" + completedTime + "',"+ status + "','"+ handlerUsername + "')";
            //System.out.println(str);
            dbConnection.executeUpdate(str);
        } catch (SQLException e) {
            System.out.println("Insert Service Request Failed!");
            e.printStackTrace();
        }
    }

    public void handleServiceRequest(ServiceRequest serviceRequest, Employee handler) {
        int id = serviceRequest.getId();
        String handlerUsername = handler.getUsername();
        Date date = new Date();
        Timestamp handledTime = new Timestamp(date.getTime());
        String str;
        try {
            str = "update ServiceRequests set handledTime = '"+ handledTime +"' status = 'handled', handler = '"+ handlerUsername +"' where id = '" + id ;
            //System.out.println(str);
            dbConnection.executeUpdate(str);
        } catch (SQLException e) {
            System.out.println("Handle Service Request Failed!");
            e.printStackTrace();
        }
    }

    public void completeServiceRequest(ServiceRequest serviceRequest) {
        int id = serviceRequest.getId();
        Date date = new Date();
        Timestamp completedTime = new Timestamp(date.getTime());
        String str;
        try {
            str = "update ServiceRequests set completedTime = '"+ completedTime +"' status = 'completed' where id = '" + id ;
            //System.out.println(str);
            dbConnection.executeUpdate(str);
        } catch (SQLException e) {
            System.out.println("Complete Service Request Failed!");
            e.printStackTrace();
        }
    }

    public void removeServiceRequest(ServiceRequest serviceRequest) {
        int id = serviceRequest.getId();
        String str;
        try {
            str = "update ServiceRequests set status = 'removed' where id = '" + id ;
            //System.out.println(str);
            dbConnection.executeUpdate(str);
        } catch (SQLException e) {
            System.out.println("Remove Service Request Failed!");
            e.printStackTrace();
        }
    }



    public void deleteServiceRequest(ServiceRequest serviceRequest){
            int id = serviceRequest.getId();
            String str;
            try {
                str ="delete from ServiceRequests where id = " + id ;
                //System.out.println(str);
                dbConnection.executeUpdate(str);
            } catch (SQLException e) {
                System.out.println("Delete Service Request Failed!");
                e.printStackTrace();
        }
    }

}
