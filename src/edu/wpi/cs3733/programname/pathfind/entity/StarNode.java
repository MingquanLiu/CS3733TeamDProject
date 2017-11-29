/**
 * Star node class is a wrapper for NodeData with the extra fields
 * neighbors: list of connected nodes to this node
 * previous node: the node that this node was accessed through in pathfinding
 * f, g: cost to travel to this node and action cost, respectively
 */

package edu.wpi.cs3733.programname.pathfind.entity;

import edu.wpi.cs3733.programname.commondata.NodeData;

import java.util.LinkedList;

public class StarNode extends edu.wpi.cs3733.programname.commondata.NodeData implements Comparable<StarNode> {

    protected double f, g; // f is total cost, g is action cost to this node
    protected StarNode previousNode;
    protected LinkedList<StarNode> neighbors = new LinkedList<StarNode>();

    public StarNode(NodeData node) {
        super(node.getNodeID(), node.getLocation(), node.getFloor(), node.getBuilding(),
                node.getNodeType(), node.getLongName(), node.getShortName(), node.getTeamAssigned());
    }

    @Override
    public int compareTo(StarNode newNode) {
        double newNodeF = newNode.getF();
        if(this.f > newNodeF) return 1;
        else if(this.f == newNodeF) return 0;
        else return -1;
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

    public boolean isonlist(){
        if (g != 0) return true;
        return false;
    }

    public void addtolist(){
        this.g = 500;
    }

    public boolean isVisited() {
        if(f != 0) return true;
        return false;
    }

    public void visit() {
        this.f = 100;
    }

    public double getF() {
        return this.f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public double getG() {
        return this.g;
    }

    public void setG(double g) {
        this.g = g;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        StarNode starNode = (StarNode) o;

        if (Double.compare(starNode.f, f) != 0) return false;
        if (Double.compare(starNode.g, g) != 0) return false;
        if (previousNode != null ? !previousNode.equals(starNode.previousNode) : starNode.previousNode != null)
            return false;
        return neighbors != null ? neighbors.equals(starNode.neighbors) : starNode.neighbors == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(f);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(g);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
