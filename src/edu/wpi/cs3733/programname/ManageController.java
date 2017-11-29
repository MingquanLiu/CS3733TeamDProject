package edu.wpi.cs3733.programname;


import edu.wpi.cs3733.programname.commondata.*;
import edu.wpi.cs3733.programname.database.*;
import edu.wpi.cs3733.programname.pathfind.PathfindingController;
import edu.wpi.cs3733.programname.database.QueryMethods.EmployeesQuery;
import edu.wpi.cs3733.programname.pathfind.PathfindingController.searchType;
import edu.wpi.cs3733.programname.pathfind.entity.PathfindingMessage;
import edu.wpi.cs3733.programname.pathfind.entity.TextDirections;
import edu.wpi.cs3733.programname.servicerequest.ServiceRequestController;

import edu.wpi.cs3733.programname.database.QueryMethods.ServiceRequestsQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static edu.wpi.cs3733.programname.commondata.Constants.INTERPRETER_REQUEST;
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
        mCsvReader.insertEdges(dbConnection.getConnection(),mCsvReader.getListOfEdges(dbConnection.getConnection()));


    }

    public List<NodeData> startPathfind(String startId, String goalId, searchType pathfindType) {
        List<NodeData> allNodes = dbQueryController.getAllNodeData();
        List<EdgeData> allEdges = dbQueryController.getAllEdgeData();
        List<NodeData> finalPath = this.pathfindingController.initializePathfind(allNodes, allEdges, startId, goalId, false, ASTAR);
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

    public List<NodeData> queryNodeByFloor(String floor) {
        return this.dbQueryController.queryNodeByFloor(floor);
    }

    public List<ServiceRequest> getUnassignedRequests() {
        return this.dbQueryController.queryServiceRequestsByStatus(Constants.UNASSIGNED_REQUEST);
    }

    public List<ServiceRequest> getAssignedRequests() {
        return this.dbQueryController.queryServiceRequestsByStatus(Constants.ASSIGNED_REQUEST);
    }

    public List<ServiceRequest> getCompletedRequests() {
        return this.dbQueryController.queryServiceRequestsByStatus(Constants.COMPLETED_REQUEST);
    }

    public List<Employee> getAllEmployees() {
        return this.dbQueryController.queryAllEmployees();
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
        Employee emp = new Employee("me", "1","","Mike","Liu",false,INTERPRETER_REQUEST,"mikelmq99@gmail.com");
//        createServiceRequest(emp, type);
    }

    public List<Employee> getAllEmployees() {
        //return this.dbQueryController.queryAllEmployees();
        return null;
    }

    public List<ServiceRequest> getUnassignedRequests() {
        //return this.dbQueryController.queryServiceRequestsByStatus("unassigned");
        return null;
    }

    public List<ServiceRequest> getAssignedRequests() {
        //return this.dbQueryController.queryServiceRequestsByStatus("assigned");
        return null;
    }

    public List<ServiceRequest> getCompletedRequests() {
        //return this.dbQueryController.queryServiceRequestsByStatus("complete");
        return null;
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

    public void sendTextDirectionsEmail(List<NodeData> path, String recipient) {
        TextDirections textDirections = new TextDirections(path);
        PathfindingMessage msg = new PathfindingMessage(recipient, textDirections.getEmailMessageBody());
        msg.sendMessage();
    }

    public ServiceRequest createServiceRequest(String requester, String type, String location1, String location2, String description) {
        //generate random id
        Random randomID = new Random();
        int id = randomID.nextInt(1000) + 1;
        ServiceRequest newServiceRequest = new ServiceRequest(id, requester, type, location1, location2, description);
        //queryServiceRequest.addServiceRequest(newServiceRequest);
        return newServiceRequest;
    }

    public ArrayList<Employee> getInterpreterEmployees(){
        return employeesQuery.queryEmployeesByType("interpreter");
    }
    public ArrayList<ServiceRequest> getInterpreterRequest(){
        return serviceRequestsQuery.queryServiceRequestsByType("interpreter");
    }
}

