package edu.wpi.cs3733.programname.pathfind.PathStrategies;

import edu.wpi.cs3733.programname.commondata.EdgeData;
import edu.wpi.cs3733.programname.pathfind.PathStrategyIF;

import java.util.LinkedList;

/**
 * Created by Treksh on 19/11/17.
 */
public class HandicappedPath implements PathStrategyIF {
    @Override
    public void PathStrategy() {

    }

    @Override
    public LinkedList<EdgeData> getEdges(LinkedList<EdgeData> allEdges) {
//
//        LinkedList<Edge> currentList = new LinkedList<Edge>();
//
//        for (Edge e : allEdges) {
//            if (e.getRestrictions().contains(Edge.Restriction.HANDICAPPED)) {
//                currentList.add(e);
//            }
//        }
//        return currentList;
        return null;
    }
}