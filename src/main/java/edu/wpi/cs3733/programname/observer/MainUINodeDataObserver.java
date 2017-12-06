package edu.wpi.cs3733.programname.observer;

import edu.wpi.cs3733.programname.boundary.TestingController;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.io.IOException;

public class MainUINodeDataObserver extends Observer {
    private TestingController testingController;
    private NodeData nodeData;
    public MainUINodeDataObserver(TestingController testingController,NodeData nodeData){
        this.testingController = testingController;
        this.nodeData = nodeData;
    }
    public void setNodeData(NodeData nodeData){
        this.nodeData = nodeData;
    }
    @Override
    public void update() throws IOException {
        testingController.passNodeData(nodeData);
    }
}
