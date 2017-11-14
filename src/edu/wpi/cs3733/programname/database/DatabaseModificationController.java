package edu.wpi.cs3733.programname.database;
import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Edge;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.sql.*;


public class DatabaseModificationController {

    ManageController manager;

    public DatabaseModificationController(ManageController manager){
        this.manager = manager;
    }

    // All of the methods' return types are String and they cannot actually connect to the database
    // But we are able to test if the sql code the function generated is what we want

    public void addNode(NodeData data) {
        Connection dbConnection;
        DBConnection db = new DBConnection();
        db.setDBConnection();
        dbConnection = db.getConnection();

        String id = data.getId();
        int x = data.getX();
        int y = data.getY();
        String type = data.getType();
        String longName = data.getLongName();
        String shortName = data.getShortName();
        String floor = data.getFloor();

        try {
            PreparedStatement pst = dbConnection.prepareStatement(
                    "INSERT INTO Nodes(nodeID, xcoord, ycoord, floor, building, nodeType, longName, shortName, teamAssigned)" +
                    "VALUES (?,?,?,?,?,?,?,?,?)");
            pst.setString(1, id);
            pst.setInt(2, x);
            pst.setInt(3, y);
            pst.setString(4, floor);
            pst.setString(5, "15 Francis");
            pst.setString(6, type);
            pst.setString(7, longName);
            pst.setString(8, shortName);
            pst.setString(9, "Team D");
            System.out.println(pst.toString());
            pst.executeUpdate();
            dbConnection.close();
        } catch (SQLException e) {
            System.out.println("Insert Node Failed!");
            e.printStackTrace();
        }
    }

    public String editNode(NodeData data) {
        String id = data.getId();
        int x = data.getX();
        int y = data.getY();
        String type = data.getType();
        String longName = data.getLongName();
        String shortName = data.getShortName();
        String str;
        // just for testing
        str = "update Nodes set xcoord = " + x + ", ycoord = " + y +
                ", nodeType = " + type + ", longName = " + longName + ", shortName = "
                + shortName + "where nodeID = " + id;
//        try {
//            Statement stmt = connection.createStatement();
//            // expected "update Nodes set xcoord = x, ycoord = y, nodeType = type, longName = longName, shortName = shortName where nodeID = id
//            str = "update Nodes set xcoord = " + x + ", ycoord = " + y +
//                    ", nodeType = " + type + ", longName = " + longName + ", shortName = "
//                    + shortName + "where nodeID = " + id;
//            stmt.executeUpdate(str);
//            stmt.close();
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Edit Node Failed!");
//            e.printStackTrace();
//            return "Edit Node Failed!";
//        }
        return str;
    }

    public String deleteNode(NodeData data){
        String id = data.getId();
        //Connection connection = connectToDB();
        String str;
        // just for testing
        str ="delete from Nodes where nodeID = " + id;
//        try {
//            Statement stmt = connection.createStatement();
//            str ="delete from Nodes where nodeID = " + id;
//            stmt.executeUpdate(str);
//            stmt.close();
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Delete Node Failed!");
//            e.printStackTrace();
//            return "Delete Node Failed!";
//        }
        return str;
    }

    public String addEdge(String node1Id, String node2Id){
        String id = node1Id + "_" + node2Id;
        //Connection connection = connectToDB();
        String str;
        // just for testing
        str = "insert into Edges values(" + id + "," + node1Id + "," + node2Id ;
//        try {
//            Statement stmt = connection.createStatement();
//            str = "insert into Edges values(" + id + "," + node1Id + "," + node2Id ;
//            stmt.executeUpdate(str);
//            stmt.close();
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Add Edge Failed!");
//            e.printStackTrace();
//            return "Add edge Failed!";
//        }
        return str;
    }

    public String deleteEdge(Edge data) {
        String id = data.getEdgeId();
        //Connection connection = connectToDB();
        String str;
        // just for testing
        str = "delete from Edges where edgeID = " + id;
//        try {
//            Statement stmt = connection.createStatement();
//            str = "delete from Edges where edgeID = " + id;
//            stmt.executeUpdate(str);
//            stmt.close();
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Delete Edge Failed!");
//            e.printStackTrace();
//            return "Delete Edge Failed!";
//        }
        return str;
    }

}
