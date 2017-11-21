package edu.wpi.cs3733.programname.servicerequest.entity;

import edu.wpi.cs3733.programname.commondata.NodeData;

import java.sql.Timestamp;

public class ServiceRequest {
    private int id;
    private Employee requester;
    private String type;
    private NodeData location1;
    private NodeData location2;
    private String description;
    private Timestamp createdTime;
    private Timestamp handledTime;
    private Timestamp completedTime;
    private String status;
    private Employee handler;


    public ServiceRequest(int id, Employee requester, String type, NodeData location1, NodeData location2, String description, Timestamp createdTime, Timestamp handledTime, Timestamp completedTime, String status, Employee handler) {
        this.id = id;
        this.requester = requester;
        this.type = type;
        this.location1 = location1;
        this.location2 = location2;
        this.description = description;
        this.createdTime = createdTime;
        this.handledTime = handledTime;
        this.completedTime = completedTime;
        this.status = status;
        this.handler = handler;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getRequester() {
        return requester;
    }

    public void setRequester(Employee requester) {
        this.requester = requester;
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

    public Employee getHandler() {
        return handler;
    }

    public void setHandler(Employee handler) {
        this.handler = handler;
    }

    @Override
    public String toString() {
        return "id=" + id + System.lineSeparator() +
                "requester=" + requester.getUsername() + System.lineSeparator() +
                ", type='" + type + '\'' + System.lineSeparator() +
                ", location1=" + location1.getLongName() + System.lineSeparator() +
                ", location2=" + location2.getLongName() + System.lineSeparator() +
                ", description='" + description + '\'' + System.lineSeparator() +
                ", createdTime=" + createdTime.toString() + System.lineSeparator() +
                ", handledTime=" + handledTime.toString() + System.lineSeparator() +
                ", completedTime=" + completedTime.toString() + System.lineSeparator() +
                ", status='" + status + '\'' + System.lineSeparator() +
                ", handler=" + handler.getUsername() + System.lineSeparator() +
                "" +System.lineSeparator() ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceRequest)) return false;

        ServiceRequest that = (ServiceRequest) o;

        if (getId() != that.getId()) return false;
        if (!getRequester().equals(that.getRequester())) return false;
        if (!getType().equals(that.getType())) return false;
        if (!getLocation1().equals(that.getLocation1())) return false;
        if (!getLocation2().equals(that.getLocation2())) return false;
        if (!getDescription().equals(that.getDescription())) return false;
        if (!getCreatedTime().equals(that.getCreatedTime())) return false;
        if (!getHandledTime().equals(that.getHandledTime())) return false;
        if (!getCompletedTime().equals(that.getCompletedTime())) return false;
        if (!getStatus().equals(that.getStatus())) return false;
        return getHandler().equals(that.getHandler());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getRequester().hashCode();
        result = 31 * result + getType().hashCode();
        result = 31 * result + getLocation1().hashCode();
        result = 31 * result + getLocation2().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getCreatedTime().hashCode();
        result = 31 * result + getHandledTime().hashCode();
        result = 31 * result + getCompletedTime().hashCode();
        result = 31 * result + getStatus().hashCode();
        result = 31 * result + getHandler().hashCode();
        return result;
    }
}
