package edu.wpi.cs3733.programname.database.ModificationMethods;

import edu.wpi.cs3733.programname.commondata.servicerequestdata.MaintenanceRequest;
import edu.wpi.cs3733.programname.database.CsvWriter;
import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.SQLException;

public class MaintenanceRequestsMethod {
    private DBConnection dbConnection ;
    private CsvWriter wrt;
    public MaintenanceRequestsMethod(DBConnection dbConnection){
        this.dbConnection = dbConnection;
        this.wrt = new CsvWriter();
    }

    public void addMaintenanceRequest(MaintenanceRequest MaintenanceRequest){
        int serviceID = MaintenanceRequest.getServiceID();
        String skill = MaintenanceRequest.getMaintenanceType();

        String str;
        try {
            str = "INSERT INTO MaintenanceRequests values(" + serviceID + ",'" + skill + "')";
            System.out.println(str);
            dbConnection.executeUpdate(str);
            this.wrt.writeMaintenanceRequests(dbConnection.getConnection());
        } catch (SQLException e) {
            System.out.println("Insert Maintenance Request Failed! Caused by addMaintenanceRequest(MaintenanceRequest MaintenanceRequest)");
            e.printStackTrace();
        }
    }

    public void deleteMaintenanceRequest(MaintenanceRequest MaintenanceRequest){
        int serviceID = MaintenanceRequest.getServiceID();
        String str;
        try {
            str ="delete from MaintenanceRequests where serviceID = " + serviceID ;
            System.out.println(str);
            dbConnection.executeUpdate(str);
            this.wrt.writeMaintenanceRequests(dbConnection.getConnection());

        } catch (SQLException e) {
            System.out.println("Delete Maintenance Request Failed!");
            e.printStackTrace();
        }
    }
}
