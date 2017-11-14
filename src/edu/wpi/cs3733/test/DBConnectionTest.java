package edu.wpi.cs3733.test;
import edu.wpi.cs3733.programname.database.DBConnection;
import edu.wpi.cs3733.programname.database.DBTables;
import edu.wpi.cs3733.programname.database.CsvReadWrite;
import edu.wpi.cs3733.programname.database.printTables;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.database.*;

import org.junit.Test;
import java.sql.Connection;
import static junit.framework.TestCase.assertEquals;

public class DBConnectionTest {
    @Test
    public void testDbConnection() {
        DBConnection TestDB = new DBConnection();   // Creates new instance of connection
        TestDB.setDBConnection();                   // Sets up the connection
        Connection conn = TestDB.getConnection();   // Initializes the connection to be passed through other methods
    }

    @Test
    public void testQueryDatabase() {
        DBConnection TestDB = new DBConnection();   // Creates new instance of connection
        TestDB.setDBConnection();                   // Sets up the connection
        DBTables mDbTable = new DBTables();
        CsvReadWrite mCsvReadWrite = new CsvReadWrite();
        Connection conn = TestDB.getConnection();   // Initializes the connection to be passed through other methods
        // MapDnodes.csv
        mDbTable.createNodesTables(conn);           // Makes nodes table
        mCsvReadWrite.csvNodes(conn);                // Reads and Writes out MapDnodes.csv file
        printTables.printNodesTable(conn);          // Pulls data in nodes table from the database and print it


        ManageController manager = new ManageController();
        // DREST00102,4125,625,2,15 Francis,REST,Restroom B elevator Floor 2,Restroom B,Team D

        NodeData expected = new NodeData("DREST00102", new Coordinate(4125,625), "2",
                "REST", "Restroom B elevator Floor 2", "Restroom B");
        assertEquals(expected, manager.getNodeData("DREST00102"));


    }
}
