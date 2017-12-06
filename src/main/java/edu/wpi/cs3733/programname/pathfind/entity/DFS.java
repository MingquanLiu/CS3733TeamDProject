/**
 * AStar class: create an AStar object each time you want to find a new path
 * Then call AStar.getFinalList() to get the list of nodes along the path
 */
package edu.wpi.cs3733.programname.pathfind.entity;

import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.util.LinkedList;
import java.util.List;

public class DFS extends PathfindingTemplate {
    /**
     * constructor for DFS
     *
     * @param nodes   list of nodes
     * @param edges   list of edges
     * @param startID starting location
     * @param goalID  destination location
     */
    public DFS(List<NodeData> nodes, List<EdgeData> edges, String startID, String goalID) throws NoPathException {
        this.allEdges = edges;
        this.allNodes = nodes;
        this.startID = startID;
        this.goalID = goalID;
    }

    /**
     * calculates path from start to finish
     *
     * @return list of nodes that make up the path
     */
    List<NodeData> pathFind() throws NoPathException {
        System.out.println("Starting DFS");
        
        frontier.add(start);

        while (!frontier.isEmpty()) {
            StarNode current = frontier.getFirst();
            current.visit();
            frontier.removeFirst(); // pop the priority queue
            if (current.getXCoord() == goal.getXCoord() && current.getYCoord() == goal.getYCoord()) {
                // If we are at the goal, we need to backtrack through the shortest path
                System.out.println("At target!");
                System.out.println(current.getNodeID());
                finalList.add(current); // we have to add the goal to the path before we start backtracking
                while (!(current.getXCoord() == start.getXCoord() && current.getYCoord() == start.getYCoord())) {
                    finalList.add(current.getPreviousNode());
                    current = current.getPreviousNode();
                    System.out.println(current.getNodeID());
                }
                return finalList;
            } else {
                // we need to get all the neighbor nodes, identify their costs, and put them into the queue
                LinkedList<StarNode> neighbors = current.getNeighbors();
                // we also need to remove the previous node from the list of neighbors because we do not want to backtrack
                neighbors.remove(current.getPreviousNode());
                for (StarNode newnode : neighbors) {
                    if (!newnode.isVisited() && !newnode.isonlist()) {
                        newnode.addtolist();
                        newnode.setPreviousNode(current);
                        frontier.addFirst(newnode);
                    }
                }
            }
        }
        throw new NoPathException(start.getLongName(), goal.getLongName());
    }
}