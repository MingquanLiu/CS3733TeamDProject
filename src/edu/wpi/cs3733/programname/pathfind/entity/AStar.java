/**
 * AStar class: create an AStar object each time you want to find a new path
 * Then call AStar.getFinalList() to get the list of nodes along the path
 *
 */
package edu.wpi.cs3733.programname.pathfind.entity;

import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AStar {
    List<NodeData> allNodes;
    List<EdgeData> allEdges;

    // We need a HashMap so we can access StarNodes via the corresponding nodeID
    HashMap<String, StarNode> allStarNodes = new HashMap<>();
    List<NodeData> finalList;

    /**
     * constructor for AStar
     * @param nodes list of nodes
     * @param edges list of edges
     * @param startID starting location
     * @param goalID destination location
     */
    public AStar(List<NodeData> nodes, List<EdgeData> edges, String startID, String goalID) {
        this.allEdges = edges;
        this.allNodes = nodes;
        this.init();
        this.finalList = this.pathFind(startID, goalID);
    }

    // Call to update the whole list of StarNodes

    /**
     * initializes A*
     */
    private void init() {
        System.out.println("Initializing A*");
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

    /**
     * calculates path from start to finish
     * @param startID starting location
     * @param goalID end location
     * @return list of nodes that make up the path
     */
    private List<NodeData> pathFind(String startID, String goalID) {
        // TODO: Throw a "No such node" exception
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
            frontier.removeFirst(); // pop the priority queue
            if(current.getXCoord() == goal.getXCoord() && current.getYCoord() == goal.getYCoord()) {
                // If we are at the goal, we need to backtrack through the shortest path
                System.out.println("At target!");
                finalPath.add(current); // we have to add the goal to the path before we start backtracking
                while(!(current.getXCoord() == start.getXCoord() && current.getYCoord() == start.getYCoord())) {
                    finalPath.add(current.getPreviousNode());
                    current = current.getPreviousNode();
                    System.out.println(current.getNodeID());
                }
                return finalPath;
            }
            else {
                // we need to get all the neighbor nodes, identify their costs, and put them into the queue
                LinkedList<StarNode> neighbors = current.getNeighbors();
                // we also need to remove the previous node from the list of neighbors because we do not want to backtrack
                neighbors.remove(current.getPreviousNode());
                for (StarNode newnode : neighbors) {
                    int nodePlace = this.listContainsId(frontier, newnode);
                    if(nodePlace > -1) {
                        System.out.println("The new node costs " + (actionCost(newnode) + distanceToGo(newnode, goal)));
                        if (frontier.get(nodePlace).getF() > actionCost(newnode) + distanceToGo(newnode, goal)) {
                            System.out.println("Here");
                            frontier.remove(frontier.get(nodePlace));
                            newnode.setPreviousNode(current);
                            frontier.add(newnode);
                            newnode.setF(actionCost(newnode) + distanceToGo(newnode, goal));
                        }
                    }
                    else {
                        newnode.setPreviousNode(current);
                        frontier.add(newnode);
                        newnode.setF(actionCost(newnode) + distanceToGo(newnode, goal));
                    }
                    // this is where the node is put in the right place in the queue
                    Collections.sort(frontier);
                }
            }
        }
        return null; // TODO: throw a "Path not found" exception
    }

    /**
     * calculates the cost of moving from starting node to given node (the bigger the cost, the worse)
     * @param node starnode is a node along possible path
     * @return the "cost" of getting to the given starNode
     */
    private double actionCost(StarNode node) {
        StarNode previous = node.getPreviousNode();
        double xDist = previous.getXCoord() - node.getXCoord();
        double yDist = previous.getYCoord() - node.getYCoord();
        double totalDist = Math.sqrt(xDist*xDist + yDist*yDist);
        node.setG(previous.getG() + totalDist);
        return node.getG();
}


    // returns the pixel distance from the provided node to the destination node

    /**
     * calculates the distance from the one star node to another (the bigger the distance, the worse)
     * @param node a starting node on the possible path
     * @param goal a ending node on the possible path
     * @return the distance between the two nodes
     */
    private double distanceToGo(StarNode node, StarNode goal) {
        double xDist = goal.getXCoord() - node.getXCoord();
        double yDist = goal.getYCoord() - node.getYCoord();
        double distToGo = Math.sqrt(xDist*xDist + yDist*yDist);
        return distToGo;
    }

    public List<NodeData> getFinalList() {
        return finalList;
    }

    private int listContainsId(LinkedList<StarNode> listOfNodes, StarNode node) {
        for(int i = 0; i < listOfNodes.size(); i++) {
            if(node.getNodeID().equals(listOfNodes.get(i).getNodeID())) {
                System.out.println("The list contains node " + listOfNodes.get(i).getNodeID());
                System.out.println("Node " + listOfNodes.get(i).getNodeID() + " costs " + listOfNodes.get(i).getF());
                return i;
            }
        }
        return -1;
    }
}