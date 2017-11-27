package edu.wpi.cs3733.programname.commondata;

import java.sql.Timestamp;
import java.util.Date;

public class ServiceRequest {
    private int id;
    private Employee sender;
    private String type;
    private NodeData location1;
    private NodeData location2;
    private String description;
    private Timestamp createdTime;
    private Timestamp handledTime;
    private Timestamp completedTime;
    private String status;
    private Employee receiver;


    public ServiceRequest(int id, Employee sender, String type, NodeData location1, NodeData location2, String description) {
        this.id = id;
        this.sender = sender;
        this.type = type;
        this.location1 = location1;
        this.location2 = location2;
        this.description = description;
        Date date = new Date();
        this.createdTime = new Timestamp(date.getTime());
        this.handledTime = null;
        this.completedTime = null;
        this.status = "unhandled";
        this.receiver = null;
    }

    public ServiceRequest(int id, Employee sender, String type, NodeData location1, NodeData location2,
                          String description, Timestamp createdTime, Timestamp handledTime, Timestamp completedTime, String status, Employee receiver) {
        this.id = id;
        this.sender = sender;
        this.type = type;
        this.location1 = location1;
        this.location2 = location2;
        this.description = description;
        this.createdTime = createdTime;
        this.handledTime = handledTime;
        this.completedTime = completedTime;
        this.status = status;
        this.receiver = receiver;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getsender() {
        return sender;
    }

    public void setsender(Employee sender) {
        this.sender = sender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public NodeData getLocation1() {
        return location1;
    }

    public void setLocation1(NodeData location) {
        this.location1 = location;
    }

    public NodeData getLocation2() {
        return location2;
    }

    public void setLocation2(NodeData location) {
        this.location2 = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getHandledTime() {
        return handledTime;
    }

    public void setHandledTime(Timestamp handledTime) {
        this.handledTime = handledTime;
    }

    public Timestamp getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(Timestamp completedTime) {
        this.completedTime = completedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee getreceiver() {
        return receiver;
    }

    public void setreceiver(Employee receiver) {
        this.receiver = receiver;
    }

    private String timeToString(Timestamp time){
        if(time == null){
            return "not handled yet";
        }
        else{
            return time.toString();
        }
    }


    @Override
    public String toString() {
        if (this.receiver == null){
            return "" +System.lineSeparator()+
                    "ID: " + id + System.lineSeparator() +
                    "sender: " + sender.getFirstName() +  " " +sender.getLastName() + System.lineSeparator() +
                    "Type: " + type + System.lineSeparator() +
                    "Location: between Location1 and Location2"+System.lineSeparator() +
                    "Location1: " + location1.getLongName() + System.lineSeparator() +
                    "Location2: " + location2.getLongName() + System.lineSeparator() +
                    "Description: " + description + System.lineSeparator() +
                    "Created Time: " + createdTime.toString() + System.lineSeparator() +
                    "Handled Time: " + this.timeToString(handledTime) + System.lineSeparator() +
                    "Completed Time: " + this.timeToString(completedTime) + System.lineSeparator() +
                    "Status: " + status + System.lineSeparator() +
                    "receiver: " + "not handled yet" + System.lineSeparator() +
                    "" +System.lineSeparator() ;
        }
        else{
            return "" +System.lineSeparator()+
                    "ID: " + id + System.lineSeparator() +
                    "sender: " + sender.getFirstName() + " " +sender.getLastName() + System.lineSeparator() +
                    "Type: " + type + System.lineSeparator() +
                    "Location: between Location1 and Location2"+System.lineSeparator() +
                    "Location1: " + location1.getLongName() + System.lineSeparator() +
                    "Location2: " + location2.getLongName() + System.lineSeparator() +
                    "Description: " + description + System.lineSeparator() +
                    "CreatedTime: " + createdTime.toString() + System.lineSeparator() +
                    "HandledTime: " + this.timeToString(handledTime) + System.lineSeparator() +
                    "CompletedTime: " + this.timeToString(completedTime) + System.lineSeparator() +
                    "Status: " + status + System.lineSeparator() +
                    "receiver: " + receiver.getUsername() + System.lineSeparator() +
                    "" +System.lineSeparator() ;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceRequest)) return false;

        ServiceRequest that = (ServiceRequest) o;

        if (getId() != that.getId()) return false;
        if (!getsender().equals(that.getsender())) return false;
        if (!getType().equals(that.getType())) return false;
        if (!getLocation1().equals(that.getLocation1())) return false;
        if (!getLocation2().equals(that.getLocation2())) return false;
        if (!getDescription().equals(that.getDescription())) return false;
        if (!getCreatedTime().equals(that.getCreatedTime())) return false;
        if (!getHandledTime().equals(that.getHandledTime())) return false;
        if (!getCompletedTime().equals(that.getCompletedTime())) return false;
        if (!getStatus().equals(that.getStatus())) return false;
        return getreceiver().equals(that.getreceiver());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getsender().hashCode();
        result = 31 * result + getType().hashCode();
        result = 31 * result + getLocation1().hashCode();
        result = 31 * result + getLocation2().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getCreatedTime().hashCode();
        result = 31 * result + getHandledTime().hashCode();
        result = 31 * result + getCompletedTime().hashCode();
        result = 31 * result + getStatus().hashCode();
        result = 31 * result + getreceiver().hashCode();
        return result;
    }
}
