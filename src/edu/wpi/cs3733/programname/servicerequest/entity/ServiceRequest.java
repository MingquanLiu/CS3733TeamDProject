package edu.wpi.cs3733.programname.servicerequest.entity;

public class ServiceRequest {
    private Employee sender;
    private LocalDateTime time;
    private String type;

    /**
     * constructor for class ServiceRequest
     * @param sender the employee who sent the service request
     * @param time the date AND time of when service request was sent
     * @param type the type of service request
     */
    public ServiceRequest(Employee sender, LocalDateTime time, String type) {
        this.sender = sender;
        this.time = time;
        this.type = type;
    }

}
