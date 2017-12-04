package edu.wpi.cs3733.programname.pathfind;

import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.pathfind.entity.AStar;
import edu.wpi.cs3733.programname.pathfind.entity.InvalidNodeException;
import edu.wpi.cs3733.programname.pathfind.entity.NoPathException;
import edu.wpi.cs3733.programname.pathfind.entity.PathFinderFacade;

import javax.xml.soap.Node;
import java.util.*;

public class PathfindingController {

    public enum searchType {
        ASTAR, DFS, BFS, DIJKSTRA
    }

    /**
     * Takes in the starting and ending locations, and calls PathFinderFacade to find the path between them
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
            PathFinderFacade pathFind = new PathFinderFacade(allNodes, allEdges, startNode, endNode);

            List<NodeData> finalPath = new LinkedList<NodeData>();
            List<EdgeData> currentList = allEdges;
            if (type == searchType.ASTAR) {
                finalPath.addAll(pathFind.findAstarPath());
            } else if (type == searchType.DFS) {
                finalPath.addAll(pathFind.findDfsPath());
            } else if (type == searchType.BFS) {
                finalPath.addAll(pathFind.findBfsPath());
            } else if (type == searchType.DIJKSTRA) {
                finalPath.addAll(pathFind.findDijkstraPath());
            }
            return finalPath;
        } catch (NoPathException npe) {
            System.out.println(npe.fillInStackTrace());
        }
        return null;

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