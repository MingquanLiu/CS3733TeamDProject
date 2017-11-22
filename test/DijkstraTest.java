import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.Edge;
import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.pathfind.entity.Dijkstra;
import edu.wpi.cs3733.programname.pathfind.entity.NoPathException;
import edu.wpi.cs3733.programname.pathfind.entity.StarNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DijkstraTest {

    NodeData node1 = new NodeData("1", new Coordinate(2, 2),"2","1","Basic", "Lobby One", "L1","D");
    NodeData node2 = new NodeData("2", new Coordinate(2, 4),"2","1","Basic", "Hallway One", "H1","D");
    NodeData node3 = new NodeData("3", new Coordinate(4, 4),"2","1","Basic", "Hallway Two", "H2","D");
    NodeData node4 = new NodeData("4", new Coordinate(6, 4),"2","1","Basic", "Hallway Three", "H3","D");
    NodeData node5 = new NodeData("5", new Coordinate(4, 6),"2","1","Basic", "Connector One", "C1","D");
    NodeData node6 = new NodeData("6", new Coordinate(6, 6),"2","1", "Basic", "Room One", "R1","D");
    NodeData node7 = new NodeData("7", new Coordinate(4, 8),"2","1", "Basic", "Connector Two", "C2","D");
    NodeData node8 = new NodeData("8", new Coordinate(6, 8),"2","1", "Basic", "Hallway Four", "H4","D");
    NodeData node9 = new NodeData("9", new Coordinate(8, 8),"2","1","Basic", "Room Two", "R2","D");

    EdgeData edge1 = new EdgeData("E1","1", "2");
    EdgeData edge2 = new EdgeData("E2","2", "3");
    EdgeData edge3 = new EdgeData("E3","3", "4");
    EdgeData edge4 = new EdgeData("E4","3", "5");
    EdgeData edge5 = new EdgeData("E5","5", "6");
    EdgeData edge6 = new EdgeData("E6","5", "7");
    EdgeData edge7 = new EdgeData("E7","7", "8");
    EdgeData edge8 = new EdgeData("E8","8", "9");
//    Edge surprise = new Edge("6","8","ES");
    // TODO: add new edges to try to throw the program off, see if we are finding most efficient path, etc.

    LinkedList<NodeData> allNodes = new LinkedList<>(Arrays.asList(node1, node2, node3, node4, node5, node6, node7, node8, node9));
    LinkedList<EdgeData> allEdges = new LinkedList<>(Arrays.asList(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8));

    StarNode star1 = new StarNode(node1);
    StarNode star2 = new StarNode(node2);
    StarNode star3 = new StarNode(node3);
    StarNode star4 = new StarNode(node4);
    StarNode star5 = new StarNode(node5);
    StarNode star6 = new StarNode(node6);
    StarNode star7 = new StarNode(node7);
    StarNode star8 = new StarNode(node8);
    StarNode star9 = new StarNode(node9);

    public DijkstraTest() {}

    @Test
    // This is a simple test. We have nodes 1-4 which are all connected by only one edge each (a straight line of nodes)
    // If we can get from node 1 to node 4, we are on the right track
    public void StraightPath() throws NoPathException {
        Dijkstra Path = new Dijkstra(allNodes, allEdges,"1", "4");
        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star4, star3, star2, star1));
        List<NodeData> DijkstraReturn = Path.getFinalList();
        for(int i = 0; i < DijkstraReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getNodeID(),
                DijkstraReturn.get(i).getNodeID());
    }

    @Test
    // We are using nodes 1-4 in a row again, but starting in the middle and trying to get to the far end
    public void IntermedPath() throws NoPathException {
        Dijkstra Path = new Dijkstra(allNodes, allEdges,"3", "1");
        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star1, star2, star3));
        List<NodeData> DijkstraReturn = Path.getFinalList();
        for(int i = 0; i < DijkstraReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getNodeID(),
                DijkstraReturn.get(i).getNodeID());
    }

    @Test
    // Let's start at the far end of the tree and try to get to the first node
    public void LongPath() throws NoPathException {
        Dijkstra Path = new Dijkstra(allNodes, allEdges,"8", "1");
        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star1, star2, star3, star5, star7, star8));
        List<NodeData> DijkstraReturn = Path.getFinalList();
        for(int i = 0; i < DijkstraReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getNodeID(),
                DijkstraReturn.get(i).getNodeID());
    }

    @Test
    // Trying to travel around the C part of the hallway
    public void CPath() throws NoPathException {
        Dijkstra Path = new Dijkstra(allNodes, allEdges,"6", "4");
        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star4, star3, star5, star6));
        List<NodeData> DijkstraReturn = Path.getFinalList();
        for(int i = 0; i < DijkstraReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getNodeID(),
                DijkstraReturn.get(i).getNodeID());
    }

    @Test
    // Can we do a super simple path?
    public void OneStepPath() throws NoPathException {
        Dijkstra Path = new Dijkstra(allNodes, allEdges,"9", "8");
        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star8, star9));
        List<NodeData> DijkstraReturn = Path.getFinalList();
        for(int i = 0; i < DijkstraReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getNodeID(),
                DijkstraReturn.get(i).getNodeID());
    }

    @Test
    // Failure case: when we go from one node to itself
    public void ZeroStepPath() throws NoPathException {
        Dijkstra Path = new Dijkstra(allNodes, allEdges,"1", "1");
        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star1));
        List<NodeData> DijkstraReturn = Path.getFinalList();
        for(int i = 0; i < DijkstraReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getNodeID(),
                DijkstraReturn.get(i).getNodeID());
    }

    @Test(expected = NoPathException.class)
    // Failure case: the path does not exist (There are no edges leading to that node)
    public void NonexistantPath() throws NoPathException {
        allNodes.add(new NodeData("10", new Coordinate(15, 15),"2","1", "Disconnected", "Outside", "O","D"));
        Dijkstra Path = new Dijkstra(allNodes, allEdges, "1", "10");
        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star1));
        List<NodeData> DijkstraReturn = Path.getFinalList();
        for(int i = 0; i < DijkstraReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getNodeID(),
                DijkstraReturn.get(i).getNodeID());
    }

    @Test(expected = java.lang.NullPointerException.class)
    // Failure case: the path does not exist (The node does not exist)
    // TODO: Catch a different exception in the future
    public void NonexistantNode() throws NoPathException {
        Dijkstra Path = new Dijkstra(allNodes, allEdges,"1", "10");
        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star1));
        List<NodeData> DijkstraReturn = Path.getFinalList();
        for(int i = 0; i < DijkstraReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getNodeID(),
                DijkstraReturn.get(i).getNodeID());
    }
}
