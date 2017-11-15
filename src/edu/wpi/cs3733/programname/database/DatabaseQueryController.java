package edu.wpi.cs3733.programname.database;

import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.Edge;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryController {

    ManageController manager;

    public DatabaseQueryController(ManageController manager) {
        this.manager = manager;
    }

    public NodeData queryNodeById(String nodeId){
        NodeData queryResult = null;

        Connection dbConnection;
        DBConnection db = new DBConnection();
        db.setDBConnection();
        dbConnection = db.getConnection();

        try {
            String sql = "SELECT * FROM Nodes " +
                    "WHERE nodeID = " + "'" + nodeId + "'";
            Statement stmt = dbConnection.createStatement();
            ResultSet result = stmt.executeQuery(sql);

            String id = "";
            int x = 0;
            int y = 0;
            String floor = "";
            String nodeType = "";
            String longName = "";
            String shortName = "";

            while(result.next()) {
                id = result.getString("nodeID");
                x = result.getInt("xcoord");
                y = result.getInt("ycoord");
                floor = result.getString("floor");
                nodeType = result.getString("nodeType");
                longName = result.getString("longName");
                shortName = result.getString("shortName");
            }

            Coordinate location = new Coordinate(x,y);
            queryResult = new NodeData(id, location, floor, nodeType,
                    longName, shortName);

            dbConnection.close();
        } catch (SQLException e) {
            System.out.println("Node Query Failed!");
            e.printStackTrace();
        }
        return queryResult;
    }

    public Edge queryEdgeById(String edgeId) {
        Edge queryResult = null;

        Connection dbConnection;
        DBConnection db = new DBConnection();
        db.setDBConnection();
        dbConnection = db.getConnection();

        String id = "";
        String startNodeId = "";
        String endNodeId = "";
        try {
            String sql = "SELECT * FROM Edges WHERE edgeID = " + "'" + edgeId + "'";
            Statement stmt = dbConnection.createStatement();
            ResultSet result = stmt.executeQuery(sql);

            while(result.next()) {
                id = result.getString("edgeID");
                startNodeId = result.getString("startNode");
                endNodeId = result.getString("endNode");
            }
        }
        catch (SQLException e) {
            System.out.println("Insert Node Failed!");
            e.printStackTrace();
        }
        queryResult = new Edge(startNodeId, endNodeId, id);
        return queryResult;
    }

    public List<NodeData> getAllNodeData() {
        NodeData queryResult = null;
        List<NodeData> allNodes = new ArrayList<NodeData>();

        Connection dbConnection;
        DBConnection db = new DBConnection();
        db.setDBConnection();
        dbConnection = db.getConnection();

        try {
            String sql = "SELECT * FROM Nodes";
            Statement stmt = dbConnection.createStatement();
            ResultSet result = stmt.executeQuery(sql);

            String id = "";
            int x = 0;
            int y = 0;
            String floor = "";
            String nodeType = "";
            String longName = "";
            String shortName = "";

            while(result.next()) {
                id = result.getString("nodeID");
                x = result.getInt("xcoord");
                y = result.getInt("ycoord");
                floor = result.getString("floor");
                nodeType = result.getString("nodeType");
                longName = result.getString("longName");
                shortName = result.getString("shortName");
                Coordinate location = new Coordinate(x,y);
                queryResult = new NodeData(id, location, floor, nodeType,
                        longName, shortName);
                allNodes.add(queryResult);
            }
            dbConnection.close();
        } catch (SQLException e) {
            System.out.println("Insert Node Failed!");
            e.printStackTrace();
        }
        return allNodes;
    }

    public List<Edge> getAllEdgeData() {
        Edge queryResult = null;
        List<Edge> allEdges = new ArrayList<Edge>();

        Connection dbConnection;
        DBConnection db = new DBConnection();
        db.setDBConnection();
        dbConnection = db.getConnection();

        String id = "";
        String startNodeId = "";
        String endNodeId = "";
        try {
            String sql = "SELECT * FROM Edges";
            Statement stmt = dbConnection.createStatement();
            ResultSet result = stmt.executeQuery(sql);

            while(result.next()) {
                id = result.getString("edgeID");
                startNodeId = result.getString("startNode");
                endNodeId = result.getString("endNode");
                queryResult = new Edge(startNodeId, endNodeId, id);
                allEdges.add(queryResult);
            }
        }
        catch (SQLException e) {
            System.out.println("Insert Node Failed!");
            e.printStackTrace();
        }
        return allEdges;
    }



}
