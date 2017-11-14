package edu.wpi.cs3733.programname.database;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dbTables {

    public static void createNodesTables(Connection conn) {

        try {
            Statement st = conn.createStatement();

            DatabaseMetaData dbm = conn.getMetaData();
            // check if "Nodes" table is there
            ResultSet tables = dbm.getTables(null, null, "NODES", null);
            if (!tables.next()) {
                String newTable = "CREATE TABLE Nodes(nodeID VARCHAR(21), xcoord INTEGER, ycoord INTEGER, floor INTEGER, building VARCHAR(20), nodeType VARCHAR(4), longName VARCHAR(50), shortName VARCHAR(30), teamAssigned VARCHAR(6))";
                // Creates new nodes table
                st.execute(newTable);
                System.out.println("\nNodes Table Created\n");
                conn.commit();

            } else {

                String dropTable = ("DROP TABLE Nodes");
                // Drops nodes table
                st.execute(dropTable);
                System.out.println("\nNodes Table Dropped");


                String newTable = "CREATE TABLE Nodes(nodeID VARCHAR(21), xcoord INTEGER, ycoord INTEGER, floor VARCHAR(3) , building VARCHAR(20), nodeType VARCHAR(4), longName VARCHAR(50), shortName VARCHAR(30), teamAssigned VARCHAR(6))";
                // Creates new nodes table
                st.execute(newTable);
                System.out.println("Nodes Table Created\n");
                conn.commit();
            }
        } catch (SQLException e) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, e);
        }
    }




    public static void createEdgesTables(Connection conn) {

        try {
            Statement st = conn.createStatement();

            DatabaseMetaData dbm = conn.getMetaData();
            // check if "Nodes" table is there
            ResultSet tables = dbm.getTables(null, null, "EDGES", null);
            if (!tables.next()) {
                String newTable = "CREATE TABLE Edges(edgeID VARCHAR(21), startNode VARCHAR(10), endNode VARCHAR(10))";

                // Creates new nodes table
                st.execute(newTable);
                System.out.println("\nEdges Table Created");
                conn.commit();

            } else {

                String dropTable = ("DROP TABLE Edges");
                // Drops edges table
                st.execute(dropTable);
                System.out.println("\nEdges Table Dropped");


                String newTable = "CREATE TABLE Edges(edgeID VARCHAR(21), startNode VARCHAR(10), endNode VARCHAR(10))";// Creates new nodes table
                // Creates new edges table
                st.execute(newTable);
                System.out.println("Edges Table Created\n");
                conn.commit();

            }

        } catch (SQLException e) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, e);
        }

    }

}