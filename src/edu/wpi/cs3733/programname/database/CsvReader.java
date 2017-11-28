package edu.wpi.cs3733.programname.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.wpi.cs3733.programname.commondata.*;
import edu.wpi.cs3733.programname.database.ReadNodeFiles.*;
import edu.wpi.cs3733.programname.database.ReadEdgeFiles.*;


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

        A = MapAnodes.readNodes(conn);
        B = MapBnodes.readNodes(conn);
        C = MapCnodes.readNodes(conn);
        D = MapDnodes.readNodes(conn);
        E = MapEnodes.readNodes(conn);
        F = MapFnodes.readNodes(conn);
        G = MapGnodes.readNodes(conn);
        H = MapHnodes.readNodes(conn);
        I = MapInodes.readNodes(conn);
        W = MapWnodes.readNodes(conn);

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

            System.out.println("Number of node rows: " + j);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } // end insertNodes


    // EDGES MapDedges.csv
    public ArrayList<EdgeData> readEdges(Connection conn) {
        ArrayList<EdgeData> A = new ArrayList<EdgeData>();
        ArrayList<EdgeData> B = new ArrayList<EdgeData>();
        ArrayList<EdgeData> C = new ArrayList<EdgeData>();
        ArrayList<EdgeData> D = new ArrayList<EdgeData>();
        ArrayList<EdgeData> E = new ArrayList<EdgeData>();
        ArrayList<EdgeData> F = new ArrayList<EdgeData>();
        ArrayList<EdgeData> G = new ArrayList<EdgeData>();
        ArrayList<EdgeData> H = new ArrayList<EdgeData>();
        ArrayList<EdgeData> I = new ArrayList<EdgeData>();
        ArrayList<EdgeData> W = new ArrayList<EdgeData>();
        ArrayList<EdgeData> FinalList = new ArrayList<EdgeData>();

        A = MapAedges.readEdges(conn);
        B = MapBedges.readEdges(conn);
        C = MapCedges.readEdges(conn);
        D = MapDedges.readEdges(conn);
        E = MapEedges.readEdges(conn);
        F = MapFedges.readEdges(conn);
        G = MapGedges.readEdges(conn);
        H = MapHedges.readEdges(conn);
        I = MapIedges.readEdges(conn);
        W = MapWedges.readEdges(conn);

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
            e.printStackTrace();
        }
    } // end insertNodes


}