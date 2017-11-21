package edu.wpi.cs3733.programname.database;

import java.awt.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.wpi.cs3733.programname.commondata.*;
import edu.wpi.cs3733.programname.database.ReadFiles.*;

import javax.xml.soap.Node;


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
    public ArrayList<NodeData> getListOfNodes(Connection conn) {
        ArrayList<NodeData> A = new ArrayList<NodeData>();
        ArrayList<NodeData> B = new ArrayList<NodeData>();
        ArrayList<NodeData> C = new ArrayList<NodeData>();
        ArrayList<NodeData> D = new ArrayList<NodeData>();
        ArrayList<NodeData> E = new ArrayList<NodeData>();
        ArrayList<NodeData> F = new ArrayList<NodeData>();
        ArrayList<NodeData> G = new ArrayList<NodeData>();
        ArrayList<NodeData> H = new ArrayList<NodeData>();
        ArrayList<NodeData> I = new ArrayList<NodeData>();
        ArrayList<NodeData> W = new ArrayList<NodeData>();
        ArrayList<NodeData> FinalList = new ArrayList<NodeData>();

        A = MapA.readNodes(conn);
        B = MapB.readNodes(conn);
        C = MapC.readNodes(conn);
        D = MapD.readNodes(conn);
        E = MapE.readNodes(conn);
        F = MapF.readNodes(conn);
        G = MapG.readNodes(conn);
        H = MapH.readNodes(conn);
        I = MapI.readNodes(conn);
        W = MapW.readNodes(conn);

        FinalList.addAll(A);
        FinalList.addAll(B);
        FinalList.addAll(C);
        FinalList.addAll(D);
        FinalList.addAll(E);
        FinalList.addAll(F);
        FinalList.addAll(G);
        FinalList.addAll(H);
        FinalList.addAll(I);
        FinalList.addAll(W);

        System.out.println(FinalList.size());
        return FinalList;

    } // end readNodes



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

            System.out.println("Number of rows: " + j);

        } catch (SQLException e) {

        }
    } // end insertNodes


    // EDGES MapDedges.csv
    public ArrayList<EdgeData> readEdges(Connection conn) {
        // ArrayLists stores data values is proper columns
        ArrayList<EdgeData> edgeList = new ArrayList<EdgeData>();
        String[] TeamLetter = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "W"};

        try {

            for (String letter: TeamLetter) {
                String fileName = "Map" + letter + "edges.csv";
                File file = new File("CSVEdges/" + fileName);
                Scanner inputStream = new Scanner(file);

                // Ignores first line in csv file i.e. header row
                inputStream.hasNextLine();

                // Reads all lines in the file
                while (inputStream.hasNextLine()) {
                    // Reads current row and converts to a string
                    String data = inputStream.nextLine();

                    // Seperates the string into fields and stores into an array
                    String[] values = data.split(",");

                    EdgeData edgeObject = new EdgeData(values[0], values[1], values[2]);
                    edgeList.add(edgeObject);

                } // end while

            }
            int count = 0;
            for (int i = 0; i < edgeList.size(); i++) {
                count++;

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        return edgeList;
    } // end readEdges


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

        }
    } // end insertNodes


}