package edu.wpi.cs3733.programname.database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class printTables {



    public static void printNodesTable(Connection conn) {
        try {
            Statement statement = conn.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * FROM NODES");
            int count = 0;

            // Initialize table fields
            String nodeID = "";
            int xcoord = 0;
            int ycoord = 0;
            int floor = 0;
            String building = "";
            String nodeType = "";
            String longName = "";
            String shortName = "";
            String teamAssigned = "";

            System.out.printf("%-15s %-10s %-10s %-10s %-15s %-15s %-50s %-30s %-20s\n", "nodeID","xcoord","ycoord","floor","building","nodeType","longName","shortName","teamAssigned");

            // Gets all data in the table
            while (rset.next()) {

                nodeID = rset.getString("nodeID");
                xcoord = rset.getInt("xcoord");
                ycoord = rset.getInt("ycoord");
                floor = rset.getInt("floor");
                building = rset.getString("building");
                nodeType = rset.getString("nodeType");
                longName = rset.getString("longName");
                shortName = rset.getString("shortName");
                teamAssigned = rset.getString("teamAssigned");


                System.out.printf("%-15s %-10s %-10s %-10s %-15s %-15s %-50s %-30s %-20s\n", nodeID, xcoord, ycoord, floor, building, nodeType, longName, shortName, teamAssigned);
                count++;
            }
            // Confirms the number of updated rows
            System.out.println("\nInserted Into Nodes Table Successfully! Rows Effected: " + count + "\n\n");
        }
        catch (SQLException e) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, e);
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
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, e);
        }

    }





}
