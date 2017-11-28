package IntegrationTests;

import edu.wpi.cs3733.programname.ManageController;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.database.*;
import edu.wpi.cs3733.programname.pathfind.PathfindingController;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class DBPathfindIntegrationTest {

    Connection conn;

    @Before
    public void setupTables() {
        DBConnection TestDB = new DBConnection();   // Creates new instance of connection
        TestDB.setDBConnection();                   // Sets up the connection
        DBTables mDbTable = new DBTables();
        CsvReader mCsvReader = new CsvReader();
        CsvWriter mCsvWriter = new CsvWriter();
        conn = TestDB.getConnection();   // Initializes the connection to be passed through other methods

        mDbTable.createAllTables(TestDB);           // Makes nodes table
        mCsvReader.insertNodes(conn,mCsvReader.getListOfNodes(conn));
        mCsvWriter.writeNodes(conn, mCsvReader.getListOfNodes(conn));
        printTables.printNodesTable(conn);          // Pulls data in nodes table from the database and print it


//        mCsvReader.insertEdges(conn,mCsvReader.readEdges(conn));
//        mCsvWriter.writeEdges(conn, mCsvReader.readEdges(conn));
//        printTables.printEdgesTable(conn);          // Pulls data in nodes table from the database and print it
    }


    @Test
    public void testBasicDbPathfind() {
        ManageController manager = new ManageController();
        NodeData hallwayOne = new NodeData("DHALL00102", new Coordinate(4770,1140), "2",
                "15 Francis","HALL", "Hallway 1 Floor 2", "Hallway D0102", "Team D");
        NodeData hallwayTwo = new NodeData("DHALL00202", new Coordinate(4750,1080), "2",
                "15 Francis", "HALL", "Hallway 2 Floor 2", "Hallway D0202", "Team D");


        List<NodeData> expected = new ArrayList<>();
        expected.add(hallwayTwo);
        expected.add(hallwayOne);
        List<NodeData> result = manager.startPathfind(hallwayOne.getNodeID(), hallwayTwo.getNodeID(), PathfindingController.searchType.ASTAR);

        int i = 0;
        for (NodeData data: result) {
            assertEquals(expected.get(i).getLocation(), data.getLocation());
            i++;
        }
    }

//    @Test
//    public void testBasicDbPathfind() {
//        ManageController manager = new ManageController();
//        NodeData hallwayOne = new NodeData("DHALL00102", new Coordinate(4770,1140), "2",
//                "HALL", "Hallway 1 Floor 2", "Hallway D0102");
//        NodeData hallwayTwo = new NodeData("DHALL00202", new Coordinate(4750,1080), "2",
//                "HALL", "Hallway 2 Floor 2", "Hallway D0202");
//
//
//        List<NodeData> expected = new ArrayList<>();
//        expected.add(hallwayTwo);
//        expected.add(hallwayOne);
//        List<NodeData> result = manager.startPathfind(hallwayOne.getId(), hallwayTwo.getId());
//
//        int i = 0;
//        for (NodeData data: result) {
//            assertEquals(expected.get(i).getLocation(), data.getLocation());
//            i++;
//        }
//    }



}
