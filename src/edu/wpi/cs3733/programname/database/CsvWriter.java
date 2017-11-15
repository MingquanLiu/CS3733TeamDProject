package edu.wpi.cs3733.programname.database;

import java.io.*;
import java.sql.Connection;
import java.util.ArrayList;
import edu.wpi.cs3733.programname.commondata.*;

public class CsvWriter {

    public CsvWriter() {}

    public void writeNodes(Connection conn, ArrayList<NodeData> nodesList) {
        try {
            // Write out the csv file
            String outFileName = "MapDnodes(WriteOut).csv";
            FileWriter wrt = new FileWriter(outFileName, false);
            BufferedWriter buf = new BufferedWriter(wrt);
            PrintWriter prt = new PrintWriter(buf);
            int j;

            // Prints header fields
            prt.println("nodeId, xcoord, ycoord, floor, building, nodeType, longName, shortName, teamAssigned");

            for (j = 0; j < nodesList.size(); j++) {
                prt.println(
                        nodesList.get(j).getId() + "," +
                                nodesList.get(j).getX() + "," +
                                nodesList.get(j).getY() + "," +
                                nodesList.get(j).getFloor() + "," +
                                "15 Francis" + "," +
                                nodesList.get(j).getType() + "," +
                                nodesList.get(j).getLongName() + "," +
                                nodesList.get(j).getShortName() + "," +
                                "Team D"

                );
            } // end for

            System.out.println("Write out success!");

            prt.flush();
            prt.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }// end csvNodes



    public void writeEdges(Connection conn, ArrayList<Edge> edgesList) {
        try {
            // Write File
            String outFileName = "MapDedges(WriteOut).csv";
            FileWriter wrt = new FileWriter(outFileName, false);
            BufferedWriter buf = new BufferedWriter(wrt);
            PrintWriter prt = new PrintWriter(buf);
            int j;

            // Prints header fields
            prt.println("edgeID, startNode, endNode");
            for (j = 0; j < edgesList.size(); j++) {
                prt.println(edgesList.get(j).getEdgeId() + "," +
                        edgesList.get(j).getFirstNodeId() + "," +
                        edgesList.get(j).getSecondNodeId()
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
