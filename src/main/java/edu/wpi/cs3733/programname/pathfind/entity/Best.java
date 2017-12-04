package edu.wpi.cs3733.programname.pathfind.entity;

import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.util.List;

public class Best extends PathfindingStrategyTemplate {

    public Best(List<NodeData> allNodes, List<EdgeData> allEdges, String startID, String goalID) {
        this.allNodes = allNodes;
        this.allEdges = allEdges;
        this.startID = startID;
        this.goalID = goalID;
    }

    List<NodeData> pathFind() throws NoPathException {

        return finalList;
    }
}
