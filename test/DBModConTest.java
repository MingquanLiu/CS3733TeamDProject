package edu.wpi.cs3733.test;


import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.EdgeData;
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
    Coordinate replacedBathroomCoord = new Coordinate(2124, 625);
    Coordinate replacedBathroomCoord2 = new Coordinate(4232, 294);
    NodeData aBathroom = new NodeData ("TREST00102", aBathroomCoord, "2", "15 Francis",
            "REST","Restroom B elevator Floor 2", "Restroom B", "Team D");

    NodeData newBathroom = new NodeData ("CREST00102", replacedBathroomCoord,"2", "15 Francis",
            "REST","Restroom B elevator Floor 2", "Restroom B", "Team D");

    NodeData newBathroom2 = new NodeData ("CREST00102", replacedBathroomCoord,"2", "15 Francis",
            "REST","Restroom B elevator Floor 2", "Restroom B", "Team D");


    EdgeData edge1 = new EdgeData("TREST00102_DREST00102", "TREST00102", "DREST00102");
    DBConnection conn = new DBConnection();   // Creates new instance of connection
    DatabaseModificationController theDBModControl = new DatabaseModificationController(conn);

    public DBModConTest(){};

    @Before
    public void setupDbTables() {
        // MapDnodes.csv
        DBTables.createAllTables(conn);           // Makes nodes table

    }


    @Test
    public void checkAddNode(){
        theDBModControl.addNode(aBathroom);
        ManageController manager = new ManageController();
        //TODO: Change getNodeData to queryByNodeID???
        NodeData TREST = manager.getNodeData("TREST00102");
        assertEquals(aBathroom, TREST);
    }


    @Test
    public void checkEditNode(){
        theDBModControl.addNode(newBathroom);
        ManageController manager = new ManageController();
        NodeData CREST = manager.getNodeData("CREST00102");
        theDBModControl.editNode(newBathroom2);

        assertEquals(newBathroom2, CREST);
    }

    @Test
    public void test(){


        theDBModControl.addNode(aBathroom);
        theDBModControl.addNode(newBathroom);
        theDBModControl.addEdge("TREST00102", "CREST00102");
        theDBModControl.editEdge(edge1);
        theDBModControl.deleteEdge(edge1);
        assertEquals(0,0);
    }

}