package edu.wpi.cs3733.programname.database;

import edu.wpi.cs3733.programname.database.Tables.EdgesTable;
import edu.wpi.cs3733.programname.database.Tables.EmployeesTable;
import edu.wpi.cs3733.programname.database.Tables.NodesTable;
import edu.wpi.cs3733.programname.database.Tables.ServiceRequestsTable;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import static edu.wpi.cs3733.programname.database.Tables.EdgesTable.createEdgesTables;
import static edu.wpi.cs3733.programname.database.Tables.EmployeesTable.createEmployeesTables;
import static edu.wpi.cs3733.programname.database.Tables.NodesTable.createNodesTables;

//import edu.wpi.cs3733.programname.database.Tables.ServiceRequestsTable;
//import static edu.wpi.cs3733.programname.database.Tables.ServiceRequestsTable.*;


public class DBTables {

    public DBTables() {
    }

    public static void createAllTables(DBConnection conn) {
        System.out.println("About to drop");
        try {
            DatabaseMetaData dbm = conn.getConnection().getMetaData();
            // check if "StaffTitles" table is there
            ResultSet tables1 = dbm.getTables(null, null, "NODES", null);
            ResultSet tables2 = dbm.getTables(null, null, "EDGES", null);
            ResultSet tables3 = dbm.getTables(null, null, "EMPLOYEES", null);
            ResultSet tables4 = dbm.getTables(null, null, "SERVICEREQUEST", null);
            if (!tables1.next()) {
                NodesTable.createNodesTables(conn);
            }
            else if (!tables2.next()){
                EdgesTable.createEdgesTables(conn);
                }
            else if (!tables3.next()){
                EmployeesTable.createEmployeesTables(conn);
            }
            else if (!tables4.next()){
                ServiceRequestsTable.createServiceRequestsTable(conn);}
            else {

                String dropTable1 = ("DROP TABLE Edges");
                String dropTable2 = ("DROP TABLE ServiceRequest");
                String dropTable3 = ("DROP TABLE Employees");
                String dropTable4 = ("DROP TABLE Nodes");
                // Drops StaffTitles table
                conn.execute(dropTable1);
                conn.execute(dropTable2);
                conn.execute(dropTable3);
                conn.execute(dropTable4);
                System.out.println("\nAll Tables Dropped");

                createNodesTables(conn);
                createEdgesTables(conn);
                createEmployeesTables(conn);
//                createServiceRequestsTable(conn);


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}







//    public static void createAllTables(DBConnection conn) {
//        try {
//        ArrayList<String> tableNames = initTableArray();
//        DatabaseMetaData dbm = conn.getConnection().getMetaData();
//        Statement stmt = conn.getConnection().createStatement();
//        String X = "";
//
//        ResultSet allTables = dbm.getTables(null, null, X, null);
//
//        System.out.println(tableNames);
//
//
//            for (int i = 0; i < tableNames.size(); i++) {
//                X = tableNames.get(i).toString();
//                System.out.println(X.toString());
//
//                if (allTables.next()) {
//                    stmt.execute("DROP TABLE " + X);
//                    System.out.println(X + "Table Dropped");
//                }
//            }
//        } catch (SQLException e) {
//            createNodesTables(conn);
//            createEdgesTables(conn);
//            createStaffTables(conn);
//            createPhoneExtensionsTables(conn);
//            createTitlesTables(conn);
//            createStaffTitlesTables(conn);
//
//        }
//    }
//
//
//
//
//
//    public static ArrayList<String> initTableArray(){
//        ArrayList<String> tableNames = new ArrayList<String>();
//        tableNames.add("Staff");
//        tableNames.add("Titles");
//        tableNames.add("StaffTitles");
//        tableNames.add("PhoneExtensions");
//        tableNames.add("Nodes");
//        tableNames.add("Edges");
//
//
//
//
//
//        return tableNames;
//    }
//
//
//    public static void createNodesTables(DBConnection conn) {
//        try {
//
//            String newTable = "CREATE TABLE Nodes(nodeID VARCHAR(10), xcoord INTEGER, ycoord INTEGER, floor VARCHAR(3), building VARCHAR(20), " +
//                    "nodeType VARCHAR(4), longName VARCHAR(50), shortName VARCHAR(30), teamAssigned VARCHAR(6)," +
//                    "CONSTRAINT Nodes PRIMARY KEY (nodeID)," +
//                    "CONSTRAINT Nodes_nodeType CHECK (nodeType IN " +
//                    "('Hall', 'ELEV', 'REST', 'STAI', 'DEPT', 'LABS', 'INFO', 'CONF', 'EXIT', 'RETL', 'SERV'))," +
//                    "CONSTRAINT Nodes_building CHECK (building IN " +
//                    "('BMT', 'Shapiro', 'Tower', '45 Francis', '15 Francis'))," +
//                    "CONSTRAINT Nodes_xcoordVal check (xcoord >= 0 AND xcoord <= 9000)," +
//                    "CONSTRAINT Nodes_ycoordVal check (ycoord >= 0 AND ycoord <= 9000))";
//            // Creates new Nodes table
//            conn.execute(newTable);
//            System.out.println("\nNodes Table Created\n");
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//    public static void createEdgesTables(DBConnection conn) {
//        try {
//
//
//            String newTable = "CREATE TABLE Edges(edgeID VARCHAR(21), startNode VARCHAR(10), endNode VARCHAR(10)," +
//                    "CONSTRAINT Edges_PK PRIMARY KEY (edgeID)," +
//                    "CONSTRAINT Edges_FK1 FOREIGN KEY (startNode) REFERENCES Nodes (nodeID)," +
//                    "CONSTRAINT Edges_FK2 FOREIGN KEY (endNode) REFERENCES Nodes (nodeID))";
//
//            // Creates new Edges table
//            conn.execute(newTable);
//            System.out.println("\nEdges Table Created");
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static void createStaffTables (DBConnection conn) {
//        try {
//
//            String newTable = "CREATE TABLE Staff (accountName VARCHAR(15), accountPassword VARCHAR(20), firstName VARCHAR(15), " +
//                    "middleInitial CHAR(1), lastName VARCHAR(20)," +
//                    "CONSTRAINT Staff_PK PRIMARY KEY (accountName)," +
//                    "CONSTRAINT Staff_firstNameVal CHECK (firstName IS NOT NULL)," +
//                    "CONSTRAINT Staff_middleInitialVal CHECK (middleInitial IS NOT NULL)," +
//                    "CONSTRAINT Staff_lastNameVal CHECK (lastName IS NOT NULL))";
//
//            // Creates new Staff table
//            conn.execute(newTable);
//            System.out.println("\nStaff Table Created");
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//    public static void createPhoneExtensionsTables (DBConnection conn) {
//        try {
//
//            String newTable = "CREATE TABLE PhoneExtensions(accountName VARCHAR(15), phoneExt INTEGER," +
//                    "CONSTRAINT PhoneExtensions_PK PRIMARY KEY (accountName, phoneExt)," +
//                    "CONSTRAINT PhoneExtensions_FK1 FOREIGN KEY (accountName) REFERENCES Staff (accountName))";
//
//            // Creates new PhoneExtensions table
//            conn.execute(newTable);
//            System.out.println("\nPhoneExtensions Table Created");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//        }
//    }
//
//
//
//    public static void createTitlesTables (DBConnection conn) {
//        try {
//
//            String newTable = "CREATE TABLE Titles (acronym VARCHAR(20), titleName VARCHAR(50)," +
//                    "CONSTRAINT Titles_PK PRIMARY KEY (acronym)," +
//                    "CONSTRAINT Titles_U1 UNIQUE (titleName))";
//
//            // Creates new Titles table
//            conn.execute(newTable);
//            System.out.println("\nTitles Table Created");
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static void createStaffTitlesTables (DBConnection conn) {
//        try {
//
//            String newTable = "CREATE TABLE StaffTitles (accountName VARCHAR(15), acronym VARCHAR(20)," +
//                    "CONSTRAINT StaffTitles_PK PRIMARY KEY (accountName, acronym)," +
//                    "CONSTRAINT StaffTitles_FK1 FOREIGN KEY (accountName) REFERENCES Staff (accountName)," +
//                    "CONSTRAINT StaffTitles_FK2 FOREIGN KEY (acronym) REFERENCES Titles (acronym))";
//
//            // Creates new StaffTitles table
//            conn.execute(newTable);
//            System.out.println("\nStaffTitles Table Created");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //TODO create all method to make all tables and drop all tables to avoid dependency errors
//}