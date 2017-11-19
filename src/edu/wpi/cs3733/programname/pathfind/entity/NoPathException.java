package edu.wpi.cs3733.programname.pathfind.entity;

public class NoPathException extends Exception {
    String startID;
    String goalID;

    public NoPathException(String startID, String goalID) {
        this.startID = startID;
        this.goalID = goalID;

        System.out.println("No such node from " + startID + " to " + goalID);
    }
}
