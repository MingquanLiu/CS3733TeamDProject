/**
 * Created by Treksh on 14/11/17.
 */

import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.Edge;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.pathfind.PathfindingController;
import edu.wpi.cs3733.programname.pathfind.entity.AStar;
import edu.wpi.cs3733.programname.pathfind.entity.StarNode;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Node;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PathFindingTest {


    NodeData node1 = new NodeData("1STAI", new Coordinate(2, 2),"2", "STAI", "Lobby One", "L1");
    NodeData node2 = new NodeData("STAI2", new Coordinate(2, 4), "2","ELEV", "Hallway One", "H1");
    NodeData node3 = new NodeData("ELEV1", new Coordinate(4, 4), "2","Basic", "Hallway Two", "H2");
    NodeData node4 = new NodeData("HALL3", new Coordinate(6, 4), "2","Basic", "Hallway Three", "H3");
    NodeData node5 = new NodeData("5HALL", new Coordinate(4, 6), "2","Basic", "Connector One", "C1");
    NodeData node6 = new NodeData("6STAI", new Coordinate(6, 6), "2","Basic", "Room One", "R1");
    NodeData node7 = new NodeData("7INFO", new Coordinate(4, 8), "2","Basic", "Connector Two", "C2");
    NodeData node8 = new NodeData("STAI_8", new Coordinate(6, 8), "2","Basic", "Hallway Four", "H4");
    NodeData node9 = new NodeData("HALL9", new Coordinate(8, 8), "2","Basic", "Room Two", "R2");

    Edge edge1 = new Edge("11STAI", "HALL32", "E1");
    Edge edge2 = new Edge("2HALL3", "3HALL3", "E2");
    Edge edge3 = new Edge("31STAI", "41STAI", "E3");
    Edge edge4 = new Edge("3HALL3", "5HALL3", "E4");
    Edge edge5 = new Edge("5STAI_8", "67INFO", "E5");
    Edge edge6 = new Edge("5ELEV1", "77INFO", "E6");
    Edge edge7 = new Edge("71STAI", "81STAI", "E7");
    Edge edge8 = new Edge("81STAI", "9ELEV1", "E8");
    Edge surprise = new Edge("67INFO","81STAI","ES");
    // TODO: add new edges to try to throw the program off, see if we are finding most efficient path, etc.

    LinkedList<NodeData> allNodes = new LinkedList<>(Arrays.asList(node1, node2, node3, node4, node5, node6, node7, node8, node9));
    LinkedList<Edge> allEdges = new LinkedList<>(Arrays.asList(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8, surprise));


    public PathFindingTest() {}

    @Test
    // This is a simple test. We have nodes 1-4 which are all connected by only one edge each (a straight line of nodes)
    // If we can get from node 1 to node 4, we are on the right track
    public void StraightPath(){
        PathfindingController controller = new PathfindingController();

        List<Edge> givenList = controller.filterPath(allEdges);
        List<Edge> expected = new LinkedList<>(Arrays.asList(edge2, edge4, edge6));
        for (int i = 0; i < givenList.size(); i++) {
            Assert.assertEquals(givenList.get(i).getEdgeId(), expected.get(i).getEdgeId());
        }
    }
}
