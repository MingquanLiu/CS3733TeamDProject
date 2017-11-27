package edu.wpi.cs3733.programname.database.QueryMethods;

import edu.wpi.cs3733.programname.commondata.ServiceRequestInfo;
import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceRequestQuery {


    public static List<ServiceRequestInfo> getAllServiceRequest(DBConnection dbConnection) {

        ServiceRequestInfo queryResult = null;
        List<ServiceRequestInfo> allServiceRequests = new ArrayList<ServiceRequestInfo>();

        try {
            String sql = "SELECT * FROM ServiceRequest";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);

            Integer serviceID = 0;
            String sender = "";          // Employee userName
            String receiver = "";        // Employee userName
            String node1 = "";           // Node nodeID
            String node2 = "";           // Node nodeID
            String serviceType = "";
            String status = "";
            String description = "";
            String requestTime = "";
            String handleTime = "";
            String completionTime = "";

            while(result.next()) {
                serviceID = result.getInt("serviceID");
                sender = result.getString("sender");
                receiver = result.getString("receiver");
                node1= result.getString("node1");
                node2 = result.getString("node2");
                serviceType = result.getString("serviceType");
                status = result.getString("status");
                description = result.getString("description");
                requestTime = result.getString("requestTime");
                handleTime = result.getString("handleTime");
                completionTime = result.getString("completionTime");

                queryResult = new ServiceRequestInfo(serviceID, sender, receiver, node1, node2, serviceType, status, description, requestTime, handleTime, completionTime);
                allServiceRequests.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Insert Node Failed!");
            e.printStackTrace();
        }
        return allServiceRequests;

    }


    public static List<ServiceRequestInfo> queryByStatus(DBConnection dbConnection, String currentStatus) {

        ServiceRequestInfo queryResult = null;
        List<ServiceRequestInfo> allServiceRequests = new ArrayList<ServiceRequestInfo>();

        try {
            String sql = "SELECT * FROM ServiceRequest WHERE status = " + "'" + currentStatus + "'";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);

            Integer serviceID = 0;
            String sender = "";          // Employee userName
            String receiver = "";        // Employee userName
            String node1 = "";           // Node nodeID
            String node2 = "";           // Node nodeID
            String serviceType = "";
            String status = "";
            String description = "";
            String requestTime = "";
            String handleTime = "";
            String completionTime = "";

            while(result.next()) {
                serviceID = result.getInt("serviceID");
                sender = result.getString("sender");
                receiver = result.getString("receiver");
                node1= result.getString("node1");
                node2 = result.getString("node2");
                serviceType = result.getString("serviceType");
                status = result.getString("status");
                description = result.getString("description");
                requestTime = result.getString("requestTime");
                handleTime = result.getString("handleTime");
                completionTime = result.getString("completionTime");

                queryResult = new ServiceRequestInfo(serviceID, sender, receiver, node1, node2, serviceType, status, description, requestTime, handleTime, completionTime);
                allServiceRequests.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Insert Node Failed!");
            e.printStackTrace();
        }
        return allServiceRequests;

    }


    public static List<ServiceRequestInfo> queryByType(DBConnection dbConnection, String type) {

        ServiceRequestInfo queryResult = null;
        List<ServiceRequestInfo> allServiceRequests = new ArrayList<ServiceRequestInfo>();

        try {
            String sql = "SELECT * FROM ServiceRequest WHERE serviceType = " + "'" + type + "'";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);

            Integer serviceID = 0;
            String sender = "";          // Employee userName
            String receiver = "";        // Employee userName
            String node1 = "";           // Node nodeID
            String node2 = "";           // Node nodeID
            String serviceType = "";
            String status = "";
            String description = "";
            String requestTime = "";
            String handleTime = "";
            String completionTime = "";

            while(result.next()) {
                serviceID = result.getInt("serviceID");
                sender = result.getString("sender");
                receiver = result.getString("receiver");
                node1= result.getString("node1");
                node2 = result.getString("node2");
                serviceType = result.getString("serviceType");
                status = result.getString("status");
                description = result.getString("description");
                requestTime = result.getString("requestTime");
                handleTime = result.getString("handleTime");
                completionTime = result.getString("completionTime");

                queryResult = new ServiceRequestInfo(serviceID, sender, receiver, node1, node2, serviceType, status, description, requestTime, handleTime, completionTime);
                allServiceRequests.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Insert Node Failed!");
            e.printStackTrace();
        }
        return allServiceRequests;

    }

    public static List<ServiceRequestInfo> queryByID(DBConnection dbConnection, String ID) {

        ServiceRequestInfo queryResult = null;
        List<ServiceRequestInfo> allServiceRequests = new ArrayList<ServiceRequestInfo>();

        try {
            String sql = "SELECT * FROM ServiceRequest WHERE serviceID = " + "'" + ID + "'";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);

            Integer serviceID = 0;
            String sender = "";          // Employee userName
            String receiver = "";        // Employee userName
            String node1 = "";           // Node nodeID
            String node2 = "";           // Node nodeID
            String serviceType = "";
            String status = "";
            String description = "";
            String requestTime = "";
            String handleTime = "";
            String completionTime = "";

            while(result.next()) {
                serviceID = result.getInt("serviceID");
                sender = result.getString("sender");
                receiver = result.getString("receiver");
                node1= result.getString("node1");
                node2 = result.getString("node2");
                serviceType = result.getString("serviceType");
                status = result.getString("status");
                description = result.getString("description");
                requestTime = result.getString("requestTime");
                handleTime = result.getString("handleTime");
                completionTime = result.getString("completionTime");

                queryResult = new ServiceRequestInfo(serviceID, sender, receiver, node1, node2, serviceType, status, description, requestTime, handleTime, completionTime);
                allServiceRequests.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Insert Node Failed!");
            e.printStackTrace();
        }
        return allServiceRequests;

    }


}
