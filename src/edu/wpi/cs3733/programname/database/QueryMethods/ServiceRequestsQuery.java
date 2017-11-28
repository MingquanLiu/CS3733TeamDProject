package edu.wpi.cs3733.programname.database.QueryMethods;

import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.database.DBConnection;
import edu.wpi.cs3733.programname.database.DatabaseQueryController;
import edu.wpi.cs3733.programname.commondata.Employee;
import edu.wpi.cs3733.programname.commondata.ServiceRequest;

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

    public ArrayList<ServiceRequest> queryAllServiceRequests(){
        ServiceRequest queryResult = null;
        ArrayList<ServiceRequest> resultList = new ArrayList<ServiceRequest>();
        try {
            String sql = "SELECT * FROM ServiceRequests";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            int serviceID;
            Employee sender;
            String senderUsername;
            String type;
            NodeData location1;
            String node1ID;
            NodeData location2;
            String node2ID;
            String description;
            Timestamp createdTime;
            Timestamp handledTime;
            Timestamp completedTime;
            String status;
            Employee receiver;
            String receiverUsername;

            while(result.next()) {
                id = result.getInt("id");
                senderUsername = result.getString("sender");
                // todo: handle null exception
                if(new EmployeesQuery(dbConnection).queryEmployeeByUsername(senderUsername) == null){
                    sender = null;
                }
                else{
                    sender = new EmployeesQuery(dbConnection).queryEmployeeByUsername(senderUsername);
                }
                type = result.getString("type");
                node1ID = result.getString("location1");
                // todo: handle null exception
                if(new DatabaseQueryController(dbConnection).queryNodeById(dbConnection,node1ID) == null){
                    location1 = null;
                }
                else{
                    location1 = new DatabaseQueryController(dbConnection).queryNodeById(dbConnection,node1ID);
                }
                node2ID = result.getString("location2");
                // todo: handle null exception
                if(new DatabaseQueryController(dbConnection).queryNodeById(dbConnection,node2ID) == null){
                    location2 = null;
                }
                else{
                    location2 = new DatabaseQueryController(dbConnection).queryNodeById(dbConnection,node2ID);
                }
                description = result.getString("description");
                createdTime = result.getTimestamp("createdTime");
                handledTime = result.getTimestamp("handledTime");
                completedTime = result.getTimestamp("completedTime");
                status = result.getString("status");
                receiverUsername = result.getString("receiver");
                // todo: handle null exception
                if(new EmployeesQuery(dbConnection).queryEmployeeByUsername(receiverUsername) == null){
                    receiver = null;
                }
                else{
                    receiver = new EmployeesQuery(dbConnection).queryEmployeeByUsername(receiverUsername);
                }
                queryResult = new ServiceRequest(serviceID,sender, type, location1, location2, description, createdTime, handledTime, completedTime, status, receiver);
                resultList.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Select Service Request Failed!");
            e.printStackTrace();
        }
        return resultList;
    }

    public ArrayList<ServiceRequest> queryServiceRequestsByStatus(String status){
        ServiceRequest queryResult = null;
        ArrayList<ServiceRequest> resultList = new ArrayList<ServiceRequest>();
        try {
            String sql = "SELECT * FROM ServiceRequests WHERE status = '" + status + "'";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            int serviceID;
            Employee sender;
            String senderUsername;
            String type;
            NodeData location1;
            String node1ID;
            NodeData location2;
            String node2ID;
            String description;
            Timestamp createdTime;
            Timestamp handledTime;
            Timestamp completedTime;
            Employee receiver;
            String receiverUsername;

            while(result.next()) {
                serviceID = result.getInt("serviceID");
                senderUsername = result.getString("sender");
                // todo: handle null exception
                if(new EmployeesQuery(dbConnection).queryEmployeeByUsername(senderUsername) == null){
                    sender = null;
                }
                else{
                    sender = new EmployeesQuery(dbConnection).queryEmployeeByUsername(senderUsername);
                }
                type = result.getString("type");
                node1ID = result.getString("location1");
                // todo: handle null exception
                if(new DatabaseQueryController(dbConnection).queryNodeById(dbConnection,node1ID) == null){
                    location1 = null;
                }
                else{
                    location1 = new DatabaseQueryController(dbConnection).queryNodeById(dbConnection, node1ID);
                }
                node2ID = result.getString("location2");
                // todo: handle null exception
                if(new DatabaseQueryController(dbConnection).queryNodeById(dbConnection,node2ID) == null){
                    location2 = null;
                }
                else{
                    location2 = new DatabaseQueryController(dbConnection).queryNodeById(dbConnection, node2ID);
                }
                description = result.getString("description");
                createdTime = result.getTimestamp("createdTime");
                handledTime = result.getTimestamp("handledTime");
                completedTime = result.getTimestamp("completedTime");
                //status = result.getString("status");
                receiverUsername = result.getString("receiver");
                // todo: handle null exception
                if(new EmployeesQuery(dbConnection).queryEmployeeByUsername(receiverUsername) == null){
                    receiver = null;
                }
                else{
                    receiver = new EmployeesQuery(dbConnection).queryEmployeeByUsername(receiverUsername);
                }
                queryResult = new ServiceRequest(serviceID,sender, type, location1, location2, description, createdTime, handledTime, completedTime, status, receiver);
                resultList.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Select Service Request Failed!");
            e.printStackTrace();
        }
        return resultList;
    }

    public ArrayList<ServiceRequest> queryServiceRequestsByType(String serviceType){
        ServiceRequest queryResult = null;
        ArrayList<ServiceRequest> resultList = new ArrayList<ServiceRequest>();
        try {
            String sql = "SELECT * FROM ServiceRequests WHERE type = '" + serviceType + "'";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            int serviceID;
            Employee sender;
            String senderUsername;
            //String type;
            NodeData location1;
            String node1ID="";
            NodeData location2;
            String node2ID;
            String description;
            Timestamp createdTime;
            Timestamp handledTime;
            Timestamp completedTime;
            String status;
            Employee receiver;
            String receiverUsername;

            while(result.next()) {
                serviceID = result.getInt("serviceID");
                senderUsername = result.getString("sender");
                // todo: handle null exception
                if(new EmployeesQuery(dbConnection).queryEmployeeByUsername(senderUsername) == null){
                    sender = null;
                }
                else{
                    sender = new EmployeesQuery(dbConnection).queryEmployeeByUsername(senderUsername);
                }
                //type = result.getString("type");
                if(new DatabaseQueryController(dbConnection).queryNodeById(dbConnection,node1ID) == null){
                    location1 = null;
                }
                else{
                    location1 = new DatabaseQueryController(dbConnection).queryNodeById(dbConnection,node1ID);
                }
                node2ID = result.getString("location2");
                // todo: handle null exception
                if(new DatabaseQueryController(dbConnection).queryNodeById(dbConnection,node2ID) == null){
                    location2 = null;
                }
                else{
                    location2 = new DatabaseQueryController(dbConnection).queryNodeById(dbConnection,node2ID);
                }
                description = result.getString("description");
                createdTime = result.getTimestamp("createdTime");
                handledTime = result.getTimestamp("handledTime");
                completedTime = result.getTimestamp("completedTime");
                status = result.getString("status");
                receiverUsername = result.getString("receiver");
                // todo: handle null exception
                if(new EmployeesQuery(dbConnection).queryEmployeeByUsername(receiverUsername) == null){
                    receiver = null;
                }
                else{
                    receiver = new EmployeesQuery(dbConnection).queryEmployeeByUsername(receiverUsername);
                }
                queryResult = new ServiceRequest(serviceID,sender, serviceType, location1, location2, description, createdTime, handledTime, completedTime, status, receiver);
                resultList.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Select Service Request Failed!");
            e.printStackTrace();
        }
        return resultList;
    }

    public ArrayList<ServiceRequest> queryServiceRequestsByID(int serviceID){
        ServiceRequest queryResult = null;
        ArrayList<ServiceRequest> resultList = new ArrayList<ServiceRequest>();
        try {
            String sql = "SELECT * FROM ServiceRequests WHERE serviceID = " + serviceID;
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            Employee sender;
            String senderUsername;
            String type;
            NodeData location1;
            String node1ID;
            NodeData location2;
            String node2ID;
            String description;
            Timestamp createdTime;
            Timestamp handledTime;
            Timestamp completedTime;
            String status;
            Employee receiver;
            String receiverUsername;

            while(result.next()) {
                senderUsername = result.getString("sender");
                // todo: handle null exception
                if(new EmployeesQuery(dbConnection).queryEmployeeByUsername(senderUsername) == null){
                    sender = null;
                }
                else{
                    sender = new EmployeesQuery(dbConnection).queryEmployeeByUsername(senderUsername);
                }
                type = result.getString("type");
                node1ID = result.getString("location1");
                // todo: handle null exception
                if(new DatabaseQueryController(dbConnection).queryNodeById(dbConnection,node1ID) == null){
                    location1 = null;
                }
                else{
                    location1 = new DatabaseQueryController(dbConnection).queryNodeById(dbConnection,node1ID);
                }
                node2ID = result.getString("location2");
                // todo: handle null exception
                if(new DatabaseQueryController(dbConnection).queryNodeById(dbConnection,node2ID) == null){
                    location2 = null;
                }
                else{
                    location2 = new DatabaseQueryController(dbConnection).queryNodeById(dbConnection,node2ID);
                }
                description = result.getString("description");
                createdTime = result.getTimestamp("createdTime");
                handledTime = result.getTimestamp("handledTime");
                completedTime = result.getTimestamp("completedTime");
                //status = result.getString("status");
                status = result.getString("status");
                receiverUsername = result.getString("receiver");
                // todo: handle null exception
                if(new EmployeesQuery(dbConnection).queryEmployeeByUsername(receiverUsername) == null){
                    receiver = null;
                }
                else{
                    receiver = new EmployeesQuery(dbConnection).queryEmployeeByUsername(receiverUsername);
                }
                queryResult = new ServiceRequest(serviceID, sender, type, location1, location2, description, createdTime, handledTime, completedTime, status, receiver);
                resultList.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Select Service Request Failed!");
            e.printStackTrace();
        }
        return resultList;
    }

    public void addServiceRequest(ServiceRequest serviceRequest){
        int serviceID = serviceRequest.getserviceID();
        Employee sender = serviceRequest.getsender();
        String senderUsername = sender.getUsername();
        String type = serviceRequest.getType();
        NodeData location1 = serviceRequest.getLocation1();
        String node1ID = location1.getNodeID();
        NodeData location2 = serviceRequest.getLocation2();
        String node2ID = location2.getNodeID();
        String description = serviceRequest.getDescription();
        Timestamp createdTime = serviceRequest.getCreatedTime();
        Timestamp handledTime = serviceRequest.getHandledTime();
        Timestamp completedTime = serviceRequest.getCompletedTime();
        String status = serviceRequest.getStatus();
        String receiverUsername;
        // todo: handle null
        if(serviceRequest.getreceiver() == null){
            receiverUsername = "unknown";
        }
        else{
            Employee receiver = serviceRequest.getreceiver();
            receiverUsername = receiver.getUsername();
        }
        String str;
        try {
            str = "insert into ServiceRequests values(" + serviceID + ",'" + senderUsername + "', '" + type + "', '" + node1ID +  "', '" + node2ID + "', '" + description +
                    "', '" + createdTime + "','" + handledTime + "', '" + completedTime + "','"+ status + "','"+ receiverUsername + "')";
            System.out.println(str);
            dbConnection.executeUpdate(str);
        } catch (SQLException e) {
            System.out.println("Insert Service Request Failed!");
            e.printStackTrace();
        }
    }

    // mark a service request as handled
    public void handleServiceRequest(ServiceRequest serviceRequest, Employee receiver) {
        int serviceID = serviceRequest.getserviceID();
        String receiverUsername = receiver.getUsername();
        Date date = new Date();
        Timestamp handledTime = new Timestamp(date.getTime());
        String str;
        try {
            str = "update ServiceRequests set handledTime = '"+ handledTime +"' status = 'handled', receiver = '"+ receiverUsername +"' where serviceID = " + serviceID ;
            System.out.println(str);
            dbConnection.executeUpdate(str);
        } catch (SQLException e) {
            System.out.println("Handle Service Request Failed!");
            e.printStackTrace();
        }
    }

    // mark a service request as completed
    public void completeServiceRequest(ServiceRequest serviceRequest) {
        int serviceID = serviceRequest.getserviceID();
        Date date = new Date();
        Timestamp completedTime = new Timestamp(date.getTime());
        String str;
        try {
            str = "update ServiceRequests set completedTime = '"+ completedTime +"' status = 'completed' where serviceID = " + serviceID ;
            System.out.println(str);
            dbConnection.executeUpdate(str);
        } catch (SQLException e) {
            System.out.println("Complete Service Request Failed!");
            e.printStackTrace();
        }
    }

    // mark a service request as removed
    public void removeServiceRequest(ServiceRequest serviceRequest) {
        int serviceID = serviceRequest.getserviceID();
        String str;
        try {
            str = "update ServiceRequests set status = 'removed' where serviceID = " + serviceID ;
            System.out.println(str);
            dbConnection.executeUpdate(str);
        } catch (SQLException e) {
            System.out.println("Remove Service Request Failed!");
            e.printStackTrace();
        }
    }


    // delete a service request from database
    public void deleteServiceRequest(ServiceRequest serviceRequest){
            int serviceID = serviceRequest.getserviceID();
            String str;
            try {
                str ="delete from ServiceRequests where serviceID = " + serviceID ;
                System.out.println(str);
                dbConnection.executeUpdate(str);
            } catch (SQLException e) {
                System.out.println("Delete Service Request Failed!");
                e.printStackTrace();
        }
    }

}
