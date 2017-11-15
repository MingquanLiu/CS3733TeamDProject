import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.Edge;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.pathfind.entity.AStar;
import edu.wpi.cs3733.programname.pathfind.entity.StarNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AstarAdvancedTest {

    NodeData node1 = new NodeData("1", new Coordinate(3, 3), "Basic", "Lobby One", "L1");
    NodeData node2 = new NodeData("2", new Coordinate(3, 4), "Basic", "Hallway One", "H1");
    NodeData node3 = new NodeData("3", new Coordinate(3, 5), "Basic", "Hallway Two", "H2");
    NodeData node4 = new NodeData("4", new Coordinate(2, 4), "Basic", "Hallway Three", "H3");
    NodeData node5 = new NodeData("5", new Coordinate(4, 4), "Basic", "Connector One", "C1");
    NodeData node6 = new NodeData("6", new Coordinate(3, 6), "Basic", "Hallway Two", "H2");
    NodeData node7 = new NodeData("7", new Coordinate(2, 5), "Basic", "Hallway Three", "H3");
    NodeData node8 = new NodeData("8", new Coordinate(4, 5), "Basic", "Connector One", "C1");

    Edge edge1 = new Edge("1", "2", "E1");
    Edge edge2 = new Edge("1", "4", "E2");
    Edge edge3 = new Edge("1", "5", "E3");
    Edge edge4 = new Edge("2", "4", "E4");
    Edge edge5 = new Edge("2", "5", "E5");
    Edge edge6 = new Edge("2", "3", "E6");
    Edge edge7 = new Edge("4", "3", "E7");
    Edge edge8 = new Edge("3", "5", "E8");
    Edge edge9 = new Edge("4", "7", "E6");
    Edge edge10 = new Edge("7", "3", "E7");
    Edge edge11 = new Edge("3", "8", "E8");
    Edge edge12 = new Edge("8", "5", "E8");
    Edge edge13 = new Edge("6", "3", "E6");
    Edge edge14 = new Edge("8", "6", "E7");
    Edge edge15 = new Edge("7", "6", "E8");

    LinkedList<NodeData> allNodes = new LinkedList<>(Arrays.asList(node1, node2, node3, node4, node5, node6, node7, node8));
    LinkedList<Edge> allEdges = new LinkedList<>(Arrays.asList(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8,
            edge9, edge10, edge11, edge12, edge13, edge14, edge15));

    StarNode star1 = new StarNode(node1);
    StarNode star2 = new StarNode(node2);
    StarNode star3 = new StarNode(node3);
    StarNode star4 = new StarNode(node4);
    StarNode star5 = new StarNode(node5);
    StarNode star6 = new StarNode(node6);
    StarNode star7 = new StarNode(node7);
    StarNode star8 = new StarNode(node8);

    public AstarAdvancedTest() {}

    @Test
    // This is a basic test to get from point 1 to point 2 along one edge
    public void StraightPath(){
        AStar Path = new AStar(allNodes, allEdges,"1", "2");
        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star2, star1));
        List<NodeData> astarReturn = Path.getFinalList();
        for(int i = 0; i < astarReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getId(),
                astarReturn.get(i).getId());
    }

    @Test
    // Now let's try to get from Node 3 to Node 1. There are 3 legal paths but only one ideal path
    public void IntermedPath(){
        AStar Path = new AStar(allNodes, allEdges,"3", "1");
        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star1, star2, star3));
        List<NodeData> astarReturn = Path.getFinalList();
        for(int i = 0; i < astarReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getId(),
                astarReturn.get(i).getId());
    }

    @Test
    // Let's start at the far end of the tree and try to get to the first node
    public void LongPath(){
        AStar Path = new AStar(allNodes, allEdges,"6", "1");
        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star1, star2, star3, star6));
        List<NodeData> astarReturn = Path.getFinalList();
        for(int i = 0; i < astarReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getId(),
                astarReturn.get(i).getId());
    }

    @Test
    // Trying to travel around the C part of the hallway
    public void CPath(){
        AStar Path = new AStar(allNodes, allEdges,"1", "7");
        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star7, star4, star1));
        List<NodeData> astarReturn = Path.getFinalList();
        for(int i = 0; i < astarReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getId(),
                astarReturn.get(i).getId());
    }
}
