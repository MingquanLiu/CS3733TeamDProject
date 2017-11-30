package edu.wpi.cs3733.programname.database.Tables;

import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.SQLException;

public class ReceiversTable {

    public static void createReceiversTables(DBConnection conn) {
        try {

            String newTable = "CREATE TABLE Receivers(username VARCHAR(15), receiverID INTEGER," +
                    "CONSTRAINT Receivers_PK PRIMARY KEY (username, receiverID)," +
                    "CONSTRAINT Receivers_FK1 FOREIGN KEY (username) REFERENCES Employees (username))";

            // Creates new Edges table
            conn.execute(newTable);
            System.out.println("\nReceivers Table Created");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

