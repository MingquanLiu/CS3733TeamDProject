package edu.wpi.cs3733.programname.pathfind.entity;

import java.util.LinkedList;

public class AStar {

    //list of all the nodes that are adjacent to nodes already explored
    LinkedList<StarNode> frontier;

    //list of all the nodes in the path from start to finish
    LinkedList<StarNode> closed;

    //constructor
    public AStar(LinkedList<StarNode> frontier) {
        this.frontier = frontier;
    }


    // calculates a path from start to finish
    public LinkedList<StarNode> pathFind() {}


    // returns the cost of moving from the starting node to the provided node
    public int actionCost(StarNode node) {}


    // returns the pixel distance from the provided node to the destination node
    public int distanceToGo(StarNode node) {}
}
