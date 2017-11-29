package PathfindingTests;

import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.pathfind.entity.AStar;
import edu.wpi.cs3733.programname.pathfind.entity.NoPathException;
import edu.wpi.cs3733.programname.pathfind.entity.StarNode;
import edu.wpi.cs3733.programname.pathfind.entity.TextDirections;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TextDirectionsTest {

    NodeData node1 = new NodeData("1", new Coordinate(1, 0),"1", "15 Francis","REST", "Restroom One", "L1", "Team D");
    NodeData node2 = new NodeData("2", new Coordinate(1, 3),"1", "15 Francis","HALL", "Hallway Three", "H1", "Team D");
    NodeData node3 = new NodeData("3", new Coordinate(5, 3),"1", "15 Francis","INFO", "Security desk", "H2", "Team D");
    NodeData node4 = new NodeData("4", new Coordinate(3, 6),"1", "15 Francis", "HALL", "Hallway Two", "H3", "Team D");
    NodeData node5 = new NodeData("5", new Coordinate(0, 6),"1", "15 Francis","ELEV", "Elevator One", "C1", "Team D");
    NodeData node6 = new NodeData("6", new Coordinate(3, 9),"1", "15 Francis", "HALL", "Hallway One", "R1", "Team D");
    NodeData node7 = new NodeData("7", new Coordinate(6, 9),"1", "15 Francis", "STAI", "Stairway Two", "C2", "Team D");
    NodeData node8 = new NodeData("8", new Coordinate(3, 12),"1", "15 Francis", "DEPT", "Department Four", "H4", "Team D");
    NodeData node9 = new NodeData("9", new Coordinate(10, 3),"2", "15 Francis","RETL", "Cafe Two", "R2", "Team D");
    NodeData node10 = new NodeData("10", new Coordinate(10, 6),"2", "15 Francis","ELEV", "Elevator One", "H1", "Team D");
    NodeData node11 = new NodeData("11", new Coordinate(13, 6),"2", "15 Francis","HALL", "Hallway Four", "H2", "Team D");
    NodeData node12 = new NodeData("12", new Coordinate(16, 6),"2", "15 Francis","REST", "Restroom Five", "H3", "Team D");
    NodeData node13 = new NodeData("13", new Coordinate(10, 9),"2", "15 Francis","STAI", "Stairway Two", "C1", "Team D");
    NodeData node14 = new NodeData("14", new Coordinate(13, 9),"2", "15 Francis", "HALL", "Hallway Five", "R1", "Team D");
    NodeData node15 = new NodeData("15", new Coordinate(16, 11),"2", "15 Francis", "LABS", "Laboratory Two", "C2", "Team D");
    NodeData node16 = new NodeData("16", new Coordinate(16, 14),"2", "15 Francis", "HALL", "Hallway Six", "H4", "Team D");
    NodeData node17 = new NodeData("17", new Coordinate(14, 14),"2", "15 Francis", "EXIT", "Exit Four", "H4", "Team D");


    EdgeData edge1 = new EdgeData("E1", "1", "2");
    EdgeData edge2 = new EdgeData("E2", "2", "3");
    EdgeData edge3 = new EdgeData("E3", "3", "4");
    EdgeData edge4 = new EdgeData("E4", "2", "4");
    EdgeData edge5 = new EdgeData("E5", "5", "4");
    EdgeData edge6 = new EdgeData("E6", "4", "6");
    EdgeData edge7 = new EdgeData("E7", "7", "6");
    EdgeData edge8 = new EdgeData("E8", "8", "6");
    EdgeData edge9 = new EdgeData("E1", "5", "10");
    EdgeData edge10 = new EdgeData("E2", "7", "13");
    EdgeData edge11 = new EdgeData("E3", "9", "11");
    EdgeData edge12 = new EdgeData("E4", "11", "10");
    EdgeData edge13 = new EdgeData("E5", "11", "12");
    EdgeData edge14 = new EdgeData("E6", "11", "14");
    EdgeData edge15 = new EdgeData("E7", "13", "14");
    EdgeData edge16 = new EdgeData("E8", "14", "15");
    EdgeData edge17 = new EdgeData("E7", "15", "16");
    EdgeData edge18 = new EdgeData("E8", "16", "17");

    StarNode star4 = new StarNode(node4);
    StarNode star5 = new StarNode(node5);
    StarNode star10 = new StarNode(node10);
    StarNode star11 = new StarNode(node11);

    LinkedList<NodeData> allNodes = new LinkedList<>(Arrays.asList(node1, node2, node3, node4, node5, node6, node7,
            node8, node9, node10, node11, node12, node13, node14, node15, node16, node17));
    LinkedList<EdgeData> allEdges = new LinkedList<>(Arrays.asList(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8,
            edge9, edge10, edge11, edge12, edge13, edge14, edge15, edge16, edge17, edge18));

    public TextDirectionsTest() {}

    @Test
    public void BasicTest() throws NoPathException {
        AStar Path = new AStar(allNodes, allEdges,"8", "1");
        List<NodeData> astarReturn = Path.getFinalList();
        TextDirections d = new TextDirections(astarReturn);
        System.out.println(d.getTextDirections());
        System.out.println(d.getEmailMessageBody());
    }

    @Test
    public void UpStairs() throws NoPathException {
        AStar Path = new AStar(allNodes, allEdges,"3", "15");
        List<NodeData> astarReturn = Path.getFinalList();
        TextDirections d = new TextDirections(astarReturn);
        System.out.println(d.getTextDirections());
    }

    @Test
    public void RestToRest() throws NoPathException {
        AStar Path = new AStar(allNodes, allEdges,"1", "12");
        List<NodeData> astarReturn = Path.getFinalList();
        TextDirections d = new TextDirections(astarReturn);
        System.out.println(d.getTextDirections());
    }

    @Test
    public void RetlToExit() throws NoPathException {
        AStar Path = new AStar(allNodes, allEdges,"9", "17");
        List<NodeData> astarReturn = Path.getFinalList();
        TextDirections d = new TextDirections(astarReturn);
        System.out.println(d.getTextDirections());
    }

    @Test
    public void H4toH2() throws NoPathException {
        AStar Path = new AStar(allNodes, allEdges,"11", "4");
        List<NodeData> astarReturn = Path.getFinalList();
        TextDirections d = new TextDirections(astarReturn);
        System.out.println(d.getTextDirections());
    }

    @Test
    public void LabtoDept1() throws NoPathException {
        AStar Path = new AStar(allNodes, allEdges,"15", "8");
        List<NodeData> astarReturn = Path.getFinalList();
        TextDirections d = new TextDirections(astarReturn);
        System.out.println(d.getTextDirections());
    }

    @Test
    public void ElevtoStair() throws NoPathException {
        AStar Path = new AStar(allNodes, allEdges,"5", "13");
        List<NodeData> astarReturn = Path.getFinalList();
        TextDirections d = new TextDirections(astarReturn);
        System.out.println(d.getTextDirections());
    }

    // TextDirections.getDirectionAngle() must be public in order to run this test
//    @Test
//    public void testDirections() {
//        TextDirections d = new TextDirections(Arrays.asList(node1));
//        double test1 = d.getDirectionAngle(node14, node11, node9);
//        double test2 = d.getDirectionAngle(node3, node4, node2);
//        double test3 = d.getDirectionAngle(node4, node6, node8);
//        double test4 = d.getDirectionAngle(node5, node4, node6);
//        Assert.assertEquals(-45,test1,0.1);
//        Assert.assertEquals(112.7,test2,0.1);
//        Assert.assertEquals(0,test3,0.1);
//        Assert.assertEquals(90, test4,0.1);
//    }
}
