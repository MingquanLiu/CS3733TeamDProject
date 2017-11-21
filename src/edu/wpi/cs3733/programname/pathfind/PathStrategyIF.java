package edu.wpi.cs3733.programname.pathfind;
import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.util.List;
public interface PathStrategyIF {
    List<NodeData> getFinalList();
}
