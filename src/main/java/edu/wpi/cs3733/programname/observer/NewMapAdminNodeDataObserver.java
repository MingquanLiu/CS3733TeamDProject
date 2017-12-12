package edu.wpi.cs3733.programname.observer;

import edu.wpi.cs3733.programname.boundary.NewMapAdminUI;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.io.IOException;

public class NewMapAdminNodeDataObserver extends Observer {
    private NewMapAdminUI mainController;
    private NodeData nodeData;

    public NewMapAdminNodeDataObserver(NewMapAdminUI mainController, NodeData nodeData) {
        this.mainController = mainController;
        this.nodeData = nodeData;
    }

    public void setNodeData(NodeData nodeData) {
        this.nodeData = nodeData;
    }

    public NodeData getNodeData() {
        return this.nodeData;
    }

    @Override
    public void update() throws IOException {
        mainController.passNodeData(nodeData);
    }

    public NewMapAdminUI getMainController() {
        return mainController;
    }

    public void updateNodeInDb() {
        mainController.getManager().editNode(nodeData);
    }

    public void showNodesOrEdges() {
        mainController.showNodesOrEdges();
    }
}
