package edu.wpi.cs3733.programname.database.ModificationMethods;

import edu.wpi.cs3733.programname.commondata.Maintenance;
import edu.wpi.cs3733.programname.database.CsvWriter;
import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.SQLException;
import java.util.ArrayList;

public class MaintenanceMethod {
    private DBConnection dbConnection;
    private CsvWriter wrt;

    public MaintenanceMethod(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.wrt = new CsvWriter();
    }

    public void addMaintenance(Maintenance employee){
        String username = employee.getUsername();
        ArrayList<String> skills = employee.getMaintenanceType();
        String str;
        for(String skill: skills){
            this.addMaintenanceSkill(username,skill);
        }
    }

    public void addMaintenanceSkill(String username, String skill){
        String str;
        try {
            str = "insert into MaintenanceSkills values('" + username + "','" + skill + "')";
            //System.out.println(str);
            dbConnection.executeUpdate(str);
            this.wrt.writeMaintenanceSkills(dbConnection.getConnection());
        } catch (SQLException e) {
            System.out.println("Insert Maintenance Skill Failed!");
            e.printStackTrace();
        }
    }

    public void removeMaintenanceSkill(String username, String skill){
        String str;
        try {
            str = "DELETE FROM MaintenanceSkills WHERE username = '" + username + "' and skill ='" +skill+ "'";
            //System.out.println(str);
            dbConnection.executeUpdate(str);
            this.wrt.writeMaintenanceSkills(dbConnection.getConnection());
        } catch (SQLException e) {
            System.out.println("Remove Maintenance Skill Failed!");
            e.printStackTrace();
        }
    }

    public void deleteMaintenance(Maintenance employee){
        String username = employee.getUsername();
        ArrayList<String> skills = employee.getMaintenanceType();
        String str;
        for(String skill: skills){
            try {
                str = "DELETE FROM MaintenanceSkills WHERE username = '" + username + "' and skill ='" +skill+ "'";
                System.out.println(str);
                dbConnection.executeUpdate(str);
                this.wrt.writeNodes(dbConnection.getConnection());
            } catch (SQLException e) {
                System.out.println("Delete MaintenanceSkills Failed!");
                e.printStackTrace();
            }
        }
    }
}
