package edu.wpi.cs3733.programname.database.QueryMethods;

import edu.wpi.cs3733.programname.boundary.Building;
import edu.wpi.cs3733.programname.boundary.Floor;
import edu.wpi.cs3733.programname.boundary.FloorSorter;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MapsQuery {
    private DBConnection dbConnection;

    public MapsQuery(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public ArrayList<Building> queryAllBuildings() {
        ArrayList<Building> queryResult = new ArrayList<>();

        try {
            String sql = "SELECT DISTINCT buildingName FROM MapInfo";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            while (result.next()) {
                Building newBld = new Building(result.getString("buildingName"));
                System.out.println("building: " + newBld);
                newBld = queryFloorsInBuilding(newBld);
                queryResult.add(newBld);
            }

            System.out.println(queryResult);
        } catch (SQLException e) {
            System.out.println("Get Map Failed!");
            e.printStackTrace();
        }
        System.out.println(queryResult);
        return queryResult;
    }

    public Building queryFloorsInBuilding(Building building) {

        try {
            String sql = "SELECT * FROM MapInfo WHERE buildingName = '" + building.getName() + "'";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            ArrayList<Floor> floorList = new ArrayList<>();
            while (result.next()) {
                Floor floor = new Floor(result.getString("floorName"), building.getName(),
                        result.getString("floorNum"), result.getString("imagePath"));
                floorList.add(floor);
            }
            Collections.sort(floorList, new FloorSorter());
            building.addAllFloors(floorList);

            System.out.println(building);
            //queryResult = new NodeData(nodeID, location, floor, building, nodeType, longName, shortName, teamAssigned);
        } catch (SQLException e) {
            System.out.println("Get Map Failed!");
            e.printStackTrace();
        }
        return building;
    }
}