package edu.wpi.cs3733.programname.servicerequest;

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
    public void createServiceRequest(Employee sender, String type){
        // get the local time
        LocalDateTime time = LocalDateTime.now();
        ServiceRequest newRequest = new ServiceRequest(sender, time, type);
    }

    public ArrayList<ServiceRequest> getActiveRequests() {
        return this.activeRequests;
    }
}
