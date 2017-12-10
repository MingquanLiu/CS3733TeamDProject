package edu.wpi.cs3733.programname.database.ModificationMethods;

import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.database.CsvWriter;
import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.SQLException;

public class MapMethod {

    private DBConnection dbConnection;
    private CsvWriter wrt;

    public MapMethod(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.wrt = new CsvWriter();
    }

    public void insertMap(String bName, String fName, String imgPath, String fNum) {
        String buildingName = bName;
        String floorName = fNum;
        String imagePath = imgPath;
        String floorNum = fNum;
        String str;

        try {

            str = "INSERT INTO MapInfo VALUES('" + buildingName + "', " + floorName + ", "
                    + imagePath + "," + floorNum + "')";

            System.out.println(str);
            dbConnection.executeUpdate(str);
            this.wrt.writeNodes(dbConnection.getConnection());
        } catch (SQLException e) {
            System.out.println("Insert Map info Failed!");
            e.printStackTrace();
        }
    }


    public void modifyMap(String bName, String fName, String imgPath, String fNum) {
        String buildingName = bName;
        String floorName = fNum;
        String imagePath = imgPath;
        String floorNum = fNum;
        String str;
        try {

            str = "UPDATE MapInfo SET "
                    + "buildingName = " + buildingName + "', " + floorName + ", "
                    + imagePath + "," + floorNum + "')";

            System.out.println(str);
            dbConnection.executeUpdate(str);
            this.wrt.writeNodes(dbConnection.getConnection());

        } catch (SQLException e) {
            System.out.println("Edit Building Info Failed!");
            e.printStackTrace();
        }
    }


    public void removeMap(String bName, String fName) {
        String buildingName = bName;
        String floor = fName;
        String str;

        try {
            str = "DELETE FROM Nodes WHERE buildingName = '" + buildingName
                    + "' AND floor = '" + floor + "'";

            System.out.println(str);
            dbConnection.executeUpdate(str);
            this.wrt.writeNodes(dbConnection.getConnection());
        } catch (SQLException e) {
            System.out.println("Delete Node Failed!");
            e.printStackTrace();
        }
    }
}
