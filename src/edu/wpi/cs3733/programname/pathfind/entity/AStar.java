package edu.wpi.cs3733.programname.pathfind.entity;

import java.util.LinkedList;

public class AStar {
    LinkedList<StarNode> frontier;
    LinkedList<StarNode> closed;

    public AStar(LinkedList<StarNode> frontier) {
        this.frontier = frontier;
    }

    public LinkedList<StarNode> pathFind() {}

    public int actionCost(StarNode node) {}

    public int distanceToGo(StarNode node) {}
}
