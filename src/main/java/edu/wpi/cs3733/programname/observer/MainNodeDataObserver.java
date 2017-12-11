package edu.wpi.cs3733.programname.observer;

import edu.wpi.cs3733.programname.boundary.MapAdminController;
import edu.wpi.cs3733.programname.boundary.NewMainUIController;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.io.IOException;

public class MainNodeDataObserver extends Observer {
    private NewMainUIController mainController;
    private NodeData nodeData;

    public MainNodeDataObserver(NewMainUIController mainController, NodeData nodeData) {
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
        //mainController.passNodeData(nodeData);
    }

    public NewMainUIController getMainController() {
        return mainController;
    }

    public void updateNodeInDb() {
        //mainController.getManager().editNode(nodeData);
    }

    public void showNodesOrEdges() {
        mainController.showNodesOrEdges();
    }
}
