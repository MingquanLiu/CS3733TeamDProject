package edu.wpi.cs3733.programname.database.ModificationMethods;

import edu.wpi.cs3733.programname.commondata.Interpreter;
import edu.wpi.cs3733.programname.database.CsvWriter;
import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.SQLException;
import java.util.ArrayList;

public class InterpreterMethod {
    private DBConnection dbConnection;
    private CsvWriter wrt;

    public InterpreterMethod(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.wrt = new CsvWriter();
    }

    public void addInterpreter(Interpreter employee){
        String username = employee.getUsername();
        ArrayList<String> languages = employee.getLanguages();
        String str;
        for(String language: languages){
            this.addInterpreterLanguage(username,language);
        }
    }

    public void addInterpreterLanguage(String username, String language){
        String str;
        try {
            str = "insert into InterpreterSkills values('" + username + "','" + language + "')";
            //System.out.println(str);
            dbConnection.executeUpdate(str);
            this.wrt.writeInterpreterSkills(dbConnection.getConnection());
        } catch (SQLException e) {
            System.out.println("Insert Interpreter Skill Failed!");
            e.printStackTrace();
        }
    }

    public void removeInterpreterLanguage(String username, String language){
        String str;
        try {
            str = "DELETE FROM InterpreterSkills WHERE username = '" + username + "' and language ='" +language+ "'";
            //System.out.println(str);
            dbConnection.executeUpdate(str);
            this.wrt.writeInterpreterSkills(dbConnection.getConnection());
        } catch (SQLException e) {
            System.out.println("Remove Interpreter Skill Failed!");
            e.printStackTrace();
        }
    }

    public void deleteInterpreter(Interpreter employee){
        String username = employee.getUsername();
        ArrayList<String> languages = employee.getLanguages();
        String str;
        for(String language: languages){
            try {
                str = "DELETE FROM InterpreterSkills WHERE username = '" + username + "' and language ='" +language+ "'";
                System.out.println(str);
                dbConnection.executeUpdate(str);
                this.wrt.writeNodes(dbConnection.getConnection());
            } catch (SQLException e) {
                System.out.println("Delete Interpreter Skills Failed!");
                e.printStackTrace();
            }
        }
    }
}
