package edu.wpi.cs3733.programname;


import edu.wpi.cs3733.programname.commondata.Edge;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.database.DBConnection;
import edu.wpi.cs3733.programname.database.DatabaseModificationController;
import edu.wpi.cs3733.programname.database.DatabaseQueryController;
import edu.wpi.cs3733.programname.pathfind.PathfindingController;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ManageController {

    DBConnection dbConnection;
    PathfindingController pathfindingController;
    DatabaseQueryController dbQueryController;
    DatabaseModificationController dbModController;
    List<NodeData> nodes;

    public ManageController() {
        this.dbConnection = new DBConnection();
        dbConnection.setDBConnection();

        this.pathfindingController = new PathfindingController();
        this.dbQueryController = new DatabaseQueryController(this.dbConnection);
        this.dbModController = new DatabaseModificationController(this.dbConnection);
        nodes = getAllNodeData();
    }

    public List<NodeData> startPathfind(String startId, String goalId) {
        List<NodeData> allNodes = dbQueryController.getAllNodeData();
        List<Edge> allEdges = dbQueryController.getAllEdgeData();
        return this.pathfindingController.initializePathfind(allNodes, allEdges, startId, goalId);
    }

    public NodeData getNodeData(String nodeId) {
        return this.dbQueryController.queryNodeById(nodeId);
    }

    public Edge getEdgeData(String edgeId) {
        return this.dbQueryController.queryEdgeById(edgeId);
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

