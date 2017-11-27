package edu.wpi.cs3733.programname.database.QueryMethods;

import edu.wpi.cs3733.programname.commondata.EmployeeInfo;
import edu.wpi.cs3733.programname.commondata.ServiceRequestInfo;
import edu.wpi.cs3733.programname.database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeQuery {

    public static List<EmployeeInfo> getAllEmployeeInfo(DBConnection dbConnection) {

        EmployeeInfo queryResult = null;
        List<EmployeeInfo> allEmployees = new ArrayList<EmployeeInfo>();

        try {
            String sql = "SELECT * FROM ServiceRequest";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);

            String userName = "";
            String passWord = "";
            String firstName = "";
            String middleName = "";
            String lastName = "";
            int sysAdmin = 0;
            String serviceType = "";

            while(result.next()) {
                userName = result.getString("userName");
                passWord = result.getString("passWord");
                firstName = result.getString("firstName");
                middleName= result.getString("middleName");
                lastName = result.getString("lastName");
                sysAdmin = result.getInt("sysAdmin");
                serviceType = result.getString("serviceType");


                queryResult = new EmployeeInfo(userName, passWord, firstName, middleName, lastName, sysAdmin, serviceType);
                allEmployees.add(queryResult);
            }
        } catch (SQLException e) {
            System.out.println("Get all EmployeeInfo Failed!");
            e.printStackTrace();
        }
        return allEmployees;

    }



    public static EmployeeInfo queryByUser(DBConnection dbConnection, String uName) {

        EmployeeInfo Employee = null;

        try {
            String sql = "SELECT * FROM ServiceRequest WHERE userName = '" + uName + "' ";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);

            String userName = "";
            String passWord = "";
            String firstName = "";
            String middleName = "";
            String lastName = "";
            int sysAdmin = 0;
            String serviceType = "";

            while(result.next()) {
                userName = result.getString("userName");
                passWord = result.getString("passWord");
                firstName = result.getString("firstName");
                middleName= result.getString("middleName");
                lastName = result.getString("lastName");
                sysAdmin = result.getInt("sysAdmin");
                serviceType = result.getString("serviceType");


                Employee = new EmployeeInfo(userName, passWord, firstName, middleName, lastName, sysAdmin, serviceType);

            }
        } catch (SQLException e) {
            System.out.println("Get all EmployeeInfo Failed!");
            e.printStackTrace();
        }
        return Employee;

    }



    public static EmployeeInfo queryByName(DBConnection dbConnection, String fName, String mName, String lName) {

        EmployeeInfo queryResult = null;

        try {
            String sql = "SELECT * FROM ServiceRequest WHERE firstName = '" + fName + "' " +
                    "AND middleName = '" + mName + "'" +
                    "AND lastName = '" + lName + "'";
            Statement stmt = dbConnection.getConnection().createStatement();
            ResultSet result = stmt.executeQuery(sql);

            String userName = "";
            String passWord = "";
            String firstName = "";
            String middleName = "";
            String lastName = "";
            int sysAdmin = 0;
            String serviceType = "";

            while(result.next()) {
                userName = result.getString("userName");
                passWord = result.getString("passWord");
                firstName = result.getString("firstName");
                middleName= result.getString("middleName");
                lastName = result.getString("lastName");
                sysAdmin = result.getInt("sysAdmin");
                serviceType = result.getString("serviceType");


                queryResult = new EmployeeInfo(userName, passWord, firstName, middleName, lastName, sysAdmin, serviceType);
            }
        } catch (SQLException e) {
            System.out.println("Get all EmployeeInfo Failed!");
            e.printStackTrace();
        }
        return queryResult;

    }

}
