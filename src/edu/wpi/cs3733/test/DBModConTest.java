package edu.wpi.cs3733.test;


import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.commondata.Coordinate;
import edu.wpi.cs3733.programname.database.DatabaseModificationController;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DBModConTest {

    Coordinate aBathroomCoord = new Coordinate(4125, 625);
    Coordinate replacedBathroomCoord = new Coordinate(5124, 625);
    NodeData aBathroom = new NodeData ("DREST00102", aBathroomCoord, "REST","Restroom B elevator Floor 2", "Restroom B");
    NodeData newBathroom = new NodeData ("DREST00102", replacedBathroomCoord, "REST","Restroom B elevator Floor 2", "Restroom B");
    DatabaseModificationController theDBModControl = new DatabaseModificationController();

    @Test
    public void checkAddNode(){
        theDBModControl.addNode(aBathroom);
      //  assertEquals("insert into Nodes values(DREST00102, 4125, 625, REST, Restroom B elevator Floor 2, Restroom B)", theDBModControl.addNode(aBathroom));
    }


    @Test
    public void checkEditNode(){
     //   assertEquals("update Nodes set xcoord = 5124, ycoord = 625, nodeType = REST, longName = Restroom B elevator Floor 2, shortName = Restroom Bwhere nodeID = DREST00102", theDBModControl.editNode(newBathroom));
    }
}