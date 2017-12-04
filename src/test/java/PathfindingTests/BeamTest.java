package PathfindingTests;

import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.pathfind.entity.BFS;
import edu.wpi.cs3733.programname.pathfind.entity.NoPathException;
import edu.wpi.cs3733.programname.pathfind.entity.StarNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BeamTest {

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

//    EdgeData surprise = new EdgeData("6","8","ES");
    // TODO: add new edges to try to throw the program off, see if we are finding most efficient path, etc.

    LinkedList<NodeData> allNodes = new LinkedList<>(Arrays.asList(node1, node2, node3, node4, node5, node6, node7, node8, node9));
    LinkedList<EdgeData> allEdges = new LinkedList<>(Arrays.asList(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8, edge9));

    StarNode star1 = new StarNode(node1);
    StarNode star2 = new StarNode(node2);
    StarNode star3 = new StarNode(node3);
    StarNode star4 = new StarNode(node4);
    StarNode star5 = new StarNode(node5);
    StarNode star6 = new StarNode(node6);
    StarNode star7 = new StarNode(node7);
    StarNode star8 = new StarNode(node8);
    StarNode star9 = new StarNode(node9);

    public BeamTest() {}

}
