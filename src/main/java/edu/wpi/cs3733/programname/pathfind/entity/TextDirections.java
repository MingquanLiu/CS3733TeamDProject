package edu.wpi.cs3733.programname.pathfind.entity;

import edu.wpi.cs3733.programname.commondata.NodeData;
import edu.wpi.cs3733.programname.commondata.TextDirection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static edu.wpi.cs3733.programname.commondata.TextDirection.directionSymbol.*;

public class TextDirections {

    List<NodeData> nodeList;
    List<TextDirection> directionList;

    /**
     * The constructor for this class generates the text directions--call getTextDirections() to return them
     * @param nodeList - a list of nodes along the desired path in order of goal->start (it is reversed here)
     */
    public TextDirections(List<NodeData> nodeList) {
        this.nodeList = nodeList;
        directionList = new LinkedList<>();
        produceText();
    }

    /**
     * This method is called automatically on object creation and generates the list of text directions
     */
    private void produceText() {
        // Gotta start somewhere
        NodeData first = nodeList.get(0);
        directionList.add(new TextDirection("Begin at " + first.getLongName(), first, START));

        NodeData lastNode;
        NodeData thisNode;
        NodeData nextNode;

        for(int i = 1; i < nodeList.size() - 1; i++) {
            int hallDistance = 0;
            lastNode = nodeList.get(i-1);
            thisNode = nodeList.get(i);
            nextNode = nodeList.get(i+1);

            if(lastNode.getNodeType().equals("HALL")) hallDistance += distanceBetween(lastNode, thisNode);

            String type = thisNode.getNodeType();
            String name = thisNode.getLongName();
            TextDirection.directionSymbol faceSymbol;
            String face; // This is the direction the node is facing relative to the last

            double directionChange = 0 - getDirectionAngle(lastNode, thisNode, nextNode);
            if(directionChange <= -45 && directionChange >= -135) {
                face = "right";
                faceSymbol = RIGHT;
            }
            else if(directionChange <= 135 && directionChange >= 45) {
                face = "left";
                faceSymbol = LEFT;
            }
            else if(directionChange > 25 && directionChange < 45) {
                face = "slight left";
                faceSymbol = SLIGHTLEFT;
            }
            else if(directionChange < -25 && directionChange > -45) {
                face = "slight right";
                faceSymbol = SLIGHTRIGHT;
            }
            else if(directionChange > -180 && directionChange < -135) {
                face = "sharp right";
                faceSymbol = SHARPRIGHT;
            }
            else if(directionChange < 180 && directionChange > 135) {
                face = "sharp left";
                faceSymbol = SHARPLEFT;
            }
            else {
                face = "straight";
                faceSymbol = STRAIGHT;
            }

            LinkedList<NodeData> hallNodes = new LinkedList<>();

            switch (type) {
                case "ELEV":
                    if(lastNode.getNodeType().equals("ELEV"))
                        directionList.add(new TextDirection("Get off the elevator on floor " + thisNode.getFloor(),
                                thisNode, INTERMED));
                    else if(lastNode.getNodeType().equals("HALL") && nextNode.getNodeType().equals("ELEV")) {
                        directionList.add(new TextDirection("Go straight down the hall for about " + hallDistance + " feet",
                                thisNode, STRAIGHT));
                        hallDistance = 0;
                        hallNodes.clear();
                        directionList.add(new TextDirection("Get on " + name, thisNode, INTERMED));
                    }
                    else if(nextNode.getNodeType().equals("ELEV")) {
                        directionList.add(new TextDirection("Get on " + name, lastNode, thisNode, faceSymbol));
                    }
                    else hallDistance += distanceBetween(thisNode, nextNode);
                    break;
                case "STAI":
                    if(lastNode.getNodeType().equals("STAI"))
                        directionList.add(new TextDirection("Exit the stairs on floor " + thisNode.getFloor(),
                                thisNode, INTERMED));
                    else if(lastNode.getNodeType().equals("HALL") && nextNode.getNodeType().equals("STAI")) {
                        directionList.add(new TextDirection("Go straight down the hall for about " + hallDistance + " feet",
                                thisNode, STRAIGHT));
                        hallDistance = 0;
                        hallNodes.clear();
                        directionList.add(new TextDirection("Enter " + name, thisNode, INTERMED));
                    }
                    else if(nextNode.getNodeType().equals("STAI")){
                        directionList.add(new TextDirection("Enter " + name, lastNode, thisNode, faceSymbol));
                    }
                    else hallDistance += distanceBetween(thisNode, nextNode);
                    break;
                case "HALL":
                    if(!lastNode.getNodeType().equals("HALL")) {
                        if (Math.abs(directionChange) > 55) {
                            directionList.add(new TextDirection("Take the next " + face + " turn down the hall",
                                    lastNode, thisNode, nextNode, faceSymbol));
                            hallDistance += distanceBetween(thisNode, nextNode);
                            hallNodes.add(thisNode);
                        }
                        else {
                            hallDistance += distanceBetween(thisNode, nextNode);
                            hallNodes.add(thisNode);
                        }
                    }
                    else if(Math.abs(directionChange) > 55) {
                        directionList.add(new TextDirection("Go down the hall for about " + hallDistance + " feet",
                                hallNodes, STRAIGHT));
                        hallDistance = 0;
                        hallNodes.clear();
                        directionList.add(new TextDirection("Take the next " + face + " turn down the hall",
                                lastNode, thisNode, nextNode, faceSymbol));
                        hallDistance += distanceBetween(thisNode, nextNode);
                        hallNodes.add(thisNode);
                    }
                    else {
                        hallDistance += distanceBetween(thisNode, nextNode);
                        hallNodes.add(thisNode);
                    }
                    break;
                default:
                    if(lastNode.getNodeType().equals("HALL")) {
                        directionList.add(new TextDirection("Travel down the hall about " + hallDistance + " feet, then continue " +
                        face + " past " + thisNode.getLongName(), hallNodes, faceSymbol));
                        hallDistance = 0;
                        hallNodes.clear();
                    }
                    else directionList.add(new TextDirection("Continue " + face + " past " + name, thisNode, faceSymbol));
                    break;
            }
        }
        NodeData secondToLast = nodeList.get(nodeList.size() - 2);
        NodeData last = nodeList.get(nodeList.size() - 1);
        directionList.add(new TextDirection("Continue until you arrive at " + last.getLongName() + " in about " + distanceBetween(secondToLast, last) + " feet",
                secondToLast, last, STRAIGHT));
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
    public List<TextDirection> getTextDirections() {
        return this.directionList;
    }

    /**
     * This method is called to get a formatted email message body containing a short message with
     * text directions included
     * @return: a lengthy string with the message inside
     */
    // TODO: Update this
    public String getEmailMessageBody() {
        String emailDirections = "Hello,\nYou recently requested directions from " + nodeList.get(0).getLongName() +
               " to " + nodeList.get(nodeList.size()-1).getLongName() + " at the Brigham and Women's hospital. " +
                "Your directions are included below.\n";
        for(int i = 0; i < directionList.size(); i++) {
            emailDirections += "\n" + (i + 1) + ". " + directionList.get(i).getDirection();
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
