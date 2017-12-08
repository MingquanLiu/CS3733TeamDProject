package edu.wpi.cs3733.programname.commondata;

import java.util.List;

public class TextDirection {
    public enum directionSymbol {
        LEFT,
        SHARPLEFT,
        RIGHT,
        SHARPRIGHT,
        STRAIGHT,
        SLIGHTLEFT,
        SLIGHTRIGHT,
        START,
        END,
        INTERMED,
        STAIR,
        ELEV
    }

    private String direction;
    private NodeData thisNode;
    private directionSymbol symbol;
    private List<NodeData> nodes;


    public TextDirection(String direction, NodeData node, directionSymbol symbol) {
        this.direction = direction;
        this.thisNode = node;
        this.symbol = symbol;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public NodeData getThisNode() {
        return thisNode;
    }

    public void setThisNode(NodeData thisNode) {
        this.thisNode = thisNode;
    }

    public directionSymbol getSymbol() {
        return symbol;
    }

    public void setSymbol(directionSymbol symbol) {
        this.symbol = symbol;
    }

    public List<NodeData> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeData> nodes) {
        this.nodes = nodes;
    }
}
