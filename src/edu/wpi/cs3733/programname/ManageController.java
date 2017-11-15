package edu.wpi.cs3733.programname;


import edu.wpi.cs3733.programname.commondata.Edge;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.database.DatabaseModificationController;
import edu.wpi.cs3733.programname.database.DatabaseQueryController;
import edu.wpi.cs3733.programname.pathfind.PathfindingController;

import java.util.List;

public class ManageController {

    PathfindingController pathfindingController;
    DatabaseQueryController dbQueryController;
    DatabaseModificationController dbModController;

    public ManageController() {
        this.pathfindingController = new PathfindingController(this);
        this.dbQueryController = new DatabaseQueryController();
//        this.dbModController = new DatabaseModificationController();
    }

    public List<NodeData> startPathfind(String startId, String goalId) {
        return this.pathfindingController.astarPathfind(startId, goalId);
    }

    public NodeData getNodeData(String nodeId) {
        return null;
    }

    public List<NodeData> getAllNodeData() {
        return null;
    }

    public List<Edge> getAllEdgeData() {
        return null;
    }

    public List<NodeData> queryNodeByType(String nodeType) {
        return null;
    }

    public void addNode(NodeData data) {

    }

    public void deleteNode(NodeData data) {

    }

    public void editNode(NodeData data) {

    }

    public void sendServiceRequest() {

    }
}

