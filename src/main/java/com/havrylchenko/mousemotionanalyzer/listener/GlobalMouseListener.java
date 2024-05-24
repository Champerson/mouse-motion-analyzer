package com.havrylchenko.mousemotionanalyzer.listener;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;
import com.havrylchenko.mousemotionanalyzer.model.MotionStorage;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.*;

import static com.havrylchenko.mousemotionanalyzer.util.MotionAnalyzerUtil.*;

@Component
public class GlobalMouseListener implements NativeMouseInputListener {


    private volatile int x;
    private volatile int y;
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

    public MotionStorage startMouseMotionCapture(int time) {
        resetCoordinates();
        cleanUp();

        try {
            if (GlobalScreen.isNativeHookRegistered()) {
                GlobalScreen.unregisterNativeHook();
            }
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeMouseListener(this);
        GlobalScreen.addNativeMouseMotionListener(this);

        Future<?> future = scheduleCaptureTime(time);

        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        var derivativeX = calculateDerivativeList(coordinatesOfX);
        var derivativeY = calculateDerivativeList(coordinatesOfY);

        cleanUp();
        resetCoordinates();

        var linguisticListX = convertDerivativesListToLingusticListByX(derivativeX);
        var linguisticListY = convertDerivativesListToLingusticListByY(derivativeY);

        MotionStorage motionStorage = new MotionStorage();
        motionStorage.setCharacterListByX(linguisticListX);
        motionStorage.setCharacterListByY(linguisticListY);

        return motionStorage;
    }

    private Future<?> scheduleCaptureTime(int shutdownTime) {
        long initialDelay = 0;
        long period = 10;

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable captureMouseMotionsTask = () -> {
            synchronized (this) {
                coordinatesOfX.add(x);
                coordinatesOfY.add(y);
            }

            System.out.println("coordinates X: " + x + " Y: " + y);
        };

        scheduler.scheduleAtFixedRate(captureMouseMotionsTask, initialDelay, period, TimeUnit.MILLISECONDS);

        return scheduler.schedule(() -> {
            scheduler.shutdown();
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException e) {
                e.printStackTrace();
            } finally {
                GlobalScreen.removeNativeMouseListener(this);
                GlobalScreen.removeNativeMouseMotionListener(this);
            }
        }, shutdownTime, TimeUnit.MINUTES);
    }

    private void resetCoordinates() {
        x = 0;
        y = 0;
    }

    private void cleanUp() {
        coordinatesOfX.clear();
        coordinatesOfY.clear();
    }

    public List<Integer> getCoordinatesOfX() {
        return coordinatesOfX;
    }

    public List<Integer> getCoordinatesOfY() {
        return coordinatesOfY;
    }
}