package edu.wpi.cs3733.programname.observer;

import edu.wpi.cs3733.programname.boundary.MapAdminController;
import edu.wpi.cs3733.programname.boundary.TestingController;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.commondata.HelperFunction;

import java.io.IOException;

public class MapAdminNodeDataObserver extends Observer {
    private MapAdminController mapAdminController;
    private NodeData nodeData;
    public MapAdminNodeDataObserver(MapAdminController mapAdminController, NodeData nodeData){
        this.mapAdminController = mapAdminController;
        this.nodeData = nodeData;
    }
    public void setNodeData(NodeData nodeData){
        this.nodeData = nodeData;
    }
    @Override
    public void update() throws IOException {
        mapAdminController.passNodeData(nodeData);
    }

    public void updateNodeInDb() {
        mapAdminController.getManager().editNode(nodeData);
    }

    public void disableScroll() {
        mapAdminController.disablePaneScroll();
    }

    public void enableScroll() {
        mapAdminController.enablePaneScroll();
    }
}
