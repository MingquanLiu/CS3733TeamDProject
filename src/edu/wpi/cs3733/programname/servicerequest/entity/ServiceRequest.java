package edu.wpi.cs3733.programname.servicerequest.entity;

import edu.wpi.cs3733.programname.commondata.NodeData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ServiceRequest {
    private Employee sender;
    private LocalDateTime time;
    private String type;
    private ArrayList<Employee> recipients;
    private NodeData location1;
    private NodeData location2;
    private String description;

    /**
     * constructor for class ServiceRequest
     * @param sender the employee who sent the service request
     * @param time the date AND time of when service request was sent
     * @param type the type of service request
     * @param location1 the node location of a request
     * @param location2 the second node location of a request on an edge, null if request is at a specific node
     */
    public ServiceRequest(Employee sender, LocalDateTime time, String type, ArrayList<Employee> recipients,
                          NodeData location1, NodeData location2, String description) {
        this.sender = sender;
        this.time = time;
        this.type = type;
        this.recipients = recipients;
        this.location1 = location1;
        this.location2 = location2;
        this.description = description;
    }

    public Employee getSender() {
        return sender;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public NodeData getLocation1() {
        return location1;
    }

    public NodeData getLocation2() {
        return location2;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Employee> getRecipients() {
        return recipients;
    }

    public void setRecipients(ArrayList<Employee> recipients) {
        this.recipients = recipients;
    }

    public void printRequest(){
        System.out.println("Sender: "+sender);
        System.out.println("Time Created: "+time);
        System.out.println("Type: "+type);
        System.out.println("Recipients: "+recipients);
        System.out.println("Location1: "+location1);
        System.out.println("Description: "+description);
        System.out.println("");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceRequest that = (ServiceRequest) o;

        if (!getSender().equals(that.getSender())) return false;
        if (getTime() != null ? !getTime().equals(that.getTime()) : that.getTime() != null) return false;
        if (getType() != null ? !getType().equals(that.getType()) : that.getType() != null) return false;
        if (getRecipients() != null ? !getRecipients().equals(that.getRecipients()) : that.getRecipients() != null)
            return false;
        if (getLocation1() != null ? !getLocation1().equals(that.getLocation1()) : that.getLocation1() != null)
            return false;
        if (getLocation2() != null ? !getLocation2().equals(that.getLocation2()) : that.getLocation2() != null)
            return false;
        return getDescription() != null ? getDescription().equals(that.getDescription()) : that.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = getSender().hashCode();
        result = 31 * result + (getTime() != null ? getTime().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getRecipients() != null ? getRecipients().hashCode() : 0);
        result = 31 * result + (getLocation1() != null ? getLocation1().hashCode() : 0);
        result = 31 * result + (getLocation2() != null ? getLocation2().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }

    //    public ArrayList<Employee> assignTo(boolean group){
//
//        if(group){
//            return addGroupRecipients();
//        }
//        else {
//            return addRecipients();
//        }
//    }

}
