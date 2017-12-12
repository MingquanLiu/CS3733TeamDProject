package edu.wpi.cs3733.programname.boundary;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FloorSorter implements Comparator<Floor>{

    private static final List<String> ORDERED_ENTRIES = Arrays.asList(
            "L2","L1","G","1","2","3");

    @Override
    public int compare(Floor f1, Floor f2) {
        String o1 = f1.getFloorNum();
        String o2 = f2.getFloorNum();
        if (ORDERED_ENTRIES.contains(o1) && ORDERED_ENTRIES.contains(o2)) {
            return ORDERED_ENTRIES.indexOf(o1) - ORDERED_ENTRIES.indexOf(o2);
        }

        if (ORDERED_ENTRIES.contains(o1)) {
            return -1;
        }

        if (ORDERED_ENTRIES.contains(o2)) {
            return 1;
        }

        return o1.toString().compareTo(o2.toString());
    }
}
