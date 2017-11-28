package edu.wpi.cs3733.programname.commondata;

import java.sql.Timestamp;
import java.util.Date;

public class ServiceRequest {
    private int serviceID;
    private String sender;
    private String serviceType;
    private String location1;
    private String location2;
    private String description;
    private Timestamp requestTime;
    private Timestamp handleTime;
    private Timestamp completionTime;
    private String status;
    private String receiver;


    public ServiceRequest(int serviceID, String sender, String serviceType, String location1, String location2, String description) {
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

    public ServiceRequest(int serviceID, String sender, String receiver, String serviceType, String location1, String location2,
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



    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getLocation1() {
        return location1;
    }

    public void setLocation1(String location) {
        this.location1 = location;
    }

    public String getLocation2() {
        return location2;
    }

    public void setLocation2(String location) {
        this.location2 = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
    }

    public Timestamp getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Timestamp handleTime) {
        this.handleTime = handleTime;
    }

    public Timestamp getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Timestamp completionTime) {
        this.completionTime = completionTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
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
                    "sender: " + sender +  " " +sender + System.lineSeparator() +
                    "serviceType: " + serviceType + System.lineSeparator() +
                    "Location: between Location1 and Location2"+System.lineSeparator() +
                    "Location1: " + location1 + System.lineSeparator() +
                    "Location2: " + location2 + System.lineSeparator() +
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
                    "sender: " + sender + " " +sender + System.lineSeparator() +
                    "serviceType: " + serviceType + System.lineSeparator() +
                    "Location: between Location1 and Location2"+System.lineSeparator() +
                    "Location1: " + location1 + System.lineSeparator() +
                    "Location2: " + location2 + System.lineSeparator() +
                    "Description: " + description + System.lineSeparator() +
                    "requestTime: " + requestTime.toString() + System.lineSeparator() +
                    "handleTime: " + this.timeToString(handleTime) + System.lineSeparator() +
                    "completionTime: " + this.timeToString(completionTime) + System.lineSeparator() +
                    "Status: " + status + System.lineSeparator() +
                    "receiver: " + receiver + System.lineSeparator() +
                    "" +System.lineSeparator() ;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceRequest)) return false;

        ServiceRequest that = (ServiceRequest) o;

        if (getServiceID() != that.getServiceID()) return false;
        if (!getSender().equals(that.getSender())) return false;
        if (!getServiceType().equals(that.getServiceType())) return false;
        if (!getLocation1().equals(that.getLocation1())) return false;
        if (!getLocation2().equals(that.getLocation2())) return false;
        if (!getDescription().equals(that.getDescription())) return false;
        if (!getRequestTime().equals(that.getRequestTime())) return false;
        if (!getHandleTime().equals(that.getHandleTime())) return false;
        if (!getCompletionTime().equals(that.getCompletionTime())) return false;
        if (!getStatus().equals(that.getStatus())) return false;
        return getReceiver().equals(that.getReceiver());
    }

    @Override
    public int hashCode() {
        int result = getServiceID();
        result = 31 * result + getSender().hashCode();
        result = 31 * result + getServiceType().hashCode();
        result = 31 * result + getLocation1().hashCode();
        result = 31 * result + getLocation2().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getRequestTime().hashCode();
        result = 31 * result + getHandleTime().hashCode();
        result = 31 * result + getCompletionTime().hashCode();
        result = 31 * result + getStatus().hashCode();
        result = 31 * result + getReceiver().hashCode();
        return result;
    }
}