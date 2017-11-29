/**
 * Created by Treksh on 28/11/17.
 */

import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.EdgeData;
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

    NodeData node1 = new NodeData("H1", new Coordinate(0, 0),"1","1","HALL", "Lobby One", "L1", "Team D");
    NodeData node2 = new NodeData("H2", new Coordinate(8, 0),"1","1","HALL", "Hallway One", "H1", "Team D");
    NodeData node3 = new NodeData("H3", new Coordinate(16, 0),"1","1","HALL", "Hallway Two", "H2", "Team D");
    NodeData node4 = new NodeData("H4", new Coordinate(0, 4),"2","1", "HALL", "Hallway Three", "H3", "Team D");
    NodeData node5 = new NodeData("H5", new Coordinate(8, 4),"2","1","HALL", "Connector One", "C1", "Team D");
    NodeData node6 = new NodeData("H6", new Coordinate(16, 4),"2","1", "HALL", "Room One", "R1", "Team D");
    NodeData node7 = new NodeData("H7", new Coordinate(0, 8),"3","1", "HALL", "Connector Two", "C2", "Team D");
    NodeData node8 = new NodeData("H8", new Coordinate(8, 8),"3","1", "HALL", "Hallway Four", "H4", "Team D");
    NodeData node9 = new NodeData("H9", new Coordinate(16, 8),"3","1","HALL", "Room Two", "R2", "Team D");
    NodeData node10 = new NodeData("S1", new Coordinate(12, 0),"1","1", "STAI", "Hallway Three", "H3", "Team D");
    NodeData node11 = new NodeData("S2", new Coordinate(12, 4),"2","1","STAI", "Connector One", "C1", "Team D");
    NodeData node12 = new NodeData("S3", new Coordinate(12, 8),"3","1", "STAI", "Room One", "R1", "Team D");
    NodeData node13 = new NodeData("E1", new Coordinate(4, 0),"1","1", "ELEV", "Connector Two", "C2", "Team D");
    NodeData node14 = new NodeData("E2", new Coordinate(4, 4),"2","1", "ELEV", "Hallway Four", "H4", "Team D");
    NodeData node15 = new NodeData("E3", new Coordinate(4, 8),"3","1","ELEV", "Room Two", "R2", "Team D");

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


    @Test
    public void Handi1Path(){
        PathfindingController controller = new PathfindingController();

        List<NodeData> givenList = controller.initializePathfind(allNodes,allEdges,"H2","H8",Boolean.TRUE, PathfindingController.searchType.ASTAR);
        List<NodeData> expected = new LinkedList<NodeData>(Arrays.asList(node8,node15,node14,node13,node2));
        for (int i = 0; i < givenList.size(); i++) {
            Assert.assertEquals(givenList.get(i).getNodeID(), expected.get(i).getNodeID());
        }
    }

    @Test
    public void Handi2Path(){
        PathfindingController controller = new PathfindingController();

        List<NodeData> givenList = controller.initializePathfind(allNodes,allEdges,"H3","H7",Boolean.TRUE, PathfindingController.searchType.ASTAR);
        List<NodeData> expected = new LinkedList<NodeData>(Arrays.asList(node7,node15,node14,node13,node2,node10,node3));
        for (int i = 0; i < givenList.size(); i++) {
            Assert.assertEquals(givenList.get(i).getNodeID(), expected.get(i).getNodeID());
        }
    }

    @Test
    public void Handi3Path(){
        PathfindingController controller = new PathfindingController();

        List<NodeData> givenList = controller.initializePathfind(allNodes,allEdges,"S2","H1",Boolean.TRUE, PathfindingController.searchType.ASTAR);
        List<NodeData> expected = new LinkedList<NodeData>(Arrays.asList(node1,node13,node14,node5,node11));
        for (int i = 0; i < givenList.size(); i++) {
            Assert.assertEquals(givenList.get(i).getNodeID(), expected.get(i).getNodeID());
        }
    }




}
