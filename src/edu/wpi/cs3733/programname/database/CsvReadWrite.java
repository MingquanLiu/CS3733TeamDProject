package edu.wpi.cs3733.programname.database;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CsvReadWrite {
    public CsvReadWrite(){
    }

    // NODES MapDnodes.csv
    public void csvNodes(Connection conn) {
        String fileName = "MapDnodes.csv";
        File file = new File(fileName);
        int i;

        // ArrayLists stores data values is proper columns
        ArrayList<String> nodeID = new ArrayList<String>();         // nodeID           String
        ArrayList<Integer> xcoord = new ArrayList<Integer>();        // xcoord           Integer
        ArrayList<Integer> ycoord = new ArrayList<Integer>();        // ycoord           Integer
        ArrayList<Integer> floor = new ArrayList<Integer>();         // floor            Integer
        ArrayList<String> building = new ArrayList<String>();       // building         String
        ArrayList<String> nodeType = new ArrayList<String>();       // nodeType         String
        ArrayList<String> longName = new ArrayList<String>();       // longName         String
        ArrayList<String> shortName = new ArrayList<String>();      // shortName        String
        ArrayList<String> teamAssigned = new ArrayList<String>();   // teamAssigned     String

        try {
            Scanner inputStream = new Scanner(file);

            // Ignores first line in csv file i.e. header row
            inputStream.nextLine();

            // Reads all lines in the file
            while (inputStream.hasNextLine()) {
                // Reads current row and converts to a string
                String data = inputStream.nextLine();

                // Seperates the string into fields and stores into an array
                String[] values = data.split(",");

                // Converts int fields from strings to integers
                int x = Integer.parseInt(values[1]);
                int y = Integer.parseInt(values[2]);
                int f = Integer.parseInt(values[3]);

                // Stores the broken down fields into respective ArrayLists
                nodeID.add(values[0]);
                xcoord.add(x);
                ycoord.add(y);
                floor.add(f);
                building.add(values[4]);
                nodeType.add(values[5]);
                longName.add(values[6]);
                shortName.add(values[7]);
                teamAssigned.add(values[8]);
            } // end while

            // nodeID is unique and used to count the number of lines read in the file minus the header
            int count = nodeID.size();

            // SQL Insert
            PreparedStatement pst = conn.prepareStatement("INSERT INTO Nodes(nodeID, xcoord, ycoord, floor, building, nodeType, longName, shortName, teamAssigned)" +
                                                                "VALUES (?,?,?,?,?,?,?,?,?)");

            // Loops and increments to insert all data from the file into the nodes table
            for (i = 0; i < count; i++) {
                pst.setString(1, nodeID.toArray()[i].toString());
                pst.setInt(2, Integer.valueOf(xcoord.toArray()[i].toString()));
                pst.setInt(3, Integer.valueOf(ycoord.toArray()[i].toString()));
                pst.setInt(4, Integer.valueOf(floor.toArray()[i].toString()));
                pst.setString(5, building.toArray()[i].toString());
                pst.setString(6, nodeType.toArray()[i].toString());
                pst.setString(7, longName.toArray()[i].toString());
                pst.setString(8, shortName.toArray()[i].toString());
                pst.setString(9, teamAssigned.toArray()[i].toString());
                pst.executeUpdate();
            } // end for

            conn.commit();

            // Write out the csv file
            String outFileName = "MapDnodes(WriteOut).csv";
            FileWriter wrt = new FileWriter(outFileName, false);
            BufferedWriter buf = new BufferedWriter(wrt);
            PrintWriter prt = new PrintWriter(buf);
            int j;

            // Prints header fields
            prt.println("nodeId, xcoord, ycoord, floor, building, nodeType, longName, shortName, teamAssigned");

            for (j = 0; j < nodeID.size(); j++) {
                prt.println(nodeID.toArray()[j].toString() + "," +
                        xcoord.toArray()[j].toString() + "," +
                        ycoord.toArray()[j].toString() + "," +
                        floor.toArray()[j].toString() + "," +
                        building.toArray()[j].toString() + "," +
                        nodeType.toArray()[j].toString() + "," +
                        longName.toArray()[j].toString() + "," +
                        shortName.toArray()[j].toString() + "," +
                        teamAssigned.toArray()[j].toString()
                );
            } // end for

            prt.flush();
            prt.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, e);

        } catch (IOException e) {
            e.printStackTrace();

        }
    } // end csvNodes
    // EDGES MapDedges.csv
    public void csvEdges(Connection conn) {
        String fileName = "MapDedges.csv";
        File file = new File(fileName);
        int i;

        // ArrayLists stores data values is proper columns
        ArrayList<String> edgeID = new ArrayList<String>();         // edgeID       String
        ArrayList<String> startNode = new ArrayList<String>();      // startNode    String
        ArrayList<String> endNode = new ArrayList<String>();        // endNode      String

        try {
            Scanner inputStream = new Scanner(file);

            // Ignores first line in csv file i.e. header row
            inputStream.nextLine();

            // Reads all lines in the file
            while (inputStream.hasNextLine()) {
                // Reads current row and converts to a string
                String data = inputStream.nextLine();

                // Seperates the string into fields and stores into an array
                String[] values = data.split(",");


                // Stores the broken down fields into respective ArrayLists
                edgeID.add(values[0]);
                startNode.add(values[1]);
                endNode.add(values[2]);
            } // end while

            // edgeID is unique and used to count the number of lines read in the file minus the header
            int count = edgeID.size();

            // SQL Insert
            PreparedStatement pst = conn.prepareStatement("INSERT INTO Edges(edgeID, startNode, endNode)" +
                    "VALUES (?,?,?)");

            // Loops and increments to insert all data from the file into the edges table
            for (i = 0; i < count; i++) {
                pst.setString(1, edgeID.toArray()[i].toString());
                pst.setString(2, startNode.toArray()[i].toString());
                pst.setString(3, endNode.toArray()[i].toString());
                pst.executeUpdate();
            } // end for

            conn.commit();

            // Write File
            String outFileName = "MapDedges(WriteOut).csv";
            FileWriter wrt = new FileWriter(outFileName, false);
            BufferedWriter buf = new BufferedWriter(wrt);
            PrintWriter prt = new PrintWriter(buf);
            int j;

            // Prints header fields
            prt.println("edgeID, startNode, endNode");
            for (j = 0; j < edgeID.size(); j++) {
                prt.println(edgeID.toArray()[j].toString() + "," +
                        startNode.toArray()[j].toString() + "," +
                        endNode.toArray()[j].toString()
                );
            } // end for

            prt.flush();
            prt.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, e);

        } catch (IOException e) {
            e.printStackTrace();

        }
    } // end csvEdges
}