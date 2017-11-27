package edu.wpi.cs3733.programname.commondata;

import edu.wpi.cs3733.programname.servicerequest.entity.ServiceRequest;

public class ServiceRequestInfo {
    Integer serviceID;
    String sender;          // Employee userName
    String receiver;        // Employee userName
    String node1;           // Node nodeID
    String node2;           // Node nodeID
    String serviceType;
    String status;
    String description;
    String requestTime;
    String handleTime;
    String completionTime;
    ;
    public ServiceRequestInfo(Integer serviceID, String sender, String receiver, String node1,
                              String node2, String serviceType, String status, String description,
                              String requestTime, String handleTime, String completionTime) {

        this.serviceID = serviceID;
        this.sender = sender;           // Employee userName
        this.receiver = receiver;       // Employee userName
        this.node1 = node1;             // Node nodeID
        this.node2 = node2;             // Node nodeID
        this.serviceType = serviceType;
        this.status = status;
        this.description = description;
        this.requestTime = requestTime;
        this.handleTime = handleTime;
        this.completionTime = completionTime;
    }


}
