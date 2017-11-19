package edu.wpi.cs3733.programname.pathfind.entity;

import edu.wpi.cs3733.programname.commondata.NodeData;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TextDirections {
    List<NodeData> nodeList;
    String directions;

    public TextDirections(List<NodeData> nodeList) {
        this.nodeList = nodeList;
        Collections.reverse(this.nodeList);
        produceText();
    }

    private void produceText() {
        // Gotta start somewhere
        directions = "Begin at " + nodeList.get(0).getLongName();
        NodeData lastNode;
        NodeData node;

        for(int i = 1; i < nodeList.size() - 1; i++) {
            node = nodeList.get(i);
            lastNode = nodeList.get(i-1);
            String type = node.getType();
            String name = node.getLongName();
            String face;

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
        directions += "\nYou have arrived at " + nodeList.get(nodeList.size()-1).getLongName();
    }


    public String getTextDirections() {
        return this.directions;
    }
}
