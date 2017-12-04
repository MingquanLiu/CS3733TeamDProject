package edu.wpi.cs3733.programname.database.ModificationMethods;

import edu.wpi.cs3733.programname.commondata.ServiceRequest.ServiceRequest;
import edu.wpi.cs3733.programname.database.CsvWriter;
import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceRequestsMethod {

    private DBConnection dbConnection ;
    private CsvWriter wrt;
    public ServiceRequestsMethod(DBConnection dbConnection){
        this.dbConnection = dbConnection;
        this.wrt = new CsvWriter();}

    public void addServiceRequest(ServiceRequest serviceRequest){
        int serviceID = serviceRequest.getServiceID();
        String senderUsername = serviceRequest.getSender();
        String serviceType = serviceRequest.getServiceType();
        String node1ID = serviceRequest.getLocation1();
        String node2ID = serviceRequest.getLocation2();
        String description = serviceRequest.getDescription();
//        Timestamp requestTime = serviceRequest.getRequestTime();
//        Timestamp handleTime = serviceRequest.getHandleTime();
//        Timestamp completionTime = serviceRequest.getCompletionTime();
        String requestTime = serviceRequest.getRequestTime();
        String handleTime = serviceRequest.getHandleTime();
        String completionTime = serviceRequest.getCompletionTime();
        String reservationTime = serviceRequest.getReservationTime();
        String status = serviceRequest.getStatus();
        String receiverUsername = serviceRequest.getReceiver();
        int severity = serviceRequest.getSeverity();
        String str;
        try {
            str = "INSERT INTO ServiceRequests values(" + serviceID + ",'" + senderUsername + "', '" + receiverUsername + "','" + serviceType + "', '" + node1ID +  "', '" + node2ID + "', '" + description +
                    "', '" + requestTime + "','" + handleTime + "', '" + completionTime + "','" + reservationTime + "','"+ status + "'," + severity + ")";
            System.out.println(str);
            dbConnection.executeUpdate(str);
            this.wrt.writeServiceRequests(dbConnection.getConnection());
        } catch (SQLException e) {
            System.out.println("Insert Service Request Failed!");
            e.printStackTrace();
        }
    }

    // mark a service request as handled
    public void handleServiceRequest(ServiceRequest serviceRequest, String receiver) {
        int serviceID = serviceRequest.getServiceID();
        Date date = new Date();
        String handleTime;// = new Timestamp(date.getTime());
        handleTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Timestamp(date.getTime()));
        String str;
        try {
            str = "update ServiceRequests set handleTime = '"+ handleTime +"', status = 'handled', receiver = '"+ receiver +"' where serviceID = " + serviceID ;
            System.out.println(str);
            dbConnection.executeUpdate(str);
            this.wrt.writeServiceRequests(dbConnection.getConnection());

        } catch (SQLException e) {
            System.out.println("Handle Service Request Failed!");
            e.printStackTrace();
        }
    }

    // mark a service request as completed
    public void completeServiceRequest(ServiceRequest serviceRequest) {
        int serviceID = serviceRequest.getServiceID();
        Date date = new Date();
        String completionTime;// = new Timestamp(date.getTime());
        completionTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Timestamp(date.getTime()));
        String str;
        try {
            str = "update ServiceRequests set completionTime = '"+ completionTime +"', status = 'completed' where serviceID = " + serviceID ;
            System.out.println(str);
            dbConnection.executeUpdate(str);
            this.wrt.writeServiceRequests(dbConnection.getConnection());

        } catch (SQLException e) {
            System.out.println("Complete Service Request Failed!");
            e.printStackTrace();
        }
    }

    // mark a service request as removed
    public void removeServiceRequest(ServiceRequest serviceRequest) {
        int serviceID = serviceRequest.getServiceID();
        String str;
        try {
            str = "update ServiceRequests set status = 'removed' where serviceID = " + serviceID ;
            System.out.println(str);
            dbConnection.executeUpdate(str);
            this.wrt.writeServiceRequests(dbConnection.getConnection());

        } catch (SQLException e) {
            System.out.println("Remove Service Request Failed!");
            e.printStackTrace();
        }
    }


    // delete a service request from database
    public void deleteServiceRequest(ServiceRequest serviceRequest){
        int serviceID = serviceRequest.getServiceID();
        String str;
        try {
            str ="delete from ServiceRequests where serviceID = " + serviceID ;
            System.out.println(str);
            dbConnection.executeUpdate(str);
            this.wrt.writeServiceRequests(dbConnection.getConnection());

        } catch (SQLException e) {
            System.out.println("Delete Service Request Failed!");
            e.printStackTrace();
        }
    }

}
