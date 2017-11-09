package edu.wpi.cs3733.programname.pathfind.entity;

public class StarNode extends edu.wpi.cs3733.programname.commondata.NodeData {
    int f, g, h;

    public StarNode(int f, int g, int h) {
        this.f = f;
        this.g = g;
        this.h = h;
    }
}
