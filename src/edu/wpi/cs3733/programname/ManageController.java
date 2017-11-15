package edu.wpi.cs3733.programname;


import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.Edge;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.database.DBConnection;
import edu.wpi.cs3733.programname.database.DatabaseModificationController;
import edu.wpi.cs3733.programname.database.DatabaseQueryController;
import edu.wpi.cs3733.programname.pathfind.PathfindingController;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManageController {

    private HashMap<Coordinate, String> nodeIDs;
    private DBConnection dbConnection;
    private PathfindingController pathfindingController;
    private DatabaseQueryController dbQueryController;
    private DatabaseModificationController dbModController;

    public ManageController() {
        this.dbConnection = new DBConnection();
        dbConnection.setDBConnection();
        this.pathfindingController = new PathfindingController();
        this.dbQueryController = new DatabaseQueryController(this.dbConnection);
        this.dbModController = new DatabaseModificationController(this.dbConnection);
        List<NodeData> allNodes = dbQueryController.getAllNodeData();
    }

    public List<NodeData> startPathfind(String startId, String goalId) {
        List<NodeData> allNodes = dbQueryController.getAllNodeData();
        List<Edge> allEdges = dbQueryController.getAllEdgeData();
        List<NodeData> finalPath = this.pathfindingController.initializePathfind(allNodes, allEdges, startId, goalId);
        System.out.println(finalPath.get(0).getId() + " to " + finalPath.get(finalPath.size() -1));
        return finalPath;
    }

    public NodeData getNodeData(String nodeId) {
        return this.dbQueryController.queryNodeById(nodeId);
    }

    public Edge getEdgeData(String edgeId) {
        return this.dbQueryController.queryEdgeById(edgeId);
    }

    public List<NodeData> getAllNodeData() {
        return this.dbQueryController.getAllNodeData();
    }

    public List<Edge> getAllEdgeData() {
        return this.dbQueryController.getAllEdgeData();
    }

    public List<NodeData> queryNodeByType(String nodeType) {
        return this.dbQueryController.queryNodeByType(nodeType);
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

