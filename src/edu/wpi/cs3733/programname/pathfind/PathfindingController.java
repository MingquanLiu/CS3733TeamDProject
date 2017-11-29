package edu.wpi.cs3733.programname.pathfind;

import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.pathfind.PathStrategies.HandicappedPath;
import edu.wpi.cs3733.programname.pathfind.entity.AStar;
import edu.wpi.cs3733.programname.pathfind.PathStrategies.StandardPath;
import edu.wpi.cs3733.programname.pathfind.entity.NoPathException;
import edu.wpi.cs3733.programname.pathfind.entity.PathFinderFacade;

import javax.xml.soap.Node;
import java.util.LinkedList;
import java.util.List;

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
                                             String endNode, Boolean handicapped, searchType type) {
        // ManageController manager = new ManageController();
        // List<EdgeData> allEdges = manager.getAllEdgeData();
        //something about getEdges()
        try {
            List<NodeData> finalPath = new LinkedList<NodeData>();
            List<EdgeData> currentList = allEdges;
            // if start and end are on same floor, filter out elevators
            // and proceed with search
            if (issamefloor(allNodes, startNode, endNode)){
                currentList = sameFloorPath(currentList);
                PathFinderFacade onefloorpath = new PathFinderFacade(allNodes, currentList, startNode, endNode);
                if (type == searchType.ASTAR) {
                    finalPath.addAll(onefloorpath.findAstarPath());
                }
                else if (type == searchType.DFS) {
                    finalPath.addAll(onefloorpath.findDfsPath());
                }
                else if (type == searchType.BFS) {
                    finalPath.addAll(onefloorpath.findBfsPath());
                }
                else if (type == searchType.DIJKSTRA) {
                    finalPath.addAll(onefloorpath.findDijkstraPath());
                }
                return finalPath;
            }

            // else check to see if handicap  (because it is a multifloor search)
            // then create 2 seperate pathfinding entitys
            // one from start node to nearest elevator
            // one from that elevator to goal node
            else {
            if(handicapped) currentList = filterPath(allEdges);
            EdgeData intermediateEdge = (findIntermediateNodes(allNodes, currentList,  startNode , endNode));
            String intermediateNode1 = intermediateEdge.getStartNode();
            String intermediateNode2 = intermediateEdge.getEndNode();
            PathFinderFacade startpath = new PathFinderFacade(allNodes, currentList, startNode, intermediateNode1);
            PathFinderFacade endpath = new PathFinderFacade(allNodes, currentList, intermediateNode2, endNode);

            if (type == searchType.ASTAR) {
                finalPath.addAll(startpath.findAstarPath());
                finalPath.addAll(endpath.findAstarPath());
            }
            else if (type == searchType.DFS) {
                finalPath.addAll(startpath.findDfsPath());
                finalPath.addAll(endpath.findDfsPath());
            }
            else if (type == searchType.BFS) {
                finalPath.addAll(startpath.findBfsPath());
                finalPath.addAll(endpath.findBfsPath());
            }
            else if (type == searchType.DIJKSTRA) {
                finalPath.addAll(startpath.findDijkstraPath());
                finalPath.addAll(endpath.findDijkstraPath());
            }
            return finalPath;
        }




        } catch (NoPathException npe) {
            // Add exception later
        }

        return null;
    }

    // if the pathfinding needs to make handicapped path, remove all the stairs
    private List<EdgeData> filterPath(List<EdgeData> edges) {
        List<EdgeData> handicappedPath = new LinkedList<EdgeData>();
        for(EdgeData e: edges) {
            if(!e.getStartNode().contains("STAI") && !e.getEndNode().contains("STAI")) {
                handicappedPath.add(e);
            }
        }
        return handicappedPath;
    }


    //if the pathfinding needs to find paths on the same floor, take all of the stairs and elevators out
    // of the list of edges
    private List<EdgeData> sameFloorPath(List<EdgeData> edges) {
        List<EdgeData> handicappedPath = new LinkedList<EdgeData>();
        for(EdgeData e: edges) {
            if(!e.getStartNode().contains("STAI") && !e.getEndNode().contains("STAI")) {
                handicappedPath.add(e);
            }
            if(!e.getStartNode().contains("ELEV") && !e.getEndNode().contains("ELEV")) {
                handicappedPath.add(e);
            }
        }
        return handicappedPath;
    }

    // returns true if the startnode and endnode are on the same floor
    private Boolean issamefloor (List<NodeData> allNodes, String StartNode, String Endnode){
        return (pullNode(allNodes , StartNode).getFloor().equals(pullNode(allNodes , Endnode).getFloor()));
    }

    // returns the node with the given id from the list of nodes
    private NodeData pullNode (List<NodeData> allNodes, String nodeID){
        for (NodeData N : allNodes){
            if (N.getNodeID().equals(nodeID)) return N;
        }
        return null;  //if node doesnt exist return nothing
    }


    // returns a list of all nodes that are elevators from the provided list of nodes
    private List<NodeData> listofElevators(List<NodeData> allNodes){
        List<NodeData> Elevators = new LinkedList<NodeData>();
        for(NodeData N : allNodes){
            if (N.getNodeID().contains("ELEV")) Elevators.add(N);
        }
        return Elevators;
    }

    //@TODO FINISH THIS METHOD BECAUSE YOU DONT UNDERSTAND CODING EVEN A LITTLE BIT
    //@TODO YOU NEED A COMPARATOR FOR NODEDATA THAT RETURNS THE RAW DISTANCE VALUE OR SOMETHING LIKE THAT
    // Returns a linked list of all the providedNodes, sorted by nearest to startNode
    private List<NodeData> sortByNearestNode(List<NodeData> providedNodes, NodeData startNode){
        List<NodeData> Sorted = new LinkedList<NodeData>();
        return Sorted;
    }

    // returns a list off all edges that contain the provided nodeID
    private  List<EdgeData> relevantedges(List<EdgeData> providededges, String searchNodeID){
        List<EdgeData> listofEdges = new LinkedList<EdgeData>();
        for (EdgeData edgeN: providededges){
            if ((edgeN.getStartNode().equals(searchNodeID)) || (edgeN.getEndNode().equals(searchNodeID)) )listofEdges.add(edgeN);
        }
        return listofEdges;
    }


    // the intermediate nodes are the startnode and endnode in the elevator edge with the same floor
    private EdgeData findIntermediateNodes(List<NodeData> allNodes, List<EdgeData> allEdges, String startnodeID , String endnodeID){
        NodeData StartNode = pullNode(allNodes, startnodeID);

        // GET A LIST OF ALL ELEVATORS ON THE SAME FLOOR AS THE START NODE, sorted by proximity
        List<NodeData> SortedElevators = sortByNearestNode(listofElevators(allNodes) , StartNode);

        // Iterate through the list of elevators
        for (NodeData ElevatorIterator: SortedElevators){

        // create a list of all edges that the elevator has
            List<EdgeData> ElevatorEdges = relevantedges(allEdges, ElevatorIterator.getNodeID());

        // iterate through the edges
            for (EdgeData EdgeIterator: ElevatorEdges ) {

        // if one of the edges is on the same floor as the start node
                if ((issamefloor(allNodes, EdgeIterator.getStartNode(), startnodeID)) || (issamefloor(allNodes, EdgeIterator.getEndNode(), startnodeID))) {

        // and one of the edges is on the same floor as the end node
                    if ((issamefloor(allNodes, EdgeIterator.getStartNode(), endnodeID)) || (issamefloor(allNodes, EdgeIterator.getEndNode(), endnodeID))) {

        // return that edge
                        return EdgeIterator;
                    }
                }
            }
        }
        return null;
    }

}