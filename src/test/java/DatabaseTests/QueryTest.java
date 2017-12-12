//package DatabaseTests;
//
//import edu.wpi.cs3733.programname.commondata.Coordinate;
//import edu.wpi.cs3733.programname.commondata.EdgeData;
//import edu.wpi.cs3733.programname.commondata.NodeData;
//import edu.wpi.cs3733.programname.database.DBConnection;
//import edu.wpi.cs3733.programname.database.DatabaseModificationController;
//import edu.wpi.cs3733.programname.database.QueryMethods.NodesQuery;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//import static org.junit.Assert.assertEquals;
//
//public class QueryTest {
//
//    Coordinate aBathroomCoord = new Coordinate(4125, 625);
//    Coordinate replacedBathroomCoord = new Coordinate(2124, 625);
//    Coordinate replacedBathroomCoord2 = new Coordinate(4232, 294);
//    NodeData aBathroom = new NodeData ("TREST00102", aBathroomCoord, "2", "15 Francis",
//            "REST","Restroom B elevator Floor 2", "Restroom B", "Team D");
//
//    NodeData newBathroom = new NodeData ("CREST00102", replacedBathroomCoord,"2", "15 Francis",
//            "REST","Restroom B elevator Floor 2", "Restroom B", "Team D");
//
//    NodeData newBathroom2 = new NodeData ("CREST00102", replacedBathroomCoord2,"2", "15 Francis",
//            "REST","Restroom B elevator Floor 2", "Restroom B", "Team D");
//
//
//    EdgeData edge1 = new EdgeData("TREST00102_DREST00102", "TREST00102", "DREST00102");
//    DBConnection conn = new DBConnection();   // Creates new instance of connection
//    NodesQuery nodesQuery = new NodesQuery(conn);
//    DatabaseModificationController theDBModControl = new DatabaseModificationController(conn);
//
//
//    @Test
//    public void testGetNodeByTypeAndFloor(){
////        theDBModControl.addNode(aBathroom);
////        theDBModControl.addNode(newBathroom);
//        List<NodeData> actual = new ArrayList<NodeData>();
//        actual = nodesQuery.getNodeByTypeAndFloor("REST","2");
//        List<NodeData> expected = new ArrayList<NodeData>();
//        expected.add(aBathroom);
//        expected.add(newBathroom);
//        assertEquals(expected,actual);
//    }
//
//    @Test
//    public void testGetNodeByCoordAndFloor(){
////        theDBModControl.addNode(aBathroom);
////        theDBModControl.addNode(newBathroom);
//        NodeData actual = nodesQuery.getNodeByCoordAndFloor(aBathroomCoord,"2");
//        assertEquals(aBathroom,actual);
//    }
//}
