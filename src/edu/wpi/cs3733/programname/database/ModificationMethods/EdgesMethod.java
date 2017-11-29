package edu.wpi.cs3733.programname.database.ModificationMethods;

import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.database.DBConnection;
import java.sql.SQLException;

public class EdgesMethod {

    private DBConnection dbConnection;
    public EdgesMethod(DBConnection dbConnection){this.dbConnection = dbConnection;}
    public void insertEdge(String node1ID, String node2ID) {
        String nodeID = node1ID + "_" + node2ID;
        String str;

        try {
            str = "INSERT INTO Edges VALUES('" + nodeID + "', '" + node1ID + "', '" + node2ID + "')";
            System.out.println(str);
            dbConnection.executeUpdate(str);

        } catch (SQLException e) {
            System.out.println("Add EdgeData Failed!");
            e.printStackTrace();
        }
    }


    public void modifyEdge( EdgeData data) {
        String edgeID = data.getEdgeID();
        String startNode = data.getStartNode();
        String endNode = data.getEndNode();
        String str;

        try {
            str = "UPDATE Edges SET startNode = '" + startNode + "', endNode = '" + endNode + "' where edgeId = '" + edgeID + "'";
            System.out.println(str);
            dbConnection.executeUpdate(str);

        } catch (SQLException e) {
            System.out.println("Add EdgeData Failed!");
            e.printStackTrace();
        }
    }

    /**
     * the given edge is deleted from the database (the nodes that make up the edge still exist)
     *
     * @param data the edge that we want to delete
     */
    public void removeEdge(EdgeData data) {
        String edgeID = data.getEdgeID();
        String str;

        try {
            str = "DELETE FROM Edges WHERE edgeID = '" + edgeID + "'";
            System.out.println(str);
            dbConnection.executeUpdate(str);

        } catch (SQLException e) {
            System.out.println("Delete EdgeData Failed!");
            e.printStackTrace();
        }
    }
}
