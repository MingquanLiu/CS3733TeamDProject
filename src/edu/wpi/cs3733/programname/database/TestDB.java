package edu.wpi.cs3733.programname.database;
import java.sql.Connection;

public class TestDB {

    public static void main(String[] args) {
        //Embedded Apache Derby
        DBConnection conn = new DBConnection();   // Creates new instance of connection


        // MapDnodes.csv
        dbTables.createNodesTables(conn);           // Makes nodes table
        csvReadWrite.csvNodes(conn.getConnection());                // Reads and Writes out MapDnodes.csv file
        printTables.printNodesTable(conn.getConnection());          // Pulls data in nodes table from the database and print it


        // MapDedges.csv
        dbTables.createEdgesTables(conn);           // Makes edges table
        csvReadWrite.csvEdges(conn.getConnection());                // Reads and Writes MapDedges.csv file
        printTables.printEdgesTable(conn.getConnection());          // Pulls data in nodes table from the database and print it

    }
}
