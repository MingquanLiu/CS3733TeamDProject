package edu.wpi.cs3733.programname.database;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBConnection {
    // Derby Embedded Driver
    private static final String DRIVER="org.apache.derby.jdbc.EmbeddedDriver";
    // DB URL
    private static final String JDBC_URL="jdbc:derby:TestDB;create=true";

    Connection conn;


    // setDBConnection method establishes connection to the database
    public void setDBConnection() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(JDBC_URL);

            // Check Connection
            if (conn != null) {
                System.out.print("Connected to DB \n");
                conn.setAutoCommit(false);

            } // end if

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, e);

        }
    } // end setDBConnection


    // getConnection method gets the established connection
    public Connection getConnection() {
        return conn;

    }
}
