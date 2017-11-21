package edu.wpi.cs3733.programname.database;

import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.database.QueryMethods.NodesQuery;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static edu.wpi.cs3733.programname.database.QueryMethods.EdgesQuery.queryEdgeByID;
import static edu.wpi.cs3733.programname.database.QueryMethods.NodesQuery.queryNodeByID;

public class DatabaseQueryController {

    ManageController manager;
    DBConnection dbConnection;

    public DatabaseQueryController(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void queryNodeById(DBConnection dbConnection, String nID) {
        queryNodeByID(dbConnection, nID);
    }

    public void queryEdgeById(String eID) {
        queryEdgeByID(dbConnection, eID);
    }


    public void getAllNodeData() {
        NodesQuery.getAllNodeInfo(dbConnection);
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
