package edu.wpi.cs3733.programname.database;
import java.sql.Connection;

public class TestDB {

    public static void main(String[] args) {
        //Embedded Apache Derby
        DBConnection TestDB = new DBConnection();   // Creates new instance of connection
        TestDB.setDBConnection();                   // Sets up the connection
        Connection conn = TestDB.getConnection();   // Initializes the connection to be passed through other methods


        // MapDnodes.csv
        dbTables.createNodesTables(conn);           // Makes nodes table
        csvReadWrite.csvNodes(conn);                // Reads and Writes out MapDnodes.csv file
        printTables.printNodesTable(conn);          // Pulls data in nodes table from the database and print it


        // MapDedges.csv
        dbTables.createEdgesTables(conn);           // Makes edges table
        csvReadWrite.csvEdges(conn);                // Reads and Writes MapDedges.csv file
        printTables.printEdgesTable(conn);          // Pulls data in nodes table from the database and print it

    }
}
