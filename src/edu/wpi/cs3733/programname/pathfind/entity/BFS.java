/**
 * AStar class: create an AStar object each time you want to find a new path
 * Then call AStar.getFinalList() to get the list of nodes along the path
 */
package edu.wpi.cs3733.programname.pathfind.entity;

import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Edge;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.pathfind.PathStrategyIF;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class BFS implements PathStrategyIF {
    List<NodeData> allNodes;
    List<Edge> allEdges;

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
    public BFS(List<NodeData> nodes, List<Edge> edges, String startID, String goalID) {
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
        for (NodeData node : allNodes) {
            // Creates the StarNodes
            allStarNodes.put(node.getId(), new StarNode(node));
        }

        for (Edge edge : allEdges) {
            StarNode node1 = allStarNodes.get(edge.getFirstNodeId());
            StarNode node2 = allStarNodes.get(edge.getSecondNodeId());

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

        while (!frontier.isEmpty()) {
            StarNode current = frontier.getFirst();
            current.visit();
            System.out.println("evaluating node: " + current.getId());
            frontier.removeFirst(); // pop the priority queue
            //System.out.println("current frontier size: " + frontier.size());
            if (current.getX() == goal.getX() && current.getY() == goal.getY()) {
                // If we are at the goal, we need to backtrack through the shortest path
                System.out.println("At target!, Begin Traceback");
                finalPath.add(current); // we have to add the goal to the path before we start backtracking
                while (!(current.getX() == start.getX() && current.getY() == start.getY())) {
                    finalPath.add(current.getPreviousNode());
                    current = current.getPreviousNode();
                    System.out.println(current.getId());
                }
                return finalPath;
            } else {
                // we need to get all the neighbor nodes, identify their costs, and put them into the queue
                LinkedList<StarNode> neighbors = current.getNeighbors();
                // we also need to remove the previous node from the list of neighbors because we do not want to backtrack
                neighbors.remove(current.getPreviousNode());
                for (StarNode newnode : neighbors) {
                    if (!newnode.isVisited() && !newnode.isonlist()) {
                        //System.out.println("add to frontier: " + newnode.getId());
                        newnode.addtolist();
                        newnode.setPreviousNode(current);
                        frontier.add(newnode);
                    }
                }
            }
        }


        return null;
    }

    public List<NodeData> getFinalList() {
        return finalList;
    }

}