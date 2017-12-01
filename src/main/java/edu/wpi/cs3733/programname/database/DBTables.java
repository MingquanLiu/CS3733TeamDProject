package edu.wpi.cs3733.programname.database;

import edu.wpi.cs3733.programname.database.Tables.EdgesTable;
import edu.wpi.cs3733.programname.database.Tables.EmployeesTable;
import edu.wpi.cs3733.programname.database.Tables.NodesTable;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import static edu.wpi.cs3733.programname.database.Tables.EdgesTable.createEdgesTables;
import static edu.wpi.cs3733.programname.database.Tables.EmployeesTable.createEmployeesTables;
import static edu.wpi.cs3733.programname.database.Tables.NodesTable.createNodesTables;
import static edu.wpi.cs3733.programname.database.Tables.ServiceRequestsTable.createServiceRequestsTable;
import static edu.wpi.cs3733.programname.database.Tables.SendersTable.createSendersTables;
import static edu.wpi.cs3733.programname.database.Tables.ReceiversTable.createReceiversTables;
//import edu.wpi.cs3733.programname.database.Tables.ServiceRequestsTable;
//import static edu.wpi.cs3733.programname.database.Tables.ServiceRequestsTable.*;


public class DBTables {

    public DBTables() {
    }

    public static void createAllTables(DBConnection conn) {

        try {
            DatabaseMetaData dbm = conn.getConnection().getMetaData();
            // check if "StaffTitles" table is there
            ResultSet tables1 = dbm.getTables(null, null, "NODES", null);
            ResultSet tables2 = dbm.getTables(null, null, "EDGES", null);
            ResultSet tables3 = dbm.getTables(null, null, "EMPLOYEES", null);
            ResultSet tables4 = dbm.getTables(null, null, "SENDERS", null);
            ResultSet tables5 = dbm.getTables(null, null, "RECEIVERS", null);
            ResultSet tables6 = dbm.getTables(null, null, "SERVICEREQUESTS", null);


            if (tables6.next() || tables6.getFetchSize() == 0){
                System.out.println("About to drop service requests table");
                String dropTableSR = ("DROP TABLE ServiceRequests");
                conn.execute(dropTableSR);
            }
            if (tables5.next() || tables5.getFetchSize() == 0){
                System.out.println("About to drop receivers table");
                String dropReceiversTable = ("DROP TABLE Receivers");
                conn.execute(dropReceiversTable);
            }

            if (tables4.next() || tables4.getFetchSize() == 0) {
                System.out.println("About to drop senders table");
                String dropSendersTable = ("DROP TABLE Senders");
                conn.execute(dropSendersTable);
            }
            if (tables3.next() || tables3.getFetchSize() == 0){
                System.out.println("About to drop employees table");
                String dropEmployeesTable = ("DROP TABLE Employees");
                conn.execute(dropEmployeesTable);
            }

            if (tables2.next() || tables2.getFetchSize() == 0) {
                System.out.println("About to drop edge table");
                String dropEdgeTable = ("DROP TABLE Edges");
                conn.execute(dropEdgeTable);
            }

            if (tables1.next() || tables1.getFetchSize() == 0) {
                System.out.println("About to drop node table");
                String dropNodeTable = ("DROP TABLE Nodes");
                conn.execute(dropNodeTable);
            }
            System.out.println("About to create node table");
            createNodesTables(conn);
            System.out.println("About to create edge table");
            createEdgesTables(conn);
            System.out.println("About to create employee table");
            createEmployeesTables(conn);
            System.out.println("About to create senders table");
            createSendersTables(conn);
            System.out.println("About to create receivers table");
            createReceiversTables(conn);
            System.out.println("About to create service request table");
            createServiceRequestsTable(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("\nAll Tables Created Successfully!");
    }
}
