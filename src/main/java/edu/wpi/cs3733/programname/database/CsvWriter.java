package edu.wpi.cs3733.programname.database;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CsvWriter {
    public DBConnection dbConnection;

    public CsvWriter() {
        this.dbConnection = dbConnection;
    }

    public void writeNodes(Connection conn) {
        try {
            Statement statement = conn.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * FROM NODES");
            String outFileName = "csv/CsvNodes/AllMapNodes.csv";
            FileWriter wrt = new FileWriter(outFileName, false);
            BufferedWriter buf = new BufferedWriter(wrt);
            PrintWriter prt = new PrintWriter(buf);


            // Initialize table fields
            String nodeID = "";
            int xcoord = 0;
            int ycoord = 0;
            String floor = "";
            String building = "";
            String nodeType = "";
            String longName = "";
            String shortName = "";
            String teamAssigned = "";

            prt.println("nodeId, xcoord, ycoord, floor, building, nodeType, longName, shortName, teamAssigned");

            // Gets all data in the table
            while (rset.next()) {

                nodeID = rset.getString("nodeID");
                xcoord = rset.getInt("xcoord");
                ycoord = rset.getInt("ycoord");
                floor = rset.getString("floor");
                building = rset.getString("building");
                nodeType = rset.getString("nodeType");
                longName = rset.getString("longName");
                shortName = rset.getString("shortName");
                teamAssigned = rset.getString("teamAssigned");

                prt.println(nodeID + "," +
                        xcoord + "," +
                        ycoord + "," +
                        floor + "," +
                        building + "," +
                        nodeType + "," +
                        longName + "," +
                        shortName + "," +
                        teamAssigned);


            }
            prt.flush();
            prt.close();


        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeMaps (Connection conn) {
        try {
            Statement statement = conn.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * FROM MapInfo");
            String outFileName = "csv/CsvNodes/AllMaps.csv";
            FileWriter wrt = new FileWriter(outFileName, false);
            BufferedWriter buf = new BufferedWriter(wrt);
            PrintWriter prt = new PrintWriter(buf);


            // Initialize table fields
            String buildingName = "";
            String imagePath = "";
            String floor = "";
            prt.println("buildingName, imagePath, floor");

            // Gets all data in the table
            while (rset.next()) {
                buildingName = rset.getString("buildingName");
                imagePath = rset.getString("imagePath");
                floor = rset.getString("floor");

                prt.println(buildingName + "," +
                        imagePath + "," +
                        floor);


            }
            prt.flush();
            prt.close();


        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeEdges(Connection conn) {
        try {
            Statement statement = conn.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * FROM EDGES");
            String outFileName = "csv/CsvEdges/AllMapEdges.csv";
            FileWriter wrt = new FileWriter(outFileName, false);
            BufferedWriter buf = new BufferedWriter(wrt);
            PrintWriter prt = new PrintWriter(buf);
            int j;


            // Initialize table fields
            String edgeID = "";
            String startNode = "";
            String endNode = "";


            prt.println("edgeID, startNode, endNode");

            // Gets all data in the table
            while (rset.next()) {
                edgeID = rset.getString("edgeID");
                startNode = rset.getString("startNode");
                endNode = rset.getString("endNode");


                prt.println(edgeID + "," +
                        startNode + "," +
                        endNode);
            }
            prt.flush();
            prt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public void writeEmployees(Connection conn) {
        try {
            Statement statement = conn.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * FROM Employees");
            this.getClass().getProtectionDomain().getCodeSource().getLocation();
            String outFileName = "csv/CsvTables/AllEmployees.csv";
            FileWriter wrt = new FileWriter(outFileName, false);
            BufferedWriter buf = new BufferedWriter(wrt);
            PrintWriter prt = new PrintWriter(buf);


            // Initialize table fields
            String username = "";
            String password = "";
            String firstName = "";
            String middleName = "";
            String lastName = "";
            int sysAdmin = 0;
            String serviceType = "";
            String email = "";


            // Prints header fields
            prt.println("username, password, firstName, middleName, lastName, sysAdmin, serviceType, email");
            while (rset.next()) {
                username = rset.getString("username");
                password = rset.getString("password");
                firstName = rset.getString("firstName");
                middleName = rset.getString("middleName");
                lastName = rset.getString("lastName");
                sysAdmin = rset.getInt("sysAdmin");
                serviceType = rset.getString("serviceType");
                email = rset.getString("email");


                prt.println(username + "," +
                        password + "," +
                        firstName + "," +
                        middleName + "," +
                        lastName + "," +
                        sysAdmin + "," +
                        serviceType + "," +
                        email);
            }


            prt.flush();
            prt.close();

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    public void writeInterpreterSkills(Connection conn) {
        try {
            Statement statement = conn.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * FROM InterpreterSkills");
            String outFileName = "csv/CsvTables/AllInterpreterSkills.csv";
            FileWriter wrt = new FileWriter(outFileName, false);
            BufferedWriter buf = new BufferedWriter(wrt);
            PrintWriter prt = new PrintWriter(buf);


            // Initialize table fields
            String username = "";
            String language = "";


            // Prints header fields
            prt.println("username, language");
            while (rset.next()) {
                username = rset.getString("username");
                language = rset.getString("language");


                prt.println(username + "," + language);
            }


            prt.flush();
            prt.close();

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void writeMaintenanceSkills(Connection conn) {
        try {
            Statement statement = conn.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * FROM MaintenanceSkills");
            String outFileName = "csv/CsvTables/AllMaintenanceSkills.csv";
            FileWriter wrt = new FileWriter(outFileName, false);
            BufferedWriter buf = new BufferedWriter(wrt);
            PrintWriter prt = new PrintWriter(buf);


            // Initialize table fields
            String username = "";
            String skill = "";


            // Prints header fields
            prt.println("username, skill");
            while (rset.next()) {
                username = rset.getString("username");
                skill = rset.getString("skill");


                prt.println(username + "," + skill);
            }


            prt.flush();
            prt.close();

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }



    public void writeServiceRequests(Connection conn) {
        try {
            Statement statement = conn.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * FROM ServiceRequests");
            String outFileName = "csv/CsvTables/AllServiceRequests.csv";
            FileWriter wrt = new FileWriter(outFileName, false);
            BufferedWriter buf = new BufferedWriter(wrt);
            PrintWriter prt = new PrintWriter(buf);



            // Initialize table fields
            String serviceID = "";
            String sender = "";
            String receiver = "";
            String serviceType = "";
            String location1 = "";
            String location2 = "";
            String description = "";
            String requestTime = "";
            String handleTime = "";
            String completionTime = "";
            String status = "";
            int severity = 0;



            // Prints header fields
            prt.println("serviceID, sender, receiver, serviceType, location1, location2, description, requestTime, handleTime, completionTime, status, severity");
            while (rset.next()) {
                serviceID = rset.getString("serviceID");
                sender = rset.getString("sender");
                receiver = rset.getString("receiver");
                serviceType = rset.getString("serviceType");
                location1 = rset.getString("location1");
                location2 = rset.getString("location2");
                description = rset.getString("description");
                requestTime = rset.getString("requestTime");
                handleTime = rset.getString("handleTime");
                completionTime = rset.getString("completionTime");
                status = rset.getString("status");
                severity = rset.getInt("severity");


                prt.println(serviceID + "," +
                        sender + "," +
                        receiver + "," +
                        serviceType + "," +
                        location1 + "," +
                        location2 + "," +
                        description + "," +
                        requestTime + "," +
                        handleTime + "," +
                        completionTime + "," +
                        status+ "," +
                        severity);
            }


            prt.flush();
            prt.close();
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void writeInterpreterRequests(Connection conn) {
        try {
            Statement statement = conn.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * FROM InterpreterRequests");
            String outFileName = "csv/CsvTables/AllInterpreterRequests.csv";
            FileWriter wrt = new FileWriter(outFileName, false);
            BufferedWriter buf = new BufferedWriter(wrt);
            PrintWriter prt = new PrintWriter(buf);


            // Initialize table fields
            String serviceID = "";
            String language = "";
            String reservationTime = "";


            // Prints header fields
            prt.println("serviceID, language, reservationTime");
            while (rset.next()) {
                serviceID = rset.getString("serviceID");
                language = rset.getString("language");
                reservationTime = rset.getString("reservationTime");


                prt.println(serviceID + "," +
                        language + "," +
                        reservationTime);
            }


            prt.flush();
            prt.close();
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    public void writeMaintenanceRequests(Connection conn) {
        try {
            Statement statement = conn.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * FROM MaintenanceRequests");
            String outFileName = "csv/CsvTables/AllMaintenanceRequests.csv";
            FileWriter wrt = new FileWriter(outFileName, false);
            BufferedWriter buf = new BufferedWriter(wrt);
            PrintWriter prt = new PrintWriter(buf);


            // Initialize table fields
            int serviceID = 0;
            String skill = "";


            // Prints header fields
            prt.println("serviceID, skill");
            while (rset.next()) {
                serviceID = rset.getInt("serviceID");
                skill = rset.getString("skill");


                prt.println(serviceID + "," +
                        skill);
            }


            prt.flush();
            prt.close();
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    public void writeTransportationRequests(Connection conn) {
        try {
            Statement statement = conn.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * FROM TransportationRequests");
            String outFileName = "csv/CsvTables/AllTransportationRequests.csv";
            FileWriter wrt = new FileWriter(outFileName, false);
            BufferedWriter buf = new BufferedWriter(wrt);
            PrintWriter prt = new PrintWriter(buf);


            // Initialize table fields
            int serviceID = 0;
            String transportationType = "";
            String destination = "";
            String reservationTime = "";


            // Prints header fields
            prt.println("serviceID, transportationType, destination, reservationTime");
            while (rset.next()) {
                serviceID = rset.getInt("serviceID");
                transportationType = rset.getString("transportationType");
                destination = rset.getString("destination");
                reservationTime = rset.getString("reservationTime");


                prt.println(serviceID + "," +
                                transportationType + ","+
                        destination + "," +
                        reservationTime);
            }


            prt.flush();
            prt.close();
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
