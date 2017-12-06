package edu.wpi.cs3733.programname.boundary;

import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;

abstract class UIController {
    abstract void passNodeData(NodeData nodeData);
    abstract void passEdgeData(EdgeData edgeData);
}
