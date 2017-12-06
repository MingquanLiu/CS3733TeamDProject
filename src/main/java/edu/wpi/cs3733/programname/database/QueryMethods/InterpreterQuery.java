package edu.wpi.cs3733.programname.database.QueryMethods;

import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class InterpreterQuery {
    private DBConnection dbConnection;
    public InterpreterQuery(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public ArrayList<String> queryInterpreterSkills(String username){
        ArrayList<String> languages = new ArrayList<String>();
        try {
            String sql = "SELECT * FROM InterpreterSkills where username  = '" + username + "'";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);
            String language;
            while(result.next()) {
                language = result.getString("language");
                languages.add(language);
            }
        } catch (SQLException e) {
            System.out.println("Query Language Failed!");
            e.printStackTrace();
        }
        return languages;
    }
}
