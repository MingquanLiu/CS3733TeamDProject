package edu.wpi.cs3733.programname.database.QueryMethods;

import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EdgesQuery {


    public static List<EdgeData> getAllEdgeInfo(DBConnection dbConnection) {
        EdgeData queryResult = null;
        List<EdgeData> allEdges = new ArrayList<EdgeData>();

        String edgeID = "";
        String startNode = "";
        String endNode = "";

        try {
            String sql = "SELECT * FROM Edges";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);

            while(result.next()) {
                edgeID = result.getString("edgeID");
                startNode = result.getString("startNode");
                endNode = result.getString("endNode");

                queryResult = new EdgeData(edgeID, startNode, endNode);
                allEdges.add(queryResult);
            }
        }
        catch (SQLException e) {
            System.out.println("Get Edge Failed!");
            e.printStackTrace();
        }
        return allEdges;
    }



    public static EdgeData queryEdgeByID(DBConnection dbConnection, String eID) {
        EdgeData queryResult = null;

        String edgeID = "";
        String startNode = "";
        String endNode = "";

        try {
            String sql = "SELECT * FROM Edges WHERE edgeID = " + "'" + eID + "'";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);

            while(result.next()) {
                edgeID = result.getString("edgeID");
                startNode = result.getString("startNode");
                endNode = result.getString("endNode");
            }
        }
        catch (SQLException e) {
            System.out.println("Get Edge Failed!");
            e.printStackTrace();
        }
        queryResult = new EdgeData(edgeID, startNode, endNode);
        return queryResult;
    }
}
