package edu.wpi.cs3733.programname.database.ModificationMethods;

import edu.wpi.cs3733.programname.commondata.Employee;
import edu.wpi.cs3733.programname.commondata.Interpreter;
import edu.wpi.cs3733.programname.database.CsvWriter;
import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.SQLException;

public class InterpreterMethod {
    private DBConnection dbConnection;
    private CsvWriter wrt;

    public InterpreterMethod(DBConnection dbConnection, CsvWriter wrt) {
        this.dbConnection = dbConnection;
        this.wrt = wrt;
    }

    public void addInterpreter(Interpreter employee){
        String username = employee.getUsername();
//        String password = employee.getPassword();
//        String firstName = employee.getFirstName();
//        String middleName = employee.getMiddleName();
//        String lastName = employee.getLastName();
//        boolean sysAdmin = employee.getSysAdmin();
//        int sysAdminInt = sysAdmin? 1: 0;
//        String serviceType = employee.getServiceType();
//        String email = employee.getEmail();
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
}
