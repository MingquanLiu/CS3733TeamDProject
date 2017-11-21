package edu.wpi.cs3733.programname.database.Tables;

import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.SQLException;

public class NodesTable {

    public static void createNodesTables(DBConnection conn) {
        try {
            if (conn == null) {
                System.out.println("1");
            }
            if (conn.getConnection() == null) {
                System.out.println("2");
            }

            String newTable = "CREATE TABLE Nodes(nodeID VARCHAR(10), xcoord INTEGER, ycoord INTEGER, floor VARCHAR(3), building VARCHAR(20), " +
                    "nodeType VARCHAR(4), longName VARCHAR(50), shortName VARCHAR(30), teamAssigned VARCHAR(6)," +
                    "CONSTRAINT Nodes PRIMARY KEY (nodeID)," +
                    "CONSTRAINT Nodes_nodeType CHECK (nodeType IN " +
                    "('HALL', 'ELEV', 'REST', 'STAI', 'DEPT', 'LABS', 'INFO', 'CONF', 'EXIT', 'RETL', 'SERV'))," +
                    "CONSTRAINT Nodes_building CHECK (building IN " +
                    "('BMT', 'Shapiro', 'Tower', '45 Francis', '15 Francis'))," +
                    "CONSTRAINT Nodes_xcoordVal check (xcoord >= 0 AND xcoord <= 5000)," +
                    "CONSTRAINT Nodes_ycoordVal check (ycoord >= 0 AND ycoord <= 3400))";
            // Creates new Nodes table
            conn.execute(newTable);
            System.out.println("\nNodes Table Created\n");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
