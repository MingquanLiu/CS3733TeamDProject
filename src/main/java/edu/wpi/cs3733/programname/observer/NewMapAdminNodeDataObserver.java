package edu.wpi.cs3733.programname.observer;

import edu.wpi.cs3733.programname.boundary.NewMapAdminUI;
import edu.wpi.cs3733.programname.commondata.NodeData;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

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
        mainController.showNodesOrEdges();
    }

    public void showNodesOrEdges() {
        mainController.showNodesOrEdges();
    }

    public void disableScroll() {
        mainController.disablePaneScroll();
    }

    public void enableScroll() {
        mainController.enablePaneScroll();
    }

    public ScrollPane getScrollPane(){
        return mainController.getPaneScroll();
    }

    public AnchorPane getAnchorPane(){
        return mainController.getAnchorPane();
    }


}
