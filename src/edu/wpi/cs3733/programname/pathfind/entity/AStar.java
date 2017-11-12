package edu.wpi.cs3733.programname.pathfind.entity;

import java.util.LinkedList;

public class AStar {
    LinkedList<StarNode> frontier;
    LinkedList<StarNode> closed;

    public AStar(LinkedList<StarNode> frontier) {
        this.frontier = frontier;
    }

    public LinkedList<StarNode> pathFind(StarNode start, StarNode goal) {
        Serial.out.println("Starting A*");

        while(!frontier.isEmpty()) {
            StarNode current = frontier.getFirst();
            if(current.getX() == goal.getX() && current.getY() == goal.getY()) {
                Serial.out.println("At target!");

            }
            
        }
    }

    public int actionCost(StarNode node) {}

    public int distanceToGo(StarNode node) {}
}