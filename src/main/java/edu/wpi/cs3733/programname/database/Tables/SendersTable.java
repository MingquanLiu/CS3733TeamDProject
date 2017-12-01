package edu.wpi.cs3733.programname.database.Tables;

import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.SQLException;

public class SendersTable {


    public static void createSendersTables(DBConnection conn) {
        try {

            String newTable = "CREATE TABLE Senders(username VARCHAR(15), senderID INTEGER, " +
                    "CONSTRAINT Senders_PK PRIMARY KEY (username, senderID)," +
                    "CONSTRAINT Senders_FK1 FOREIGN KEY (username) REFERENCES Employees (username))";

            // Creates new Edges table
            conn.execute(newTable);
            System.out.println("\nSenders Table Created");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}