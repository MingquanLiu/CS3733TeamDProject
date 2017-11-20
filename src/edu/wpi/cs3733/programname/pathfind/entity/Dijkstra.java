package edu.wpi.cs3733.programname.pathfind.entity;

import edu.wpi.cs3733.programname.commondata.Edge;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.util.*;

public class Dijkstra implements PathfindingFacadeIF {
    List<NodeData> allNodes;
    List<Edge> allEdges;

    // We need a HashMap so we can access StarNodes via the corresponding nodeID
    HashMap<String, StarNode> allStarNodes = new HashMap<>();
    List<NodeData> finalList;

    /**
     * constructor for Dijkstra's algorithm
     * @param nodes list of nodes
     * @param edges list of edges
     * @param startID starting location
     * @param goalID destination location
     */
    public Dijkstra(List<NodeData> nodes, List<Edge> edges, String startID, String goalID) throws NoPathException {
        this.allEdges = edges;
        this.allNodes = nodes;
        this.init();
        this.finalList = this.pathFind(startID, goalID);
    }

    /**
     * initializes Dijkstra's algorithm
     */
    private void init() {
        System.out.println("Initializing A*");
        for (NodeData node : allNodes) {
            // Creates the StarNodes
            allStarNodes.put(node.getId(), new StarNode(node));
            allStarNodes.get(node.getId()).setF(10000);
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
    private List<NodeData> pathFind(String startID, String goalID) throws NoPathException {
        StarNode start = allStarNodes.get(startID);
        StarNode goal = allStarNodes.get(goalID);

        //list of all the nodes that are adjacent to nodes already explored
        LinkedList<StarNode> queue = new LinkedList<StarNode>();

        //list of all the nodes in the path from start to finish
        LinkedList<NodeData> finalPath = new LinkedList<NodeData>();

        start.setF(0);

        queue.add(start);

        while(!queue.isEmpty()) {
            StarNode current = queue.getFirst();
            queue.removeFirst(); // pop the priority queue
            if(current.getX() == goal.getX() && current.getY() == goal.getY()) {
                // If we are at the goal, we need to backtrack through the shortest path
                System.out.println("At target!");
                finalPath.add(current); // we have to add the goal to the path before we start backtracking
                while(!(current.getX() == start.getX() && current.getY() == start.getY())) {
                    finalPath.add(current.getPreviousNode());
                    current = current.getPreviousNode();
                    System.out.println(current.getId());
                }
                return finalPath;
            }
            else {
                // we need to get all the neighbor nodes, identify their costs, and put them into the queue
                LinkedList<StarNode> neighbors = current.getNeighbors();
                // we also need to remove the previous node from the list of neighbors because we do not want to backtrack
                neighbors.remove(current.getPreviousNode());
                for (StarNode newnode : neighbors) {
                    if(newnode.getF() > current.getF() + distanceToNode(current, newnode)) {
                        newnode.setF(current.getF() + distanceToNode(current, newnode));
                        newnode.setPreviousNode(current);
                        queue.add(newnode);
                    }

                    // this is where the node is put in the right place in the queue
                    Collections.sort(queue);
                }
            }
        }
        throw new NoPathException(startID, goalID);
    }

    // returns the pixel distance from the provided node to the destination node

    /**
     * calculates the distance from the one star node to another (the bigger the distance, the worse)
     * @param node a starting node on the possible path
     * @param goal a ending node on the possible path
     * @return the distance between the two nodes
     */
    private double distanceToNode(StarNode node, StarNode goal) {
        double xDist = goal.getX() - node.getX();
        double yDist = goal.getY() - node.getY();
        double distToGo = Math.sqrt(xDist*xDist + yDist*yDist);
        return distToGo;
    }

    public List<NodeData> getFinalList() {
        return finalList;
    }

}
