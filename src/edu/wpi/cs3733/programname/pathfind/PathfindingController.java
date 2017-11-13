package edu.wpi.cs3733.programname.pathfind;

import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Edge;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.pathfind.entity.AStar;
import edu.wpi.cs3733.programname.pathfind.PathStrategies.StandardPath;

import java.util.List;

public class PathfindingController {

    ManageController manager;

    public PathfindingController(ManageController manager) {
        this.manager = manager;
    }

    public List<NodeData> astarPathfind(String startNodeId, String goalNodeId){
        AStar astar;
        List<NodeData> allNodes = manager.getAllNodeData();
        List<Edge> allEdges = manager.getAllEdgeData();
        astar = new AStar(startNodeId, goalNodeId, allNodes, allEdges);
        return astar.getFinalList();
    }
}