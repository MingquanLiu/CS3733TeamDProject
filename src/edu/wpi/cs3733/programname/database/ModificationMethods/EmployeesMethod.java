package edu.wpi.cs3733.programname.database.ModificationMethods;

import edu.wpi.cs3733.programname.commondata.Employee;
import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.SQLException;

public class EmployeesMethod {

    private DBConnection dbConnection ;
    public EmployeesMethod(DBConnection dbConnection){this.dbConnection = dbConnection;}

    public void addEmployee(Employee employee){
        String username = employee.getUsername();
        String password = employee.getPassword();
        String firstName = employee.getFirstName();
        String middleName = employee.getMiddleName();
        String lastName = employee.getLastName();
        boolean sysAdmin = employee.getSysAdmin();
        int sysAdminInt = sysAdmin? 1: 0;
        String serviceType = employee.getServiceType();
        String email = employee.getEmail();
        String str;
        try {
            str = "insert into Employees values('" + username + "', '" + password + "', '" + firstName + "', '" + middleName + "', '" + lastName + "'," + sysAdminInt + ", '" + serviceType + "','" + email + "')";
            //System.out.println(str);
            dbConnection.executeUpdate(str);
        } catch (SQLException e) {
            System.out.println("Insert Employee Failed!");
            e.printStackTrace();
        }
    }

}
