package edu.wpi.cs3733.programname.pathfind.entity;

import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Edge;
import edu.wpi.cs3733.programname.commondata.NodeData;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.List;

public class AStar {
    List<NodeData> allNodes;
    List<Edge> allEdges;
    List<StarNode> allStarNodes;

    ManageController controller;
    //constructor
    public AStar(ManageController controller){
        this.controller = controller;
    }

    public void init() {
        allNodes = controller.getAllNodeData();
        allEdges = controller.getAllEdgeData();
        for(NodeData node: allNodes) {
            allStarNodes.add(new StarNode(node));
//            LinkedList<StarNode> tempNeighbors = new LinkedList<StarNode>();
//            for(Edge edge: allEdges) {
//                if(edge.getFirstNodeId() == node.getId()) {
//                    tempNeighbors.add()
//                }
//            }
        }
        for(Edge edge: allEdges) {
            NodeData node1 = controller.getNodeData(edge.getFirstNodeId());
            
        }
    }

    // calculates a path from start to finish
    public LinkedList<StarNode> pathFind(StarNode start, StarNode goal) {
        //list of all the nodes that are adjacent to nodes already explored
        LinkedList<StarNode> frontier = new LinkedList<StarNode>();

        //list of all the nodes in the path from start to finish
        LinkedList<StarNode> finalPath = new LinkedList<StarNode>();

        frontier.add(start);

        System.out.println("Starting A*");

        while(!frontier.isEmpty()) {
            StarNode current = frontier.getFirst();
            frontier.removeFirst();
            if(current.getX() == goal.getX() && current.getY() == goal.getY()) {
                System.out.println("At target!");
                while(!(current.getX() == start.getX() && current.getY() == start.getY())) {
                    finalPath.add(current.getPreviousNode());
                    current = current.getPreviousNode();
                }
                return finalPath;
            }
            else {
                LinkedList<StarNode> neighbors = current.getNeighbors();
                for (StarNode newnode : neighbors) {
                    newnode.setPreviousNode(current);
                    newnode.setF(actionCost(newnode) + distanceToGo(newnode, goal));
                    for (int i = 0; i < frontier.size(); i++) {
                        if (newnode.f < frontier.get(i).f)
                            frontier.add(i - 1, newnode); // This is where stuff maybe blows up
                    }
                }
            }
        }
        return null; // Possibly throw an exception in the future
    }

    // returns the cost of moving from the starting node to the provided node
    public int actionCost(StarNode node) {
        StarNode previous = node.getPreviousNode();
        int xDist = previous.getX() - node.getX();
        int yDist = previous.getY() - node.getY();
        double totalDist = Math.sqrt(xDist*xDist + yDist*yDist);
        node.setG(previous.getG() + (int)Math.floor(totalDist));
        return node.getG();
}


    // returns the pixel distance from the provided node to the destination node
    public int distanceToGo(StarNode node, StarNode goal) {
        int xDist = goal.getX() - node.getX();
        int yDist = goal.getY() - node.getY();
        double distToGo = Math.sqrt(xDist*xDist + yDist*yDist);
        return (int)distToGo;
    }
}