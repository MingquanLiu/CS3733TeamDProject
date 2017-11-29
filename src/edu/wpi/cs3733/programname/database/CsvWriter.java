package edu.wpi.cs3733.programname.database;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import edu.wpi.cs3733.programname.commondata.*;

public class CsvWriter {

    public CsvWriter() {}

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
            String outFileName = "AllMapNodes.csv";
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
            String outFileName = "AllMapEdges.csv";
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




    public void writeEmployees(Connection conn, ArrayList<Employee> employeeList) {
        try {
            // Write File
            String outFileName = "Employees(WriteOut).csv";
            FileWriter wrt = new FileWriter(outFileName, false);
            BufferedWriter buf = new BufferedWriter(wrt);
            PrintWriter prt = new PrintWriter(buf);
            int j;

            // Prints header fields
            prt.println("username, password, firstName, middleName, lastName, sysAdmin, serviceType, email");
            for (j = 0; j < employeeList.size(); j++) {
                prt.println(employeeList.get(j).getUsername() + "," +
                        employeeList.get(j).getPassword() + "," +
                        employeeList.get(j).getFirstName() + "," +
                        employeeList.get(j).getMiddleName() + "," +
                        employeeList.get(j).getLastName() + "," +
                        employeeList.get(j).getSysAdmin() + "," +
                        employeeList.get(j).getServiceType() + "," +
                        employeeList.get(j).getEmail()

                );
            } // end for

            prt.flush();
            prt.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }



    public void writeServiceRequests(Connection conn, ArrayList<ServiceRequest> srList) {
        try {
            // Write File
            String outFileName = "ServiceRequests(WriteOut).csv";
            FileWriter wrt = new FileWriter(outFileName, false);
            BufferedWriter buf = new BufferedWriter(wrt);
            PrintWriter prt = new PrintWriter(buf);
            int j;

            // Prints header fields
            prt.println("serviceID, sender, serviceType, location1, location2, description");
            for (j = 0; j < srList.size(); j++) {
                prt.println(srList.get(j).getServiceID() + "," +
                        srList.get(j).getSender() + "," +
                        srList.get(j).getServiceType() + "," +
                        srList.get(j).getLocation1() + "," +
                        srList.get(j).getLocation2() + "," +
                        srList.get(j).getDescription()

                );
            } // end for

            prt.flush();
            prt.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
