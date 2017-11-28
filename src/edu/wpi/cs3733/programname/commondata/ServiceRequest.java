package edu.wpi.cs3733.programname.commondata;

import java.sql.Timestamp;
import java.util.Date;

public class ServiceRequest {
    private int serviceID;
    private Employee sender;
    private String serviceType;
    private NodeData location1;
    private NodeData location2;
    private String description;
    private Timestamp requestTime;
    private Timestamp handleTime;
    private Timestamp completionTime;
    private String status;
    private Employee receiver;


    public ServiceRequest(int serviceID, Employee sender, String serviceType, NodeData location1, NodeData location2, String description) {
        this.serviceID = serviceID;
        this.sender = sender;
        this.serviceType = serviceType;
        this.location1 = location1;
        this.location2 = location2;
        this.description = description;
        Date date = new Date();
        this.requestTime = new Timestamp(date.getTime());
        this.handleTime = null;
        this.completionTime = null;
        this.status = "unhandled";
        this.receiver = null;
    }

    public ServiceRequest(int serviceID, Employee sender, Employee receiver, String serviceType, NodeData location1, NodeData location2,
                          String description, Timestamp requestTime, Timestamp handleTime, Timestamp completionTime, String status) {
        this.serviceID = serviceID;
        this.sender = sender;
        this.serviceType = serviceType;
        this.location1 = location1;
        this.location2 = location2;
        this.description = description;
        this.requestTime = requestTime;
        this.handleTime = handleTime;
        this.completionTime = completionTime;
        this.status = status;
        this.receiver = receiver;
    }



    public int getserviceID() {
        return serviceID;
    }

    public void setserviceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public Employee getsender() {
        return sender;
    }

    public void setsender(Employee sender) {
        this.sender = sender;
    }

    public String getserviceType() {
        return serviceType;
    }

    public void setserviceType(String serviceType) {
        this.serviceType = serviceType;
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

    public Timestamp getrequestTime() {
        return requestTime;
    }

    public void setrequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
    }

    public Timestamp gethandleTime() {
        return handleTime;
    }

    public void sethandleTime(Timestamp handleTime) {
        this.handleTime = handleTime;
    }

    public Timestamp getcompletionTime() {
        return completionTime;
    }

    public void setcompletionTime(Timestamp completionTime) {
        this.completionTime = completionTime;
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
                    "ID: " + serviceID + System.lineSeparator() +
                    "sender: " + sender.getFirstName() +  " " +sender.getLastName() + System.lineSeparator() +
                    "serviceType: " + serviceType + System.lineSeparator() +
                    "Location: between Location1 and Location2"+System.lineSeparator() +
                    "Location1: " + location1.getLongName() + System.lineSeparator() +
                    "Location2: " + location2.getLongName() + System.lineSeparator() +
                    "Description: " + description + System.lineSeparator() +
                    "Created Time: " + requestTime.toString() + System.lineSeparator() +
                    "Handled Time: " + this.timeToString(handleTime) + System.lineSeparator() +
                    "Completed Time: " + this.timeToString(completionTime) + System.lineSeparator() +
                    "Status: " + status + System.lineSeparator() +
                    "receiver: " + "not handled yet" + System.lineSeparator() +
                    "" +System.lineSeparator() ;
        }
        else{
            return "" +System.lineSeparator()+
                    "ID: " + serviceID + System.lineSeparator() +
                    "sender: " + sender.getFirstName() + " " +sender.getLastName() + System.lineSeparator() +
                    "serviceType: " + serviceType + System.lineSeparator() +
                    "Location: between Location1 and Location2"+System.lineSeparator() +
                    "Location1: " + location1.getLongName() + System.lineSeparator() +
                    "Location2: " + location2.getLongName() + System.lineSeparator() +
                    "Description: " + description + System.lineSeparator() +
                    "requestTime: " + requestTime.toString() + System.lineSeparator() +
                    "handleTime: " + this.timeToString(handleTime) + System.lineSeparator() +
                    "completionTime: " + this.timeToString(completionTime) + System.lineSeparator() +
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

        if (getserviceID() != that.getserviceID()) return false;
        if (!getsender().equals(that.getsender())) return false;
        if (!getserviceType().equals(that.getserviceType())) return false;
        if (!getLocation1().equals(that.getLocation1())) return false;
        if (!getLocation2().equals(that.getLocation2())) return false;
        if (!getDescription().equals(that.getDescription())) return false;
        if (!getrequestTime().equals(that.getrequestTime())) return false;
        if (!gethandleTime().equals(that.gethandleTime())) return false;
        if (!getcompletionTime().equals(that.getcompletionTime())) return false;
        if (!getStatus().equals(that.getStatus())) return false;
        return getreceiver().equals(that.getreceiver());
    }

    @Override
    public int hashCode() {
        int result = getserviceID();
        result = 31 * result + getsender().hashCode();
        result = 31 * result + getserviceType().hashCode();
        result = 31 * result + getLocation1().hashCode();
        result = 31 * result + getLocation2().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getrequestTime().hashCode();
        result = 31 * result + gethandleTime().hashCode();
        result = 31 * result + getcompletionTime().hashCode();
        result = 31 * result + getStatus().hashCode();
        result = 31 * result + getreceiver().hashCode();
        return result;
    }
}
