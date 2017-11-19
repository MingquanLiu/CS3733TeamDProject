package edu.wpi.cs3733.programname.servicerequest.entity;

import edu.wpi.cs3733.programname.commondata.NodeData;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ServiceRequest {
    private Employee sender;
    private LocalDateTime time;
    private String type;
    private ArrayList<Employee> recipients;
    private NodeData location;
    private String description;

    /**
     * constructor for class ServiceRequest
     * @param sender the employee who sent the service request
     * @param time the date AND time of when service request was sent
     * @param type the type of service request
     */
    public ServiceRequest(Employee sender, LocalDateTime time, String type, ArrayList<Employee> recipients, NodeData location, String description) {
        this.sender = sender;
        this.time = time;
        this.type = type;
        this.recipients = recipients;
        this.location = location;
        this.description = description;
    }

    public void printRequest(){
        System.out.println("Sender: "+sender);
        System.out.println("Time Created: "+time);
        System.out.println("Type: "+type);
        System.out.println("Recipients: "+recipients);
        System.out.println("Location: "+location);
        System.out.println("Description: "+description);
        System.out.println("");
    }

}
