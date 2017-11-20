package edu.wpi.cs3733.programname.servicerequest;

import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.servicerequest.entity.Employee;
import edu.wpi.cs3733.programname.servicerequest.entity.ServiceRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ServiceRequestController {

    ArrayList<ServiceRequest> activeRequests;

    /**
     * constructor for class ServiceRequestController
     */
    public ServiceRequestController(){
        this.activeRequests = new ArrayList<>();
    }

    /**
     * create a new service request with given sender and type
     * @param sender the employee who sends the service request
     * @param type the type of the service request
     */
    public void createServiceRequest(Employee sender, String type, ArrayList<Employee> recipients, NodeData location, String description){
        // get the local time
        LocalDateTime time = LocalDateTime.now();
        ServiceRequest newRequest = new ServiceRequest(sender, time, type, recipients, location, description);
        this.activeRequests.add(newRequest);
    }

    public ArrayList<ServiceRequest> getActiveRequests() {
        return this.activeRequests;
    }

    public void delteServiceRequest(ServiceRequest request){
        this.activeRequests.remove(request);
    }

    public void printActiveRequests(){
        this.activeRequests.forEach(ServiceRequest::printRequest);
    }

    public ArrayList<Employee> addGroupRecipients(ServiceRequest request, String type){
        ArrayList<Employee> recipients = request.getGroupEmployees(type);
        return recipients;
    }

    public ArrayList<Employee> addRecipients(ServiceRequest request, Employee newRecipient){
        request.getRecipients().add(newRecipient);
        return request.getRecipients();
    }

    public void addServiceRequestInDB(Employee sender, String time, String type, ArrayList<Employee> recipients, NodeData location, String description){
        // todo
    }

    public void deleteServiceRequestInDB(ServiceRequest request){
        // todo
    }
}
