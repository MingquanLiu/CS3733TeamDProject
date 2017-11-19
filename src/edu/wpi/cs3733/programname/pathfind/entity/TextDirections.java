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
        String lastType = null;
        for(NodeData node: this.nodeList) {
            String type = node.getType();
            String name = node.getLongName();

            if(lastType == null) {
                // We need to start somewhere!
                directions = "Begin at " + name;
            }

            else {
                switch (type) {
                    case "ELEV":
                        directions += "\nGet on " + name;
                        break;
                    case "STAI":
                        directions += "\nEnter " + name;
                        break;
                    case "HALL":
                        if(lastType != "HALL")
                            directions += "\nProceed down the hall";
                        break;

                }
            }

            lastType = type;
        }
    }


    public String getTextDirections() {
        return this.directions;
    }
}
