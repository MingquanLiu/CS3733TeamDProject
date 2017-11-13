package edu.wpi.cs3733.programname.pathfind.entity;

import edu.wpi.cs3733.programname.commondata.NodeData;

import java.util.LinkedList;

public class StarNode extends edu.wpi.cs3733.programname.commondata.NodeData {
    protected int f, g;
    protected StarNode previousNode;
    protected LinkedList<StarNode> neighbors;

    public StarNode(NodeData node) {
        super(node.getId(), node.getLocation(), node.getType(), node.getLongName(), node.getShortName());
    }

    public LinkedList<StarNode> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(StarNode neighbor) {
        neighbors.add(neighbor);
    }

    public StarNode getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(StarNode previousNode) {
        this.previousNode = previousNode;
    }

    public int getF() {
        return this.f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getG() {
        return this.g;
    }

    public void setG(int g) {
        this.g = g;
    }
}
