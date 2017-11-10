package edu.wpi.cs3733.programname.servicerequest;

public class ServiceRequestController {
    /**
     * constructor for class ServiceRequestController
     */
    public ServiceRequestController(){ }

    /**
     * create a new service request with given sender and type
     * @param sender the employee who sends the service request
     * @param type the type of the service request
     */
    public createServiceRequest(Employee sender, RequestType type){
        // get the local time
        LocalDateTime time = LocalDateTime.now();
        ServiceRequest newRequest = new ServiceRequest(sender, time, type);
    }

}
