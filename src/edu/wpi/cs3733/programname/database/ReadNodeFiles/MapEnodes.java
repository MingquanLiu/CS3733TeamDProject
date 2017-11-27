package edu.wpi.cs3733.programname.database.ReadNodeFiles;

import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.NodeData;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class MapEnodes {

    public static ArrayList<NodeData> readNodes(Connection conn) {
        ArrayList<NodeData> nodeList = new ArrayList<NodeData>();
        File file = new File("CSVNodes/MapEnodes.csv");

        try {
// A C


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
                NodeData nodeObject = new NodeData(values[0], location, values[3], values[4], values[5], values[6], values[7], values[8]);
                nodeList.add(nodeObject);



            } // end while




        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        return nodeList;
    } // end readNodes
}
