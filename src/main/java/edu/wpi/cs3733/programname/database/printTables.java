package edu.wpi.cs3733.programname.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class printTables{

    public static void printNodesTable(Connection conn) {
        try {
            Statement statement = conn.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * FROM NODES");
            int count = 0;

            // Initialize table fields
            String nodeID = "";
            int xcoord = 0;
            int ycoord = 0;
            String floor = "";
            String building = "";
            String nodeType = "";
            String longName = "";
            String shortName = "";
            String teamAssigned = "";

            System.out.printf("%-15s %-10s %-10s %-10s %-15s %-15s %-55s %-30s %-20s\n", "nodeID","xcoord","ycoord","floor","building","nodeType","longName","shortName","teamAssigned");

            // Gets all data in the table
            while (rset.next()) {

                nodeID = rset.getString("nodeID");
                xcoord = rset.getInt("xcoord");
                ycoord = rset.getInt("ycoord");
                floor = rset.getString("floor");
                building = rset.getString("building");
                nodeType = rset.getString("nodeType");
                longName = rset.getString("longName");
                shortName = rset.getString("shortName");
                teamAssigned = rset.getString("teamAssigned");


                System.out.printf("%-15s %-10s %-10s %-10s %-15s %-15s %-55s %-30s %-20s\n", nodeID, xcoord, ycoord, floor, building, nodeType, longName, shortName, teamAssigned);
                count++;
            }
            // Confirms the number of updated rows
            System.out.println("\nInserted Into Nodes Table Successfully! Rows Effected: " + count + "\n\n");
        }
        catch (SQLException e) {
        }
    }

    public static void printEdgesTable(Connection conn) {
        try {
            Statement statement = conn.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * FROM EDGES");
            int count = 0;
            // Initialize table fields
            String edgeID = "";
            String startNode = "";
            String endNode = "";



            System.out.printf("%-25s %-15s %-15s\n", "edgeID", "startNode", "endNode");

            // Gets all data in the table
            while (rset.next()) {
                edgeID = rset.getString("edgeID");
                startNode = rset.getString("startNode");
                endNode = rset.getString("endNode");

                System.out.printf("%-25s %-15s %-15s\n", edgeID, startNode, endNode);

                count++;
            }

            // Confirms the number of updated rows
            System.out.println("\nInserted Into Edges Table Successfully! Rows Effected: " + count + "\n\n");

        }
        catch (SQLException e) {
        }
    }

    public static void printServiceRequestsTable(Connection conn) {
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM ServiceRequests");
            int count = 0;

            // Initialize table fields
            String nodeID = "";
            int xcoord = 0;
            int ycoord = 0;
            String floor = "";
            String building = "";
            String nodeType = "";
            String longName = "";
            String shortName = "";
            String teamAssigned = "";

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

            System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n", "serviceID","senderUsername","receiverUsername","serviceType","node1ID","node2ID","description","requestTime","handleTime","completionTime","status");

            // Gets all data in the table
            while (result.next()) {

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


                System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n", serviceID,senderUsername,receiverUsername,serviceType,node1ID,node2ID,description,requestTime,handleTime,completionTime,status);
                count++;
            }
            // Confirms the number of updated rows
            System.out.println("\nInserted Into ServiceRequest Table Successfully! Rows Effected: " + count + "\n\n");
        }
        catch (SQLException e) {
        }
    }
}
