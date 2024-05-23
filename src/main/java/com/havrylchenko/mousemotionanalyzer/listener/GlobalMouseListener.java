package com.havrylchenko.mousemotionanalyzer.listener;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;
import com.havrylchenko.mousemotionanalyzer.model.MotionStorage;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.havrylchenko.mousemotionanalyzer.util.MotionAnalyzerUtil.calculateDerivativeList;
import static com.havrylchenko.mousemotionanalyzer.util.MotionAnalyzerUtil.convertDerivativesListToLingusticList;

@Component
public class GlobalMouseListener implements NativeMouseInputListener {


    private int x;
    private int y;
    private List<Integer> coordinatesOfX = new ArrayList<>();
    private List<Integer> coordinatesOfY = new ArrayList<>();

    public void nativeMouseMoved(NativeMouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    public void nativeMouseDragged(NativeMouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    public MotionStorage startMouseMotionCapture() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalMouseListener globalMouseListener = new GlobalMouseListener();

        GlobalScreen.addNativeMouseListener(globalMouseListener);
        GlobalScreen.addNativeMouseMotionListener(globalMouseListener);

        scheduleCaptureTime(1);

        var derivativeX = calculateDerivativeList(coordinatesOfX);
        var derivativeY = calculateDerivativeList(coordinatesOfY);

        cleanUp();

        var linguisticListX = convertDerivativesListToLingusticList(derivativeX);
        var linguisticListY = convertDerivativesListToLingusticList(derivativeY);

        return new MotionStorage(linguisticListX, linguisticListY);
    }

    private void scheduleCaptureTime(int shutdownTime) {
        long initialDelay = 0;
        long period = 10;

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable captureMouseMotionsTask = () -> {
            coordinatesOfX.add(x);
            coordinatesOfY.add(y);
        };

        scheduler.scheduleAtFixedRate(captureMouseMotionsTask, initialDelay, period, TimeUnit.MILLISECONDS);

        scheduler.schedule(() -> {
            scheduler.shutdown();
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException e) {
                e.printStackTrace();
            }
        }, shutdownTime, TimeUnit.MINUTES);
    }

    private void cleanUp() {
        coordinatesOfX.clear();
        coordinatesOfY.clear();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<Integer> getCoordinatesOfX() {
        return coordinatesOfX;
    }

    public void setCoordinatesOfX(List<Integer> coordinatesOfX) {
        this.coordinatesOfX = coordinatesOfX;
    }

    public List<Integer> getCoordinatesOfY() {
        return coordinatesOfY;
    }

    public void setCoordinatesOfY(List<Integer> coordinatesOfY) {
        this.coordinatesOfY = coordinatesOfY;
    }
}