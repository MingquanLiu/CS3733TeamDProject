package edu.wpi.cs3733.programname.pathfind.entity;

import edu.wpi.cs3733.programname.commondata.Edge;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.util.List;

public class PathFinderFacade {

    private DFS dfs;
    private BFS bfs;
    private AStar astar;

    /**
     * Constructor:
     * @param nodes a list of all nodes to examine for a path
     * @param edges a list of all edges connecting the given nodes
     * @param startID the text ID of the starting node
     * @param goalID the text ID of the goal or target node
     */
    public PathFinderFacade(List<NodeData> nodes, List<Edge> edges, String startID, String goalID) {
        this.dfs = new DFS(nodes, edges, startID, goalID);
        this.bfs = new BFS(nodes, edges, startID, goalID);
        this.astar = new AStar(nodes, edges, startID, goalID);
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
}
