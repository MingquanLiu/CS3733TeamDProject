package edu.wpi.cs3733.programname.pathfind.entity;

import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class PathfindingStrategyTemplate {
    List<NodeData> allNodes;
    List<EdgeData> allEdges;
    List<NodeData> finalList = new LinkedList<>();
    LinkedList<StarNode> frontier = new LinkedList<StarNode>();
    HashMap<String, StarNode> allStarNodes = new HashMap<>();
    String startID, goalID;
    StarNode start, goal;

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
        for(NodeData inode: allNodes){
 //           System.out.println("iterating through node: " + inode.getNodeID());
//            System.out.println(startID );
            if (startID.equals(inode.getLongName()) || startID.equals(inode.getNodeID())){
                System.out.println(inode.getLongName());
                this.start = allStarNodes.get(inode.getNodeID());
            }
            if (goalID.equals(inode.getLongName()) || goalID.equals(inode.getNodeID())){
                System.out.println(inode.getLongName());
                this.goal = allStarNodes.get(inode.getNodeID());
            }
        }
//        this.start = allStarNodes.get(this.startID);
//        this.goal = allStarNodes.get(this.goalID);
    }

    abstract List<NodeData> pathFind() throws NoPathException;

    public List<NodeData> getFinalList() throws NoPathException {
        this.init();
        finalList = this.pathFind();
        Collections.reverse(finalList);
        return finalList;
    }

}
