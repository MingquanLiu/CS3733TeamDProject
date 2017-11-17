package edu.wpi.cs3733.programname.pathfind.entity;

import edu.wpi.cs3733.programname.commondata.Edge;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.util.List;

public class PathFinderFacade {
    private DFS dfs;
    private BFS bfs;
    private AStar astar;

    public PathFinderFacade(List<NodeData> nodes, List<Edge> edges, String startID, String goalID) {
        this.dfs = new DFS(nodes, edges, startID, goalID);
        this.bfs = new BFS(nodes, edges, startID, goalID);
        this.astar = new AStar(nodes, edges, startID, goalID);
    }

    public void findBfsPath() {
        bfs.getFinalList();
    }

    public void findDfsPath() {
        dfs.getFinalList();
    }

    public void findAstarPath() {
        astar.getFinalList();
    }
}
