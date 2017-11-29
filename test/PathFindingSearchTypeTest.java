///**
// * Created by Treksh on 28/11/17.
// */
//
//import edu.wpi.cs3733.programname.commondata.Coordinate;
//import edu.wpi.cs3733.programname.commondata.EdgeData;
//import edu.wpi.cs3733.programname.commondata.NodeData;
//import edu.wpi.cs3733.programname.pathfind.PathfindingController;
//import edu.wpi.cs3733.programname.pathfind.entity.AStar;
//import edu.wpi.cs3733.programname.pathfind.entity.NoPathException;
//import edu.wpi.cs3733.programname.pathfind.entity.StarNode;
//import edu.wpi.cs3733.programname.pathfind.entity.TextDirections;
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.List;
//
//public class PathFindingSearchTypeTest {
//
//    NodeData node1 = new NodeData("1", new Coordinate(2, 2),"2","1","Basic", "Lobby One", "L1", "Team D");
//    NodeData node2 = new NodeData("2", new Coordinate(2, 4),"2","1","Basic", "Hallway One", "H1", "Team D");
//    NodeData node3 = new NodeData("3", new Coordinate(4, 4),"2","1","Basic", "Hallway Two", "H2", "Team D");
//    NodeData node4 = new NodeData("4", new Coordinate(6, 4),"2","1", "Basic", "Hallway Three", "H3", "Team D");
//    NodeData node5 = new NodeData("5", new Coordinate(4, 6),"2","1","Basic", "Connector One", "C1", "Team D");
//    NodeData node6 = new NodeData("6", new Coordinate(6, 6),"2","1", "Basic", "Room One", "R1", "Team D");
//    NodeData node7 = new NodeData("7", new Coordinate(4, 8),"2","1", "Basic", "Connector Two", "C2", "Team D");
//    NodeData node8 = new NodeData("8", new Coordinate(6, 8),"2","1", "Basic", "Hallway Four", "H4", "Team D");
//    NodeData node9 = new NodeData("9", new Coordinate(8, 8),"2","1","Basic", "Room Two", "R2", "Team D");
//
//    EdgeData edge1 = new EdgeData("E1","1", "2");
//    EdgeData edge2 = new EdgeData("E2","2", "3");
//    EdgeData edge3 = new EdgeData("E3","3", "4");
//    EdgeData edge4 = new EdgeData("E4","3", "5");
//    EdgeData edge5 = new EdgeData("E5","5", "6");
//    EdgeData edge6 = new EdgeData("E6","5", "7");
//    EdgeData edge7 = new EdgeData("E7","7", "8");
//    EdgeData edge8 = new EdgeData("E8","8", "9");
//
//    LinkedList<NodeData> allNodes = new LinkedList<>(Arrays.asList(node1, node2, node3, node4, node5, node6, node7, node8, node9));
//    LinkedList<EdgeData> allEdges = new LinkedList<>(Arrays.asList(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8));
//
//    // All Search types(BFS,DFS,etc) have been tested in other test files, this only tests to see if the initializePathfind works.
//    @Test
//    public void BFSPath(){
//        PathfindingController controller = new PathfindingController();
//
//        List<NodeData> givenList = controller.initializePathfind(allNodes,allEdges,"1","4",Boolean.FALSE, PathfindingController.searchType.BFS);
//        List<NodeData> expected = new LinkedList<NodeData>(Arrays.asList(node4,node3,node2,node1));
//        for (int i = 0; i < givenList.size(); i++) {
//            Assert.assertEquals(givenList.get(i).getNodeID(), expected.get(i).getNodeID());
//        }
//    }
//
//}
