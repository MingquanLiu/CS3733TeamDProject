package edu.wpi.cs3733.programname.servicerequest.entity;

public class ServiceRequest {
    private Employee sender;
    private LocalDateTime date;
    private Enumeration<RequestType> type;

    /**
     * constructor for class ServiceRequest
     * @param sender the employee who sent the service request
     * @param date the date AND time of when service request was sent
     * @param type the type of service request
     */
    public ServiceRequest(Employee sender, LocalDateTime date, Enumeration<RequestType> type) {
        this.sender = sender;
        this.date = date;
        this.type = type;
    }

}
