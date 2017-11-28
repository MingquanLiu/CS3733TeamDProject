package edu.wpi.cs3733.programname.database.Tables;

import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.SQLException;

public class ServiceRequestsTable {

    public static void createServiceRequestsTable(DBConnection conn) {
        try {

            String newTable = "CREATE TABLE ServiceRequest(serviceID Integer, sender VARCHAR(15), " +
                    "receiver VARCHAR(15), node1ID VARCHAR(20), node2ID VARCHAR(20), " +
                    "serviceType VARCHAR(25), status VARCHAR(10), description VARCHAR(500), requestTime VARCHAR(30), " +
                    "handleTime VARCHAR(30), completionTime VARCHAR(30)," +
                    "CONSTRAINT ServiceRequests PRIMARY KEY (serviceID)," +
                    "CONSTRAINT ServiceRequests_FK1 FOREIGN KEY (sender) REFERENCES Employees (userName) ON DELETE SET NULL," +
                    "CONSTRAINT ServiceRequests_FK2 FOREIGN KEY (receiver) REFERENCES Employees (userName) ON DELETE SET NULL," +
                    "CONSTRAINT ServiceRequests_FK3 FOREIGN KEY (node1ID) REFERENCES Nodes (nodeID) ON DELETE CASCADE," +
                    "CONSTRAINT ServiceRequests_FK4 FOREIGN KEY (node2ID) REFERENCES Nodes (nodeID) ON DELETE CASCADE)";

            // Creates new StaffTitles table
            conn.execute(newTable);
            System.out.println("\nServiceRequest Table Created");



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}