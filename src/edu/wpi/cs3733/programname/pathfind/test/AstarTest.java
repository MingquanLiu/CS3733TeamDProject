package edu.wpi.cs3733.programname.pathfind.test;

import edu.wpi.cs3733.programname.pathfind.entity.AStar;
import edu.wpi.cs3733.programname.pathfind.entity.StarNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static junit.framework.TestCase.assertEquals;

public class AstarTest {

    public AstarTest() {}
    // List of tests we need:
    // -Action Cost
    // -Distance To Go
    // -A* finds best path

    @Before
    public void init() {
        //initialize examples here
        AStar Astarexample = new AStar();
        StarNode startingnode = new StarNode();
        StarNode destinationnode = new StarNode();
        StarNode intermediatenode = new StarNode();

        LinkedList<StarNode> finalOrder = new LinkedList<StarNode>
                (Arrays.asList(destinationnode,intermediatenode,startingnode));

    }

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
