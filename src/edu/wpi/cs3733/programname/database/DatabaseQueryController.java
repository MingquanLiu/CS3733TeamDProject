package edu.wpi.cs3733.programname.database;

import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.Edge;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.servicerequest.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryController {

    ManageController manager;
    DBConnection dbConnection;

    public DatabaseQueryController(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public NodeData queryNodeById(String nodeId) {
        NodeData queryResult = null;

        try {
            String sql = "SELECT * FROM Nodes " +
                    "WHERE nodeID = " + "'" + nodeId + "'";
            Statement stmt = dbConnection.getConnection().createStatement();
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
        } catch (SQLException e) {
            System.out.println("Insert Node Failed!");
            e.printStackTrace();
        }
        return queryResult;
    }

    public Edge queryEdgeById(String edgeId) {
        Edge queryResult = null;

        String id = "";
        String startNodeId = "";
        String endNodeId = "";
        try {
            String sql = "SELECT * FROM Edges WHERE edgeID = " + "'" + edgeId + "'";
            Statement stmt = dbConnection.getConnection().createStatement();
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

        try {
            String sql = "SELECT * FROM Nodes";
            Statement stmt = dbConnection.getConnection().createStatement();
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
        } catch (SQLException e) {
            System.out.println("Insert Node Failed!");
            e.printStackTrace();
        }
        return allNodes;
    }

    public List<Edge> getAllEdgeData() {
        Edge queryResult = null;
        List<Edge> allEdges = new ArrayList<Edge>();

        String id = "";
        String startNodeId = "";
        String endNodeId = "";
        try {
            String sql = "SELECT * FROM Edges";
            Statement stmt = dbConnection.getConnection().createStatement();
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

    public List<NodeData> queryNodeByType(String findNodeType) {

        NodeData queryResult = null;
        List<NodeData> allNodeTypes = new ArrayList<NodeData>();

        try {
            String sql = "SELECT * FROM Nodes WHERE nodeType = " + "'" + findNodeType + "'";
            Statement stmt = dbConnection.getConnection().createStatement();
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
                queryResult = new NodeData(id, location, floor, nodeType, longName, shortName);
                allNodeTypes.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Insert Node Failed!");
            e.printStackTrace();
        }
        return allNodeTypes;

    }

    public ArrayList<Employee> queryEmployeesByType(String type){
        Employee queryResult = null;
        ArrayList<Employee> group = new ArrayList<Employee>();
        try {
            String sql = "SELECT * FROM Employees WHERE serviceType = " + type;
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);

            String username;
            String password;
            String firstName;
            String middleName;
            String lastName;
            boolean sysAdmin;
            int sysAdminInt;
            String serviceType;

            while(result.next()) {
                username = result.getString("username");
                password = result.getString("password");
                firstName = result.getString("firstName");
                middleName = result.getString("middleName");
                lastName = result.getString("lastName");
                sysAdminInt = result.getInt("sysAdmin");
                serviceType = result.getString("serviceType");
                if(sysAdminInt==1){
                    sysAdmin = true;
                }
                else{
                    sysAdmin = false;
                }
                queryResult = new Employee(username, password, firstName, middleName, lastName, sysAdmin, serviceType);
                group.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Insert Employee Failed!");
            e.printStackTrace();
        }
        return group;
    }



}
