package edu.wpi.cs3733.programname.servicerequest;

import edu.wpi.cs3733.programname.commondata.servicerequestdata.*;
import edu.wpi.cs3733.programname.database.DBConnection;
import edu.wpi.cs3733.programname.database.QueryMethods.EmployeesQuery;
import edu.wpi.cs3733.programname.database.QueryMethods.ServiceRequestsQuery;
import edu.wpi.cs3733.programname.commondata.Employee;

public class ServiceRequestController {
    private DBConnection dbConnection;
    private EmployeesQuery queryEmployee = new EmployeesQuery(dbConnection);
    private ServiceRequestsQuery queryServiceRequest = new ServiceRequestsQuery(dbConnection);
    public ServiceRequestController(DBConnection dbConnection, EmployeesQuery queryEmployee, ServiceRequestsQuery queryServiceRequest) {
        this.dbConnection = dbConnection;
        this.queryEmployee = queryEmployee;
        this.queryServiceRequest = queryServiceRequest;
    }

    public String requestReport(ServiceRequest request){
        return request.toString();
    }

    public void sendEmailToEmployee(ServiceRequest request, Employee receiver){
        String recipient = receiver.getEmail();
        String content = this.requestReport(request);
        ServiceRequestMessage email = new ServiceRequestMessage(recipient,content);
        email.sendMessage();
    }

    public void sendInterpreterRequest(InterpreterRequest request, String receiver){
        String content = this.requestReport(request) + request.toString();
        ServiceRequestMessage email = new ServiceRequestMessage(receiver,content);
        email.sendMessage();
    }

    public void sendMaintenanceRequest(MaintenanceRequest request, String receiver){
        String content = this.requestReport(request) + request.toString();
        ServiceRequestMessage email = new ServiceRequestMessage(receiver,content);
        email.sendMessage();
    }

    public void sendTransportationRequest(TransportationRequest request, String receiver){
        String content = this.requestReport(request) + request.toString();
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
