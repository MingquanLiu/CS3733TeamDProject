package edu.wpi.cs3733.programname.database;

import edu.wpi.cs3733.programname.commondata.NodeData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseModificationController {

    public DatabaseModificationController(){}


    public Connection connectToDB(){
        // need to change
        String USERID = "";
        // need to change
        String PASSWORD = "";

        System.out.println("-------JDBC COnnection Testing ---------");
        try {
            // need to change
            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {
            // need to change
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return null;
        }
        System.out.println("JDBC Driver Registered!");
        Connection connection = null;

        try {
            // need to change
            connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.wpi.edu:1521:orcl", USERID, PASSWORD);

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;
        }
        System.out.println("JDBC Driver Connected!");

        return connection;
    }

    // All of the methods' return types are String, just for now
    public String addNode(NodeData data) {
        String id = data.getId();
        int x = data.getX();
        int y = data.getY();
        String type = data.getType();
        String longName = data.getLongName();
        String shortName = data.getShortName();
        Connection connection = connectToDB();
        String str;
        try {
            Statement stmt = connection.createStatement();
            // expected "insert into Nodes values (id, x, y, type, longName, shortName)"
            str = "insert into Nodes values(" + id + ", " + x + ", " + y + ", " + type + ", " + longName + ", " + shortName + ")";
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
        Connection connection = connectToDB();
        String str;
        try {
            Statement stmt = connection.createStatement();
            // expected "update Nodes set xcoord = x, ycoord = y, nodeType = type, longName = longName, shortName = shortName where nodeID = id
            str = "update Nodes set xcoord = " + x + ", ycoord = " + y +
                    ", nodeType = " + type + ", longName = " + longName + ", shortName = "
                    + shortName + "where nodeID = " + id;
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
        Connection connection = connectToDB();
        String str;
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
        Connection connection = connectToDB();
        String str;
        try {
            Statement stmt = connection.createStatement();
            str = "insert into Edges values(" + id + "," + node1Id + "," + node2Id ;
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

//    public String deleteEdge(Edge data){
//        String id = data.getId();
//        Connection connection = connectToDB();
//        try {
//            Statement stmt = connection.createStatement();
//            String str ="delete from Edges where edgeID = " + id;
//            stmt.executeUpdate(str);
//            stmt.close();
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Delete Edge Failed!");
//            e.printStackTrace();
//            return "Delete Edge Failed!";
//        }
//        return str;
//    }

}
