package edu.wpi.cs3733.programname.database;

import org.apache.derby.tools.ij;

import java.io.*;
import java.sql.Connection;
import java.util.Scanner;


public class RunScript {
    public RunScript(){}

    public boolean runScript(Connection connection) throws IOException {
        InputStream csv = this.getClass().getClassLoader().getResourceAsStream("SqlFiles/Tables.sql");

        try {

            int result =
                    ij.runScript(connection, csv, "UTF-8", System.out, "UTF-8");
            System.out.println("Result code is: " + result);
            return (result == 0);
        } catch (UnsupportedEncodingException e) {
            return false;
        } finally {
            if (csv != null) {
                try {
                    csv.close();
                } catch (IOException e) {
                }
            }
        }


    }
}