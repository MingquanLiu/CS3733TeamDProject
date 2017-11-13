package edu.wpi.cs3733.programname.pathfind.entity;

import edu.wpi.cs3733.programname.commondata.NodeData;

import java.util.LinkedList;

public class StarNode extends edu.wpi.cs3733.programname.commondata.NodeData {
    protected int f, g;
    protected StarNode previousNode;
    LinkedList<StarNode> neighbors;

    public StarNode(LinkedList<StarNode> neighbors, NodeData node) {
        this.neighbors = neighbors;

    }

    public LinkedList<StarNode> getNeighbors() {
        return neighbors;
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
