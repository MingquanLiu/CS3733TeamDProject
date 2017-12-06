package edu.wpi.cs3733.programname.database;

import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.Employee;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.commondata.servicerequestdata.ServiceRequest;
import edu.wpi.cs3733.programname.database.QueryMethods.*;
import edu.wpi.cs3733.programname.database.QueryMethods.EdgesQuery;
import edu.wpi.cs3733.programname.database.QueryMethods.EmployeesQuery;
import edu.wpi.cs3733.programname.database.QueryMethods.NodesQuery;
import edu.wpi.cs3733.programname.database.QueryMethods.ServiceRequestsQuery;

import java.util.ArrayList;
import java.util.List;


public class DatabaseQueryController {

    private NodesQuery nodesQuery;
    private EdgesQuery edgesQuery;
    private EmployeesQuery employeesQuery;
    private ServiceRequestsQuery serviceRequestsQuery;
    private InterpreterQuery interpreterQuery;
    private MaintenanceQuery maintenanceQuery;

    public DatabaseQueryController(DBConnection dbConnection) {
        nodesQuery = new NodesQuery(dbConnection);
        edgesQuery = new EdgesQuery(dbConnection);
        employeesQuery = new EmployeesQuery(dbConnection);
        serviceRequestsQuery = new ServiceRequestsQuery(dbConnection);
        interpreterQuery = new InterpreterQuery(dbConnection);
        maintenanceQuery = new MaintenanceQuery(dbConnection);
    }


    // Query By ID
    public NodeData queryNodeById(String nID) {
        return nodesQuery.queryNodeByID( nID);
    }


    public EdgeData queryEdgeById(String eID) {
        return edgesQuery.queryEdgeByID(eID);
    }


//    public List<ServiceRequestInfo> queryServiceRequesByID(DBConnection dbConnection, String srID) {
//        return ServiceRequestsQuery.queryByID(dbConnection, srID);
//    }


    // Get List of  all data
    public List<NodeData> getAllNodeData() {
        return nodesQuery.getAllNodeInfo();
    }


    public List<EdgeData> getAllEdgeData() {
        return edgesQuery.getAllEdgeInfo();
    }

    public List<EdgeData> queryEdgeDataByFloor(String floor) {
        return edgesQuery.queryEdgeByFloor(floor);
    }
//    public List<EmployeeInfo> getAllEmployeeInfo() {
//        return EmployeeQuery.getAllEmployeeInfo(dbConnection);
//    }

//
//    public List<ServiceRequestInfo> getAllServiceRequestInfo() {
//        return ServiceRequestQuery.getAllServiceRequest(dbConnection);
//    }




    // Query by type
    public List<NodeData> queryNodeByType(String nType) {
        return nodesQuery.queryNodeByType(nType);
    }

    public List<NodeData> queryNodeByFloor(String floor) { return nodesQuery.queryNodeByFloor(floor);}

    public List<NodeData> queryNodeByTypeFloor(String type, String floor) { return nodesQuery.getNodeByTypeAndFloor(type, floor);}

//    public List<ServiceRequestInfo> queryServiceRequesByType(DBConnection dbConnection, String type) {
//        return ServiceRequestQuery.queryByType(dbConnection, type);
//    }
//

//    // Query by status
//    public List<ServiceRequestInfo> queryServiceRequesByStatus(DBConnection dbConnection, String status) {
//        return ServiceRequestQuery.queryByStatus(dbConnection, status);
//    }

//
//    // Query by userName
//    public EmployeeInfo queryEmployeesByUserName(DBConnection dbConnection, String uName){
//        return EmployeeQuery.queryByUser(dbConnection, uName);
//    }

//
//    // Query by fullName
//    public EmployeeInfo queryEmployeesByFullName(DBConnection dbConnection, String fName, String mName, String lName){
//        return EmployeeQuery.queryByName(dbConnection, fName, mName, lName);
//    }

    //Employee Query
    public ArrayList<Employee> queryAllEmployees(){
        return employeesQuery.queryAllEmployees();
    }

    public ArrayList<Employee> queryEmployeesByType(String type){
        return employeesQuery.queryEmployeesByType(type);
    }

    public Employee queryEmployeeByUsername(String username){
        return employeesQuery.queryEmployeeByUsername(username);
    }

    public boolean login(String username, String password) {
        return employeesQuery.validateLogin(username, password);
    }

    // Interpreter Query
    public ArrayList<String> queryInterpreterSkillsbyUsername(String username){
        return interpreterQuery.queryInterpreterSkills(username);
    }

    // Maintenance Query
    public ArrayList<String> queryMaintenanceSkillsbyUsername(String username) {
        return maintenanceQuery.queryMaintenanceSkills(username);
    }

    //Service Request Query
    public ArrayList<ServiceRequest> queryAllServiceRequests(){
        return serviceRequestsQuery.queryAllServiceRequests();
    }

    public ArrayList<ServiceRequest> queryServiceRequestsByStatus(String status){
        return serviceRequestsQuery.queryServiceRequestsByStatus(status);
    }

    public ArrayList<ServiceRequest> queryServiceRequestsByType(String serviceType){
        return serviceRequestsQuery.queryServiceRequestsByType(serviceType);
    }

    public ServiceRequest queryServiceRequestsByID(String serviceID){
        return serviceRequestsQuery.queryServiceRequestsByID(serviceID);
    }

    public ArrayList<ServiceRequest>  queryRequestsByHandler(Employee emp) {
        ArrayList<ServiceRequest> allReqs = serviceRequestsQuery.queryAllServiceRequests();
        ArrayList<ServiceRequest> output = new ArrayList<>();
        for (ServiceRequest request: allReqs) {
            if(request.getReceiver() == emp.getUsername()) {
                output.add(request);
            }
        }
        return output;
    }







    // Writer Methods

}
