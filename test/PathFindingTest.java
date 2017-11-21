/**
 * Created by Treksh on 14/11/17.
 */

import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.EdgeData;
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


    NodeData node1 = new NodeData("1", new Coordinate(2, 2),"2","15 Francis","Basic", "Lobby One", "L1","Team D");
    NodeData node2 = new NodeData("2", new Coordinate(2, 4),"2","15 Francis","Basic", "Hallway One", "H1","Team D");
    NodeData node3 = new NodeData("3", new Coordinate(4, 4),"2","15 Francis","Basic", "Hallway Two", "H2","Team D");
    NodeData node4 = new NodeData("4", new Coordinate(6, 4),"2","15 Francis", "Basic", "Hallway Three", "H3","Team D");
    NodeData node5 = new NodeData("5", new Coordinate(4, 6),"2","15 Francis","Basic", "Connector One", "C1","Team D");
    NodeData node6 = new NodeData("6", new Coordinate(6, 6),"2","15 Francis", "Basic", "Room One", "R1","Team D");
    NodeData node7 = new NodeData("7", new Coordinate(4, 8),"2","15 Francis", "Basic", "Connector Two", "C2","Team D");
    NodeData node8 = new NodeData("8", new Coordinate(6, 8),"2","15 Francis", "Basic", "Hallway Four", "H4","Team D");
    NodeData node9 = new NodeData("9", new Coordinate(8, 8),"2","15 Francis","Basic", "Room Two", "R2","Team D");

    EdgeData edge1 = new EdgeData("1", "1", "2");
    EdgeData edge2 = new EdgeData("2", "2", "3");
    EdgeData edge3 = new EdgeData("3", "3", "4");
    EdgeData edge4 = new EdgeData("3", "4", "5");
    EdgeData edge5 = new EdgeData("5", "5", "6");
    EdgeData edge6 = new EdgeData("5", "6", "7");
    EdgeData edge7 = new EdgeData("7", "7", "8");
    EdgeData edge8 = new EdgeData("8", "8", "9");
    EdgeData edge9 = new EdgeData("9", "9", "1");

//    Edge surprise = new Edge("6","8","ES");
    // TODO: add new edges to try to throw the program off, see if we are finding most efficient path, etc.

    LinkedList<NodeData> allNodes = new LinkedList<>(Arrays.asList(node1, node2, node3, node4, node5, node6, node7, node8, node9));
    LinkedList<EdgeData> allEdges = new LinkedList<>(Arrays.asList(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8, edge9));



    public PathFindingTest() {}

    @Test
    // This is a simple test. We have nodes 1-4 which are all connected by only one edge each (a straight line of nodes)
    // If we can get from node 1 to node 4, we are on the right track
    public void StraightPath(){
        PathfindingController controller = new PathfindingController();

        List<NodeData> givenList = controller.initializePathfind(allNodes,allEdges,"1","4");

        for (int i=4; i>0; i--) {
            Assert.assertEquals(givenList.get(4-i).getNodeID(), Integer.toString(i));
            System.out.println("Asserted that Node " + givenList.get(4-i).getNodeID() + " is in the list.");
        }
    }
}
