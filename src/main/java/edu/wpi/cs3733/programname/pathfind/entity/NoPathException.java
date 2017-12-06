package edu.wpi.cs3733.programname.pathfind.entity;

public class NoPathException extends Exception {
    public String startID;
    public String goalID;

    public NoPathException(String startID, String goalID) {
        this.startID = startID;
        this.goalID = goalID;

        System.out.println("No such path from " + startID + " to " + goalID);
    }
}
