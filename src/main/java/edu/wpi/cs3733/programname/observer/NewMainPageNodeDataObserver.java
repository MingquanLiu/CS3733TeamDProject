package edu.wpi.cs3733.programname.observer;

import edu.wpi.cs3733.programname.boundary.NewMainPageController;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.io.IOException;

public class NewMainPageNodeDataObserver extends Observer {
    private NewMainPageController newMainPageController;
    private NodeData nodeData;

    public NewMainPageNodeDataObserver(NewMainPageController newMainPageController, NodeData nodeData) {
        this.newMainPageController = newMainPageController;
        this.nodeData = nodeData;
    }

    public void setNodeData(NodeData nodeData) {
        this.nodeData = nodeData;
    }

    @Override
    public void update() throws IOException {
        newMainPageController.passNodeData(nodeData);
    }
}
