package edu.wpi.cs3733.programname;


import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.database.*;
import edu.wpi.cs3733.programname.pathfind.PathfindingController;
import edu.wpi.cs3733.programname.servicerequest.ServiceRequestController;

import java.util.List;
import static edu.wpi.cs3733.programname.database.DBTables.createAllTables;


import static edu.wpi.cs3733.programname.pathfind.PathfindingController.searchType.ASTAR;

public class ManageController {

    private DBConnection dbConnection;
    private PathfindingController pathfindingController;
    private DatabaseQueryController dbQueryController;
    private DatabaseModificationController dbModController;
    private ServiceRequestController serviceController;
    private EmployeesQuery employeesQuery;
    private ServiceRequestsQuery serviceRequestsQuery;

    public ManageController() {
        this.dbConnection = new DBConnection();
        dbConnection.setDBConnection();

        this.pathfindingController = new PathfindingController();
        this.dbQueryController = new DatabaseQueryController(this.dbConnection);
        this.dbModController = new DatabaseModificationController(this.dbConnection);
        this.serviceController = new ServiceRequestController(dbConnection, employeesQuery, serviceRequestsQuery);
        CsvReader mCsvReader = new CsvReader();
        createAllTables(dbConnection);
        mCsvReader.insertNodes(dbConnection.getConnection(),mCsvReader.getListOfNodes(dbConnection.getConnection()));
        mCsvReader.insertEdges(dbConnection.getConnection(),mCsvReader.readEdges(dbConnection.getConnection()));
//        DBTables.createEdgesTables(dbConnection);

    }

    public List<NodeData> startPathfind(String startId, String goalId) {
        List<NodeData> allNodes = dbQueryController.getAllNodeData();
        List<EdgeData> allEdges = dbQueryController.getAllEdgeData();
        List<NodeData> finalPath = this.pathfindingController.initializePathfind(allNodes, allEdges, startId, goalId, false, ASTAR);
        System.out.println(finalPath.get(0).getNodeID() + " to " + finalPath.get(finalPath.size() -1));

        return finalPath;
    }

    public NodeData getNodeData(String nodeId) {
        return this.dbQueryController.queryNodeById(dbConnection, nodeId);
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
        return this.dbQueryController.queryNodeByType(dbConnection, nodeType);
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

//    public List<Employee> queryEmployeeByRequestType(String requestType) {
//        return dbQueryController.queryEmployeesByType(requestType);
//    }

//    public void createServiceRequest(Employee sender, String type, String description, NodeData location1,
//                                     NodeData location2) {
//         serviceController.createServiceRequest(sender, type, null, location1,
//                location2, description);
//    }

//    public void assignServiceRequest(ServiceRequest request, Employee recipient) {
//        ArrayList<Employee> recipientList = new ArrayList<Employee>();
//        recipientList.add(recipient);
//        serviceController.assignRequest(request, recipientList);
//    }

//    public void assignServiceRequest(ServiceRequest request, String requestType) {
//        ArrayList<Employee> recipients = dbQueryController.queryEmployeesByType(requestType);
//        serviceController.assignRequest(request, recipients);
//    }

    public void addEdge(String nodeId1, String nodeId2){
        this.dbModController.addEdge(nodeId1,nodeId2);
    }
}

