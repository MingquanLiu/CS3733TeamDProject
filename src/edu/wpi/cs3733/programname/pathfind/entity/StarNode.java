package edu.wpi.cs3733.programname.pathfind.entity;

import java.util.LinkedList;

public class StarNode extends edu.wpi.cs3733.programname.commondata.NodeData {
    protected int f, g;
    protected StarNode previousNode;

    public StarNode() { }

    public LinkedList<StarNode> getNeighbors() {
        return null;
    }

    public StarNode getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(StarNode previousNode) {
        this.previousNode = previousNode;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }
}
