package edu.wpi.cs3733.programname.commondata;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class ServiceRequest {
    private int serviceID;
    private Employee sender;
    private Employee receiver;
    private String serviceType;
    private NodeData location1;
    private NodeData location2;
    private String description;
//    private Timestamp requestTime;
//    private Timestamp handleTime;
//    private Timestamp completionTime;
    private String requestTime;
    private String handleTime;
    private String completionTime;
    private String status;


    public ServiceRequest(int serviceID, Employee sender, String serviceType, NodeData location1, NodeData location2, String description) {
        this.serviceID = serviceID;
        this.sender = sender;
        this.serviceType = serviceType;
        this.location1 = location1;
        this.location2 = location2;
        this.description = description;
        Date date = new Date();
//        this.requestTime = new Timestamp(date.getTime());
        this.requestTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Timestamp(date.getTime()));
//        this.handleTime = null;
//        this.completionTime = null;
        this.handleTime = "";
        this.completionTime = "";
        this.status = "unhandled";
        this.receiver = null;
    }

//    public ServiceRequest(int serviceID, Employee sender, Employee receiver, String serviceType, NodeData location1, NodeData location2,
//                          String description, Timestamp requestTime, Timestamp handleTime, Timestamp completionTime, String status) {
//        this.serviceID = serviceID;
//        this.sender = sender;
//        this.serviceType = serviceType;
//        this.location1 = location1;
//        this.location2 = location2;
//        this.description = description;
//        this.requestTime = requestTime;
//        this.handleTime = handleTime;
//        this.completionTime = completionTime;
//        this.status = status;
//        this.receiver = receiver;
//    }


    public ServiceRequest(int serviceID, Employee sender, Employee receiver, String serviceType, NodeData location1, NodeData location2, String description, String requestTime, String handleTime, String completionTime, String status) {
        this.serviceID = serviceID;
        this.sender = sender;
        this.receiver = receiver;
        this.serviceType = serviceType;
        this.location1 = location1;
        this.location2 = location2;
        this.description = description;
        this.requestTime = requestTime;
        this.handleTime = handleTime;
        this.completionTime = completionTime;
        this.status = status;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public Employee getSender() {
        return sender;
    }

    public void setSender(Employee sender) {
        this.sender = sender;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
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

//    public Timestamp getRequestTime() {
//        return requestTime;
//    }
//
//    public void setRequestTime(Timestamp requestTime) {
//        this.requestTime = requestTime;
//    }
//
//    public Timestamp getHandleTime() {
//        return handleTime;
//    }
//
//    public void setHandleTime(Timestamp handleTime) {
//        this.handleTime = handleTime;
//    }
//
//    public Timestamp getCompletionTime() {
//        return completionTime;
//    }
//
//    public void setCompletionTime(Timestamp completionTime) {
//        this.completionTime = completionTime;
//    }


    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(String handleTime) {
        this.handleTime = handleTime;
    }

    public String getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(String completionTime) {
        this.completionTime = completionTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee getReceiver() {
        return receiver;
    }

    public void setReceiver(Employee receiver) {
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


//    @Override
//    public String toString() {
//        if (this.receiver == null){
//            return "" +System.lineSeparator()+
//                    "ID: " + serviceID + System.lineSeparator() +
//                    "sender: " + sender.getFirstName() +  " " +sender.getLastName() + System.lineSeparator() +
//                    "serviceType: " + serviceType + System.lineSeparator() +
//                    "Location: between Location1 and Location2"+System.lineSeparator() +
//                    "Location1: " + location1.getLongName() + System.lineSeparator() +
//                    "Location2: " + location2.getLongName() + System.lineSeparator() +
//                    "Description: " + description + System.lineSeparator() +
//                    "Created Time: " + requestTime.toString() + System.lineSeparator() +
//                    "Handled Time: " + this.timeToString(handleTime) + System.lineSeparator() +
//                    "Completed Time: " + this.timeToString(completionTime) + System.lineSeparator() +
//                    "Status: " + status + System.lineSeparator() +
//                    "receiver: " + "not handled yet" + System.lineSeparator() +
//                    "" +System.lineSeparator() ;
//        }
//        else{
//            return "" +System.lineSeparator()+
//                    "ID: " + serviceID + System.lineSeparator() +
//                    "sender: " + sender.getFirstName() + " " +sender.getLastName() + System.lineSeparator() +
//                    "serviceType: " + serviceType + System.lineSeparator() +
//                    "Location: between Location1 and Location2"+System.lineSeparator() +
//                    "Location1: " + location1.getLongName() + System.lineSeparator() +
//                    "Location2: " + location2.getLongName() + System.lineSeparator() +
//                    "Description: " + description + System.lineSeparator() +
//                    "requestTime: " + requestTime.toString() + System.lineSeparator() +
//                    "handleTime: " + this.timeToString(handleTime) + System.lineSeparator() +
//                    "completionTime: " + this.timeToString(completionTime) + System.lineSeparator() +
//                    "Status: " + status + System.lineSeparator() +
//                    "receiver: " + receiver.getUsername() + System.lineSeparator() +
//                    "" +System.lineSeparator() ;
//        }
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceRequest)) return false;

        ServiceRequest that = (ServiceRequest) o;

        if (getServiceID() != that.getServiceID()) return false;
        if (getSender() != null ? !getSender().equals(that.getSender()) : that.getSender() != null) return false;
        if (getServiceType() != null ? !getServiceType().equals(that.getServiceType()) : that.getServiceType() != null)
            return false;
        if (getLocation1() != null ? !getLocation1().equals(that.getLocation1()) : that.getLocation1() != null)
            return false;
        if (getLocation2() != null ? !getLocation2().equals(that.getLocation2()) : that.getLocation2() != null)
            return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        if (getRequestTime() != null ? !getRequestTime().equals(that.getRequestTime()) : that.getRequestTime() != null)
            return false;
        if (getHandleTime() != null ? !getHandleTime().equals(that.getHandleTime()) : that.getHandleTime() != null)
            return false;
        if (getCompletionTime() != null ? !getCompletionTime().equals(that.getCompletionTime()) : that.getCompletionTime() != null)
            return false;
        if (getStatus() != null ? !getStatus().equals(that.getStatus()) : that.getStatus() != null) return false;
        return getReceiver() != null ? getReceiver().equals(that.getReceiver()) : that.getReceiver() == null;
    }

//    @Override
//    public int hashCode() {
//        int result = getServiceID();
//        result = 31 * result + getSender().hashCode();
//        result = 31 * result + getServiceType().hashCode();
//        result = 31 * result + getLocation1().hashCode();
//        result = 31 * result + getLocation2().hashCode();
//        result = 31 * result + getDescription().hashCode();
//        result = 31 * result + getRequestTime().hashCode();
//        result = 31 * result + getHandleTime().hashCode();
//        result = 31 * result + getCompletionTime().hashCode();
//        result = 31 * result + getStatus().hashCode();
//        result = 31 * result + getReceiver().hashCode();
//        return result;
//    }
}