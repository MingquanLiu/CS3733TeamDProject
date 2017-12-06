package edu.wpi.cs3733.programname.pathfind.entity;

import edu.wpi.cs3733.programname.commondata.NodeData;

import java.util.Collections;
import java.util.List;

import static sun.security.krb5.Confounder.intValue;

public class TextDirections {

    List<NodeData> nodeList;
    String directions, prettyDirections;

    /**
     * The constructor for this class generates the text directions--call getTextDirections() to return them
     * @param nodeList - a list of nodes along the desired path in order of goal->start (it is reversed here)
     */
    public TextDirections(List<NodeData> nodeList) {
        this.nodeList = nodeList;
        Collections.reverse(this.nodeList);
        produceText();
    }

    /**
     * This method is called automatically on object creation and generates the string of text directions
     */
    private void produceText() {
        // Gotta start somewhere
        directions = "Begin at " + nodeList.get(0).getLongName();
        NodeData lastNode;
        NodeData node;
        NodeData nextNode;

        for(int i = 1; i < nodeList.size() - 1; i++) {
            int hallDistance = 0;
            node = nodeList.get(i);
            lastNode = nodeList.get(i-1);
            nextNode = nodeList.get(i+1);
            if(lastNode.getNodeType().equals("HALL")) hallDistance += distanceBetween(lastNode, node);
            String type = node.getNodeType();
            String name = node.getLongName();
            String face; // This is the direction the node is facing relative to the last
            double directionChange = 0 - getDirectionAngle(lastNode, node, nextNode);
            if(directionChange <= -45 && directionChange >= -135) face = "right";
            else if(directionChange <= 135 && directionChange >= 45) face = "left";
            else if(directionChange > 25 && directionChange < 45) face = "slight left";
            else if(directionChange < -25 && directionChange > -45) face = "slight right";
            else if(directionChange > -180 && directionChange < -135) face = "sharp right";
            else if(directionChange < 180 && directionChange > 135) face = "sharp left";
            else face = "straight";
            switch (type) {
                case "ELEV":
                    if(lastNode.getNodeType().equals("ELEV"))
                        directions += "\nGet off the elevator on floor " + node.getFloor();
                    else if(lastNode.getNodeType().equals("HALL")) {
                        directions += "\nGo straight down the hall for about " + hallDistance + " feet";
                        hallDistance = 0;
                        directions += "\nGet on " + name;
                    }
                    else directions += "\nGet on " + name;
                    break;
                case "STAI":
                    if(lastNode.getNodeType().equals("STAI"))
                        directions += "\nExit the stairs on floor " + node.getFloor();
                    else if(lastNode.getNodeType().equals("HALL") && nextNode.getNodeType().equals("STAI")) {
                        directions += "\nGo straight down the hall for about " + hallDistance + " feet";
                        hallDistance = 0;
                        directions += "\nEnter " + name;
                    }
                    else hallDistance += distanceBetween(node, nextNode);
                    break;
                case "HALL":
                    if(!lastNode.getNodeType().equals("HALL")) {
                        if (Math.abs(directionChange) > 55) {
                            directions += "\nTake the next " + face + " turn down the hall";
                            hallDistance += distanceBetween(node, nextNode);
                        }
                        else {
                            hallDistance += distanceBetween(node, nextNode);
                        }
                    }
                    else if(Math.abs(directionChange) > 55) {
                        directions += "\nGo down the hall for about " + hallDistance + " feet";
                        hallDistance = 0;
                        directions += "\nTake the next " + face + " and continue down the hall";
                        hallDistance += distanceBetween(node, nextNode);
                        System.out.println("\nTake the next " + face + " and continue down the hall " + hallDistance);
                    }
                    else hallDistance += distanceBetween(node, nextNode);
                    break;
                default:
                    if(lastNode.getNodeType().equals("HALL")) {
                        directions += "\nTravel down the hall about " + hallDistance + " feet, then continue " + face + " past " + name;
                        hallDistance = 0;
                    }
                    else directions += "\nContinue " + face + " past " + name;
                    break;
            }
        }
        NodeData secondToLast = nodeList.get(nodeList.size() - 2);
        NodeData last = nodeList.get(nodeList.size() - 1);
        directions += "\nContinue until you arrive at " + last.getLongName() + " in " + distanceBetween(secondToLast, last) + " feet";
        String[] lines = this.directions.split("\\r?\\n");
        for(int i = 0; i < lines.length; i++) {
            prettyDirections += "\n" + (i + 1) + ". " + lines[i];
        }
        System.out.println(prettyDirections);
    }

    /**
     * Helper function to figure out the angle you have to turn at a specific node to get to the next one
     *
     * @param previous: the previous node in the path
     * @param current: the current node (this is the one the visitor is hypothetically standing at)
     * @param next: the next node to travel to
     * @return: the angle between them. 90 is a left turn, -90 is a right turn, etc.
     */
    private double getDirectionAngle(NodeData previous, NodeData current, NodeData next) {
        double firstEdgeAngle = Math.atan2((current.getYCoord()-previous.getYCoord()),(current.getXCoord()-previous.getXCoord()));
        double secondEdgeAngle = Math.atan2((next.getYCoord()-current.getYCoord()),(next.getXCoord()-current.getXCoord()));
        firstEdgeAngle = Math.toDegrees(firstEdgeAngle);
        secondEdgeAngle = Math.toDegrees(secondEdgeAngle);

        double a = secondEdgeAngle - firstEdgeAngle;
        double angleBetween = ((a + 180)%360) - 180;

        if(angleBetween > 180) angleBetween = angleBetween - 360;
        else if(angleBetween < -180) angleBetween = angleBetween + 360;

        return angleBetween;

    }

    /**
     * This must be called to get the actual text directions
     *
     * @return: a block of text in a String with the directions from the start to the goal
     */
    public String getTextDirections() {
        return this.prettyDirections;
    }

    /**
     * This method is called to get a formatted email message body containing a short message with
     * text directions included
     * @return: a lengthy string with the message inside
     */
    public String getEmailMessageBody() {
        String emailDirections = "Hello,\nYou recently requested directions from " + nodeList.get(0).getLongName() +
               " to " + nodeList.get(nodeList.size()-1).getLongName() + " at the Brigham and Women's hospital. " +
                "Your directions are included below.\n";
        String[] lines = this.directions.split("\\r?\\n");
        for(int i = 0; i < lines.length; i++) {
            emailDirections += "\n" + (i + 1) + ". " + lines[i];
        }
        emailDirections += "\nRegards,\nKiosk Devs\n\n(Note: This inbox is not monitored. Please do not reply.)";
        return emailDirections;
    }

    /**
     *
     * @param node1 the first node
     * @param node2 the second node
     * @return the distance in feet between the two nodes
     */
    private int distanceBetween(NodeData node1, NodeData node2) {
        double xDist = node1.getXCoord() - node2.getXCoord();
        double yDist = node1.getYCoord() - node2.getYCoord();
        Double distToGo = Math.sqrt(xDist*xDist + yDist*yDist);
        return distToGo.intValue();
    }
}
