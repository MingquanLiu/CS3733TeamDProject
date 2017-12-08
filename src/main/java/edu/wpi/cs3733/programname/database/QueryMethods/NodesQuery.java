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
    private DBConnection dbConnection;

    public NodesQuery(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public NodeData queryNodeByID(String nID) {
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

            while (result.next()) {
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
            System.out.println("Get Node Failed!");
            e.printStackTrace();
        }
        return queryResult;
    }


    public List<NodeData> getAllNodeInfo() {
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

            while (result.next()) {
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
            System.out.println("Get Node Failed!");
            e.printStackTrace();
        }
        return allNodes;
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

            while (result.next()) {
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
            System.out.println("Get Node Failed!");
            e.printStackTrace();
        }
        return allNodes;
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

            while (result.next()) {
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
            System.out.println("Get Node Failed!");
            e.printStackTrace();
        }
        return allNodeTypes;

    }


    public List<NodeData> getNodeByTypeAndFloor(String type, String floor) {

        NodeData queryResult = null;
        List<NodeData> allNodeTypes = new ArrayList<NodeData>();

        try {
            String sql = "SELECT * FROM Nodes " +
                    "WHERE nodeType = '" + type + "' and floor = '" + floor + "'";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);

            String nodeID = "";
            int xcoord = 0;
            int ycoord = 0;
            String building = "";
            String longName = "";
            String shortName = "";
            String teamAssigned = "";

            while (result.next()) {
                nodeID = result.getString("nodeID");
                xcoord = result.getInt("xcoord");
                ycoord = result.getInt("ycoord");
                floor = result.getString("floor");
                building = result.getString("building");
                longName = result.getString("longName");
                shortName = result.getString("shortName");
                teamAssigned = result.getString("teamAssigned");

                Coordinate location = new Coordinate(xcoord, ycoord);
                queryResult = new NodeData(nodeID, location, floor, building, type, longName, shortName, teamAssigned);
                allNodeTypes.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Get Node Failed!");
            e.printStackTrace();
        }
        return allNodeTypes;

    }


    public NodeData getNodeByCoordAndFloor(Coordinate coord, String floor) {
        NodeData queryResult = null;
        int xcoord = coord.getXCoord();
        int ycoord = coord.getYCoord();

        try {
            String sql = "SELECT * FROM Nodes " +
                    "WHERE xcoord = " + xcoord + "and ycoord =" + ycoord + " and floor = '" + floor + "'";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            String nodeID = "";
            String building = "";
            String nodeType = "";
            String longName = "";
            String shortName = "";
            String teamAssigned = "";
            while (result.next()) {
                nodeID = result.getString("nodeID");
                xcoord = result.getInt("xcoord");
                ycoord = result.getInt("ycoord");
                building = result.getString("building");
                nodeType = result.getString("nodeType");
                longName = result.getString("longName");
                shortName = result.getString("shortName");
                teamAssigned = result.getString("teamAssigned");
            }
            Coordinate location = new Coordinate(xcoord, ycoord);
            queryResult = new NodeData(nodeID, location, floor, building, nodeType, longName, shortName, teamAssigned);
        } catch (SQLException e) {
            System.out.println("Get Node Failed!");
            e.printStackTrace();
        }
        return queryResult;
    }

    public List<NodeData> queryNodeByFloorAndBuilding(String nFloor, String nBuilding) {

        NodeData queryResult = null;
        List<NodeData> allNodesByFloor = new ArrayList<NodeData>();

        try {
            String sql;
            if (nBuilding.matches("Hospital|BTM|(15|25|45) Francis|Tower|Shapiro"))
                sql = "SELECT * FROM Nodes WHERE floor = " + "'" + nFloor + "'" + "AND (building = 'BTM' OR building = '45 Francis' OR building = '15 Francis' OR building = 'Tower' OR building = 'Shapiro')";
            else
                sql = "SELECT * FROM Nodes WHERE floor = " + "'" + nFloor + "'" + "AND building = " + "'" + nBuilding + "'";
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

            while (result.next()) {
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
                allNodesByFloor.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Get Node Failed!");
            e.printStackTrace();
        }
        return allNodesByFloor;

    }

    public List<NodeData> queryNodeByFloor(String nFloor) {

        NodeData queryResult = null;
        List<NodeData> allNodesByFloor = new ArrayList<NodeData>();

        try {
            String sql = "SELECT * FROM Nodes WHERE floor = " + "'" + nFloor + "'";
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

            while (result.next()) {
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
                allNodesByFloor.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Get Node Failed!");
            e.printStackTrace();
        }
        return allNodesByFloor;

    }

    public List<NodeData> queryNodeByLongName(String longName) {

        NodeData queryResult = null;
        List<NodeData> allNodes = new ArrayList<NodeData>();

        try {
            String sql = "SELECT * FROM Nodes WHERE longName = " + "'" + longName + "'";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);

            String nodeID = "";
            int xcoord = 0;
            int ycoord = 0;
            String floor = "";
            String building = "";
            String nodeType = "";
            String shortName = "";
            String teamAssigned = "";

            while (result.next()) {
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
            System.out.println("Get Node Failed!");
            e.printStackTrace();
        }
        return allNodes;

    }
}