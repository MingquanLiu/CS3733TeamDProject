/**
 * AStar class: create an AStar object each time you want to find a new path
 * Then call AStar.getFinalList() to get the list of nodes along the path
 *
 */
package edu.wpi.cs3733.programname.pathfind.entity;

import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.util.*;

public class AStar extends PathfindingStrategyTemplate {

    public AStar(List<NodeData> allNodes, List<EdgeData> allEdges, String startID, String goalID) {
        this.allNodes = allNodes;
        this.allEdges = allEdges;
        this.startID = startID;
        this.goalID = goalID;
    }

    /**
     * calculates path from start to finish
     * @return list of nodes that make up the path
     */
    @Override
    List<NodeData> pathFind() throws NoPathException {
        System.out.println("Starting A*");

        frontier.add(start);

        while(!frontier.isEmpty()) {
            StarNode current = frontier.getFirst();
            frontier.removeFirst(); // pop the priority queue
            if(current.getXCoord() == goal.getXCoord() && current.getYCoord() == goal.getYCoord()) {
                // If we are at the goal, we need to backtrack through the shortest path
                System.out.println("At target!");
                finalList.add(current); // we have to add the goal to the path before we start backtracking
                while(!(current.getXCoord() == start.getXCoord() && current.getYCoord() == start.getYCoord())) {
                    finalList.add(current.getPreviousNode());
                    current = current.getPreviousNode();
                    System.out.println(current.getNodeID());
                }
                return finalList;
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

                    // This fixes the problem with infinitely looping elevators (I hope)
                    if(current.getNodeType().equals("ELEV") && newnode.getNodeType().equals("ELEV")) {
                        for (Iterator<StarNode> iterator = newnode.neighbors.iterator(); iterator.hasNext();) {
                            StarNode newneighbor = iterator.next();
                            if (newneighbor.getNodeType().equals("ELEV")) {
                                // Remove the current element from the iterator and the list.
                                iterator.remove();
                            }
                        }
                    }
                    if(current.getNodeType().equals("STAI") && newnode.getNodeType().equals("STAI")) {
                        for (Iterator<StarNode> iterator = newnode.neighbors.iterator(); iterator.hasNext();) {
                            StarNode newneighbor = iterator.next();
                            if (newneighbor.getNodeType().equals("STAI")) {
                                // Remove the current element from the iterator and the list.
                                iterator.remove();
                            }
                        }
                    }
                    // this is where the node is put in the right place in the queue
                    Collections.sort(frontier);
                }
            }
        }
        throw new NoPathException(start.getLongName(), goal.getLongName());
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
        /*************************************************************
         * This is where the new elevator stuff was added
         **************************************************************/
//        if(node.getNodeType().equals("STAI")) {
//            distToGo = 4 * distToGo;// * Math.abs(Integer.parseInt(node.getFloor()) - Integer.parseInt(goal.getFloor()));
//        }
//        else if(node.getNodeType().equals("ELEV")) {
//            distToGo = 0 * distToGo;// * Math.abs(Integer.parseInt(node.getFloor()) - Integer.parseInt(goal.getFloor()));
//        }
        return distToGo;
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