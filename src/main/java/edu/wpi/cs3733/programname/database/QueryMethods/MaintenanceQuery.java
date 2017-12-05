package edu.wpi.cs3733.programname.database.QueryMethods;

import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MaintenanceQuery {
    private DBConnection dbConnection;
    public MaintenanceQuery(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public ArrayList<String> queryMaintenanceSkills(String username){
        ArrayList<String> skills = new ArrayList<String>();
        try {
            String sql = "SELECT * FROM MaintenanceSkills where username  = '" + username + "'";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            String skill;
            while(result.next()) {
                skill = result.getString("skill");
                skills.add(skill);
            }
        } catch (SQLException e) {
            System.out.println("Query Language Failed!");
            e.printStackTrace();
        }
        return skills;
    }
}
