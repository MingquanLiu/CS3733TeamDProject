package edu.wpi.cs3733.programname.pathfind;

import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.Edge;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.pathfind.PathStrategies.HandicappedPath;
import edu.wpi.cs3733.programname.pathfind.entity.AStar;
import edu.wpi.cs3733.programname.pathfind.PathStrategies.StandardPath;
import edu.wpi.cs3733.programname.pathfind.entity.NoPathException;
import edu.wpi.cs3733.programname.pathfind.entity.PathFinderFacade;

import javax.xml.soap.Node;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PathfindingController {


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
    public List<NodeData> initializePathfind(LinkedList<NodeData> allNodes, LinkedList<Edge> allEdges, String startNode, String endNode, LinkedList<Edge.Restriction> restrictions) {
        // ManageController manager = new ManageController();
        // List<Edge> allEdges = manager.getAllEdgeData();
        //something about getEdges()
        try {


        LinkedList<Edge> currentList = allEdges;
        if(restrictions.contains(Edge.Restriction.GENERAL))
        {
            StandardPath standardPath = new StandardPath();
            currentList = standardPath.getEdges(currentList);
        }
        if(restrictions.contains(Edge.Restriction.HANDICAPPED))
        {
            HandicappedPath handicappedPath = new HandicappedPath();
            currentList = handicappedPath.getEdges(currentList);
        }

        PathFinderFacade newPath = new PathFinderFacade(allNodes, currentList, startNode, endNode);

        if(restrictions.contains(Edge.Restriction.ASTAR))
        {
            return newPath.findAstarPath();
        }
        if(restrictions.contains(Edge.Restriction.DEPTH))
        {
            return newPath.findDfsPath();
        }
        if(restrictions.contains(Edge.Restriction.BREADTH))
        {
            return newPath.findBfsPath();
        }

        } catch (NoPathException npe){
        }
    }
}