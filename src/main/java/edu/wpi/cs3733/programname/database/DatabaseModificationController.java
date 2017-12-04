package edu.wpi.cs3733.programname.database;
import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.Employee;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.commondata.ServiceRequest;
import edu.wpi.cs3733.programname.database.ModificationMethods.EdgesMethod;
import edu.wpi.cs3733.programname.database.ModificationMethods.EmployeesMethod;
import edu.wpi.cs3733.programname.database.ModificationMethods.NodesMethod;
import edu.wpi.cs3733.programname.database.ModificationMethods.ServiceRequestsMethod;
import edu.wpi.cs3733.programname.database.QueryMethods.EmployeesQuery;
import edu.wpi.cs3733.programname.database.QueryMethods.ServiceRequestsQuery;

import java.sql.Connection;


public class DatabaseModificationController {
    private NodesMethod nodesMethod;
    private EdgesMethod edgesMethod;
    private EmployeesMethod employeesMethod;
    private ServiceRequestsMethod serviceRequestsMethod;
    /**
     *
     * @param conn the connection to the database
     */
    public DatabaseModificationController(DBConnection conn){
        nodesMethod = new NodesMethod(conn);
        edgesMethod = new EdgesMethod(conn);
        employeesMethod = new EmployeesMethod(conn);
        serviceRequestsMethod = new ServiceRequestsMethod(conn);
    }

    //Node Modification
    public void addNode(NodeData data) {
        nodesMethod.insertNode(data);
    }


    public void editNode(NodeData data) {
        nodesMethod.modifyNode(data);
    }


    public void deleteNode(NodeData data){
        nodesMethod.removeNode(data);
    }

    //EdgeData Modification

    public void addEdge(String node1ID, String node2ID){
        edgesMethod.insertEdge(node1ID, node2ID);
    }


    public void editEdge(EdgeData data){
        edgesMethod.modifyEdge(data);
    }

    /**
     * the given edge is deleted from the database (the nodes that make up the edge still exist)
     * @param data the edge that we want to delete
     */
    public void deleteEdge(EdgeData data) {
        edgesMethod.removeEdge(data);
    }

    //Employee Modification
    public void addEmployee(Employee employee){
        employeesMethod.addEmployee(employee);
    }

    public void editEmployee(Employee employee) {
        employeesMethod.editEmployee(employee);
    }

    public void deleteEmployee(String username) {
        employeesMethod.deleteEmployee(username);
    }

    //Service Request Modification
    public void addServiceRequest(ServiceRequest serviceRequest){
        serviceRequestsMethod.addServiceRequest(serviceRequest);
    }
    public void handleServiceRequest(ServiceRequest serviceRequest, String receiver){
        serviceRequestsMethod.handleServiceRequest(serviceRequest,receiver);
    }

    public void completeServiceRequest(ServiceRequest serviceRequest){
        serviceRequestsMethod.completeServiceRequest(serviceRequest);
    }
    public void removeServiceRequest(ServiceRequest serviceRequest){
        serviceRequestsMethod.removeServiceRequest(serviceRequest);
    }
    public void deleteServiceRequest(ServiceRequest serviceRequest){
        serviceRequestsMethod.deleteServiceRequest(serviceRequest);
    }

//    // Reader Methods
//
//    public void updateCsvNodes(Connection conn){
//        mCsv.writeNodes(conn);
//    }
//
//    public void updateCsvEdges(Connection conn){
//        mCsv.writeEdges(conn);
//    }
//
//    public void updateCsvEmployees(Connection conn){
//        mCsv.writeEmployees(conn);
//    }
//
//    public void updateCsvServiceRequests(Connection conn){
//        mCsv.writeServiceRequests(conn);
//    }


    // get
}
