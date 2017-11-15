package edu.wpi.cs3733.programname.database;
import java.sql.Connection;

public class TestDB {

    public static void main(String[] args) {
        //Embedded Apache Derby
        DBConnection conn = new DBConnection();   // Creates new instance of connection

        DBTables mDbTable = new DBTables();
        CsvReader mCsvReader = new CsvReader();
        CsvWriter mCsvWriter = new CsvWriter();

        // MapDnodes.csv
        mDbTable.createNodesTables(conn);           // Makes nodes table
        mCsvReader.insertNodes(conn.getConnection(), mCsvReader.readNodes(conn.getConnection()));        // Reads and Writes out MapDnodes.csv file
        mCsvWriter.writeNodes(conn.getConnection(), mCsvReader.readNodes(conn.getConnection()));
        printTables.printNodesTable(conn.getConnection());          // Pulls data in nodes table from the database and print it


        // MapDedges.csv
        mDbTable.createEdgesTables(conn);           // Makes edges table
        mCsvReader.insertEdges(conn.getConnection(), mCsvReader.readEdges(conn.getConnection()));                // Reads and Writes MapDedges.csv file
        mCsvWriter.writeEdges(conn.getConnection(), mCsvReader.readEdges(conn.getConnection()));
        printTables.printEdgesTable(conn.getConnection());          // Pulls data in nodes table from the database and print it
    }
}
