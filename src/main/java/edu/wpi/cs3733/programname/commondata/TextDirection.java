package edu.wpi.cs3733.programname.commondata;

import javafx.scene.image.Image;

import java.util.Arrays;
import java.util.LinkedList;
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
        INTERMED
    }

    private String direction;
    private List<NodeData> nodes;
    private directionSymbol symbol;

    public TextDirection(String direction, List<NodeData> nodes, directionSymbol symbol) {
        this.direction = direction;
        this.nodes = nodes;
        this.symbol = symbol;
    }

    public TextDirection(String direction, NodeData node, directionSymbol symbol) {
        this.direction = direction;
        this.nodes = new LinkedList<>(Arrays.asList(node));
        this.symbol = symbol;
    }

    public TextDirection(String direction, NodeData node1, NodeData node2, directionSymbol symbol) {
        this.direction = direction;
        this.nodes = new LinkedList<>(Arrays.asList(node1, node2));
        this.symbol = symbol;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public List<NodeData> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeData> nodes) {
        this.nodes = nodes;
    }

    public directionSymbol getSymbol() {
        return symbol;
    }

    public void setSymbol(directionSymbol symbol) {
        this.symbol = symbol;
    }
}
