package edu.wpi.cs3733.programname.boundary.observers;

import edu.wpi.cs3733.programname.boundary.TestingController;

public class RequestObserver extends AbsObserver {

    TestingController testingController;

    public RequestObserver(TestingController testingController) {
        this.testingController = testingController;
    }

    @Override
    public void update() {

    }
}
