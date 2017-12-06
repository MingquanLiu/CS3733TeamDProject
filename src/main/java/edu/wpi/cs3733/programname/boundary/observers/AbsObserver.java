package edu.wpi.cs3733.programname.boundary.observers;

import edu.wpi.cs3733.programname.database.DbObservable;

public abstract class AbsObserver {
    private DbObservable observable;

    public void update() {

    }

    public DbObservable getObservable() {
        return this.observable;
    }
}
