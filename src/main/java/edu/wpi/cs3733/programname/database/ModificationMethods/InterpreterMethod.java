package edu.wpi.cs3733.programname.database.ModificationMethods;

import edu.wpi.cs3733.programname.commondata.Interpreter;
import edu.wpi.cs3733.programname.database.CsvWriter;
import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.SQLException;

public class InterpreterMethod {
    private DBConnection dbConnection;
    private CsvWriter wrt;

    public InterpreterMethod(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.wrt = new CsvWriter();
    }

    public void addInterpreter(Interpreter employee){
        String username = employee.getUsername();
        String language = employee.getLanguage();
        String str;
        try {
            str = "insert into InterpreterSkills values('" + username + "','" + language + "')";
            //System.out.println(str);
            dbConnection.executeUpdate(str);
            this.wrt.writeEmployees(dbConnection.getConnection());
        } catch (SQLException e) {
            System.out.println("Insert Interpreter Employee Failed!");
            e.printStackTrace();
        }
    }

    public void deleteInterpreter(Interpreter employee){
        String username = employee.getUsername();
        String language = employee.getLanguage();
        String str;
        try {
            str = "DELETE FROM InterpreterSkills WHERE username = '" + username + "' and language ='" +language+ "'";
            System.out.println(str);
            dbConnection.executeUpdate(str);
            this.wrt.writeNodes(dbConnection.getConnection());
        } catch (SQLException e) {
            System.out.println("Delete InterpreterSkills Failed!");
            e.printStackTrace();
        }
    }
}
