package edu.wpi.cs3733.programname.database;
import edu.wpi.cs3733.programname.commondata.*;
import edu.wpi.cs3733.programname.commondata.servicerequestdata.InterpreterRequest;
import edu.wpi.cs3733.programname.commondata.servicerequestdata.MaintenanceRequest;
import edu.wpi.cs3733.programname.commondata.servicerequestdata.TransportationRequest;
import edu.wpi.cs3733.programname.database.ModificationMethods.*;

import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.Employee;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.commondata.servicerequestdata.ServiceRequest;
import edu.wpi.cs3733.programname.database.ModificationMethods.EdgesMethod;
import edu.wpi.cs3733.programname.database.ModificationMethods.EmployeesMethod;
import edu.wpi.cs3733.programname.database.ModificationMethods.NodesMethod;
import edu.wpi.cs3733.programname.database.ModificationMethods.ServiceRequestsMethod;


public class DatabaseModificationController {
    private NodesMethod nodesMethod;
    private EdgesMethod edgesMethod;
    private EmployeesMethod employeesMethod;
    private InterpreterMethod interpreterMethod;
    private ServiceRequestsMethod serviceRequestsMethod;
    private InterpreterRequestsMethod interpreterRequestsMethod;
    private MaintenanceRequestsMethod maintenanceRequestMethod;
    private TransportationRequestMethod transportationRequestMethod;
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
        maintenanceRequestMethod = new MaintenanceRequestsMethod(conn);
        transportationRequestMethod = new TransportationRequestMethod(conn);
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

    public void deleteEmployee(Employee employee){
        employeesMethod.deleteEmployee(employee);
    }

    // add interpreter employee
    public void addInterpreter(Interpreter interpreter){
        employeesMethod.addEmployee(interpreter);
        interpreterMethod.addInterpreter(interpreter);
    }

    // add interpreter skill(language) to an employee
    public void addLanguageToInterpreter(String interpreter, String language){
        interpreterMethod.addInterpreterLanguage(interpreter,language);
    }

    public void removeLanguageFromInterpreter(String interpreter, String language) {
        interpreterMethod.removeInterpreterLanguage(interpreter, language);
    }

    // delete interpreter employee
    public void deleteInterpreter(Interpreter interpreter){
        interpreterMethod.deleteInterpreter(interpreter);
        employeesMethod.deleteEmployee(interpreter);
    }

    public void editEmployee(Employee employee) {
        employeesMethod.editEmployee(employee);
    }

    //Service Request Modification
    public void addServiceRequest(ServiceRequest serviceRequest){
        serviceRequestsMethod.addServiceRequest(serviceRequest);
    }

    public void addInterpreterRequest(InterpreterRequest interpreterRequest){
        serviceRequestsMethod.addServiceRequest(interpreterRequest);
        interpreterRequestsMethod.addInterpreterRequest(interpreterRequest);
    }

    public void deleteInterpreterRequest(InterpreterRequest interpreterRequest){
        interpreterRequestsMethod.deleteInterpreterRequest(interpreterRequest);
        serviceRequestsMethod.deleteServiceRequest(interpreterRequest);
    }

    public void addMaintenanceRequest(MaintenanceRequest maintenanceRequest){
        serviceRequestsMethod.addServiceRequest(maintenanceRequest);
        maintenanceRequestMethod.addMaintenanceRequest(maintenanceRequest);
    }

    public void deleteMaintenanceRequest(MaintenanceRequest maintenanceRequest){
        maintenanceRequestMethod.deleteMaintenanceRequest(maintenanceRequest);
        serviceRequestsMethod.deleteServiceRequest(maintenanceRequest);
    }

    public void addTransportationRequest(TransportationRequest transportationRequest){
        serviceRequestsMethod.addServiceRequest(transportationRequest);
        transportationRequestMethod.addTransportationRequest(transportationRequest);
    }

    public void deleteTransportationRequest(TransportationRequest transportationRequest){
        transportationRequestMethod.deleteTransportationRequest(transportationRequest);
        serviceRequestsMethod.deleteServiceRequest(transportationRequest);
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
