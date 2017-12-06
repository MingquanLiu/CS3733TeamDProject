package edu.wpi.cs3733.programname.database;

import edu.wpi.cs3733.programname.boundary.observers.AbsObserver;
import edu.wpi.cs3733.programname.boundary.observers.MapObserver;

import java.util.ArrayList;

public class DbObservable {

    ArrayList<AbsObserver> observers;

    public DbObservable(ArrayList<AbsObserver> newObservers) {
        observers = new ArrayList<>();
        for(AbsObserver observer: newObservers) {
            observers.add(observer);
        }
    }

    public void notifyObservers() {
        for(AbsObserver observer: observers) {
            observer.update();
        }
    }

}
