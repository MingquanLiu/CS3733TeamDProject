package edu.wpi.cs3733.programname.boundary;

import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.io.IOException;

abstract class UIController {
    abstract void passNodeData(NodeData nodeData) throws IOException;
    abstract void passEdgeData(EdgeData edgeData);
}
