import org.junit.Assert;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
package edu.wpi.cs3733.test;


public class ExampleTest {

    //initialize examples here
    Astar Astarexample = new Astar();
    StarNode startingnode = new StarNode();
    StarNode destinationnode = new StarNode();
    StarNode intermediatenode = new StarNode();

    public ExampleTest() {}
    // List of tests we need:
    //TODO test ASTAR Function as a whole

    @Test
    public void exampleTest1(){
        Assert.assertEquals(1,1);
    }

    @Test
    public void testActionCostStarting() {
        Assert.assertEquals(Astarexample.actionCost(startingnode), 0, 0);
    }

    @Test
    public void testActionCostFinal() {
        Assert.assertEquals(Astarexample.actionCost(destinationnode), 850, 0); //TODO add correct value
    }

    @Test
    public void testDistanceToGoStarting() {
        Assert.assertEquals(Astarexample.DistanceToGo(startingnode), 850, 0); //TODO add correct value
    }

    @Test
    public void testDistanceToGoDestination() {
        Assert.assertEquals(Astarexample.DistanceToGo(destinationnode), 0, 0); //
    }

    @Test
    public void testDistanceToGoIntermediate() {
        Assert.assertEquals(Astarexample.DistanceToGo(intermediatenodenode), 0, 0); //TODO add correct value
    }

}
