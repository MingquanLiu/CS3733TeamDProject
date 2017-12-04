package edu.wpi.cs3733.programname.pathfind.entity;

import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.util.HashMap;
import java.util.List;

public abstract class PathfindingStrategyTemplate {
    List<NodeData> allNodes;
    List<EdgeData> allEdges;
    List<NodeData> finalList;
    HashMap<String, StarNode> allStarNodes = new HashMap<>();
    String startID, goalID;

    void init() {
        System.out.println("Initializing Path Finder");
        for(NodeData node: allNodes) {
            // Creates the StarNodes
            allStarNodes.put(node.getNodeID(), new StarNode(node));
        }

        for(EdgeData edge: allEdges) {
            StarNode node1 = allStarNodes.get(edge.getStartNode());
            StarNode node2 = allStarNodes.get(edge.getEndNode());
            node1.addNeighbor(node2);
            node2.addNeighbor(node1);
        }
    }

    abstract List<NodeData> pathFind() throws NoPathException;

    public List<NodeData> getFinalList() throws NoPathException {
        this.init();
        finalList = this.pathFind();

        return finalList;
    }

}
