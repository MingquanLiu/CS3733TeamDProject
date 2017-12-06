package edu.wpi.cs3733.programname.boundary.observers;

import edu.wpi.cs3733.programname.boundary.TestingController;
import edu.wpi.cs3733.programname.database.DbObservable;

public class MapObserver extends AbsObserver {

    TestingController testingController;

    public MapObserver(TestingController testingController) {
        this.testingController = testingController;
    }

    @Override
    public void update() {

    }
}
