package edu.wpi.cs3733.test;

import edu.wpi.cs3733.programname.database.DBConnection;
import edu.wpi.cs3733.programname.database.dbTables;
;
import edu.wpi.cs3733.programname.database.csvReadWrite;
import edu.wpi.cs3733.programname.database.printTables;
import org.junit.Test;

import java.sql.Connection;

public class DBConnectionTest {

    @Test
    public void testDbConnection() {
        DBConnection conn = new DBConnection();   // Creates new instance of connection


        // MapDnodes.csv
        dbTables.createNodesTables(conn);           // Makes nodes table
        csvReadWrite.csvNodes(conn.getConnection());                // Reads and Writes out MapDnodes.csv file
        printTables.printNodesTable(conn.getConnection());          // Pulls data in nodes table from the database and print it
    }
}
