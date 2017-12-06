package edu.wpi.cs3733.programname.database.QueryMethods;

import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TransportationRequestQuery {
    private DBConnection dbConnection;
    public TransportationRequestQuery(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public ArrayList<String> queryTransportationRequestByID(int serviceID){
        ArrayList<String> queryResult = new ArrayList<String>();
        try {
            String sql = "SELECT * FROM TransportationRequests WHERE serviceID = " + serviceID;
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            String transportationType;
            String destination;
            String reservationTime;

            while(result.next()) {
                transportationType = result.getString("transportationType");
                destination = result.getString("destination");
                reservationTime = result.getString("reservationTime");
                queryResult.add(transportationType);
                queryResult.add(destination);
                queryResult.add(reservationTime);
            }
        } catch (SQLException e) {
            System.out.println("Query Transportation Request Failed!");
            e.printStackTrace();
        }
        return queryResult;
    }
}
