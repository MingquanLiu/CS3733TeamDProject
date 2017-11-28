package edu.wpi.cs3733.programname.database.Tables;

import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.SQLException;

public class ServiceRequestsTable {

    public static void createServiceRequestsTable(DBConnection conn) {
        try {

            String newTable = "CREATE TABLE ServiceRequest(serviceID Integer, sender VARCHAR(15), " +
                    "receiver VARCHAR(15), serviceType VARCHAR(25), location1ID VARCHAR(10), location2 VARCHAR(10), " +
                    "description VARCHAR(500), requestTime VARCHAR(30), " +
                    "handleTime VARCHAR(30), completionTime VARCHAR(30), status VARCHAR(10), " +
                    "CONSTRAINT ServiceRequests PRIMARY KEY (serviceID)," +
                    "CONSTRAINT ServiceRequests_FK1 FOREIGN KEY (sender) REFERENCES Employees (userName)," +
                    "CONSTRAINT ServiceRequests_FK2 FOREIGN KEY (receiver) REFERENCES Employees (userName)," +
                    "CONSTRAINT ServiceRequests_FK3 FOREIGN KEY (location1) REFERENCES Nodes (nodeID)," +
                    "CONSTRAINT ServiceRequests_FK4 FOREIGN KEY (location2) REFERENCES Nodes (nodeID))";

            // Creates new StaffTitles table
            conn.execute(newTable);
            System.out.println("\nServiceRequest Table Created");



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}