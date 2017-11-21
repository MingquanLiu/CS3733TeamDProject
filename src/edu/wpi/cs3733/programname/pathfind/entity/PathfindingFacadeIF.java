package edu.wpi.cs3733.programname.pathfind.entity;
import edu.wpi.cs3733.programname.commondata.Edge;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.util.List;
public interface PathfindingFacadeIF {
    List<NodeData> getFinalList();
}