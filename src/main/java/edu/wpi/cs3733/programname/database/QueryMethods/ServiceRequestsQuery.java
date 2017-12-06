package edu.wpi.cs3733.programname.database.QueryMethods;

import edu.wpi.cs3733.programname.commondata.servicerequestdata.InterpreterRequest;
import edu.wpi.cs3733.programname.commondata.servicerequestdata.MaintenanceRequest;
import edu.wpi.cs3733.programname.commondata.servicerequestdata.TransportationRequest;
import edu.wpi.cs3733.programname.database.DBConnection;
import edu.wpi.cs3733.programname.commondata.servicerequestdata.ServiceRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServiceRequestsQuery {
    private DBConnection dbConnection;
    private InterpreterRequestsQuery interpreterRequestsQuery;
    private TransportationRequestQuery transportationRequestQuery;
    private MaintenanceRequestsQuery maintenanceRequestsQuery;
    public ServiceRequestsQuery(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.interpreterRequestsQuery = new InterpreterRequestsQuery(dbConnection);
        this.transportationRequestQuery = new TransportationRequestQuery(dbConnection);
        this.maintenanceRequestsQuery = new MaintenanceRequestsQuery(dbConnection);
    }

    public ArrayList<ServiceRequest> queryAllServiceRequests(){
        ServiceRequest queryResult;
        ArrayList<ServiceRequest> resultList = new ArrayList<ServiceRequest>();
        try {
            String sql = "SELECT * FROM ServiceRequests";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            String serviceID;
            String senderUsername;
            String serviceType;
            String node1ID;
            String node2ID;
            String description;
            String requestTime;
            String handleTime;
            String completionTime;
            String status;
            String receiverUsername;
            int severity;
            String language;
            String reservationTime;
            String transportationType;
            String destination;

            while(result.next()) {
                serviceID = result.getString("serviceID");
                senderUsername = result.getString("sender");
                serviceType = result.getString("serviceType");
                node1ID = result.getString("location1");
                node2ID = result.getString("location2");
                description = result.getString("description");
                requestTime = result.getString("requestTime");
                handleTime = result.getString("handleTime");
                completionTime = result.getString("completionTime");
                status = result.getString("status");
                receiverUsername = result.getString("receiver");
                severity = result.getInt("severity");

                if(serviceType.equals("interpreter")){
                    ArrayList<String> interpreterRequest = this.interpreterRequestsQuery.queryInterpreterRequestByID(serviceID);
                    language = interpreterRequest.get(0);
                    reservationTime = interpreterRequest.get(1);
                    queryResult = new InterpreterRequest(serviceID,senderUsername, receiverUsername, serviceType, node1ID, node2ID, description, requestTime, handleTime, completionTime, status, severity, language, reservationTime);
                }
                else if(serviceType.equals("transportation")){
                    ArrayList<String> transportationRequest = this.transportationRequestQuery.queryTransportationRequestByID(serviceID);
                    transportationType = transportationRequest.get(0);
                    destination = transportationRequest.get(1);
                    reservationTime = transportationRequest.get(2);
                    queryResult = new TransportationRequest(serviceID,senderUsername, receiverUsername, serviceType, node1ID, node2ID, description, requestTime, handleTime, completionTime, status, severity, transportationType, destination, reservationTime);
                }
                else if(serviceType.equals("maintenance")){
                    String maintenanceType = this.maintenanceRequestsQuery.queryMaintenanceRequestByID(serviceID);
                    queryResult = new MaintenanceRequest(serviceID,senderUsername, receiverUsername, serviceType, node1ID, node2ID, description, requestTime, handleTime, completionTime, status, severity, maintenanceType);
                }
                else{
                    queryResult = new ServiceRequest(serviceID,senderUsername, receiverUsername, serviceType, node1ID, node2ID, description, requestTime, handleTime, completionTime, status, severity);
                }

                resultList.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Query Service Request Failed!");
            e.printStackTrace();
        }
        return resultList;
    }

    public ArrayList<ServiceRequest> queryServiceRequestsByStatus(String status){
        ServiceRequest queryResult = null;
        ArrayList<ServiceRequest> resultList = new ArrayList<ServiceRequest>();
        try {
            String sql = "SELECT * FROM ServiceRequests WHERE status = '" + status + "'";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            String serviceID;
            String senderUsername;
            String serviceType;
            String node1ID;
            String node2ID;
            String description;
            String requestTime;
            String handleTime;
            String completionTime;
            String receiverUsername;
            int severity;
            String language;
            String reservationTime;
            String transportationType;
            String destination;

            while(result.next()) {
                serviceID = result.getString("serviceID");
                senderUsername = result.getString("sender");
                serviceType = result.getString("serviceType");
                node1ID = result.getString("location1");
                node2ID = result.getString("location2");
                description = result.getString("description");
                requestTime = result.getString("requestTime");
                handleTime = result.getString("handleTime");
                completionTime = result.getString("completionTime");
                receiverUsername = result.getString("receiver");
                severity = result.getInt("severity");
                if(serviceType.equals("interpreter")){
                    ArrayList<String> interpreterRequest = this.interpreterRequestsQuery.queryInterpreterRequestByID(serviceID);
                    language = interpreterRequest.get(0);
                    reservationTime = interpreterRequest.get(1);
                    queryResult = new InterpreterRequest(serviceID,senderUsername, receiverUsername, serviceType, node1ID, node2ID, description, requestTime, handleTime, completionTime, status, severity, language, reservationTime);
                }
                else if(serviceType.equals("transportation")){
                    ArrayList<String> transportationRequest = this.transportationRequestQuery.queryTransportationRequestByID(serviceID);
                    transportationType = transportationRequest.get(0);
                    destination = transportationRequest.get(1);
                    reservationTime = transportationRequest.get(2);
                    queryResult = new TransportationRequest(serviceID,senderUsername, receiverUsername, serviceType, node1ID, node2ID, description, requestTime, handleTime, completionTime, status, severity, transportationType, destination, reservationTime);
                }
                else if(serviceType.equals("maintenance")){
                    String maintenanceType = this.maintenanceRequestsQuery.queryMaintenanceRequestByID(serviceID);
                    queryResult = new MaintenanceRequest(serviceID,senderUsername, receiverUsername, serviceType, node1ID, node2ID, description, requestTime, handleTime, completionTime, status, severity, maintenanceType);
                }
                else{
                    queryResult = new ServiceRequest(serviceID,senderUsername, receiverUsername, serviceType, node1ID, node2ID, description, requestTime, handleTime, completionTime, status, severity);
                }
                resultList.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Query Service Request Failed!");
            e.printStackTrace();
        }
        return resultList;
    }

    public ArrayList<ServiceRequest> queryServiceRequestsByType(String serviceType){
        ServiceRequest queryResult = null;
        ArrayList<ServiceRequest> resultList = new ArrayList<ServiceRequest>();
        try {
            String sql = "SELECT * FROM ServiceRequests WHERE serviceType = '" + serviceType + "'";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            String serviceID;
            String senderUsername;
            //String serviceType;
            String node1ID;
            String node2ID;
            String description;
            String requestTime;
            String handleTime;
            String completionTime;
            String status;
            String receiverUsername;
            int severity;
            String language;
            String reservationTime;
            String transportationType;
            String destination;

            while(result.next()) {
                serviceID = result.getString("serviceID");
                senderUsername = result.getString("sender");
                node1ID = result.getString("location1");
                node2ID = result.getString("location2");
                description = result.getString("description");
                requestTime = result.getString("requestTime");
                handleTime = result.getString("handleTime");
                completionTime = result.getString("completionTime");
                status = result.getString("status");
                receiverUsername = result.getString("receiver");
                severity = result.getInt("severity");
                if(serviceType.equals("interpreter")){
                    ArrayList<String> interpreterRequest = this.interpreterRequestsQuery.queryInterpreterRequestByID(serviceID);
                    language = interpreterRequest.get(0);
                    reservationTime = interpreterRequest.get(1);
                    queryResult = new InterpreterRequest(serviceID,senderUsername, receiverUsername, serviceType, node1ID, node2ID, description, requestTime, handleTime, completionTime, status, severity, language, reservationTime);
                }
                else if(serviceType.equals("transportation")){
                    ArrayList<String> transportationRequest = this.transportationRequestQuery.queryTransportationRequestByID(serviceID);
                    transportationType = transportationRequest.get(0);
                    destination = transportationRequest.get(1);
                    reservationTime = transportationRequest.get(2);
                    queryResult = new TransportationRequest(serviceID,senderUsername, receiverUsername, serviceType, node1ID, node2ID, description, requestTime, handleTime, completionTime, status, severity, transportationType, destination, reservationTime);
                }
                else if(serviceType.equals("maintenance")){
                    String maintenanceType = this.maintenanceRequestsQuery.queryMaintenanceRequestByID(serviceID);
                    queryResult = new MaintenanceRequest(serviceID,senderUsername, receiverUsername, serviceType, node1ID, node2ID, description, requestTime, handleTime, completionTime, status, severity, maintenanceType);
                }
                else{
                    queryResult = new ServiceRequest(serviceID,senderUsername, receiverUsername, serviceType, node1ID, node2ID, description, requestTime, handleTime, completionTime, status, severity);
                }
                resultList.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Query Service Request Failed!");
            e.printStackTrace();
        }
        return resultList;
    }

    public ServiceRequest queryServiceRequestsByID(String serviceID){
        ServiceRequest queryResult = null;
        try {
            String sql = "SELECT * FROM ServiceRequests WHERE serviceID = '" + serviceID +"'";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            String senderUsername;
            String serviceType;
            String node1ID;
            String node2ID;
            String description;
            String requestTime;
            String handleTime;
            String completionTime;
            String status;
            String receiverUsername;
            int severity;
            String language;
            String reservationTime;
            String transportationType;
            String destination;

            while(result.next()) {
                senderUsername = result.getString("sender");
                serviceType = result.getString("serviceType");
                node1ID = result.getString("location1");
                node2ID = result.getString("location2");
                description = result.getString("description");
                requestTime = result.getString("requestTime");
                handleTime = result.getString("handleTime");
                completionTime = result.getString("completionTime");
                status = result.getString("status");
                receiverUsername = result.getString("receiver");
                severity = result.getInt("severity");
                if(serviceType.equals("interpreter")){
                    ArrayList<String> interpreterRequest = this.interpreterRequestsQuery.queryInterpreterRequestByID(serviceID);
                    language = interpreterRequest.get(0);
                    reservationTime = interpreterRequest.get(1);
                    queryResult = new InterpreterRequest(serviceID,senderUsername, receiverUsername, serviceType, node1ID, node2ID, description, requestTime, handleTime, completionTime, status, severity, language, reservationTime);
                }
                else if(serviceType.equals("transportation")){
                    ArrayList<String> transportationRequest = this.transportationRequestQuery.queryTransportationRequestByID(serviceID);
                    transportationType = transportationRequest.get(0);
                    destination = transportationRequest.get(1);
                    reservationTime = transportationRequest.get(2);
                    queryResult = new TransportationRequest(serviceID,senderUsername, receiverUsername, serviceType, node1ID, node2ID, description, requestTime, handleTime, completionTime, status, severity, transportationType, destination, reservationTime);
                }
                else if(serviceType.equals("maintenance")){
                    String maintenanceType = this.maintenanceRequestsQuery.queryMaintenanceRequestByID(serviceID);
                    queryResult = new MaintenanceRequest(serviceID,senderUsername, receiverUsername, serviceType, node1ID, node2ID, description, requestTime, handleTime, completionTime, status, severity, maintenanceType);
                }
                else{
                    queryResult = new ServiceRequest(serviceID,senderUsername, receiverUsername, serviceType, node1ID, node2ID, description, requestTime, handleTime, completionTime, status, severity);
                }
            }
        } catch (SQLException e) {
            System.out.println("Query Service Request Failed!");
            e.printStackTrace();
        }
        return queryResult;
    }


}
