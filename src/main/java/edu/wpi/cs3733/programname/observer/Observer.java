package edu.wpi.cs3733.programname.observer;

import java.io.IOException;

abstract class Observer {
    public abstract void update() throws IOException;
}
