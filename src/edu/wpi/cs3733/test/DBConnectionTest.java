package edu.wpi.cs3733.test;
import edu.wpi.cs3733.programname.database.DBConnection;
import edu.wpi.cs3733.programname.database.DBTables;
import edu.wpi.cs3733.programname.database.printTables;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.database.*;

import org.junit.Test;
import java.sql.Connection;
import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;

public class DBConnectionTest {


    @Test
    public void testDbConnection() {
        DBConnection conn = new DBConnection();   // Creates new instance of connection
    }


    @Test
    public void testNodeQueryDatabase() {
        DBConnection TestDB = new DBConnection();   // Creates new instance of connection
        TestDB.setDBConnection();                   // Sets up the connection
        DBTables mDbTable = new DBTables();
        CsvReader mCsvReader = new CsvReader();
        CsvWriter mCsvWriter = new CsvWriter();
        Connection conn = TestDB.getConnection();   // Initializes the connection to be passed through other methods

        // MapDnodes.csv

        mDbTable.dropAllTables(TestDB);           // Makes nodes table
        mCsvReader.insertNodes(conn,mCsvReader.readNodes(conn));
        mCsvWriter.writeNodes(conn, mCsvReader.readNodes(conn));
        printTables.printNodesTable(conn);          // Pulls data in nodes table from the database and print it


        ManageController manager = new ManageController();


        NodeData expected = new NodeData("DREST00102", new Coordinate(4125,625), "2", "15 Francis",
                "REST", "Restroom B elevator Floor 2", "Restroom B", "Team D");
        assertEquals(expected, manager.getNodeData("DREST00102"));


        NodeData expected2 = new NodeData("DDEPT00302",	3900, 640,	"2", "15 Francis",
                "DEPT",	"Brigham Health Floor 2", "Brigham Health", "Team D");
        assertEquals(expected2, manager.getNodeData("DDEPT00302"));


    }


//    @Test
//    public void testEdgeQueryDatabase() {
//        DBConnection TestDB = new DBConnection();   // Creates new instance of connection
//        TestDB.setDBConnection();                   // Sets up the connection
//        DBTables mDbTable = new DBTables();
//        CsvReader mCsvReader = new CsvReader();
//        CsvWriter mCsvWriter = new CsvWriter();
//        Connection conn = TestDB.getConnection();   // Initializes the connection to be passed through other methods
//
//        // MapDedges.csv
//        mDbTable.createEdgesTables(TestDB);           // Makes nodes table
//        mCsvReader.insertEdges(conn,mCsvReader.readEdges(conn));
//        mCsvWriter.writeEdges(conn, mCsvReader.readEdges(conn));
//        printTables.printEdgesTable(conn);          // Pulls data in nodes table from the database and print it
//
////        ManageController manager = new ManageController();
////
////        Edge expected = new Edge("DHALL00702", "DHALL00802", "DHALL00702_DHALL00802");
////        assertEquals(expected, manager.getEdgeData("DHALL00702_DHALL00802"));
//
//    }

//    @Test
//    public void testStaffQueryDatabase() {
//        DBConnection TestDB = new DBConnection();   // Creates new instance of connection
//        TestDB.setDBConnection();                   // Sets up the connection
//        DBTables mDbTable = new DBTables();
//
//
//        // MapDedges.csv
//        mDbTable.createStaffTables(TestDB);           // Makes nodes table
//
//
//
//    }
//
//    @Test
//    public void testPhoneExtensionsQueryDatabase() {
//        DBConnection TestDB = new DBConnection();   // Creates new instance of connection
//        TestDB.setDBConnection();                   // Sets up the connection
//        DBTables mDbTable = new DBTables();
//        Connection conn = TestDB.getConnection();   // Initializes the connection to be passed through other methods
//
//        // MapDedges.csv
//        mDbTable.createPhoneExtensionsTables(TestDB);           // Makes nodes table
//
//
//
//    }
//
//    @Test
//    public void testTitlesQueryDatabase() {
//        DBConnection TestDB = new DBConnection();   // Creates new instance of connection
//        TestDB.setDBConnection();                   // Sets up the connection
//        DBTables mDbTable = new DBTables();
//        CsvReader mCsvReader = new CsvReader();
//        CsvWriter mCsvWriter = new CsvWriter();
//        Connection conn = TestDB.getConnection();   // Initializes the connection to be passed through other methods
//
//        // MapDedges.csv
//        mDbTable.createTitlesTables(TestDB);           // Makes nodes table
//
//
//
//    }
//
//    @Test
//    public void testStaffTitlesQueryDatabase() {
//        DBConnection TestDB = new DBConnection();   // Creates new instance of connection
//        TestDB.setDBConnection();                   // Sets up the connection
//        DBTables mDbTable = new DBTables();
//        CsvReader mCsvReader = new CsvReader();
//        CsvWriter mCsvWriter = new CsvWriter();
//        Connection conn = TestDB.getConnection();   // Initializes the connection to be passed through other methods
//
//        // MapDedges.csv
//        mDbTable.createStaffTitlesTables(TestDB);           // Makes nodes table
//
//
//
//    }

    @Test
    public void dropAllTest() throws SQLException{
        DBConnection TestDB = new DBConnection();   // Creates new instance of connection
        TestDB.setDBConnection();                   // Sets up the connection
        DBTables mDbTable = new DBTables();
        CsvReader mCsvReader = new CsvReader();
        CsvWriter mCsvWriter = new CsvWriter();
        Connection conn = TestDB.getConnection();

        mDbTable.dropAllTables(TestDB);
    }
}
