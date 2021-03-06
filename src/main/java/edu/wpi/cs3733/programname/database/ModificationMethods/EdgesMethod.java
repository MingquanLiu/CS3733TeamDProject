package edu.wpi.cs3733.programname.database.ModificationMethods;


import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.database.CsvWriter;
import edu.wpi.cs3733.programname.database.DBConnection;
import java.sql.SQLException;

public class EdgesMethod {

    private DBConnection dbConnection;
    private CsvWriter wrt;
    public EdgesMethod(DBConnection dbConnection){
        this.dbConnection = dbConnection;
        this.wrt = new CsvWriter();}

    public void insertEdge(String node1ID, String node2ID) {
        String nodeID = node1ID + "_" + node2ID;
        String str;

        try {
            str = "INSERT INTO Edges VALUES('" + nodeID + "', '" + node1ID + "', '" + node2ID + "')";
            System.out.println(str);
            dbConnection.executeUpdate(str);
            this.wrt.writeEdges(dbConnection.getConnection());

        } catch (SQLException e) {
            System.out.println("Add Edge Failed!");
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
            this.wrt.writeEdges(dbConnection.getConnection());

        } catch (SQLException e) {
            System.out.println("Modify Edge Failed!");
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
            this.wrt.writeEdges(dbConnection.getConnection());

        } catch (SQLException e) {
            System.out.println("Delete Edge Failed!");
            e.printStackTrace();
        }
    }



}
