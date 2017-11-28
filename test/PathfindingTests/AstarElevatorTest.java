package PathfindingTests;

import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.pathfind.entity.AStar;
import edu.wpi.cs3733.programname.pathfind.entity.NoPathException;
import edu.wpi.cs3733.programname.pathfind.entity.StarNode;
import edu.wpi.cs3733.programname.pathfind.entity.TextDirections;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AstarElevatorTest {
    NodeData node1 = new NodeData("H1", new Coordinate(0, 0),"1","Building1","HALL", "Lobby One", "L1", "TeamD");
    NodeData node2 = new NodeData("H2", new Coordinate(8, 0),"1", "Buidling1","HALL", "Hallway One", "H1", "TeamD");
    NodeData node3 = new NodeData("H3", new Coordinate(16, 0),"1", "Building1", "HALL", "Hallway Two", "H2", "D");
    NodeData node4 = new NodeData("H4", new Coordinate(0, 4),"2", "1", "HALL", "Hallway Three", "H3", "D");
    NodeData node5 = new NodeData("H5", new Coordinate(8, 4),"2", "1", "HALL", "Connector One", "C1", "D");
    NodeData node6 = new NodeData("H6", new Coordinate(16, 4),"2", "1", "HALL", "Room One", "R1", "D");
    NodeData node7 = new NodeData("H7", new Coordinate(0, 8),"3", "2", "HALL", "Connector Two", "C2", "D");
    NodeData node8 = new NodeData("H8", new Coordinate(8, 8),"3", "HALL", "1","Hallway Four", "H4", "D");
    NodeData node9 = new NodeData("H9", new Coordinate(16, 8),"3", "3","HALL", "Room Two", "R2", "D");
    NodeData node10 = new NodeData("S1", new Coordinate(12, 0),"1", "2", "STAI", "Hallway Three", "H3", "D");
    NodeData node11 = new NodeData("S2", new Coordinate(12, 4),"2", "1","STAI", "Connector One", "C1", "D");
    NodeData node12 = new NodeData("S3", new Coordinate(12, 8),"3", "2", "STAI", "Room One", "R1","D");
    NodeData node13 = new NodeData("E1", new Coordinate(4, 0),"1", "2","ELEV", "Connector Two", "C2","D");
    NodeData node14 = new NodeData("E2", new Coordinate(4, 4),"2", "1", "ELEV", "Hallway Four", "H4","D");
    NodeData node15 = new NodeData("E3", new Coordinate(4, 8),"3", "1","ELEV", "Room Two", "R2", "D");

    EdgeData edge1 = new EdgeData("E1","H1", "E1");
    EdgeData edge2 = new EdgeData("E2","H2", "E1");
    EdgeData edge3 = new EdgeData("E3","H2", "S1");
    EdgeData edge4 = new EdgeData("E4","H3", "S1");
    EdgeData edge5 = new EdgeData("E5","E2", "E1");
    EdgeData edge6 = new EdgeData("E6","S1", "S2");
    EdgeData edge7 = new EdgeData("E7","E2", "H4");
    EdgeData edge8 = new EdgeData("E8","E2", "H5");
    EdgeData edge9 = new EdgeData("E9","S2", "H5");
    EdgeData edge10 = new EdgeData("E10","S2", "H6");
    EdgeData edge11 = new EdgeData("E11","S3", "S2");
    EdgeData edge12 = new EdgeData("E12","E2", "E3");
    EdgeData edge13 = new EdgeData("E13","E3", "H7");
    EdgeData edge14 = new EdgeData("E14","E3", "H8");
    EdgeData edge15 = new EdgeData("E15","S3", "H8");
    EdgeData edge16 = new EdgeData("E16","S3", "H9");

    LinkedList<NodeData> allNodes = new LinkedList<>(Arrays.asList(node1, node2, node3, node4, node5, node6, node7,
            node8, node9, node10, node11, node12, node13, node14, node15));
    LinkedList<EdgeData> allEdges = new LinkedList<>(Arrays.asList(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8,
            edge9, edge10, edge11, edge12, edge13, edge14, edge15, edge16));

    StarNode star1 = new StarNode(node1);
    StarNode star2 = new StarNode(node2);
    StarNode star3 = new StarNode(node3);
    StarNode star4 = new StarNode(node4);
    StarNode star5 = new StarNode(node5);
    StarNode star6 = new StarNode(node6);
    StarNode star7 = new StarNode(node7);
    StarNode star8 = new StarNode(node8);
    StarNode star9 = new StarNode(node9);
    StarNode star10 = new StarNode(node10);
    StarNode star11 = new StarNode(node11);
    StarNode star12 = new StarNode(node12);
    StarNode star13 = new StarNode(node13);
    StarNode star14 = new StarNode(node14);
    StarNode star15 = new StarNode(node15);

    public AstarElevatorTest() {}

    @Test
    public void ElevPath() throws NoPathException {
        AStar Path = new AStar(allNodes, allEdges,"H2", "H8");
        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star8, star15, star14, star13, star2));
        List<NodeData> astarReturn = Path.getFinalList();
        for(int i = 0; i < astarReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getNodeID(),
                astarReturn.get(i).getNodeID());
    }

    @Test
    public void StairPath() throws NoPathException {
        AStar Path = new AStar(allNodes, allEdges,"H3", "H9");
        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star9, star12, star11, star10, star3));
        List<NodeData> astarReturn = Path.getFinalList();
        for(int i = 0; i < astarReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getNodeID(),
                astarReturn.get(i).getNodeID());
    }

    @Test
    public void ElevPath2() throws NoPathException {
        AStar Path = new AStar(allNodes, allEdges,"H3", "H7");
        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star7, star15, star14, star13, star2, star10, star3));
        List<NodeData> astarReturn = Path.getFinalList();
        for(int i = 0; i < astarReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getNodeID(),
                astarReturn.get(i).getNodeID());
    }

    @Test
    public void ElevPath3() throws NoPathException {
        AStar Path = new AStar(allNodes, allEdges,"S2", "H1");
        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star1, star13, star14, star5, star11));
        List<NodeData> astarReturn = Path.getFinalList();
        for(int i = 0; i < astarReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getNodeID(),
                astarReturn.get(i).getNodeID());
    }

    @Test
    public void OneStepPath() throws NoPathException {
        AStar Path = new AStar(allNodes, allEdges,"H9", "H5");
        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star5, star14, star15, star8, star12, star9));
        List<NodeData> astarReturn = Path.getFinalList();
        for(int i = 0; i < astarReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getNodeID(),
                astarReturn.get(i).getNodeID());
    }

}
