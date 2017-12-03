package edu.wpi.cs3733.programname.database;
import jdk.nashorn.internal.scripts.JD;
import org.apache.derby.jdbc.EmbeddedDriver;

import java.sql.*;

public class DBConnection {

    // Derby Embedded Driver
    private static final String DRIVER="org.apache.derby.jdbc.EmbeddedDriver";
    // DB URL
    private static final String JDBC_URL="jdbc:derby:TestDB;create=true";

    Connection conn;
    Statement stat;

    public DBConnection(){
        setDBConnection();
    }

    // setDBConnection method establishes connection to the database
    public void setDBConnection() {
        try {
            Class.forName(DRIVER);
            Driver emDriver = new EmbeddedDriver();
            DriverManager.registerDriver(emDriver);
            conn = DriverManager.getConnection(JDBC_URL);

            // Check Connection
            if (conn != null) {
                System.out.print("Connected to DB \n");
            } // end if

            stat = conn.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } // end setDBConnection
    // getConnection method gets the established connection

    public Connection getConnection() {
        return conn;
    }

    public void executeUpdate(String str) throws SQLException {
        stat.executeUpdate(str);
    }
    public ResultSet executeQuery(String str) throws SQLException {
        ResultSet mSet = stat.executeQuery(str);
        return mSet;
    }
    public void execute(String str) throws SQLException {
        stat.execute(str);
    }
}
