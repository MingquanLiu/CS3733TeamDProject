package edu.wpi.cs3733.programname.database.Tables;

import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.SQLException;

public class ServiceRequestsTable {

    public static void createServiceRequestsTable(DBConnection conn) {
        try {

            String newTable = "CREATE TABLE ServiceRequest(serviceID Integer, sender VARCHAR(15), " +
                    "receiver VARCHAR(15), location1 VARCHAR(20), location2 VARCHAR(20), " +
                    "serviceType VARCHAR(25), status VARCHAR(10), description VARCHAR(500), requestTime VARCHAR(30), " +
                    "handleTime VARCHAR(30), completionTime VARCHAR(30)," +
                    "CONSTRAINT ServiceRequests PRIMARY KEY (serviceID)," +
                    "CONSTRAINT ServiceRequests_FK1 FOREIGN KEY (sender) REFERENCES Employees (userName)," +
                    "CONSTRAINT ServiceRequests_FK2 FOREIGN KEY (receiver) REFERENCES Employees (userName)," +
                    "CONSTRAINT ServiceRequests_FK3 FOREIGN KEY (location1) REFERENCES Nodes (nodeID) ON DELETE CASCADE," +
                    "CONSTRAINT ServiceRequests_FK4 FOREIGN KEY (location2) REFERENCES Nodes (nodeID) ON DELETE CASCADE)";

            // Creates new StaffTitles table
            conn.execute(newTable);
            System.out.println("\nServiceRequest Table Created");



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}