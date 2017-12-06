package edu.wpi.cs3733.programname.database.QueryMethods;

import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MaintenanceRequestsQuery {
    private DBConnection dbConnection;
    public MaintenanceRequestsQuery(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public String queryMaintenanceRequestByID(int serviceID){
        String queryResult = "";
        try {
            String sql = "SELECT * FROM MaintenanceRequests WHERE serviceID = " + serviceID;
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            String maintenanceType;

            while(result.next()) {
                maintenanceType = result.getString("maintenanceType");
                queryResult = maintenanceType;
            }
        } catch (SQLException e) {
            System.out.println("Query Maintenance Request Failed!");
            e.printStackTrace();
        }
        return queryResult;
    }
}
