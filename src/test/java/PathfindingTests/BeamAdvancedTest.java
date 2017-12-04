package PathfindingTests;

import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.pathfind.entity.Beam;
import edu.wpi.cs3733.programname.pathfind.entity.NoPathException;
import edu.wpi.cs3733.programname.pathfind.entity.StarNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BeamAdvancedTest {

    NodeData node1 = new NodeData("1", new Coordinate(3, 3), "2","1", "Basic", "Lobby One", "L1","D");
    NodeData node2 = new NodeData("2", new Coordinate(3, 4), "2","1","Basic", "Hallway One", "H1","D");
    NodeData node3 = new NodeData("3", new Coordinate(3, 5), "2","1","Basic", "Hallway Two", "H2","d");
    NodeData node4 = new NodeData("4", new Coordinate(2, 4), "2","1","Basic", "Hallway Three", "H3","D");
    NodeData node5 = new NodeData("5", new Coordinate(4, 4), "2","1","Basic", "Connector One", "C1","D");
    NodeData node6 = new NodeData("6", new Coordinate(3, 6), "2","1","Basic", "Hallway Two", "H2","D");
    NodeData node7 = new NodeData("7", new Coordinate(2, 5), "2","1","Basic", "Hallway Three", "H3","D");
    NodeData node8 = new NodeData("8", new Coordinate(4, 5), "2","1","Basic", "Connector One", "C1","D");

    EdgeData edge1 = new EdgeData("E1","1", "2");
    EdgeData edge2 = new EdgeData("E2","1", "4");
    EdgeData edge3 = new EdgeData("E3","1", "5");
    EdgeData edge4 = new EdgeData("E4","2", "4");
    EdgeData edge5 = new EdgeData("E5","2", "5");
    EdgeData edge6 = new EdgeData("E6","2", "3");
    EdgeData edge7 = new EdgeData("E7","4", "3");
    EdgeData edge8 = new EdgeData("E8","3", "5");
    EdgeData edge9 = new EdgeData("E9","4", "7");
    EdgeData edge10 = new EdgeData("E10","7", "3");
    EdgeData edge11 = new EdgeData("E11","3", "8");
    EdgeData edge12 = new EdgeData("E12","8", "5");
    EdgeData edge13 = new EdgeData("E13","6", "3");
    EdgeData edge14 = new EdgeData("E14","8", "6");
    EdgeData edge15 = new EdgeData("E15","7", "6");

    LinkedList<NodeData> allNodes = new LinkedList<>(Arrays.asList(node1, node2, node3, node4, node5, node6, node7, node8));
    LinkedList<EdgeData> allEdges = new LinkedList<>(Arrays.asList(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8,
            edge9, edge10, edge11, edge12, edge13, edge14, edge15));

    StarNode star1 = new StarNode(node1);
    StarNode star2 = new StarNode(node2);
    StarNode star3 = new StarNode(node3);
    StarNode star4 = new StarNode(node4);
    StarNode star5 = new StarNode(node5);
    StarNode star6 = new StarNode(node6);
    StarNode star7 = new StarNode(node7);
    StarNode star8 = new StarNode(node8);

    public BeamAdvancedTest() {}

}
