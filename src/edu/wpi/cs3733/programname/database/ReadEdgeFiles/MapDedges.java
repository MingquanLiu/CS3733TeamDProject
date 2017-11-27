package edu.wpi.cs3733.programname.database.ReadEdgeFiles;

import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class MapDedges {

    // EDGES MapDedges.csv
    public static ArrayList<EdgeData> readEdges(Connection conn) {
        // ArrayLists stores data values is proper columns
        ArrayList<EdgeData> edgeList = new ArrayList<EdgeData>();
        File file = new File("CSVEdges/MapDedges.csv");

        try {

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




        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        return edgeList;
    } // end readEdges
}
