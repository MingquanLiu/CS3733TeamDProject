package edu.wpi.cs3733.programname;


import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.database.*;
import edu.wpi.cs3733.programname.pathfind.PathfindingController;
import edu.wpi.cs3733.programname.servicerequest.ServiceRequestController;
import edu.wpi.cs3733.programname.servicerequest.entity.Employee;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManageController {

    private DBConnection dbConnection;
    private PathfindingController pathfindingController;
    private DatabaseQueryController dbQueryController;
    private DatabaseModificationController dbModController;
    private ServiceRequestController serviceController;

    public ManageController() {
        this.dbConnection = new DBConnection();
        dbConnection.setDBConnection();

        this.pathfindingController = new PathfindingController();
        this.dbQueryController = new DatabaseQueryController(this.dbConnection);
        this.dbModController = new DatabaseModificationController(this.dbConnection);
        this.serviceController = new ServiceRequestController();
        CsvReader mCsvReader = new CsvReader();
        DBTables.createNodesTables(dbConnection);
        mCsvReader.insertNodes(dbConnection.getConnection(),mCsvReader.readNodes(dbConnection.getConnection()));
        DBTables.createEdgesTables(dbConnection);           // Makes nodes table
        mCsvReader.insertEdges(dbConnection.getConnection(),mCsvReader.readEdges(dbConnection.getConnection()));
//        DBTables.createEdgesTables(dbConnection);

    }

    public List<NodeData> startPathfind(String startId, String goalId) {
        List<NodeData> allNodes = dbQueryController.getAllNodeData();
        List<EdgeData> allEdges = dbQueryController.getAllEdgeData();
        List<NodeData> finalPath = this.pathfindingController.initializePathfind(allNodes, allEdges, startId, goalId);
        System.out.println(finalPath.get(0).getNodeID() + " to " + finalPath.get(finalPath.size() -1));
        return finalPath;
    }

    public NodeData getNodeData(String nodeId) {
        return this.dbQueryController.queryNodeById(nodeId);
    }

    public EdgeData getEdgeData(String edgeId) {
        return this.dbQueryController.queryEdgeById(edgeId);
    }

    public List<NodeData> getAllNodeData() {
        return this.dbQueryController.getAllNodeData();
    }

    public List<EdgeData> getAllEdgeData() {
        return this.dbQueryController.getAllEdgeData();
    }

    public List<NodeData> queryNodeByType(String nodeType) {
        return this.dbQueryController.queryNodeByType(nodeType);
    }

    public void addNode(NodeData data) {
        this.dbModController.addNode(data);
    }

    public void deleteNode(NodeData data) {
        this.dbModController.deleteNode(data);
    }

    public void editNode(NodeData data) {
        this.dbModController.editNode(data);
    }

    public void sendServiceRequest(String type) {
        Employee emp = new Employee("me", 1, false);
        this.serviceController.createServiceRequest(emp, type);
    }
    public void addEdge(String nodeId1, String nodeId2){
        this.dbModController.addEdge(nodeId1,nodeId2);
    }
}

