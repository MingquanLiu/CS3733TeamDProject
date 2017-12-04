package edu.wpi.cs3733.programname.database;
import edu.wpi.cs3733.programname.commondata.*;
import edu.wpi.cs3733.programname.database.ModificationMethods.*;
import edu.wpi.cs3733.programname.database.QueryMethods.EmployeesQuery;
import edu.wpi.cs3733.programname.database.QueryMethods.ServiceRequestsQuery;

import java.sql.Connection;


public class DatabaseModificationController {
    private NodesMethod nodesMethod;
    private EdgesMethod edgesMethod;
    private EmployeesMethod employeesMethod;
    private InterpreterMethod interpreterMethod;
    private ServiceRequestsMethod serviceRequestsMethod;
    private InterpreterRequestsMethod interpreterRequestsMethod;
    /**
     *
     * @param conn the connection to the database
     */
    public DatabaseModificationController(DBConnection conn){
        nodesMethod = new NodesMethod(conn);
        edgesMethod = new EdgesMethod(conn);
        employeesMethod = new EmployeesMethod(conn);
        interpreterMethod = new InterpreterMethod(conn);
        serviceRequestsMethod = new ServiceRequestsMethod(conn);
        interpreterRequestsMethod = new InterpreterRequestsMethod(conn);
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

    // add interpreter employee
    public void addInterpreter(Interpreter interpreter){
        employeesMethod.addEmployee(interpreter);
        interpreterMethod.addInterpreter(interpreter);
    }

    // delete interpreter employee
    public void deleteEmployee(Interpreter interpreter){
        Employee employee = interpreter.toEmployee();
        interpreterMethod.deleteInterpreter(interpreter);
        employeesMethod.deleteEmployee(employee);
    }

    //Service Request Modification
    public void addServiceRequest(ServiceRequest serviceRequest){
        serviceRequestsMethod.addServiceRequest(serviceRequest);
    }

    public void addInterpreterRequest(InterpreterRequest interpreterRequest){
        ServiceRequest serviceRequest = interpreterRequest.toServiceRequest();
        serviceRequestsMethod.addServiceRequest(serviceRequest);
        interpreterRequestsMethod.addInterpreterRequest(interpreterRequest);
    }

    public void deleteInterpreterRequest(InterpreterRequest interpreterRequest){
        ServiceRequest serviceRequest = interpreterRequest.toServiceRequest();
        interpreterRequestsMethod.deleteInterpreterRequest(interpreterRequest);
        serviceRequestsMethod.deleteServiceRequest(serviceRequest);
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
