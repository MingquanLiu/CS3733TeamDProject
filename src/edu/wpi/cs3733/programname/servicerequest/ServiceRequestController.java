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
    private ServiceRequestsQuery queryEmployee = new ServiceRequestsQuery(dbConnection);
    private ServiceRequestsQuery queryServiceRequest = new ServiceRequestsQuery(dbConnection);
    public ServiceRequestController(DBConnection dbConnection, ServiceRequestsQuery queryEmployee, ServiceRequestsQuery queryServiceRequest) {
        this.dbConnection = dbConnection;
        this.queryEmployee = queryEmployee;
        this.queryServiceRequest = queryServiceRequest;
    }

    public void createServiceRequest(Employee requester, String type, NodeData location, String description) {
        Random randomID = new Random();
        int id = randomID.nextInt(1000) + 1;
        Date date = new Date();
        Timestamp createdTime = new Timestamp(date.getTime());
        Timestamp handledTime = null;
        Timestamp completedTime = null;
        String status = "unhandled";
        Employee handler = null;

        ServiceRequest newServiceRequest = new ServiceRequest(id, requester, type, location, description, createdTime, handledTime, completedTime, status, handler);
        queryServiceRequest.addServiceRequest(newServiceRequest);
    }


//    public ArrayList<Employee> addGroupRecipients(ServiceRequest request, String type){
//        ArrayList<Employee> recipients = new ArrayList<>();
//        return recipients;
//    }


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
