import org.junit.Assert;
import org.junit.Test;
import CS3733TeamDProject1/src/edu/wpi/cs3733/programname/pathfind/entity;
package edu.wpi.cs3733.test;


public class ExampleTest {

  //  initialize examples here
    Astar Astarexample = new Astar();
    StarNode startingnode = new StarNode();
    StarNode goalnode = new StarNode();


    // Nodes to use
    // DCONF00102 (4595, 550) Carrie M. Hall Conference Center Floor 2      STARTING NODE
    // DREST00302 (3635, 830) Restroom C-D elevator Floor 2                 GOAL NODE

    public ExampleTest() {}
    // List of tests we need:
    //TODO test ASTAR Function as a whole

    @Test
    public void exampleTest1(){
        Assert.assertEquals(1,1);
    }


    //4594-3635= 960= 921600, 550-830 = -280 = 78400 = 1000
    @Test
    public void testDistanceToGoStarting() {
        Assert.assertEquals(Astarexample.DistanceToGo(startingnode, goalnode), 1000, 0); //TODO add correct value
    }

    @Test
    public void testDistanceToGoDestination() {
        Assert.assertEquals(Astarexample.DistanceToGo(goalnode, goalnode), 0, 0); //
    }


}
