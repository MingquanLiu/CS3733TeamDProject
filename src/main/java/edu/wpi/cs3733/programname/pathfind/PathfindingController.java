package edu.wpi.cs3733.programname.pathfind;

import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.pathfind.entity.*;

import javax.xml.soap.Node;
import java.util.*;

public class PathfindingController {

    public enum searchType {
        ASTAR, DFS, BFS, DIJKSTRA
    }
    private PathfindingStrategyTemplate pathFinder;

    /**
     * Takes in the starting and ending locations, and calls PathFindingStrategyTemplate to find the path between them
     * currently also takes linkedlists for nodedata and edges
     *
     * @param allNodes  - the list of all nodes
     * @param allEdges  - the list of all edges
     * @param startNode - the ID of the starting location for the path
     * @param endNode   - the ID of the end node, aka the destination, for the path
     * @return - result is the list of nodes efficiently connecting startNode to endNode
     */
    public List<NodeData> initializePathfind(List<NodeData> allNodes, List<EdgeData> allEdges, String startNode,
                                             String endNode, Boolean handicapped, searchType type)
            throws InvalidNodeException {

        try {

            List<NodeData> finalPath = new LinkedList<NodeData>();

            if (type == searchType.ASTAR) {
                pathFinder = new AStar(allNodes, allEdges, startNode, endNode);
                finalPath.addAll(pathFinder.getFinalList());
            } else if (type == searchType.DFS) {
                pathFinder = new DFS(allNodes, allEdges, startNode, endNode);
                finalPath.addAll(pathFinder.getFinalList());
            } else if (type == searchType.BFS) {
                pathFinder = new BFS(allNodes, allEdges, startNode, endNode);
                finalPath.addAll(pathFinder.getFinalList());
            } else if (type == searchType.DIJKSTRA) {
                pathFinder = new Dijkstra(allNodes, allEdges, startNode, endNode);
                finalPath.addAll(pathFinder.getFinalList());
            }
            return finalPath;
        } catch (NoPathException npe) {
            System.out.println(npe.fillInStackTrace());
            return null;
        }
    }

    // if the pathfinding needs to make handicapped path, remove all the stairs
    private List<EdgeData> filterPath(List<EdgeData> edges) {
        List<EdgeData> handicappedPath = new LinkedList<EdgeData>();
        for (EdgeData e : edges) {
            if (!e.getStartNode().contains("STAI") && !e.getEndNode().contains("STAI")) {
                handicappedPath.add(e);
            }
        }
        return handicappedPath;
    }
}