package edu.wpi.cs3733.programname.pathfind.entity;

import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Best extends PathfindingTemplate {

    public Best(List<NodeData> allNodes, List<EdgeData> allEdges, String startID, String goalID) {
        this.allNodes = allNodes;
        this.allEdges = allEdges;
        this.startID = startID;
        this.goalID = goalID;
    }

    List<NodeData> pathFind() throws NoPathException {
        System.out.println("Starting Best First search");

        for(NodeData node: allNodes) allStarNodes.get(node.getNodeID()).setF(10000);

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
                    if(newnode.getF() > distanceToNode(current, newnode)) {
                        newnode.setF(distanceToNode(current, newnode));
                        newnode.setPreviousNode(current);
                        frontier.add(newnode);
                    }

                    // this is where the node is put in the right place in the queue
                    Collections.sort(frontier);
                }
            }
        }
        throw new NoPathException(start.getLongName(), goal.getLongName());
    }

    /**
     * calculates the distance from the one star node to another (the bigger the distance, the worse)
     * @param node a starting node on the possible path
     * @param goal a ending node on the possible path
     * @return the distance between the two nodes
     */
    private double distanceToNode(StarNode node, StarNode goal) {
        double xDist = goal.getXCoord() - node.getXCoord();
        double yDist = goal.getYCoord() - node.getYCoord();
        double distToGo = Math.sqrt(xDist*xDist + yDist*yDist);
        return distToGo;
    }
}
