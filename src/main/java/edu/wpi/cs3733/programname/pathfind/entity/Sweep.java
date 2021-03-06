/**
 * AStar class: create an AStar object each time you want to find a new path
 * Then call AStar.getFinalList() to get the list of nodes along the path
 */
package edu.wpi.cs3733.programname.pathfind.entity;

import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.util.LinkedList;
import java.util.List;

public class Sweep extends PathfindingTemplate {
    // sweep search is modified BFS that searches outwards from the starting node and returns a path to
    // to the first node that matches the type of node that matches the goalID
    /**
     * constructor for AStar
     * @param nodes list of nodes
     * @param edges list of edges
     * @param startID starting location
     * @param goalID destination location
     */
    public Sweep(List<NodeData> nodes, List<EdgeData> edges, String startID, String goalID) throws NoPathException {
        this.allEdges = edges;
        this.allNodes = nodes;
        this.startID = startID;
        this.goalID = goalID;
    }

    /**
     * calculates path from start to finish
     * @return list of nodes that make up the path
     */
    List<NodeData> pathFind() throws NoPathException {
        System.out.println("Starting sweep for " + goalID);

        frontier.add(start);

        while (!frontier.isEmpty()) {
            StarNode current = frontier.getFirst();
            current.visit();
            System.out.println("evaluating node: " + current.getNodeID());
            frontier.removeFirst(); // pop the priority queue
            System.out.println("current frontier size: " + frontier.size());
            if ( current.getNodeType().equals(goalID) ) {
                // If we are at the goal, we need to backtrack through the shortest path
                System.out.println("At target!, Begin Traceback");
                finalList.add(current); // we have to add the goal to the path before we start backtracking
                while (!(current.getXCoord() == start.getXCoord() && current.getYCoord() == start.getYCoord())) {
                    finalList.add(current.getPreviousNode());
                    current = current.getPreviousNode();
                    System.out.println(current.getNodeID());
                }
                return finalList;
            } else {
                System.out.println("wrong node, adding neighbors ");
                // we need to get all the neighbor nodes, identify their costs, and put them into the queue
                LinkedList<StarNode> neighbors = current.getNeighbors();
                // we also need to remove the previous node from the list of neighbors because we do not want to backtrack
                neighbors.remove(current.getPreviousNode());
                for (StarNode newnode : neighbors) {
                    if (!newnode.isVisited() && !newnode.isonlist()) {
                        System.out.println("add to frontier: " + newnode.getNodeID());
                        newnode.addtolist();
                        newnode.setPreviousNode(current);
                        frontier.add(newnode);
                    }
                }
            }
        }
        throw new NoPathException(start.getLongName(), goal.getLongName());
    }
}