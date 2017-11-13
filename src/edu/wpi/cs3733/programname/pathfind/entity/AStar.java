package edu.wpi.cs3733.programname.pathfind.entity;

import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Edge;
import edu.wpi.cs3733.programname.commondata.NodeData;
import sun.awt.image.ImageWatched;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AStar {
    List<NodeData> allNodes;
    List<Edge> allEdges;
    HashMap<String, StarNode> allStarNodes;
    List<NodeData> finalList;

    ManageController controller;

    //constructor
    public AStar(ManageController controller, String startID, String goalID){
        this.controller = controller;
        this.init();
        this.finalList = this.pathFind(startID, goalID);
    }

    private void init() {
        allNodes = controller.getAllNodeData();
        allEdges = controller.getAllEdgeData();
        for(NodeData node: allNodes) {
            allStarNodes.put(node.getId(), new StarNode(node));
        }

        for(Edge edge: allEdges) {
            StarNode node1 = allStarNodes.get(edge.getFirstNodeId());
            StarNode node2 = allStarNodes.get(edge.getSecondNodeId());

            node1.addNeighbor(node2);
            node2.addNeighbor(node1);
        }

    }

    // calculates a path from start to finish
    private List<NodeData> pathFind(String startID, String goalID) {
        StarNode start = allStarNodes.get(startID);
        StarNode goal = allStarNodes.get(goalID);

        //list of all the nodes that are adjacent to nodes already explored
        LinkedList<StarNode> frontier = new LinkedList<StarNode>();

        //list of all the nodes in the path from start to finish
        LinkedList<NodeData> finalPath = new LinkedList<NodeData>();

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
    private int actionCost(StarNode node) {
        StarNode previous = node.getPreviousNode();
        int xDist = previous.getX() - node.getX();
        int yDist = previous.getY() - node.getY();
        double totalDist = Math.sqrt(xDist*xDist + yDist*yDist);
        node.setG(previous.getG() + (int)Math.floor(totalDist));
        return node.getG();
}


    // returns the pixel distance from the provided node to the destination node
    private int distanceToGo(StarNode node, StarNode goal) {
        int xDist = goal.getX() - node.getX();
        int yDist = goal.getY() - node.getY();
        double distToGo = Math.sqrt(xDist*xDist + yDist*yDist);
        return (int)distToGo;
    }

    public List<NodeData> getFinalList() {
        return finalList;
    }
}