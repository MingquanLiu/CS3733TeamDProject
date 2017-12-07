package edu.wpi.cs3733.programname.commondata;

import edu.wpi.cs3733.programname.boundary.Floor;
import edu.wpi.cs3733.programname.boundary.TestingController;
import edu.wpi.cs3733.programname.pathfind.PathfindingController;
import edu.wpi.cs3733.programname.pathfind.PathfindingController.searchType;

public class AppSettings {

    private static AppSettings appSettings = new AppSettings();

    private searchType searchType;
    private boolean handicapPath;
    private int mapRatioIndex;

    private final int INITIAL_MAP_SCALE = 3;

    private AppSettings() {
        searchType = PathfindingController.searchType.ASTAR;
        handicapPath = false;
        mapRatioIndex = INITIAL_MAP_SCALE;
    }

    public static AppSettings getInstance() {
        return appSettings;
    }
    public searchType getSearchType() {
        return this.searchType;
    }
    public void setSearchType(searchType type) {
        this.searchType = type;
    }
    public boolean isHandicapPath() {
        return handicapPath;
    }
    public void setHandicapPath(boolean handicapPath) {
        this.handicapPath = handicapPath;
    }
    public int getMapRatioIndex() {
        return mapRatioIndex;
    }
    public void setMapRatioIndex(int mapRatioIndex) {
        this.mapRatioIndex = mapRatioIndex;
    }
}
