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

//    public void writeNodes(Connection conn, ArrayList<NodeData> nodesList) {
//        try {
//            // Write out the csv file
//            String outFileName = "AllMapNodes.csv";
//            FileWriter wrt = new FileWriter(outFileName, false);
//            BufferedWriter buf = new BufferedWriter(wrt);
//            PrintWriter prt = new PrintWriter(buf);
//            int j;
//
//            // Prints header fields
//            prt.println("nodeId, xcoord, ycoord, floor, building, nodeType, longName, shortName, teamAssigned");
//
//            for (j = 0; j < nodesList.size(); j++) {
//                prt.println(
//                        nodesList.get(j).getNodeID() + "," +
//                                nodesList.get(j).getXCoord() + "," +
//                                nodesList.get(j).getYCoord() + "," +
//                                nodesList.get(j).getFloor() + "," +
//                                nodesList.get(j).getBuilding() + "," +
//                                nodesList.get(j).getNodeType() + "," +
//                                nodesList.get(j).getLongName() + "," +
//                                nodesList.get(j).getShortName() + "," +
//                                nodesList.get(j).getTeamAssigned()
//                );
//            } // end for
//
//            System.out.println("Write out success!");
//
//            prt.flush();
//            prt.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }
//    }// end csvNodes





    public void writeNodes(Connection conn) {
        try {
            Statement statement = conn.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * FROM NODES");
            String outFileName = "CsvNodes/AllMapNodes.csv";
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














    public void writeEdges(Connection conn) {
        try {
            Statement statement = conn.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * FROM EDGES");
            String outFileName = "CsvEdges/AllMapEdges.csv";
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
            String outFileName = "CsvTables/AllEmployees.csv";
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



    public void writeServiceRequests(Connection conn) {
        try {
            Statement statement = conn.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * FROM ServiceRequests");
            String outFileName = "CsvTables/AllServiceRequests.csv";
            FileWriter wrt = new FileWriter(outFileName, false);
            BufferedWriter buf = new BufferedWriter(wrt);
            PrintWriter prt = new PrintWriter(buf);



            // Initialize table fields
            int serviceID = 0;
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



            // Prints header fields
            prt.println("serviceID, sender, receiver, serviceType, location1, location2, description, requestTime, handleTime, completionTime, status");
            while (rset.next()) {
                serviceID = rset.getInt("serviceID");
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
                        status);
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
