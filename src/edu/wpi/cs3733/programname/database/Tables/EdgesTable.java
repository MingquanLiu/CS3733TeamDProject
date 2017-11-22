package edu.wpi.cs3733.programname.database.Tables;

import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.SQLException;

public class EdgesTable {

    public static void createEdgesTables(DBConnection conn) {
        try {

            String newTable = "CREATE TABLE Edges(edgeID VARCHAR(25), startNode VARCHAR(20), endNode VARCHAR(20))";
//                    "CONSTRAINT Edges_PK PRIMARY KEY (edgeID)," +
//                    "CONSTRAINT Edges_FK1 FOREIGN KEY (startNode) REFERENCES Nodes (nodeID)," +
//                    "CONSTRAINT Edges_FK2 FOREIGN KEY (endNode) REFERENCES Nodes (nodeID))";

            // Creates new Edges table
            conn.execute(newTable);
            System.out.println("\nEdges Table Created");



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
