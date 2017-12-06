package edu.wpi.cs3733.programname.database.QueryMethods;

import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class InterpreterRequestsQuery {
    private DBConnection dbConnection;
    public InterpreterRequestsQuery(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public ArrayList<String> queryInterpreterRequestByID(int serviceID){
        ArrayList<String> queryResult = new ArrayList<String>();
        try {
            String sql = "SELECT * FROM InterpreterRequests WHERE serviceID = " + serviceID;
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            String language;
            String reservationTime;

            while(result.next()) {
                language = result.getString("language");
                reservationTime = result.getString("reservationTime");
                queryResult.add(language);
                queryResult.add(reservationTime);
            }
        } catch (SQLException e) {
            System.out.println("Query Interpreter Request Failed!");
            e.printStackTrace();
        }
        return queryResult;
    }
}
