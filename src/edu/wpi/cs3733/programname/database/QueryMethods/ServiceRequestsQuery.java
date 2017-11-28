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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ServiceRequestsQuery {
    private DBConnection dbConnection;
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
            String senderUsername;
            String serviceType;
            String node1ID;
            String node2ID;
            String description;
//            Timestamp requestTime;
//            Timestamp handleTime;
//            Timestamp completionTime;
            String requestTime;
            String handleTime;
            String completionTime;
            String status;
            String receiverUsername;

            while(result.next()) {
                serviceID = result.getInt("serviceID");
                senderUsername = result.getString("sender");
                serviceType = result.getString("serviceType");
                node1ID = result.getString("location1");
                node2ID = result.getString("location2");
                description = result.getString("description");
//                requestTime = result.getTimestamp("requestTime");
//                handleTime = result.getTimestamp("handleTime");
//                completionTime = result.getTimestamp("completionTime");
                requestTime = result.getString("requestTime");
                handleTime = result.getString("handleTime");
                completionTime = result.getString("completionTime");
                status = result.getString("status");
                receiverUsername = result.getString("receiver");
                queryResult = new ServiceRequest(serviceID,senderUsername, receiverUsername, serviceType, node1ID, node2ID, description, requestTime, handleTime, completionTime, status);
                resultList.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Get Service Request Failed!");
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
            String senderUsername;
            String serviceType;
            String node1ID;
            String node2ID;
            String description;
            String requestTime;
            String handleTime;
            String completionTime;
            String receiverUsername;

            while(result.next()) {
                serviceID = result.getInt("serviceID");
                senderUsername = result.getString("sender");
                serviceType = result.getString("serviceType");
                node1ID = result.getString("location1");
                node2ID = result.getString("location2");
                description = result.getString("description");
//                requestTime = result.getTimestamp("requestTime");
//                handleTime = result.getTimestamp("handleTime");
//                completionTime = result.getTimestamp("completionTime");
                requestTime = result.getString("requestTime");
                handleTime = result.getString("handleTime");
                completionTime = result.getString("completionTime");
                //status = result.getString("status");
                receiverUsername = result.getString("receiver");
                queryResult = new ServiceRequest(serviceID,senderUsername, receiverUsername, serviceType, node1ID, node2ID, description, requestTime, handleTime, completionTime, status);
                resultList.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Get Service Request Failed!");
            e.printStackTrace();
        }
        return resultList;
    }

    public ArrayList<ServiceRequest> queryServiceRequestsByType(String serviceserviceType){
        ServiceRequest queryResult = null;
        ArrayList<ServiceRequest> resultList = new ArrayList<ServiceRequest>();
        try {
            String sql = "SELECT * FROM ServiceRequests WHERE serviceType = '" + serviceserviceType + "'";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            int serviceID;
            String senderUsername;
            //String serviceType;
            String node1ID;
            String node2ID;
            String description;
//            Timestamp requestTime;
//            Timestamp handleTime;
//            Timestamp completionTime;
            String requestTime;
            String handleTime;
            String completionTime;
            String status;
            String receiverUsername;

            while(result.next()) {
                serviceID = result.getInt("serviceID");
                senderUsername = result.getString("sender");
                //serviceType = result.getString("serviceType");
                node1ID = result.getString("location1");
                node2ID = result.getString("location2");
                description = result.getString("description");
//                requestTime = result.getTimestamp("requestTime");
//                handleTime = result.getTimestamp("handleTime");
//                completionTime = result.getTimestamp("completionTime");
                requestTime = result.getString("requestTime");
                handleTime = result.getString("handleTime");
                completionTime = result.getString("completionTime");
                status = result.getString("status");
                receiverUsername = result.getString("receiver");
                queryResult = new ServiceRequest(serviceID,senderUsername, receiverUsername, serviceserviceType, node1ID, node2ID, description, requestTime, handleTime, completionTime, status);
                resultList.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Get Service Request Failed!");
            e.printStackTrace();
        }
        return resultList;
    }

    public ServiceRequest queryServiceRequestsByID(int serviceID){
        ServiceRequest queryResult = null;
        try {
            String sql = "SELECT * FROM ServiceRequests WHERE serviceID = " + serviceID;
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            String senderUsername;
            String serviceType;
            String node1ID;
            String node2ID;
            String description;
//            Timestamp requestTime;
//            Timestamp handleTime;
//            Timestamp completionTime;
            String requestTime;
            String handleTime;
            String completionTime;
            String status;
            String receiverUsername;

            while(result.next()) {
                senderUsername = result.getString("sender");
                serviceType = result.getString("serviceType");
                node1ID = result.getString("location1");
                node2ID = result.getString("location2");
                description = result.getString("description");
//                requestTime = result.getTimestamp("requestTime");
//                handleTime = result.getTimestamp("handleTime");
//                completionTime = result.getTimestamp("completionTime");
                requestTime = result.getString("requestTime");
                handleTime = result.getString("handleTime");
                completionTime = result.getString("completionTime");
                status = result.getString("status");
                receiverUsername = result.getString("receiver");
                queryResult = new ServiceRequest(serviceID, senderUsername, receiverUsername, serviceType, node1ID, node2ID, description, requestTime, handleTime, completionTime, status);
            }
        } catch (SQLException e) {
            System.out.println("Select Service Request Failed!");
            e.printStackTrace();
        }
        return queryResult;
    }

    public void addServiceRequest(ServiceRequest serviceRequest){
        int serviceID = serviceRequest.getServiceID();
        String senderUsername = serviceRequest.getSender();
        String serviceType = serviceRequest.getServiceType();
        String node1ID = serviceRequest.getLocation1();
        String node2ID = serviceRequest.getLocation2();
        String description = serviceRequest.getDescription();
//        Timestamp requestTime = serviceRequest.getRequestTime();
//        Timestamp handleTime = serviceRequest.getHandleTime();
//        Timestamp completionTime = serviceRequest.getCompletionTime();
        String requestTime = serviceRequest.getRequestTime();
        String handleTime = serviceRequest.getHandleTime();
        String completionTime = serviceRequest.getCompletionTime();
        String status = serviceRequest.getStatus();
        String receiverUsername = serviceRequest.getReceiver();
        String str;
        try {
            str = "INSERT INTO ServiceRequests values(" + serviceID + ",'" + senderUsername + "', '" + receiverUsername + "','" + serviceType + "', '" + node1ID +  "', '" + node2ID + "', '" + description +
                    "', '" + requestTime + "','" + handleTime + "', '" + completionTime + "','"+ status + "')";
            System.out.println(str);
            dbConnection.executeUpdate(str);
        } catch (SQLException e) {
            System.out.println("Insert Service Request Failed!");
            e.printStackTrace();
        }
    }

    // mark a service request as handled
    public void handleServiceRequest(ServiceRequest serviceRequest, String receiver) {
        int serviceID = serviceRequest.getServiceID();
        Date date = new Date();
        String handleTime;// = new Timestamp(date.getTime());
        handleTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Timestamp(date.getTime()));
        String str;
        try {
            str = "update ServiceRequests set handleTime = '"+ handleTime +"', status = 'handled', receiver = '"+ receiver +"' where serviceID = " + serviceID ;
            System.out.println(str);
            dbConnection.executeUpdate(str);
        } catch (SQLException e) {
            System.out.println("Handle Service Request Failed!");
            e.printStackTrace();
        }
    }

    // mark a service request as completed
    public void completeServiceRequest(ServiceRequest serviceRequest) {
        int serviceID = serviceRequest.getServiceID();
        Date date = new Date();
        String completionTime;// = new Timestamp(date.getTime());
        completionTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Timestamp(date.getTime()));
        String str;
        try {
            str = "update ServiceRequests set completionTime = '"+ completionTime +"', status = 'completed' where serviceID = " + serviceID ;
            System.out.println(str);
            dbConnection.executeUpdate(str);
        } catch (SQLException e) {
            System.out.println("Complete Service Request Failed!");
            e.printStackTrace();
        }
    }

    // mark a service request as removed
    public void removeServiceRequest(ServiceRequest serviceRequest) {
        int serviceID = serviceRequest.getServiceID();
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
            int serviceID = serviceRequest.getServiceID();
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
