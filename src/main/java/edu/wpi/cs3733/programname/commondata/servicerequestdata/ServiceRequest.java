package edu.wpi.cs3733.programname.commondata.servicerequestdata;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceRequest {
    private String serviceID;
    private String sender;
    private String serviceType;
    private String location1;
    private String location2;
    private String description;
    private String requestTime;
    private String handleTime;
    private String completionTime;
    private String status;
    private String receiver;
    private int severity;


    // constructor using all the fields
    public ServiceRequest(String serviceID, String sender, String receiver, String serviceType, String location1, String location2, String description,
                          String requestTime, String handleTime, String completionTime, String status, int severity) {
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
        this.severity = severity;
    }

    // short constructor
    public ServiceRequest(String sender, String serviceType, String location1,
                          String location2, String description, int severity) {
        Date requestTimeDate = new Date();
        Long id = requestTimeDate.getTime();
        System.out.println("Id "+id);
        this.serviceID = id.toString();
        this.sender = sender;
        this.serviceType = serviceType;
        this.location1 = location1;
        this.location2 = location2;
        this.description = description;
        this.requestTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Timestamp(requestTimeDate.getTime()));
        this.handleTime = "";
        this.completionTime = "";
        this.status = "unhandled";
        this.receiver = "";
        this.severity = severity;
    }


    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
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

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }


    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    @Override
    public String toString() {
    return "" +System.lineSeparator()+
                    "ID: " + serviceID + System.lineSeparator() +
                    "sender: " + sender + System.lineSeparator() +
                    "serviceType: " + serviceType + System.lineSeparator() +
                    "Location: Near Location1 (If Location2 exists: between Location1 and Location2)"+System.lineSeparator() +
                    "Location1: " + location1 + System.lineSeparator() +
                    "Location2: " + location2 + System.lineSeparator() +
                    "Description: " + description + System.lineSeparator() +
                    "RequestTime: " + requestTime + System.lineSeparator() +
                    "HandleTime: " + handleTime + System.lineSeparator() +
                    "CompletionTime: " + completionTime + System.lineSeparator() +
                    "Status: " + status + System.lineSeparator() +
                    "Severity: " + severity + System.lineSeparator() +
                    "Receiver: " + receiver + System.lineSeparator();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceRequest)) return false;

        ServiceRequest that = (ServiceRequest) o;

        if (getSeverity() != that.getSeverity()) return false;
        if (getServiceID() != null ? !getServiceID().equals(that.getServiceID()) : that.getServiceID() != null)
            return false;
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

    @Override
    public int hashCode() {
        int result = getServiceID() != null ? getServiceID().hashCode() : 0;
        result = 31 * result + (getSender() != null ? getSender().hashCode() : 0);
        result = 31 * result + (getServiceType() != null ? getServiceType().hashCode() : 0);
        result = 31 * result + (getLocation1() != null ? getLocation1().hashCode() : 0);
        result = 31 * result + (getLocation2() != null ? getLocation2().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getRequestTime() != null ? getRequestTime().hashCode() : 0);
        result = 31 * result + (getHandleTime() != null ? getHandleTime().hashCode() : 0);
        result = 31 * result + (getCompletionTime() != null ? getCompletionTime().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getReceiver() != null ? getReceiver().hashCode() : 0);
        result = 31 * result + getSeverity();
        return result;
    }
}