package edu.wpi.cs3733.programname.servicerequest;

import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.database.DBConnection;
import edu.wpi.cs3733.programname.database.QueryMethods.EmployeesQuery;
import edu.wpi.cs3733.programname.database.QueryMethods.ServiceRequestsQuery;
import edu.wpi.cs3733.programname.commondata.Employee;
import edu.wpi.cs3733.programname.commondata.ServiceRequest;
import edu.wpi.cs3733.programname.commondata.ServiceRequestMessage;

import java.util.ArrayList;
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

//    public ServiceRequest createServiceRequest(String requester, String type, String location1, String location2, String description) {
//        //generate random id
//        Random randomID = new Random();
//        int id = randomID.nextInt(1000) + 1;
//        ServiceRequest newServiceRequest = new ServiceRequest(id, requester, type, location1, location2, description);
//        //queryServiceRequest.addServiceRequest(newServiceRequest);
//        return newServiceRequest;
//    }

//    public void addServiceRequest(ServiceRequest newRequest){
//        queryServiceRequest.addServiceRequest(newRequest);
//    }
//
//    public void addEmployee(Employee newEmployee){
//        queryEmployee.addEmployee(newEmployee);
//    }
//
//    public ArrayList<Employee> getAllEmployees(){
//        return queryEmployee.queryAllEmployees();
//    }
//
//    public void printAllEmployees(){
//        System.out.print(queryEmployee.queryAllEmployees());
//    }
//

//
//    public void printInterpreterEmployees(){
//        System.out.print(queryEmployee.queryEmployeesByType("interpreter"));
//    }
//
//    public ArrayList<Employee> getMaintenanceEmployees(){
//        return queryEmployee.queryEmployeesByType("maintenance");
//    }
//
//    public void printMaintenanceEmployees(){
//        System.out.print(queryEmployee.queryEmployeesByType("maintenance"));
//    }
//
//    public ArrayList<Employee> getTransportationEmployees(){
//        return queryEmployee.queryEmployeesByType("transportation");
//    }
//
//    public void printTransportationEmployees(){
//        System.out.print(queryEmployee.queryEmployeesByType("transportation"));
//    }
//
//    public ArrayList<ServiceRequest> getAllServiceRequest(){
//        return queryServiceRequest.queryAllServiceRequests();
//    }
//
//    public void printAllServiceRequest(){
//        System.out.print(queryServiceRequest.queryAllServiceRequests());
//    }
//

//
//    public void printInterpreterRequest(){
//        System.out.print(queryServiceRequest.queryServiceRequestsByType("interpreter"));
//    }
//
//    public ArrayList<ServiceRequest> getMaintenanceRequest(){
//        return queryServiceRequest.queryServiceRequestsByType("maintenance");
//    }
//
//    public void printMaintenanceRequest(){
//        System.out.print(queryServiceRequest.queryServiceRequestsByType("maintenance"));
//    }
//
//    public ArrayList<ServiceRequest> getTransportationRequest(){
//        return queryServiceRequest.queryServiceRequestsByType("transportation");
//    }
//
//    public void printTransportationRequest(){
//        System.out.print(queryServiceRequest.queryServiceRequestsByType("transportation"));
//    }
//
//    public ArrayList<ServiceRequest> getUnhandledRequest(){
//        return queryServiceRequest.queryServiceRequestsByStatus("unhandled");
//    }
//
//    public void printUnhandledServiceRequest(){
//        System.out.print(queryServiceRequest.queryServiceRequestsByStatus("unhandled"));
//    }
//
//    public ArrayList<ServiceRequest> getHandledRequest(){
//        return queryServiceRequest.queryServiceRequestsByStatus("handled");
//    }
//
//    public void printHandledServiceRequest(){
//        System.out.print(queryServiceRequest.queryServiceRequestsByStatus("handled"));
//    }
//
//    public ArrayList<ServiceRequest> getCompletedRequest(){
//        return queryServiceRequest.queryServiceRequestsByStatus("completed");
//    }
//
//    public void printCompletedServiceRequest(){
//        System.out.print(queryServiceRequest.queryServiceRequestsByStatus("completed"));
//    }
//
//    public ArrayList<ServiceRequest> getRemovedRequest(){
//        return queryServiceRequest.queryServiceRequestsByStatus("removed");
//    }
//
//    public void printRemovedServiceRequest(){
//        System.out.print(queryServiceRequest.queryServiceRequestsByStatus("removed"));
//    }
//
//    public void handleServiceRequest(ServiceRequest request, String handler){
//        queryServiceRequest.handleServiceRequest(request, handler);
//    }
//
//    public ServiceRequest getServiceRequestByID(int srID){
//        return queryServiceRequest.queryServiceRequestsByID(srID);
//    }
//
//    public Employee getEmployeeByUsername(String username){
//        return queryEmployee.queryEmployeeByUsername(username);
//    }
//
//
//    public void completeServiceRequest(ServiceRequest request){
//        queryServiceRequest.completeServiceRequest(request);
//    }
//
//    public void removeServiceRequest(ServiceRequest request){
//        queryServiceRequest.removeServiceRequest(request);
//    }
//
//    public void deleteServiceRequest(ServiceRequest request){
//        queryServiceRequest.deleteServiceRequest(request);
//    }

    public String requestReport(ServiceRequest request){
        return request.toString();
    }

    public void sendEmailToEmployee(ServiceRequest request, Employee receiver){
        String recipient = receiver.getEmail();
        String content = this.requestReport(request);
        ServiceRequestMessage email = new ServiceRequestMessage(recipient,content);
        email.sendMessage();
    }

    public void sendEmailByUsername(ServiceRequest request, String receiver){
        String content = this.requestReport(request);
        ServiceRequestMessage email = new ServiceRequestMessage(receiver,content);
        email.sendMessage();
    }


    public void sendEmailByType(ServiceRequest request){
        String recipient = "";
        switch(request.getServiceType()){
            case "interpreter":
                recipient = "interpreterbwhospital@gmail.com";
                break;

            case "maintenance":
                recipient = "maintenancebwhospital@gmail.com";
                break;

            case "transportation":
                recipient = "transportationbwhospital@gmail.com";
                break;
        }
        String content = this.requestReport(request);
        ServiceRequestMessage email = new ServiceRequestMessage(recipient,content);
        email.sendMessage();
    }
}
