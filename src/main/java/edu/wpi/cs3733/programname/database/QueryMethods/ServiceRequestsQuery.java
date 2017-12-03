package edu.wpi.cs3733.programname.database.QueryMethods;

import edu.wpi.cs3733.programname.database.DBConnection;
import edu.wpi.cs3733.programname.commondata.ServiceRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServiceRequestsQuery {
    private DBConnection dbConnection;
    public ServiceRequestsQuery(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public ArrayList<ServiceRequest> queryAllServiceRequests(){
        ServiceRequest queryResult;
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
            String requestTime;
            String handleTime;
            String completionTime;
            String status;
            String receiverUsername;
            int severity;

            while(result.next()) {
                serviceID = result.getInt("serviceID");
                senderUsername = result.getString("sender");
                serviceType = result.getString("serviceType");
                node1ID = result.getString("location1");
                node2ID = result.getString("location2");
                description = result.getString("description");
                requestTime = result.getString("requestTime");
                handleTime = result.getString("handleTime");
                completionTime = result.getString("completionTime");
                status = result.getString("status");
                receiverUsername = result.getString("receiver");
                severity = result.getInt("severity");
                queryResult = new ServiceRequest(serviceID,senderUsername, receiverUsername, serviceType, node1ID, node2ID, description, requestTime, handleTime, completionTime, status, severity);
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
            int severity;

            while(result.next()) {
                serviceID = result.getInt("serviceID");
                senderUsername = result.getString("sender");
                serviceType = result.getString("serviceType");
                node1ID = result.getString("location1");
                node2ID = result.getString("location2");
                description = result.getString("description");
                requestTime = result.getString("requestTime");
                handleTime = result.getString("handleTime");
                completionTime = result.getString("completionTime");
                receiverUsername = result.getString("receiver");
                severity = result.getInt("severity");
                queryResult = new ServiceRequest(serviceID,senderUsername, receiverUsername, serviceType, node1ID, node2ID, description, requestTime, handleTime, completionTime, status, severity);
                resultList.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Get Service Request Failed!");
            e.printStackTrace();
        }
        return resultList;
    }

    public ArrayList<ServiceRequest> queryServiceRequestsByType(String serviceType){
        ServiceRequest queryResult = null;
        ArrayList<ServiceRequest> resultList = new ArrayList<ServiceRequest>();
        try {
            String sql = "SELECT * FROM ServiceRequests WHERE serviceType = '" + serviceType + "'";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            int serviceID;
            String senderUsername;
            //String serviceType;
            String node1ID;
            String node2ID;
            String description;
            String requestTime;
            String handleTime;
            String completionTime;
            String reservationTime;
            String status;
            String receiverUsername;
            int severity;

            while(result.next()) {
                serviceID = result.getInt("serviceID");
                senderUsername = result.getString("sender");
                node1ID = result.getString("location1");

                node2ID = result.getString("location2");
                description = result.getString("description");
                requestTime = result.getString("requestTime");
                handleTime = result.getString("handleTime");
                completionTime = result.getString("completionTime");
                status = result.getString("status");
                receiverUsername = result.getString("receiver");
                severity = result.getInt("severity");
                queryResult = new ServiceRequest(serviceID,senderUsername, receiverUsername, serviceType, node1ID, node2ID, description, requestTime, handleTime, completionTime, status, severity);
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
            String requestTime;
            String handleTime;
            String completionTime;
            String status;
            String receiverUsername;
            int severity;

            while(result.next()) {
                senderUsername = result.getString("sender");
                serviceType = result.getString("serviceType");
                node1ID = result.getString("location1");
                node2ID = result.getString("location2");
                description = result.getString("description");
                requestTime = result.getString("requestTime");
                handleTime = result.getString("handleTime");
                completionTime = result.getString("completionTime");
                status = result.getString("status");
                receiverUsername = result.getString("receiver");
                severity = result.getInt("severity");
                queryResult = new ServiceRequest(serviceID, senderUsername, receiverUsername, serviceType, node1ID, node2ID, description, requestTime, handleTime, completionTime, status, severity);
            }
        } catch (SQLException e) {
            System.out.println("Select Service Request Failed!");
            e.printStackTrace();
        }
        return queryResult;
    }


}
