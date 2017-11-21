package edu.wpi.cs3733.programname.database.Tables;

import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.SQLException;

public class ServiceRequestsTable {

    public static void createServiceRequests(DBConnection conn) {
        try {

            String newTable = "CREATE TABLE ServiceRequests(serviceID Integer, sender VARCHAR(15), " +
                    "receiver VARCHAR(15), node1 VARCHAR(10), node2 VARCHAR(10) default null, " +
                    "serviceType VARCHAR(25), status VARCHAR(10), description VARCHAR(500), requestTime VARCHAR(30), " +
                    "handleTime VARCHAR(30), completionTime VARCHAR(30))";

            // Creates new StaffTitles table
            conn.execute(newTable);
            System.out.println("\nEmployees Table Created");



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}