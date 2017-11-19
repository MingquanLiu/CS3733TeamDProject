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
        NodeData nextNode;

        for(int i = 1; i < nodeList.size() - 1; i++) {
            node = nodeList.get(i);
            lastNode = nodeList.get(i-1);
            nextNode = nodeList.get(i+1);
            String type = node.getType();
            String name = node.getLongName();
            String face; // This is the direction the node is facing relative to the last

            double directionChange = getDirectionAngle(lastNode, node, nextNode);
            if(directionChange <= 45 && directionChange > -45) face = "right";
            else if(directionChange >= 135 && directionChange < -135) face = "left";
            else face = "straight";
            switch (type) {
                case "ELEV":
                    if(lastNode.getType().equals("ELEV"))
                        directions += "\nGet off the elevator on floor " + node.getFloor();
                    else
                        directions += "\nGet on " + name;
                    break;
                case "STAI":
                    if(lastNode.getType().equals("STAI"))
                        directions += "\nExit the stairs on floor " + node.getFloor();
                    else
                        directions += "\nEnter " + name;
                    break;
                case "HALL":
                    if(!lastNode.getType().equals("HALL") || !face.equals("straight"))
                        directions += "\nGo " + face + " down the hall";
                    break;
                default:
                    directions += "\nContinue past " + name;
                    break;
            }
        }
        directions += "\nYou have arrived at " + nodeList.get(nodeList.size()-1).getLongName();
    }

    public double getDirectionAngle(NodeData previous, NodeData current, NodeData next) {
        double firstEdgeAngle = Math.atan2((current.getY()-previous.getY()),(current.getX()-previous.getX()));
        double secondEdgeAngle = Math.atan2((next.getY()-current.getY()),(next.getX()-current.getX()));
        firstEdgeAngle = Math.toDegrees(firstEdgeAngle);
        secondEdgeAngle = Math.toDegrees(secondEdgeAngle);

        double a = secondEdgeAngle - firstEdgeAngle;
        double angleBetween = ((a + 180)%360) - 180;
        System.out.println(angleBetween);

        return angleBetween;

    }

    public String getTextDirections() {
        return this.directions;
    }
}
