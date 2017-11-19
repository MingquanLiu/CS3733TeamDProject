package edu.wpi.cs3733.programname.pathfind.entity;

import edu.wpi.cs3733.programname.commondata.NodeData;

import java.util.HashMap;
import java.util.List;

public class TextDirections {
    List<NodeData> nodeList;
    String directions;

    public TextDirections(List<NodeData> nodeList) {
        this.nodeList = nodeList;
    }

    private void produceText() {
        for(NodeData node: this.nodeList) {
            String type = node.getType();
            String name = node.getLongName();

            directions += "\nGo to " + name;
        }
    }


    public String getTextDirections() {
        return this.directions;
    }
}
