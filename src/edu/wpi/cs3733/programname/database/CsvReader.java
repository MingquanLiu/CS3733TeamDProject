package edu.wpi.cs3733.programname.database;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.wpi.cs3733.programname.commondata.*;

public class CsvReader {

    /**
     * empty constructor for CsvReader
     */
    public CsvReader() {
    }

    // NODES MapDnodes.csv

    /**
     *
     * @param conn
     * @return
     */
    public ArrayList<NodeData> readNodes(Connection conn) {
        ArrayList<NodeData> nodeList = new ArrayList<NodeData>();
        String fileName = "MapDnodes.csv";
        File file = new File(fileName);


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
                Coordinate location = new Coordinate(x, y);
                NodeData nodeObject = new NodeData(values[0], x, y, values[3], values[5], values[6], values[7]);
                nodeList.add(nodeObject);
            } // end while

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        return nodeList;
    } // end readNodes


    // Insert Nodes into DB
    public void insertNodes(Connection conn, ArrayList<NodeData> nodesList) {
        try {
            int i;
            int count = nodesList.size();
            PreparedStatement pst = conn.prepareStatement("INSERT INTO Nodes(nodeID, xcoord, ycoord, floor,building, nodeType, longName, shortName, teamAssigned)" +
                    "VALUES (?,?,?,?,?,?,?,?,?)");

            for (i = 0; i < count; i++) {
                pst.setString(1, nodesList.get(i).getId());
                pst.setInt(2, nodesList.get(i).getX());
                pst.setInt(3, nodesList.get(i).getY());
                pst.setString(4, nodesList.get(i).getFloor());
                pst.setString(5, "15 Francis");
                pst.setString(6, nodesList.get(i).getType());
                pst.setString(7, nodesList.get(i).getLongName());
                pst.setString(8, nodesList.get(i).getShortName());
                pst.setString(9, "Team D");
                pst.executeUpdate();

            }


        } catch (
                SQLException e)

        {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, e);

        }
    } // end insertNodes


    // EDGES MapDedges.csv
    public ArrayList<Edge> readEdges(Connection conn) {
        String fileName = "MapDedges.csv";
        File file = new File(fileName);

        // ArrayLists stores data values is proper columns
        ArrayList<Edge> edgeList = new ArrayList<Edge>();

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

                Edge edgeObject = new Edge(values[1], values[2], values[0]);
                edgeList.add(edgeObject);

            } // end while
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        return edgeList;
    } // end readEdges


    public void insertEdges(Connection conn, ArrayList<Edge> edgesList) {
        // edgeID is unique and used to count the number of lines read in the file minus the header
        int i;
        int count = edgesList.size();

        try {
            // SQL Insert
            PreparedStatement pst = conn.prepareStatement("INSERT INTO Edges(edgeID, startNode, endNode)" +
                    "VALUES (?,?,?)");

            // Loops and increments to insert all data from the file into the edges table
            for (i = 0; i < count; i++) {
                pst.setString(1, edgesList.get(i).getEdgeId());
                pst.setString(2, edgesList.get(i).getFirstNodeId());
                pst.setString(3, edgesList.get(i).getSecondNodeId());
                pst.executeUpdate();
            }

        } catch (
                SQLException e)

        {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, e);

        }
    } // end insertNodes


}