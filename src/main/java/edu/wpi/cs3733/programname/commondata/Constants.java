package edu.wpi.cs3733.programname.commondata;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.awt.*;

public class Constants {

// This is just a test comment
    public static String INTERPRETER_REQUEST = "interpreter";
    public static String MAINTENANCE_REQUEST = "maintenance";
    public static String TRANSPORTATION_REQUEST = "transportation";
    public static String NONE = "none";

    public static String UNASSIGNED_REQUEST = "unhandled";
    public static String ASSIGNED_REQUEST = "handled";
    public static String COMPLETED_REQUEST = "completed";
    public static String REMOVED_REQUEST = "removed";
    // Node Constant
    public final static int ORIGINAL_NODE_WIDTH = 30;
    public final static int ORIGINAL_NODE_HEIGHT = 30;

    public final static double MAX_UI_WIDTH = 5000;

    public final static double CIRCILE_RADIUS = 12;
    public final static double EXPANDED_CIRCILE_RADIUS = 17;

    public final static double OPACITY_SHOWN = 0.95;
    public final static double OPACITY_NOT_SHOWN = 0;

    public final static double OPACITY_KEY_LOCATION_SHOWN = 1.00;
    public final static double OPACITY_KEY_LOCATION_NOT_SHOWN = 0.4;
    public final static Paint NODE_COLOR = Color.rgb(231, 76, 60);
    public final static Paint NODE_ENLARGED_COLOR = Color.rgb(88, 214, 141);
    public final static Paint NODE_STROKE_COLOR = Color.rgb(0, 0, 0);
}
