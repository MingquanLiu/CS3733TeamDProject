//package edu.wpi.cs3733.test;
//
//
//import edu.wpi.cs3733.programname.commondata.Edge;
//import edu.wpi.cs3733.programname.commondata.NodeData;
//import edu.wpi.cs3733.programname.commondata.Coordinate;
//import edu.wpi.cs3733.programname.database.*;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.sql.Connection;
//
//import static org.junit.Assert.assertEquals;
//
//public class DBModConTest {
//    Coordinate aBathroomCoord = new Coordinate(4125, 625);
//    Coordinate replacedBathroomCoord = new Coordinate(5124, 625);
//    NodeData aBathroom = new NodeData ("TREST00102", aBathroomCoord, "2","REST","Restroom B elevator Floor 2", "Restroom B");
//    NodeData newBathroom = new NodeData ("DREST00102", replacedBathroomCoord,"2", "REST","Restroom B elevator Floor 2", "Restroom B");
//    Edge edge1 = new Edge("TREST00102", "DREST00102", "TREST00102_DREST00102");
//    DBConnection conn = new DBConnection();   // Creates new instance of connection
//    DatabaseModificationController theDBModControl = new DatabaseModificationController(conn);
//
//    @Before
//    public void setupDbTables() {
//        // MapDnodes.csv
//        DBTables.createNodesTables(conn);           // Makes nodes table
//    }
//
//
////    @Test
////    public void checkAddNode(){
////        String result = theDBModControl.addNode(aBathroom);
////        assertEquals("insert into Nodes values('TREST00102', 4125, 625, '2', '15 Francis', 'REST', 'Restroom B elevator Floor 2', 'Restroom B', 'Team D')", result);
////    }
////
////
////    @Test
////    public void checkEditNode(){
////        assertEquals("update Nodes set xcoord = 5124, ycoord = 625, floor = '2', building = '15 Francis', nodeType = 'REST', longName = 'Restroom B elevator Floor 2', shortName = 'Restroom B', teamAssigned = 'Team D' where nodeID = 'DREST00102'", theDBModControl.editNode(newBathroom));
////    }
//
//    @Test
//    public void test(){
//        theDBModControl.deleteNode(aBathroom);
//        theDBModControl.addEdge("TREST00102", "DREST00102");
//        theDBModControl.editEdge(edge1);
//        theDBModControl.deleteEdge(edge1);
//        assertEquals(0,0);
//    }
//
//}