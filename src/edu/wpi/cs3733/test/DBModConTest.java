package edu.wpi.cs3733.test;

import com.sun.corba.se.impl.orbutil.graph.NodeData;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DBModConTest {


    Coordinate aBathroomCoord = new Coordinate(4125, 625);
    NodeData aBathroom = new NodeData ("DREST00102", aBathroomCoord, "REST","Restroom B elevator Floor 2", "Restroom B");
    DatabaseModficationController theDBModControl = new DatabaseModificationController ();

    @Test
    public String addNode1(){
        assertEquals("insert into Node values (DREST00102, 4125, 625, REST, Restroom B elevator Floor 2, Restroom B)", theDBModControl.addNode(aBathroom));
    }

    @Test
    public String addNode2(){
        assertEquals("Error", theDBModControl.addNode(aBathroom));
    }

    @Test
    public String editNode1(){
        assertEquals("")
    }
}