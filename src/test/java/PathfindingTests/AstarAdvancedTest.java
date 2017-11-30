//import edu.wpi.cs3733.programname.ManageController;
//import edu.wpi.cs3733.programname.commondata.Coordinate;
//import edu.wpi.cs3733.programname.commondata.EdgeData;
//import edu.wpi.cs3733.programname.commondata.NodeData;
//import edu.wpi.cs3733.programname.database.*;
//import edu.wpi.cs3733.programname.pathfind.entity.AStar;
//import edu.wpi.cs3733.programname.pathfind.entity.StarNode;
//
//
//import edu.wpi.cs3733.programname.database.DBConnection;
//import edu.wpi.cs3733.programname.pathfind.entity.StarNode;
//import edu.wpi.cs3733.programname.pathfind.entity.StarNode;
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.sql.Connection;
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.List;
//
//
//
//public class AstarAdvancedTest {
//    ManageController manage = new ManageController();
//    List<NodeData> allNodes = manage.getAllNodeData();
//    List<EdgeData> allEdges = manage.getAllEdgeData();
//
//
//    public AstarAdvancedTest() {}
//
//    @Test
//    public void StraightPath(){
//
//
//        AStar Path = new AStar(allNodes, allEdges,"1", "4");
//        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star4, star3, star2, star1));
//        List<NodeData> astarReturn = Path.getFinalList();
//        for(int i = 0; i < astarReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getNodeID(),
//                astarReturn.get(i).getNodeID());
//    }
//
//    @Test
//    // We are using nodes 1-4 in a row again, but starting in the middle and trying to get to the far end
//    public void IntermedPath(){
//        AStar Path = new AStar(allNodes, allEdges,"3", "1");
//        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star1, star2, star3));
//        List<NodeData> astarReturn = Path.getFinalList();
//        for(int i = 0; i < astarReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getNodeID(),
//                astarReturn.get(i).getNodeID());
//    }
//
//    @Test
//    // Let's start at the far end of the tree and try to get to the first node
//    public void LongPath(){
//        AStar Path = new AStar(allNodes, allEdges,"8", "1");
//        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star1, star9, star8));
//        List<NodeData> astarReturn = Path.getFinalList();
//        for(int i = 0; i < astarReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getNodeID(),
//                astarReturn.get(i).getNodeID());
//    }
//
//    @Test
//    // Trying to travel around the C part of the hallway
//    public void CPath(){
//        AStar Path = new AStar(allNodes, allEdges,"6", "4");
//        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star4, star5, star6));
//        List<NodeData> astarReturn = Path.getFinalList();
//        for(int i = 0; i < astarReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getNodeID(),
//                astarReturn.get(i).getNodeID());
//    }
//
//    @Test
//    // Can we do a super simple path?
//    public void OneStepPath(){
//        AStar Path = new AStar(allNodes, allEdges,"9", "8");
//        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star8, star9));
//        List<NodeData> astarReturn = Path.getFinalList();
//        for(int i = 0; i < astarReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getNodeID(),
//                astarReturn.get(i).getNodeID());
//    }
//
//    @Test
//    // Failure case: when we go from one node to itself
//    public void ZeroStepPath(){
//        AStar Path = new AStar(allNodes, allEdges,"1", "1");
//        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star1));
//        List<NodeData> astarReturn = Path.getFinalList();
//        for(int i = 0; i < astarReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getNodeID(),
//                astarReturn.get(i).getNodeID());
//    }
//
//    @Test(expected = java.lang.NullPointerException.class)
//    // Failure case: the path does not exist (There are no edges leading to that node)
//    // TODO: Catch a different exception in the future
//    public void NonexistantPath() {
//        allNodes.add(new NodeData("10", new Coordinate(15, 15),"2","15 Francis", "Disconnected", "Outside", "O", "Team Data"));
//        AStar Path = new AStar(allNodes, allEdges, "1", "10");
//        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star1));
//        List<NodeData> astarReturn = Path.getFinalList();
//        for(int i = 0; i < astarReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getNodeID(),
//                astarReturn.get(i).getNodeID());
//    }
//
//    @Test(expected = java.lang.NullPointerException.class)
//    // Failure case: the path does not exist (The node does not exist)
//    // TODO: Catch a different exception in the future
//    public void NonexistantNode() {
//        AStar Path = new AStar(allNodes, allEdges,"1", "10");
//        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star1));
//        List<NodeData> astarReturn = Path.getFinalList();
//        for(int i = 0; i < astarReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getNodeID(),
//                astarReturn.get(i).getNodeID());
//    }
//}
