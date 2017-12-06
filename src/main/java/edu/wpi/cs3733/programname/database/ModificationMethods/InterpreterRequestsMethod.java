package edu.wpi.cs3733.programname.database.ModificationMethods;

import edu.wpi.cs3733.programname.commondata.servicerequestdata.InterpreterRequest;
import edu.wpi.cs3733.programname.database.CsvWriter;
import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.SQLException;

public class InterpreterRequestsMethod {
    private DBConnection dbConnection ;
    private CsvWriter wrt;
    public InterpreterRequestsMethod(DBConnection dbConnection){
        this.dbConnection = dbConnection;
        this.wrt = new CsvWriter();
    }

    public void addInterpreterRequest(InterpreterRequest interpreterRequest){
        String serviceID = interpreterRequest.getServiceID();
        String language = interpreterRequest.getLanguage();
        String reservationTime = interpreterRequest.getReservationTime();

        String str;
        try {
            str = "INSERT INTO InterpreterRequests values('" + serviceID + "','" + language + "','"+ reservationTime +"')";
            System.out.println(str);
            dbConnection.executeUpdate(str);
            this.wrt.writeInterpreterRequests(dbConnection.getConnection());
        } catch (SQLException e) {
            System.out.println("Insert Interpreter Request Failed! Caused by addInterpreterRequest(InterpreterRequest interpreterRequest)");
            e.printStackTrace();
        }
    }

    public void deleteInterpreterRequest(InterpreterRequest interpreterRequest){
        String serviceID = interpreterRequest.getServiceID();
        String str;
        try {
            str ="delete from InterpreterRequests where serviceID = '" + serviceID +"'";
            System.out.println(str);
            dbConnection.executeUpdate(str);
            this.wrt.writeInterpreterRequests(dbConnection.getConnection());

        } catch (SQLException e) {
            System.out.println("Delete Interpreter Request Failed!");
            e.printStackTrace();
        }
    }
}
