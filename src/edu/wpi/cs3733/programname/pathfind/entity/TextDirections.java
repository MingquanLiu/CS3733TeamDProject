package edu.wpi.cs3733.programname.pathfind.entity;

import edu.wpi.cs3733.programname.commondata.NodeData;

import java.util.HashMap;
import java.util.List;

public class TextDirections {
    List<NodeData> nodeList;
    String directions;

    public TextDirections(List<NodeData> nodeList) {
        this.nodeList = nodeList;
        produceText();
    }

    private void produceText() {
        NodeData lastNode = null;
        for(NodeData node: this.nodeList) {
            String type = node.getType();
            String name = node.getLongName();
            String face;

            if(lastNode == null) {
                // We need to start somewhere!
                directions = "Begin at " + name;
            }

            else {
                double directionChange = Math.atan2((lastNode.getY()-node.getY()),(lastNode.getX()-node.getX()));
                if(directionChange < 90 && directionChange > 50) face = "left";
                else if(directionChange > -90 && directionChange < -50) face = "right";
                else face = "straight";
                switch (type) {
                    case "ELEV":
                        directions += "\nGet on " + name;
                        break;
                    case "STAI":
                        directions += "\nEnter " + name;
                        break;
                    case "HALL":
                        if(!lastNode.getType().equals("HALL"))
                            directions += "\nGo " + face + " down the hall";
                        break;
                    default:
                        directions += "\nContinue past " + name;
                        break;
                }
            }

            lastNode = node;
        }
    }


    public String getTextDirections() {
        return this.directions;
    }
}
