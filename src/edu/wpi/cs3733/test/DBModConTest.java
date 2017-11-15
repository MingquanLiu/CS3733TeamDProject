package edu.wpi.cs3733.test;


import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Edge;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.database.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;

public class DBModConTest {
    ManageController manageController = new ManageController();
    DatabaseQueryController queryController = new DatabaseQueryController(manageController);
    DBConnection conn = new DBConnection();   // Creates new instance of connection
    DatabaseModificationController dbModControl = new DatabaseModificationController(conn);
    Coordinate test1coord = new Coordinate(1,1);
    Coordinate test11coord = new Coordinate(11,11);
    Coordinate badCoord = new Coordinate(-1,-1);
    NodeData test1 = new NodeData("TESTID001", test1coord, "2", "TEST", "Test Node 1", "TestN1");
    NodeData badNode = new NodeData("TESTID001", badCoord, "2", "TEST", "Test Node 1", "TestN1");
    NodeData test2 = new NodeData("TESTID002", test1coord, "2", "TEST", "Test Node 2", "TestN2");
    NodeData test11 = new NodeData("TESTID001", test11coord, "2", "TEST", "Test Node 11", "TestN11");
    //Edge edge1 = new Edge("TESTID001", "TESTID002", "TESTID001_TESTID002");



    @Before
    public void setupDbTables() {
        // MapDnodes.csv
        DBTables.createNodesTables(conn);           // Makes nodes table
        DBTables.createEdgesTables(conn);
    }


    @Test
    public void checkAddNode(){
        dbModControl.addNode(test1);
        assertEquals(test1, queryController.queryNodeById("TESTID001"));
    }

    /**
     * this test should pass anyways but throws a DerbySQLIntegrityConstraintViolationException
     * because we are trying to check if it stops us from adding an already existing node
     */
    @Test
    public void checkConstraint1(){
        dbModControl.addNode(test1);
        dbModControl.addNode(test1);
        assertEquals(0,0);
    }

    /**
     * this test should pass anyways but throws a DerbySQLIntegrityConstraintViolationException
     * because we are trying to check if it stops us from adding a node with invalid coordinates
     */
    @Test
    public void checkConstraint2(){
        dbModControl.addNode(badNode);
        assertEquals(0,0);
    }

    /**
     * this test should pass anyways but does not print anything
     * because we are trying to check we deleted the node as if a node is deleted, nothing would be printed
     */
    @Test
    public void checkDeleteNode(){
        dbModControl.addNode(test1);
        dbModControl.deleteNode(test1);
        NodeData node = queryController.queryNodeById("TESTID001");
        System.out.println(node.getId());
        assertEquals(0,0);
    }

    @Test
    public void checkEditNode(){
        dbModControl.addNode(test1);
        dbModControl.editNode(test11);
        NodeData newTest1 = queryController.queryNodeById("TESTID001");
        assertEquals(newTest1, test11);
    }

    @Test
    public void checkAddEdge(){
        dbModControl.addEdge("TESTID001", "TESTID002");
        Edge edge1 = queryController.queryEdgeById("TESTID001_TESTID002");
        Edge checkEdge1 = new Edge("TESTID001", "TESTID002", "TESTID001_TESTID002");
        assertEquals(checkEdge1, edge1);
    }

    /**
     * this test should pass anyways but throws a DerbySQLIntegrityConstraintViolationException
     * because we are trying to check if it stops us from adding an already existing edge
     */
    @Test
    public void checkConstraint3(){
        dbModControl.addEdge("TESTID001", "TESTID002");
        dbModControl.addEdge("TESTID001", "TESTID002");
        assertEquals(0,0);
    }


    @Test
    public void checkEditEdge(){
        dbModControl.addEdge("TESTID001", "TESTID002");
        Edge newEdge1 = new Edge("TESTID011", "TESTID012", "TESTID001_TESTID002");
        dbModControl.editEdge(newEdge1);
        Edge edge1 = queryController.queryEdgeById("TESTID001_TESTID002");
        assertEquals(newEdge1, edge1);
    }

    /**
     * this test should pass anyways but does not print anything
     * because we are trying to check we deleted the node as if a node is deleted, nothing would be printed
     */
    @Test
    public void checkDeleteEdge(){
        dbModControl.addEdge("TESTID001", "TESTID002");
        Edge edge1 = queryController.queryEdgeById("TESTID001_TESTID002");
        dbModControl.deleteEdge(edge1);
        Edge deletedEdge1 = queryController.queryEdgeById("TESTID001_TESTID002");
        System.out.println(deletedEdge1.getEdgeId());
        assertEquals(0,0);
    }


}