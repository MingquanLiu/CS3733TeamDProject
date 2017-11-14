package edu.wpi.cs3733.test;


import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.database.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;

public class DBModConTest {

    Coordinate aBathroomCoord = new Coordinate(4125, 625);
    Coordinate replacedBathroomCoord = new Coordinate(5124, 625);
    NodeData aBathroom = new NodeData ("TREST00102", aBathroomCoord, "REST","Restroom B elevator Floor 2", "Restroom B");
    NodeData newBathroom = new NodeData ("TREST00102", replacedBathroomCoord, "REST","Restroom B elevator Floor 2", "Restroom B");
    DatabaseModificationController theDBModControl = new DatabaseModificationController();

    @Before
    public void setupDbTables() {
        DBConnection TestDB = new DBConnection();   // Creates new instance of connection
        TestDB.setDBConnection();                   // Sets up the connection
        Connection conn = TestDB.getConnection();   // Initializes the connection to be passed through other methods

        // MapDnodes.csv
        dbTables.createNodesTables(conn);           // Makes nodes table
        csvReadWrite.csvNodes(conn);                // Reads and Writes out MapDnodes.csv file
        printTables.printNodesTable(conn);          // Pulls data in nodes table from the database and print it
    }


    @Test
    public void checkAddNode(){
        assertEquals("insert into Nodes values('TREST00102', 4125, 625, '2', '15 Francis', 'REST', 'Restroom B elevator Floor 2', 'Restroom B', 'Team D')", theDBModControl.addNode(aBathroom));
    }


    @Test
    public void checkEditNode(){
        assertEquals("update Nodes set xcoord = 5124, ycoord = 625, floor = '2', building = '15 Francis', nodeType = 'REST', longName = 'Restroom B elevator Floor 2', shortName = 'Restroom B' where nodeID = 'TREST00102'", theDBModControl.editNode(newBathroom));
    }
}