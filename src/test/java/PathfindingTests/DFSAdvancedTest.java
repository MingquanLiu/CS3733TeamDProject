//import edu.wpi.cs3733.programname.commondata.Coordinate;
//import edu.wpi.cs3733.programname.commondata.EdgeData;
//import edu.wpi.cs3733.programname.commondata.EdgeData;
//import edu.wpi.cs3733.programname.commondata.NodeData;
//import edu.wpi.cs3733.programname.pathfind.entity.DFS;
//import edu.wpi.cs3733.programname.pathfind.entity.NoPathException;
//import edu.wpi.cs3733.programname.pathfind.entity.StarNode;
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.List;
//
//public class DFSAdvancedTest {
//
//    NodeData node1 = new NodeData("1", new Coordinate(3, 3), "2", "15 Francis","Basic", "Lobby One", "L1","Team D");
//    NodeData node2 = new NodeData("2", new Coordinate(3, 4), "2","15 Francis","Basic", "Hallway One", "H1","Team D");
//    NodeData node3 = new NodeData("3", new Coordinate(3, 5), "2","15 Francis","Basic", "Hallway Two", "H2","Team D");
//    NodeData node4 = new NodeData("4", new Coordinate(2, 4), "2","15 Francis","Basic", "Hallway Three", "H3","Team D");
//    NodeData node5 = new NodeData("5", new Coordinate(4, 4), "2","15 Francis","Basic", "Connector One", "C1","Team D");
//    NodeData node6 = new NodeData("6", new Coordinate(3, 6), "2","15 Francis","Basic", "Hallway Two", "H2","Team D");
//    NodeData node7 = new NodeData("7", new Coordinate(2, 5), "2","15 Francis","Basic", "Hallway Three", "H3","Team D");
//    NodeData node8 = new NodeData("8", new Coordinate(4, 5), "2","15 Francis","Basic", "Connector One", "C1","Team D");
//
//    EdgeData edge1 = new EdgeData("1", "2", "E1");
//    EdgeData edge2 = new EdgeData("1", "4", "E2");
//    EdgeData edge3 = new EdgeData("1", "5", "E3");
//    EdgeData edge4 = new EdgeData("2", "4", "E4");
//    EdgeData edge5 = new EdgeData("2", "5", "E5");
//    EdgeData edge6 = new EdgeData("2", "3", "E6");
//    EdgeData edge7 = new EdgeData("4", "3", "E7");
//    EdgeData edge8 = new EdgeData("3", "5", "E8");
//    EdgeData edge9 = new EdgeData("4", "7", "E6");
//    EdgeData edge10 = new EdgeData("7", "3", "E7");
//    EdgeData edge11 = new EdgeData("3", "8", "E8");
//    EdgeData edge12 = new EdgeData("8", "5", "E8");
//    EdgeData edge13 = new EdgeData("6", "3", "E6");
//    EdgeData edge14 = new EdgeData("8", "6", "E7");
//    EdgeData edge15 = new EdgeData("7", "6", "E8");
//
//    LinkedList<NodeData> allNodes = new LinkedList<>(Arrays.asList(node1, node2, node3, node4, node5, node6, node7, node8));
//    LinkedList<EdgeData> allEdges = new LinkedList<>(Arrays.asList(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8,
//            edge9, edge10, edge11, edge12, edge13, edge14, edge15));
//
//    StarNode star1 = new StarNode(node1);
//    StarNode star2 = new StarNode(node2);
//    StarNode star3 = new StarNode(node3);
//    StarNode star4 = new StarNode(node4);
//    StarNode star5 = new StarNode(node5);
//    StarNode star6 = new StarNode(node6);
//    StarNode star7 = new StarNode(node7);
//    StarNode star8 = new StarNode(node8);
//
//    public DFSAdvancedTest() {}
//
//    private boolean hasEdge(String node1, String node2) {
//        for(EdgeData e: this.allEdges) {
//            if(e.getStartNode() == node1 && e.getEndNode() == node2) return true;
//            else if(e.getStartNode() == node2 && e.getEndNode() == node1) return true;
//        }
//        return false;
//    }
//
//    private boolean isValidPath(List<NodeData> nodes) {
//        for(int i = 0; i < (nodes.size() - 1); i++) {
//            if(!hasEdge(nodes.get(i).getNodeID(), nodes.get(i+1).getNodeID())) return false;
//        }
//        return true;
//    }
//
//    @Test
//    // This is a basic test to get from point 1 to point 2 along one edge
//    public void StraightPath() throws NoPathException {
//        DFS Path = new DFS(allNodes, allEdges,"1", "2");
//        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star2, star1));
//        List<NodeData> DFSReturn = Path.getFinalList();
//        Assert.assertTrue(isValidPath(DFSReturn));
////        for(int i = 0; i < DFSReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getNodeID(),
////                DFSReturn.get(i).getNodeID());
//    }
//
//    @Test
//    // Now let's try to get from Node 3 to Node 1. There are 3 legal paths but only one ideal path
//    public void IntermedPath() throws NoPathException {
//        DFS Path = new DFS(allNodes, allEdges,"3", "1");
//        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star1, star2, star3));
//        List<NodeData> DFSReturn = Path.getFinalList();
//        Assert.assertTrue(isValidPath(DFSReturn));
////        for(int i = 0; i < DFSReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getNodeID(),
////                DFSReturn.get(i).getNodeID());
//    }
//
//    @Test
//    // Let's start at the far end of the tree and try to get to the first node
//    public void LongPath() throws NoPathException {
//        DFS Path = new DFS(allNodes, allEdges,"6", "1");
//        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star1, star2, star3, star6));
//        List<NodeData> DFSReturn = Path.getFinalList();
//        Assert.assertTrue(isValidPath(DFSReturn));
////        for(int i = 0; i < DFSReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getNodeID(),
////                DFSReturn.get(i).getNodeID());
//    }
//
//    @Test
//    // Trying to travel around the C part of the hallway
//    public void CPath() throws NoPathException {
//        DFS Path = new DFS(allNodes, allEdges,"1", "7");
//        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star7, star4, star1));
//        List<NodeData> DFSReturn = Path.getFinalList();
//        Assert.assertTrue(isValidPath(DFSReturn));
////        for(int i = 0; i < DFSReturn.size(); i++)
////            Assert.assertEquals(finalOrder.get(i).getNodeID(), DFSReturn.get(i).getNodeID());
//    }
//}
