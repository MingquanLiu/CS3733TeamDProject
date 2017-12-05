package edu.wpi.cs3733.programname.database.ModificationMethods;

import edu.wpi.cs3733.programname.commondata.servicerequestdata.TransportationRequest;
import edu.wpi.cs3733.programname.database.CsvWriter;
import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.SQLException;

public class TransportationRequestMethod {
    private DBConnection dbConnection ;
    private CsvWriter wrt;
    public TransportationRequestMethod(DBConnection dbConnection){
        this.dbConnection = dbConnection;
        this.wrt = new CsvWriter();
    }

    public void addTransportationRequest(TransportationRequest transportationRequest){
        int serviceID = transportationRequest.getServiceID();
        String transportType = transportationRequest.getTransportType();
        String destination = transportationRequest.getDestination();
        String reservationTime = transportationRequest.getReservationTime();

        String str;
        try {
            str = "INSERT INTO TransportationRequests values(" + serviceID + ",'" + transportType + "','"+ destination + "','" +reservationTime +"')";
            System.out.println(str);
            dbConnection.executeUpdate(str);
            this.wrt.writeTransportationRequests(dbConnection.getConnection());
        } catch (SQLException e) {
            System.out.println("Insert Transportation Request Failed!");
            e.printStackTrace();
        }
    }

    public void deleteTransportationRequest(TransportationRequest transportationRequest){
        int serviceID = transportationRequest.getServiceID();
        String str;
        try {
            str ="delete from TransportationRequests where serviceID = " + serviceID ;
            System.out.println(str);
            dbConnection.executeUpdate(str);
            this.wrt.writeTransportationRequests(dbConnection.getConnection());

        } catch (SQLException e) {
            System.out.println("Delete Transportation Request Failed!");
            e.printStackTrace();
        }
    }
}
