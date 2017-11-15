package edu.wpi.cs3733.programname.database;

import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.sql.*;

public class DatabaseQueryController {

    ManageController manager;

    public DatabaseQueryController(ManageController manager) {
        this.manager = manager;
    }

    public NodeData queryDatabase(String nodeId) {
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
                id = result.getString("nodeId");
                x = result.getInt("xcoord");
                y = result.getInt("ycoord");
                floor = result.getString("floor");
                nodeType = result.getString("nodeType");
                longName = result.getString("longName");
                shortName = result.getString("shortName");
            }

            Coordinate location = new Coordinate(x,y);
            queryResult = new NodeData(nodeId, location, floor, nodeType,
                    longName, shortName);


        } catch (SQLException e) {
            System.out.println("Insert Node Failed!");
            e.printStackTrace();
        }
        return queryResult;
    }


}
