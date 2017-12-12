package edu.wpi.cs3733.programname;

import edu.wpi.cs3733.programname.commondata.AppSettings;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class GlobalKeyListener implements NativeKeyListener {
    public void nativeKeyPressed(NativeKeyEvent e) {
        AppSettings.getInstance().kickTimer();
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        AppSettings.getInstance().kickTimer();
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        AppSettings.getInstance().kickTimer();
    }

}