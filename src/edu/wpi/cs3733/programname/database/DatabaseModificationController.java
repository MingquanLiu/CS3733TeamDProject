package edu.wpi.cs3733.programname.database;
import edu.wpi.cs3733.programname.commondata.Edge;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseModificationController {

    public DatabaseModificationController(){}

    public String addNode(NodeData data) {
        String id = data.getId();
        int x = data.getX();
        int y = data.getY();
        String type = data.getType();
        String longName = data.getLongName();
        String shortName = data.getShortName();

        String str;
        try {
            DBConnection conn = new DBConnection();
            conn.setDBConnection();
            Connection connection = conn.getConnection();
            Statement stmt = connection.createStatement();
            // expected "insert into Nodes values (id, x, y, type, 2, 15 Francis, longName, shortName, Team D)"
            str = "insert into Nodes values('" + id + "', " + x + ", " + y + ", '2', '15 Francis', '" + type + "', '" + longName + "', '" + shortName + "', 'Team D')";
            System.out.println(str);
            stmt.executeUpdate(str);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Insert Node Failed!");
            e.printStackTrace();
            return "Insert Node Failed!";
        }

        return str;
    }

    public String editNode(NodeData data) {
        String id = data.getId();
        int x = data.getX();
        int y = data.getY();
        String type = data.getType();
        String longName = data.getLongName();
        String shortName = data.getShortName();
        DBConnection conn = new DBConnection();
        conn.setDBConnection();
        Connection connection = conn.getConnection();
        String str;
        // just for testing
//        str = "update Nodes set xcoord = " + x + ", ycoord = " + y +
//                ", nodeType = " + type + ", longName = " + longName + ", shortName = "
//                + shortName + "where nodeID = " + id;
        try {
            Statement stmt = connection.createStatement();
            // expected "update Nodes set xcoord = x, ycoord = y, floor = '2', building = 15 Francis, nodeType = type, longName = longName, shortName = shortName, teamAssigned = Team D where nodeID = id
            str = "update Nodes set xcoord = '" + x + "', ycoord = '" + y +
                    "', floor = '2', building = '15 Francis', nodeType = '" + type + "', longName = '" + longName + "', shortName = '"
                    + shortName + "', teamAssigned = 'Team D' where nodeID = " + id;
            stmt.executeUpdate(str);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Edit Node Failed!");
            e.printStackTrace();
            return "Edit Node Failed!";
        }
        return str;
    }

    public String deleteNode(NodeData data){
        String id = data.getId();
        DBConnection conn = new DBConnection();
        Connection connection = conn.getConnection();
        String str;
        // just for testing
        //str ="delete from Nodes where nodeID = " + id;
        try {
            Statement stmt = connection.createStatement();
            str ="delete from Nodes where nodeID = " + id;
            stmt.executeUpdate(str);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Delete Node Failed!");
            e.printStackTrace();
            return "Delete Node Failed!";
        }
        return str;
    }

    public String addEdge(String node1Id, String node2Id){
        String id = node1Id + "_" + node2Id;
        DBConnection conn = new DBConnection();
        Connection connection = conn.getConnection();
        String str;
        // just for testing
        //str = "insert into Edges values(" + id + "," + node1Id + "," + node2Id ;
        try {
            Statement stmt = connection.createStatement();
            str = "insert into Edges values('" + id + "', '" + node1Id + "', '" + node2Id +"'";
            stmt.executeUpdate(str);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Add Edge Failed!");
            e.printStackTrace();
            return "Add edge Failed!";
        }
        return str;
    }

    public String deleteEdge(Edge data) {
        String id = data.getEdgeId();
        DBConnection conn = new DBConnection();
        Connection connection = conn.getConnection();
        String str;
        // just for testing
        //str = "delete from Edges where edgeID = " + id;
        try {
            Statement stmt = connection.createStatement();
            str = "delete from Edges where edgeID = '" + id +"'";
            stmt.executeUpdate(str);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Delete Edge Failed!");
            e.printStackTrace();
            return "Delete Edge Failed!";
        }
        return str;
    }

}
