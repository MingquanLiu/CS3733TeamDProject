package edu.wpi.cs3733.programname.database.QueryMethods;

import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NodesQuery {

    public static NodeData queryNodeByID(DBConnection dbConnection, String nID) {
        NodeData queryResult = null;

        try {
            String sql = "SELECT * FROM Nodes " +
                    "WHERE nodeID = " + "'" + nID + "'";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);

            String nodeID = "";
            int xcoord = 0;
            int ycoord = 0;
            String floor = "";
            String building = "";
            String nodeType = "";
            String longName = "";
            String shortName = "";
            String teamAssigned = "";

            while(result.next()) {
                nodeID = result.getString("nodeID");
                xcoord = result.getInt("xcoord");
                ycoord = result.getInt("ycoord");
                floor = result.getString("floor");
                building = result.getString("building");
                nodeType = result.getString("nodeType");
                longName = result.getString("longName");
                shortName = result.getString("shortName");
                teamAssigned = result.getString("teamAssigned");
            }

            Coordinate location = new Coordinate(xcoord, ycoord);
            queryResult = new NodeData(nodeID, location, floor, building, nodeType, longName, shortName, teamAssigned);
        } catch (SQLException e) {
            System.out.println("Insert Node Failed!");
            e.printStackTrace();
        }
        return queryResult;
    }


    public static List<NodeData> getAllNodeInfo(DBConnection dbConnection) {
        NodeData queryResult = null;
        ArrayList<NodeData> allNodes = new ArrayList<NodeData>();

        try {
            String sql = "SELECT * FROM Nodes";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);

            String nodeID = "";
            int xcoord = 0;
            int ycoord = 0;
            String floor = "";
            String building = "";
            String nodeType = "";
            String longName = "";
            String shortName = "";
            String teamAssigned = "";

            while(result.next()) {
                nodeID = result.getString("nodeID");
                xcoord = result.getInt("xcoord");
                ycoord = result.getInt("ycoord");
                floor = result.getString("floor");
                building = result.getString("building");
                nodeType = result.getString("nodeType");
                longName = result.getString("longName");
                shortName = result.getString("shortName");
                teamAssigned = result.getString("teamAssigned");

                Coordinate location = new Coordinate(xcoord, ycoord);
                queryResult = new NodeData(nodeID, location, floor, building, nodeType, longName, shortName, teamAssigned);
                allNodes.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Insert Node Failed!");
            e.printStackTrace();
        }
        return allNodes;
    }


    public static List<NodeData> getAllNodeData(DBConnection dbConnection) {
        NodeData queryResult = null;
        List<NodeData> allNodes = new ArrayList<NodeData>();

        try {
            String sql = "SELECT * FROM Nodes";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);

            String nodeID = "";
            int xcoord = 0;
            int ycoord = 0;
            String floor = "";
            String building = "";
            String nodeType = "";
            String longName = "";
            String shortName = "";
            String teamAssigned = "";

            while(result.next()) {
                nodeID = result.getString("nodeID");
                xcoord = result.getInt("xcoord");
                ycoord = result.getInt("ycoord");
                floor = result.getString("floor");
                building = result.getString("building");
                nodeType = result.getString("nodeType");
                longName = result.getString("longName");
                shortName = result.getString("shortName");
                teamAssigned = result.getString("teamAssigned");

                Coordinate location = new Coordinate(xcoord, ycoord);
                queryResult = new NodeData(nodeID, location, floor, building, nodeType, longName, shortName, teamAssigned);
                allNodes.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Insert Node Failed!");
            e.printStackTrace();
        }
        return allNodes;
    }


    public static List<NodeData> queryNodeByType(DBConnection dbConnection, String nType) {

        NodeData queryResult = null;
        List<NodeData> allNodeTypes = new ArrayList<NodeData>();

        try {
            String sql = "SELECT * FROM Nodes WHERE nodeType = " + "'" + nType + "'";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);

            String nodeID = "";
            int xcoord = 0;
            int ycoord = 0;
            String floor = "";
            String building = "";
            String nodeType = "";
            String longName = "";
            String shortName = "";
            String teamAssigned = "";

            while(result.next()) {
                nodeID = result.getString("nodeID");
                xcoord = result.getInt("xcoord");
                ycoord = result.getInt("ycoord");
                floor = result.getString("floor");
                building = result.getString("building");
                nodeType = result.getString("nodeType");
                longName = result.getString("longName");
                shortName = result.getString("shortName");
                teamAssigned = result.getString("teamAssigned");

                Coordinate location = new Coordinate(xcoord, ycoord);
                queryResult = new NodeData(nodeID, location, floor, building, nodeType, longName, shortName, teamAssigned);
                allNodeTypes.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Insert Node Failed!");
            e.printStackTrace();
        }
        return allNodeTypes;

    }
}
