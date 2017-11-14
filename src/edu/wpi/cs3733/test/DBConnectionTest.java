package edu.wpi.cs3733.test;

import edu.wpi.cs3733.programname.database.DBConnection;
import edu.wpi.cs3733.programname.database.DBTables;
;
import edu.wpi.cs3733.programname.database.CsvReadWrite;
import edu.wpi.cs3733.programname.database.printTables;
import org.junit.Test;

import java.sql.Connection;

public class DBConnectionTest {

    @Test
    public void testDbConnection() {
        DBConnection TestDB = new DBConnection();   // Creates new instance of connection
        TestDB.setDBConnection();                   // Sets up the connection
        Connection conn = TestDB.getConnection();   // Initializes the connection to be passed through other methods
        DBTables mDbTable = new DBTables();
        CsvReadWrite mCsvReadWrite = new CsvReadWrite();

        // MapDnodes.csv
        mDbTable.createNodesTables(conn);           // Makes nodes table
        mCsvReadWrite.csvNodes(conn);                // Reads and Writes out MapDnodes.csv file
        printTables.printNodesTable(conn);          // Pulls data in nodes table from the database and print it
    }
}
