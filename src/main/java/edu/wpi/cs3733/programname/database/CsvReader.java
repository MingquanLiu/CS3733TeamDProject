package edu.wpi.cs3733.programname.database;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.wpi.cs3733.programname.commondata.*;
import edu.wpi.cs3733.programname.commondata.servicerequestdata.InterpreterRequest;
import edu.wpi.cs3733.programname.commondata.servicerequestdata.MaintenanceRequest;
import edu.wpi.cs3733.programname.commondata.servicerequestdata.ServiceRequest;
import edu.wpi.cs3733.programname.commondata.servicerequestdata.TransportationRequest;


public class CsvReader {

    ArrayList<String[]> interpreterInfo;        //Array Size = 3
    ArrayList<String[]> maintenanceInfo;        //Array Size = 3
    ArrayList<String[]> transportationInfo;     //Array Size = 4

    ArrayList<String[]> interpreterEmployeeInfo;        //Array Size = 2
    ArrayList<String[]> maintenanceEmployeeInfo;        //Array Size = 2

    /**
     * empty constructor for CsvReader
     */
    public CsvReader() {

    }


    // NODES MapDnodes.csv

    /**
     * @param conn
     * @return
     */


    public ArrayList<NodeData> getListOfNodes(Connection conn) throws IOException{
        ArrayList<NodeData> nodeList = new ArrayList<NodeData>();
        BufferedReader buf;

        try {
            System.out.println("About to read nodes table");
            try {
                InputStream in = new FileInputStream(new File("csv/CsvNodes/AllMapNodes.csv").getPath());
                buf = new BufferedReader(new InputStreamReader(in));
                StringBuilder out = new StringBuilder();
            } catch (FileNotFoundException ioe) {
                String csv = "csv/CsvNodes/AllMapNodes.csv";
                InputStream input = ClassLoader.getSystemResourceAsStream(csv);
                buf = new BufferedReader(new InputStreamReader(input));
            }
            String line;
            buf.readLine();
            // Reads all lines in the file
            while ((line = buf.readLine()) != null) {
                // Reads current row and converts to a string


                // Seperates the string into fields and stores into an array
                String[] values = line.split(",");

                // Converts int fields from strings to integers
                int x = Integer.parseInt(values[1]);
                int y = Integer.parseInt(values[2]);
                Coordinate location = new Coordinate(x, y);
                NodeData nodeObject = new NodeData(values[0], location, values[3], values[4], values[5], values[6], values[7], values[8]);
                nodeList.add(nodeObject);


            } // end while

        } catch (IOException e) {

        }
        return nodeList;
    }// end readNodes






    // Insert Nodes into DB
    public void insertNodes(Connection conn, ArrayList<NodeData> nodesList) {
        try {
            int i;
            int count = nodesList.size();
            PreparedStatement pst = conn.prepareStatement("INSERT INTO Nodes(nodeID, xcoord, ycoord, floor, building, nodeType, longName, shortName, teamAssigned)" +
                    "VALUES (?,?,?,?,?,?,?,?,?)");

            int j = 0;

            for (i = 0; i < count; i++) {
                pst.setString(1, nodesList.get(i).getNodeID());
                pst.setInt(2, nodesList.get(i).getXCoord());
                pst.setInt(3, nodesList.get(i).getYCoord());
                pst.setString(4, nodesList.get(i).getFloor());
                pst.setString(5, nodesList.get(i).getBuilding());
                pst.setString(6, nodesList.get(i).getNodeType());
                pst.setString(7, nodesList.get(i).getLongName());
                pst.setString(8, nodesList.get(i).getShortName());
                pst.setString(9, nodesList.get(i).getTeamAssigned());
                pst.executeUpdate();
                j++;

            }

            System.out.println("Number of node rows: " + j);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // EDGES
    public ArrayList<EdgeData> getListOfEdges(Connection conn) {
        // ArrayLists stores data values is proper columns
        ArrayList<EdgeData> edgeList = new ArrayList<EdgeData>();
        BufferedReader buf;

        try {
            try {
                InputStream in = new FileInputStream(new File("csv/CsvEdges/AllMapEdges.csv").getPath());
                buf = new BufferedReader(new InputStreamReader(in));
                StringBuilder out = new StringBuilder();
            } catch (FileNotFoundException ioe) {
                String csv = "csv/CsvEdges/AllMapEdges.csv";
                InputStream input = ClassLoader.getSystemResourceAsStream(csv);
                buf = new BufferedReader(new InputStreamReader(input));
            }


            String line;
            buf.readLine();

            // Reads all lines in the file
            while ((line = buf.readLine()) != null) {
                // Reads current row and converts to a string


                // Seperates the string into fields and stores into an array
                String[] values = line.split(",");

                EdgeData edgeObject = new EdgeData(values[0], values[1], values[2]);
                edgeList.add(edgeObject);

            } // end while


        } catch (IOException e) {

        } // end readEdges
        System.out.println("Number of edges in csv: " + edgeList.size());
        return edgeList;
    }



    public void insertEdges(Connection conn, ArrayList<EdgeData> edgesList) {
        // edgeID is unique and used to count the number of lines read in the file minus the header
        int i;
        int count = edgesList.size();

        try {
            // SQL Insert
            PreparedStatement pst = conn.prepareStatement("INSERT INTO Edges(edgeID, startNode, endNode)" +
                    "VALUES (?,?,?)");

            // Loops and increments to insert all data from the file into the edges table
            for (i = 0; i < count; i++) {
                pst.setString(1, edgesList.get(i).getEdgeID());
                pst.setString(2, edgesList.get(i).getStartNode());
                pst.setString(3, edgesList.get(i).getEndNode());
                pst.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } // end insertNodes



    // EMPLOYEES
    public ArrayList<Employee> getListOfEmployees(Connection conn) {

        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        BufferedReader buf;

        getInterpreterEmployeeInfo();
        getMaintenanceEmployeeInfo();

        try {
            try {
                InputStream in = new FileInputStream(new File("csv/CsvTables/AllEmployees.csv").getPath());
                buf = new BufferedReader(new InputStreamReader(in));
                StringBuilder out = new StringBuilder();
            } catch (FileNotFoundException ioe) {
                String csv = "csv/CsvTables/AllEmployees.csv";
                InputStream input = ClassLoader.getSystemResourceAsStream(csv);
                buf = new BufferedReader(new InputStreamReader(input));
            }

            String line;
            buf.readLine();
            // Reads all lines in the file
            while ((line = buf.readLine()) != null) {
                // Reads current row and converts to a string

                // Seperates the string into fields and stores into an array
                String[] values = line.split(",");

                boolean sysAdmin;
                if (Integer.parseInt(values[5]) == 1) {
                    sysAdmin = true;
                } else {
                    sysAdmin = false;
                }

                Employee employeeObject;
                if (values[6].equals(Constants.INTERPRETER_REQUEST)) {
                    ArrayList<String> languages = new ArrayList<>();

                    //for each entry in the interpreter db for this employee,
                    //add that entry's language to this employee
                    for (String[] data: interpreterEmployeeInfo) {
                        if(data[0].equals(values[0])){
                            languages.add(data[1]);
                        }
                    }
                    employeeObject = new Interpreter(values[0], values[1], values[2], values[3], values[4], sysAdmin, values[6], values[7], languages);
                    employeeList.add(employeeObject);
                } else if (values[6].equals(Constants.MAINTENANCE_REQUEST)) {
                    ArrayList<String> skills = new ArrayList<>();

                    for(String[] data: maintenanceEmployeeInfo) {
                        if(data[0].equals(values[0])) {
                            skills.add(data[1]);
                            break;
                        }
                    }
                    employeeObject = new Maintenance(values[0], values[1], values[2], values[3], values[4], sysAdmin, values[6], values[7], skills);
                    employeeList.add(employeeObject);
                }
                else {
                    employeeObject = new Employee(values[0], values[1], values[2], values[3], values[4], sysAdmin, values[6], values[7]);
                    employeeList.add(employeeObject);
                }

            } // end while
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public void getInterpreterEmployeeInfo() {
        interpreterEmployeeInfo = new ArrayList<>();
        BufferedReader buf;
        try {
            try {
                InputStream in = new FileInputStream(new File("csv/CsvTables/AllInterpreterSkills.csv").getPath());
                buf = new BufferedReader(new InputStreamReader(in));
                StringBuilder out = new StringBuilder();
            } catch (FileNotFoundException ioe) {
                String csv = "csv/CsvTables/AllInterpreterSkills.csv";
                InputStream input = ClassLoader.getSystemResourceAsStream(csv);
                buf = new BufferedReader(new InputStreamReader(input));
            }

            String line;
            buf.readLine();

            while((line = buf.readLine()) != null) {
                System.out.println(line);
                String[] values = line.split(",");
                interpreterEmployeeInfo.add(values);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void getMaintenanceEmployeeInfo() {
        maintenanceEmployeeInfo = new ArrayList<>();
        BufferedReader buf;
        try {
            try {
                InputStream in = new FileInputStream(new File("csv/CsvTables/AllMaintenanceSkills.csv").getPath());
                buf = new BufferedReader(new InputStreamReader(in));
                StringBuilder out = new StringBuilder();
            } catch (FileNotFoundException ioe) {
                String csv = "csv/CsvTables/AllMaintenanceSkills.csv";
                InputStream input = ClassLoader.getSystemResourceAsStream(csv);
                buf = new BufferedReader(new InputStreamReader(input));
            }
            String line;
            buf.readLine();

            while((line = buf.readLine()) != null) {
                System.out.println(line);
                String[] values = line.split(",");
                interpreterEmployeeInfo.add(values);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public void insertEmployees(Connection conn, ArrayList<Employee> employeeList) {

        int i;
        int count = employeeList.size();

        try {
            // SQL Insert
            PreparedStatement pst = conn.prepareStatement("INSERT INTO Employees(username, password, firstName, middleName, lastName, sysAdmin, serviceType, email)" +
                    "VALUES (?,?,?,?,?,?,?,?)");


            for (i = 0; i < count; i++) {
                pst.setString(1, employeeList.get(i).getUsername());
                pst.setString(2, employeeList.get(i).getPassword());
                pst.setString(3, employeeList.get(i).getFirstName());
                pst.setString(4, employeeList.get(i).getMiddleName());
                pst.setString(5, employeeList.get(i).getLastName());
                pst.setBoolean(6, employeeList.get(i).getSysAdmin());
                pst.setString(7, employeeList.get(i).getServiceType());
                pst.setString(8, employeeList.get(i).getEmail());
                pst.executeUpdate();

                PreparedStatement skillsInsert;
                if (employeeList.get(i).getServiceType().equals(Constants.INTERPRETER_REQUEST)) {
                    Interpreter interpreter = (Interpreter) employeeList.get(i);
                    for(String language: interpreter.getLanguages()) {
                        skillsInsert = conn.prepareStatement("INSERT INTO InterpreterSkills(username, language)" +
                                " VALUES (?,?)");
                        skillsInsert.setString(1, interpreter.getUsername());
                        skillsInsert.setString(2, language);
                        skillsInsert.executeUpdate();
                    }
                } else if (employeeList.get(i).getServiceType().equals(Constants.MAINTENANCE_REQUEST)) {
                    Maintenance maintenanceEmployee = (Maintenance) employeeList.get(i);
                    for (String skill: maintenanceEmployee.getMaintenanceType()) {
                        skillsInsert = conn.prepareStatement("INSERT INTO InterpreterSkills(username, language)" +
                                " VALUES (?,?)");
                        skillsInsert.setString(1, maintenanceEmployee.getUsername());
                        skillsInsert.setString(2, skill);
                        skillsInsert.executeUpdate();
                    }
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // SERVICEREQUESTS
    public void getInterpreterTableInfo() {
        interpreterInfo = new ArrayList<>();
        BufferedReader buf;
        try {
            try {
                InputStream in = new FileInputStream(new File("csv/CsvTables/AllInterpreterRequests.csv").getPath());
                buf = new BufferedReader(new InputStreamReader(in));
                StringBuilder out = new StringBuilder();
            } catch (FileNotFoundException ioe) {
                String csv = "csv/CsvTables/AllInterpreterRequests.csv";
                InputStream input = ClassLoader.getSystemResourceAsStream(csv);
                buf = new BufferedReader(new InputStreamReader(input));
            }

            String line;
            buf.readLine();

            while((line = buf.readLine()) != null) {
                System.out.println(line);
                String[] values = line.split(",");
                interpreterInfo.add(values);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void getMaintenanceTableInfo() {
        maintenanceInfo = new ArrayList<>();
        BufferedReader buf;
        try {
            try {
                InputStream in = new FileInputStream(new File("csv/CsvTables/AllMaintenanceRequests.csv").getPath());
                buf = new BufferedReader(new InputStreamReader(in));
                StringBuilder out = new StringBuilder();
            } catch (FileNotFoundException ioe) {
                String csv = "csv/CsvTables/AllMaintenanceRequests.csv";
                InputStream input = ClassLoader.getSystemResourceAsStream(csv);
                buf = new BufferedReader(new InputStreamReader(input));
            }
            String line;
            buf.readLine();

            while((line = buf.readLine()) != null) {
                System.out.println(line);
                String[] values = line.split(",");
                maintenanceInfo.add(values);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void getTransportationTableInfo() {
        transportationInfo = new ArrayList<>();
        BufferedReader buf;
        try {
            try {
                InputStream in = new FileInputStream(new File("csv/CsvTables/AllTransportationRequests.csv").getPath());
                buf = new BufferedReader(new InputStreamReader(in));
                StringBuilder out = new StringBuilder();
            } catch (FileNotFoundException ioe) {
                String csv = "csv/CsvTables/AllTransportationRequests.csv";
                InputStream input = ClassLoader.getSystemResourceAsStream(csv);
                buf = new BufferedReader(new InputStreamReader(input));
            }
            String line;
            buf.readLine();

            while((line = buf.readLine()) != null) {
                System.out.println(line);
                String[] values = line.split(",");
                transportationInfo.add(values);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public ArrayList<ServiceRequest> getListOfServiceRequests(Connection conn) {

        ArrayList<ServiceRequest> srList = new ArrayList<ServiceRequest>();
        BufferedReader buf;
        getInterpreterTableInfo();
        getMaintenanceTableInfo();
        getTransportationTableInfo();

        try {
            try {
                InputStream in = new FileInputStream(new File("csv/CsvTables/AllServiceRequests.csv").getPath());
                buf = new BufferedReader(new InputStreamReader(in));
                StringBuilder out = new StringBuilder();
            } catch (FileNotFoundException ioe) {
                String csv = "csv/CsvTables/AllServiceRequests.csv";
                InputStream input = ClassLoader.getSystemResourceAsStream(csv);
                buf = new BufferedReader(new InputStreamReader(input));
            }

            String line;
            buf.readLine();

            buf.readLine();
            while ((line = buf.readLine()) != null) {
                // Reads current row and converts to a string

                System.out.println(line);
                // Seperates the string into fields and stores into an array
                String[] values = line.split(",");
                for (int i = 0; i<values.length;i++ ){
                    System.out.println(i + "  "+values[i]);
                }


                int severity = Integer.parseInt(values[11]);
                System.out.println("length"+values.length);

                ServiceRequest srObject = null;
                ArrayList<String[]> subtable;
                if (values[3].equals(Constants.INTERPRETER_REQUEST)) {
                    subtable = interpreterInfo;
                    String[] data;
                    for (String[] info: subtable) {
                        if(values[0].equals(info[0])){
                            data = info;
                            srObject = new InterpreterRequest(values[0], values[1], values[2], values[3],
                                    values[4], values[5], values[6], values[7], values[8], values[9],
                                    values[10],severity, data[1], data[2]);
                            break;
                        }
                    }
                } else if (values[3].equals(Constants.MAINTENANCE_REQUEST)) {
                    subtable = maintenanceInfo;
                    String[] data;
                    for(String[] info: subtable) {
                        if(values[0].equals(info[0])) {
                            data = info;
                            srObject = new MaintenanceRequest(values[0], values[1], values[2], values[3],
                                    values[4], values[5], values[6], values[7], values[8], values[9],
                                    values[10],severity,data[1]);
                            break;
                        }
                    }
                } else {
                    subtable = transportationInfo;
                    String[] data;
                    for(String[] info: subtable) {
                        if(values[0].equals(info[0])) {
                            data = info;
                            srObject = new TransportationRequest(values[0], values[1], values[2], values[3],
                                    values[4], values[5], values[6], values[7], values[8], values[9],
                                    values[10],severity, data[1], data[2], data[3]);
                            break;
                        }
                    }
                }
                srList.add(srObject);
            } // end while

        }catch (IOException e) {
            try {
                InputStream in = this.getClass().getClassLoader().getResourceAsStream("csv/CsvTables/AllServiceRequests.csv");
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder out = new StringBuilder();
                String line;
                reader.readLine();
                // Reads all lines in the file
                while ((line = reader.readLine()) != null) {
                    // Reads current row and converts to a string


                    // Seperates the string into fields and stores into an array
                    String[] values = line.split(",");

                    int severity = Integer.parseInt(values[11]);

                    ServiceRequest srObject = new ServiceRequest(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9], values[10], severity);
                    srList.add(srObject);

                } // end while
            } catch (IOException w) {
            }
        }


        return srList;
    }






    public void insertServiceRequests(Connection conn, ArrayList<ServiceRequest> srList) {

        int i;
        int count = srList.size();

        try {
            // SQL Insert
            PreparedStatement pst = conn.prepareStatement("INSERT INTO ServiceRequests(serviceID, sender, serviceType, location1, location2, description, requestTime, handleTime, completionTime, status, severity)" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?)");


            for (i = 0; i < count; i++) {
                pst.setString(1, srList.get(i).getServiceID());
                pst.setString(2, srList.get(i).getSender());
                pst.setString(3, srList.get(i).getServiceType());
                pst.setString(4, srList.get(i).getLocation1());
                pst.setString(5, srList.get(i).getLocation2());
                pst.setString(6, srList.get(i).getDescription());
                pst.setString(7, srList.get(i).getRequestTime());
                pst.setString(8, srList.get(i).getHandleTime());
                pst.setString(9, srList.get(i).getCompletionTime());
                pst.setString(10, srList.get(i).getStatus());
                pst.setInt(11, srList.get(i).getSeverity());
                pst.executeUpdate();

                PreparedStatement detailInsert;
                if (srList.get(i).getServiceType().equals(Constants.INTERPRETER_REQUEST)) {
                    InterpreterRequest interpreterRequest = (InterpreterRequest) srList.get(i);
                    detailInsert = conn.prepareStatement("INSERT INTO InterpreterRequests(serviceID, language, reservationTime)" +
                    " VALUES (?,?,?)");
                    detailInsert.setString(1, interpreterRequest.getServiceID());
                    detailInsert.setString(2, interpreterRequest.getLanguage());
                    detailInsert.setString(3, interpreterRequest.getReservationTime());
                    detailInsert.executeUpdate();
                } else if (srList.get(i).getServiceType().equals(Constants.MAINTENANCE_REQUEST)) {
                    MaintenanceRequest maintenanceRequest = (MaintenanceRequest) srList.get(i);
                    detailInsert = conn.prepareStatement("INSERT INTO MaintenanceRequests(serviceID, maintenanceType)" +
                    " VALUES (?,?)");
                    detailInsert.setString(1, maintenanceRequest.getServiceID());
                    detailInsert.setString(2, maintenanceRequest.getMaintenanceType());
                    detailInsert.executeUpdate();
                } else if (srList.get(i).getServiceType().equals(Constants.TRANSPORTATION_REQUEST)) {
                    TransportationRequest transportationRequest = (TransportationRequest) srList.get(i);
                    detailInsert = conn.prepareStatement("INSERT INTO TransportationRequests(serviceID, transportType, destination, reservationTime)" +
                    " VALUES (?,?,?,?)");
                    detailInsert.setString(1, transportationRequest.getServiceID());
                    detailInsert.setString(2, transportationRequest.getTransportType());
                    detailInsert.setString(3, transportationRequest.getDestination());
                    detailInsert.setString(4, transportationRequest.getReservationTime());
                    detailInsert.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}