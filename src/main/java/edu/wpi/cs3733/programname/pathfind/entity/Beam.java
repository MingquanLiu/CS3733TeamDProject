package edu.wpi.cs3733.programname.pathfind.entity;

import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Beam extends PathfindingStrategyTemplate {
    int beamWidth = 3;

    public Beam(List<NodeData> allNodes, List<EdgeData> allEdges, String startID, String goalID) {
        this.allNodes = allNodes;
        this.allEdges = allEdges;
        this.startID = startID;
        this.goalID = goalID;
    }

    List<NodeData> pathFind() throws NoPathException {
        System.out.println("Starting Beam Search, beam width " + beamWidth);

        start.setF(distanceToGo(start));
        frontier.add(start);

        while (!frontier.isEmpty()) {
            StarNode current = frontier.getFirst();
            current.visit();
            System.out.println("evaluating node: " + current.getNodeID());
            frontier.removeFirst(); // pop the priority queue
            //System.out.println("current frontier size: " + frontier.size());
            if (current.getXCoord() == goal.getXCoord() && current.getYCoord() == goal.getYCoord()) {
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
                // we need to get all the neighbor nodes, identify their costs, and put them into the queue
                LinkedList<StarNode> neighbors = current.getNeighbors();
                // we also need to remove the previous node from the list of neighbors because we do not want to backtrack
                neighbors.remove(current.getPreviousNode());
                for (StarNode newnode : neighbors) {
                    if (!newnode.isVisited() && !newnode.isonlist()) {
                        newnode.setF(distanceToGo(newnode));
                        newnode.addtolist();
                        newnode.setPreviousNode(current);
                        frontier.add(newnode);
                    }
                }
                LinkedList<StarNode> sortList = new LinkedList<>(frontier);
                Collections.sort(sortList);
                if(frontier.size() > 3) {
                    // To change the beam width, change local beamWidth variable at the top of this file
                    for(int i = 0; i < beamWidth; i++) sortList.removeFirst();
                    for(StarNode node: sortList) frontier.remove(node);
                }

            }
        }
        throw new NoPathException(start.getLongName(), goal.getLongName());
    }

    /**
     * calculates the distance from the one star node to another (the bigger the distance, the worse)
     * @param node a starting node on the possible path
     * @return the distance between the two nodes
     */
    private double distanceToGo(StarNode node) {
        double xDist = goal.getXCoord() - node.getXCoord();
        double yDist = goal.getYCoord() - node.getYCoord();
        double distToGo = Math.sqrt(xDist*xDist + yDist*yDist);
        return distToGo;
    }
}
