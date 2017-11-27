package edu.wpi.cs3733.programname.database.Tables;

import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.SQLException;

public class EmployeesTable {

    public static void createEmployeesTables(DBConnection conn) {
        try {

            String newTable = "CREATE TABLE Employees(username VARCHAR(15), password VARCHAR(20), firstName VARCHAR(15), " +
                    "middleName VARCHAR(10), lastName VARCHAR(20), sysAdmin Integer, serviceType VARCHAR(20)," +
                    "CONSTRAINT Employees_PK PRIMARY KEY (userName), " +
                    "CONSTRAINT serviceType_VAL CHECK (serviceType IN ('interpreter', 'maintenance', 'transportation', 'none')))";

            // Creates new StaffTitles table
            conn.execute(newTable);
            System.out.println("\nEmployees Table Created");



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
