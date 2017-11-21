package edu.wpi.cs3733.programname.servicerequest;

import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.database.DBConnection;
import edu.wpi.cs3733.programname.servicerequest.entity.Employee;
import edu.wpi.cs3733.programname.servicerequest.entity.ServiceRequest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

public class ServiceRequestController {
    private DBConnection dbConnection;
    private EmployeesQuery queryEmployee = new EmployeesQuery(dbConnection);
    private ServiceRequestsQuery queryServiceRequest = new ServiceRequestsQuery(dbConnection);
    public ServiceRequestController(DBConnection dbConnection, EmployeesQuery queryEmployee, ServiceRequestsQuery queryServiceRequest) {
        this.dbConnection = dbConnection;
        this.queryEmployee = queryEmployee;
        this.queryServiceRequest = queryServiceRequest;
    }

    public void createServiceRequest(Employee requester, String type, NodeData location1, NodeData location2, String description) {
        //generate random id
        Random randomID = new Random();
        int id = randomID.nextInt(1000) + 1;
        Date date = new Date();
        Timestamp createdTime = new Timestamp(date.getTime());
        Timestamp handledTime = null;
        Timestamp completedTime = null;
        String status = "unhandled";
        Employee handler = null;

        ServiceRequest newServiceRequest = new ServiceRequest(id, requester, type, location1, location2, description, createdTime, handledTime, completedTime, status, handler);
        queryServiceRequest.addServiceRequest(newServiceRequest);
        //send email
    }


    public void printAllEmployees(){
        System.out.print(queryEmployee.queryAllEmployees());
    }

    public void printInterpreterEmployees(){
        System.out.print(queryEmployee.queryEmployeesByType("interpreter"));
    }

    public void printMaintenanceEmployees(){
        System.out.print(queryEmployee.queryEmployeesByType("maintenance"));
    }

    public void printTransportationEmployees(){
        System.out.print(queryEmployee.queryEmployeesByType("transportation"));
    }

    public void printAllServiceRequest(){
        System.out.print(queryServiceRequest.queryAllServiceRequests());
    }

    public void printInterpreterRequest(){
        System.out.print(queryServiceRequest.queryServiceRequestsByType("interpreter"));
    }

    public void printMaintenanceRequest(){
        System.out.print(queryServiceRequest.queryServiceRequestsByType("maintenance"));
    }

    public void printTransportationRequest(){
        System.out.print(queryServiceRequest.queryServiceRequestsByType("transportation"));
    }

    public void printUnhandledServiceRequest(){
        System.out.print(queryServiceRequest.queryServiceRequestsByStatus("unhandled"));
    }

    public void printHandledServiceRequest(){
        System.out.print(queryServiceRequest.queryServiceRequestsByStatus("handled"));
    }

    public void printCompletedServiceRequest(){
        System.out.print(queryServiceRequest.queryServiceRequestsByStatus("completed"));
    }

    public void printRemovedServiceRequest(){
        System.out.print(queryServiceRequest.queryServiceRequestsByStatus("removed"));
    }

    public void handleServiceRequest(ServiceRequest request, Employee handler){
        queryServiceRequest.handleServiceRequest(request, handler);
    }

    public void completeServiceRequest(ServiceRequest request){
        queryServiceRequest.completeServiceRequest(request);
    }

    public void removeServiceRequest(ServiceRequest request){
        queryServiceRequest.removeServiceRequest(request);
    }

    public void deleteServiceRequest(ServiceRequest request){
        queryServiceRequest.deleteServiceRequest(request);
    }
}
