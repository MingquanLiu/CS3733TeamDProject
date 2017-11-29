/**
 * Created by Treksh on 28/11/17.
 */

import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.Edge;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.pathfind.PathfindingController;
import edu.wpi.cs3733.programname.pathfind.entity.AStar;
import edu.wpi.cs3733.programname.pathfind.entity.NoPathException;
import edu.wpi.cs3733.programname.pathfind.entity.StarNode;
import edu.wpi.cs3733.programname.pathfind.entity.TextDirections;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class HanndicappedTest {

    NodeData node1 = new NodeData("H1", new Coordinate(0, 0),"1","HALL", "Lobby One", "L1");
    NodeData node2 = new NodeData("H2", new Coordinate(8, 0),"1","HALL", "Hallway One", "H1");
    NodeData node3 = new NodeData("H3", new Coordinate(16, 0),"1","HALL", "Hallway Two", "H2");
    NodeData node4 = new NodeData("H4", new Coordinate(0, 4),"2", "HALL", "Hallway Three", "H3");
    NodeData node5 = new NodeData("H5", new Coordinate(8, 4),"2","HALL", "Connector One", "C1");
    NodeData node6 = new NodeData("H6", new Coordinate(16, 4),"2", "HALL", "Room One", "R1");
    NodeData node7 = new NodeData("H7", new Coordinate(0, 8),"3", "HALL", "Connector Two", "C2");
    NodeData node8 = new NodeData("H8", new Coordinate(8, 8),"3", "HALL", "Hallway Four", "H4");
    NodeData node9 = new NodeData("H9", new Coordinate(16, 8),"3","HALL", "Room Two", "R2");
    NodeData node10 = new NodeData("S1", new Coordinate(12, 0),"1", "STAI", "Hallway Three", "H3");
    NodeData node11 = new NodeData("S2", new Coordinate(12, 4),"2","STAI", "Connector One", "C1");
    NodeData node12 = new NodeData("S3", new Coordinate(12, 8),"3", "STAI", "Room One", "R1");
    NodeData node13 = new NodeData("E1", new Coordinate(4, 0),"1", "ELEV", "Connector Two", "C2");
    NodeData node14 = new NodeData("E2", new Coordinate(4, 4),"2", "ELEV", "Hallway Four", "H4");
    NodeData node15 = new NodeData("E3", new Coordinate(4, 8),"3","ELEV", "Room Two", "R2");

    Edge edge1 = new Edge("H1", "E1", "E1");
    Edge edge2 = new Edge("H2", "E1", "E2");
    Edge edge3 = new Edge("H2", "S1", "E3");
    Edge edge4 = new Edge("H3", "S1", "E4");
    Edge edge5 = new Edge("E2", "E1", "E5");
    Edge edge6 = new Edge("S1", "S2", "E6");
    Edge edge7 = new Edge("E2", "H4", "E7");
    Edge edge8 = new Edge("E2", "H5", "E8");
    Edge edge9 = new Edge("S2", "H5", "E9");
    Edge edge10 = new Edge("S2", "H6", "E10");
    Edge edge11 = new Edge("S3", "S2", "E11");
    Edge edge12 = new Edge("E2", "E3", "E12");
    Edge edge13 = new Edge("E3", "H7", "E13");
    Edge edge14 = new Edge("E3", "H8", "E14");
    Edge edge15 = new Edge("S3", "H8", "E15");
    Edge edge16 = new Edge("S3", "H9", "E16");

    LinkedList<NodeData> allNodes = new LinkedList<>(Arrays.asList(node1, node2, node3, node4, node5, node6, node7,
            node8, node9, node10, node11, node12, node13, node14, node15));
    LinkedList<Edge> allEdges = new LinkedList<>(Arrays.asList(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8,
            edge9, edge10, edge11, edge12, edge13, edge14, edge15, edge16));


    @Test
    public void Handi1Path(){
        PathfindingController controller = new PathfindingController();

        List<NodeData> givenList = controller.initializePathfind(allNodes,allEdges,"H2","H8",Boolean.TRUE, PathfindingController.searchType.ASTAR);
        List<NodeData> expected = new LinkedList<NodeData>(Arrays.asList(node8,node15,node14,node13,node2));
        for (int i = 0; i < givenList.size(); i++) {
            Assert.assertEquals(givenList.get(i).getId(), expected.get(i).getId());
        }
    }

    @Test
    public void Handi2Path(){
        PathfindingController controller = new PathfindingController();

        List<NodeData> givenList = controller.initializePathfind(allNodes,allEdges,"H3","H7",Boolean.TRUE, PathfindingController.searchType.ASTAR);
        List<NodeData> expected = new LinkedList<NodeData>(Arrays.asList(node7,node15,node14,node13,node2,node10,node3));
        for (int i = 0; i < givenList.size(); i++) {
            Assert.assertEquals(givenList.get(i).getId(), expected.get(i).getId());
        }
    }

    @Test
    public void Handi3Path(){
        PathfindingController controller = new PathfindingController();

        List<NodeData> givenList = controller.initializePathfind(allNodes,allEdges,"S2","H1",Boolean.TRUE, PathfindingController.searchType.ASTAR);
        List<NodeData> expected = new LinkedList<NodeData>(Arrays.asList(node1,node13,node14,node5,node11));
        for (int i = 0; i < givenList.size(); i++) {
            Assert.assertEquals(givenList.get(i).getId(), expected.get(i).getId());
        }
    }


/*
    @Test
    public void ElevPath3() throws NoPathException {
        AStar Path = new AStar(allNodes, allEdges,"S2", "H1");
        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>(Arrays.asList(star1, star13, star14, star5, star11));
        List<NodeData> astarReturn = Path.getFinalList();
        for(int i = 0; i < astarReturn.size(); i++) Assert.assertEquals(finalOrder.get(i).getId(),
                astarReturn.get(i).getId());
    }
*/


}
