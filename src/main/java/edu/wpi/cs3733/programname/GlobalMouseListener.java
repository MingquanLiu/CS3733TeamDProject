package edu.wpi.cs3733.programname;

import edu.wpi.cs3733.programname.commondata.AppSettings;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

public class GlobalMouseListener implements NativeMouseInputListener {

    public void nativeMouseClicked(NativeMouseEvent e) {
        AppSettings.getInstance().kickTimer();
    }

    public void nativeMousePressed(NativeMouseEvent e) {
        AppSettings.getInstance().kickTimer();
    }

    public void nativeMouseReleased(NativeMouseEvent e) {
        AppSettings.getInstance().kickTimer();
    }

    public void nativeMouseMoved(NativeMouseEvent e) {
        AppSettings.getInstance().kickTimer();
    }

    public void nativeMouseDragged(NativeMouseEvent e) {
        AppSettings.getInstance().kickTimer();
    }

}
