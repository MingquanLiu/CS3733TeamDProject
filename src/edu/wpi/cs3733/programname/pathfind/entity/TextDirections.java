package edu.wpi.cs3733.programname.pathfind.entity;

import edu.wpi.cs3733.programname.commondata.NodeData;

import java.util.List;

public class TextDirections {
    List<NodeData> nodeList;
    String directions;

    public TextDirections(List<NodeData> nodeList) {
        this.nodeList = nodeList;
    }

    private void produceText() {
        
    }


    public String getTextDirections() {
        return this.directions;
    }
}
