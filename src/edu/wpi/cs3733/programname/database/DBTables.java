package edu.wpi.cs3733.programname.database;
import java.sql.*;


public class DBTables {
    public DBTables(){
    }
    public static void createNodesTables(DBConnection conn) {
        try {
            if(conn == null){
                System.out.println("1");
            }
            if(conn.getConnection() ==null){
                System.out.println("2");
            }
            DatabaseMetaData dbm = conn.getConnection().getMetaData();
            // check if "Nodes" table is there
            ResultSet tables = dbm.getTables(null, null, "NODES", null);
            if (!tables.next()) {
                String newTable = "CREATE TABLE Nodes(nodeID VARCHAR(10), xcoord INTEGER, ycoord INTEGER, floor VARCHAR(3), building VARCHAR(20), " +
                                    "nodeType VARCHAR(4), longName VARCHAR(50), shortName VARCHAR(30), teamAssigned VARCHAR(6)," +
                                    "CONSTRAINT Nodes PRIMARY KEY (nodeID)," +
                                    "CONSTRAINT Nodes_nodeType CHECK (nodeType IN " +
                                    "('Hall', 'ELEV', 'REST', 'STAI', 'DEPT', 'LABS', 'INFO', 'CONF', 'EXIT', 'RETL', 'SERV'))," +
                                    "CONSTRAINT Nodes_building CHECK (building IN " +
                                    "('BMT', 'Shapiro', 'Tower', '45 Francis', '15 Francis'))," +
                                    "CONSTRAINT Nodes_xcoordVal check (xcoord >= 0 AND xcoord <= 9000)," +
                                    "CONSTRAINT Nodes_ycoordVal check (ycoord >= 0 AND ycoord <= 9000))";
                // Creates new Nodes table
                conn.execute(newTable);
                System.out.println("\nNodes Table Created\n");

            } else {
                String dropTable = ("DROP TABLE Nodes");
                // Drops Nodes table
                conn.execute(dropTable);
                System.out.println("\nNodes Table Dropped");


                String newTable = "CREATE TABLE Nodes(nodeID VARCHAR(10), xcoord INTEGER, ycoord INTEGER, floor VARCHAR(3), building VARCHAR(20), " +
                                    "nodeType VARCHAR(4), longName VARCHAR(50), shortName VARCHAR(30), teamAssigned VARCHAR(6)," +
                                    "CONSTRAINT Nodes PRIMARY KEY (nodeID)," +
                                    "CONSTRAINT Nodes_nodeType CHECK (nodeType IN " +
                                    "('Hall', 'ELEV', 'REST', 'STAI', 'DEPT', 'LABS', 'INFO', 'CONF', 'EXIT', 'RETL', 'SERV'))," +
                                    "CONSTRAINT Nodes_building CHECK (building IN " +
                                    "('BMT', 'Shapiro', 'Tower', '45 Francis', '15 Francis'))," +
                                    "CONSTRAINT Nodes_xcoordVal check (xcoord >= 0 AND xcoord <= 9000)," +
                                    "CONSTRAINT Nodes_ycoordVal check (ycoord >= 0 AND ycoord <= 9000))";
                // Creates new nodes table
                conn.execute(newTable);
                System.out.println("Nodes Table Created\n");
            }
        } catch (SQLException e) {

        }
    }



    public static void createEdgesTables(DBConnection conn) {
        try {
            DatabaseMetaData dbm = conn.getConnection().getMetaData();
            // check if "Edges" table is there
            ResultSet tables = dbm.getTables(null, null, "EDGES", null);
            if (!tables.next()) {
                String newTable = "CREATE TABLE Edges(edgeID VARCHAR(21), startNode VARCHAR(10), endNode VARCHAR(10)," +
                                    "CONSTRAINT Edges_PK PRIMARY KEY (edgeID)," +
                                    "CONSTRAINT Edges_FK1 FOREIGN KEY (startNode) REFERENCES Nodes (nodeID)," +
                                    "CONSTRAINT Edges_FK2 FOREIGN KEY (endNode) REFERENCES Nodes (nodeID))";

                // Creates new Edges table
                conn.execute(newTable);
                System.out.println("\nEdges Table Created");
            } else {
                String dropTable = ("DROP TABLE Edges");
                // Drops Edges table
                conn.execute(dropTable);
                System.out.println("\nEdges Table Dropped");

                String newTable = "CREATE TABLE Edges(edgeID VARCHAR(21), startNode VARCHAR(10), endNode VARCHAR(10)," +
                                    "CONSTRAINT Edges_PK PRIMARY KEY (edgeID)," +
                                    "CONSTRAINT Edges_FK1 FOREIGN KEY (startNode) REFERENCES Nodes (nodeID)," +
                                    "CONSTRAINT Edges_FK2 FOREIGN KEY (endNode) REFERENCES Nodes (nodeID))";
                // Creates new Edges table
                conn.execute(newTable);
                System.out.println("Edges Table Created\n");
            }

        } catch (SQLException e) {

        }
    }


    public void createStaffTable (DBConnection conn) {
        try {
            DatabaseMetaData dbm = conn.getConnection().getMetaData();
            // check if "Staff" table is there
            ResultSet tables = dbm.getTables(null, null, "STAFF", null);
            if (!tables.next()) {
                String newTable = "CREATE TABLE Staff (accountName VARCHAR(15), accountPassword VARCHAR(20)," +
                                    "firstName VARCHAR(15), middleInitial CHAR(1) lastName VARCHAR(20)," +
                                    "CONSTRAINT CSStaff_PK PRIMARY KEY (accountName)," +
                                    "CONSTRAINT CSStaff_firstNameVal CHECK (firstName IS NOT NULL)," +
                                    "CONSTRAINT CSStaff_middleInitialVal CHECK (middleInitial IS NOT NULL)," +
                                    "CONSTRAINT CSStaff_lastNameVal CHECK (lastName IS NOT NULL))";

                // Creates new nodes table
                conn.execute(newTable);
                System.out.println("\nStaff Table Created");
            } else {
                String dropTable = ("DROP TABLE Staff");
                // Drops edges table
                conn.execute(dropTable);
                System.out.println("\nStaff Table Dropped");

                String newTable = "CREATE TABLE Staff (accountName VARCHAR(15), accountPassword VARCHAR(20)," +
                                    "firstName VARCHAR(15), middleInitial CHAR(1) lastName VARCHAR(20)," +
                                    "CONSTRAINT CSStaff_PK PRIMARY KEY (accountName)," +
                                    "CONSTRAINT CSStaff_firstNameVal CHECK (firstName IS NOT NULL)," +
                                    "CONSTRAINT CSStaff_middleInitialVal CHECK (middleInitial IS NOT NULL)," +
                                    "CONSTRAINT CSStaff_lastNameVal CHECK (lastName IS NOT NULL))";
                // Creates new Staff table
                conn.execute(newTable);
                System.out.println("Staff Table Created\n");
            }

        } catch (SQLException e) {

        }
    }
    }
}