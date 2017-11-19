package edu.wpi.cs3733.programname.database;

import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryController {

    ManageController manager;
    DBConnection dbConnection;

    public DatabaseQueryController(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public NodeData queryNodeById(String nID) {
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

    public EdgeData queryEdgeById(String eID) {
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
            System.out.println("Insert Node Failed!");
            e.printStackTrace();
        }
        queryResult = new EdgeData(edgeID, startNode, endNode);
        return queryResult;
    }


    public List<NodeData> getAllNodeData() {
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

    public List<EdgeData> getAllEdgeData() {
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
            System.out.println("Insert Node Failed!");
            e.printStackTrace();
        }
        return allEdges;
    }


    public List<NodeData> queryNodeByType(String nType) {

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
