package edu.wpi.cs3733.programname.pathfind.entity;

import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.util.List;

public class PathFinderFacade {

    private DFS dfs;
    private BFS bfs;
    private AStar astar;
    private Dijkstra dijkstra;

    /**
     * Constructor:
     * @param nodes a list of all nodes to examine for a path
     * @param edges a list of all edges connecting the given nodes
     * @param startID the text ID of the starting node
     * @param goalID the text ID of the goal or target node
     */
    public PathFinderFacade(List<NodeData> nodes, List<EdgeData> edges, String startID, String goalID)
            throws NoPathException, InvalidNodeException {
        boolean startFound = false;
        boolean goalFound = false;
        for(NodeData n: nodes) {
            if(n.getNodeID().equals(startID)){
                startFound = true;
            }
            else if (n.getNodeID().equals(goalID)) {
                goalFound = true;
            }
        }

        if (!startFound || !goalFound) {
            throw new InvalidNodeException("Invalid Parameters: NodeID does not exist!");
        }
        this.dfs = new DFS(nodes, edges, startID, goalID);
        this.bfs = new BFS(nodes, edges, startID, goalID);
        this.astar = new AStar(nodes, edges, startID, goalID);
        this.dijkstra = new Dijkstra(nodes, edges, startID, goalID);
    }

    /**
     * Finds the Breadth-First Search Path
     * @return list of nodes connecting start to goal
     */
    public List<NodeData> findBfsPath() {
        return bfs.getFinalList();
    }

    /**
     * Finds the Depth-First Search path
     * @return list of nodes connecting start to goal
     */
    public List<NodeData> findDfsPath() {
        return dfs.getFinalList();
    }

    /**
     * Finds the A* path
     * @return list of nodes connecting start to goal (this one is best)
     */
    public List<NodeData> findAstarPath() {
        return astar.getFinalList();
    }

    /**
     * Finds the path according to Dijkstra's algorithm
     * @return list of nodes connecting start to goal
     */
    public List<NodeData> findDijkstraPath() {
        return dijkstra.getFinalList();
    }
}
