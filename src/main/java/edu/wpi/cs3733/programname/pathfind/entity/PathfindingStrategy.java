package edu.wpi.cs3733.programname.pathfind.entity;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.util.List;
public interface PathfindingStrategy {
    List<NodeData> getFinalList();
}