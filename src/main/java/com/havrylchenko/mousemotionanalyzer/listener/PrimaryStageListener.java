package com.havrylchenko.mousemotionanalyzer.listener;

import com.havrylchenko.mousemotionanalyzer.controller.MouseMotionController;
import com.havrylchenko.mousemotionanalyzer.event.StageReadyEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class PrimaryStageListener implements ApplicationListener<StageReadyEvent> {

    private final FxWeaver fxWeaver;

    @Autowired
    public PrimaryStageListener(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.stage;
        Scene scene = new Scene(fxWeaver.loadView(MouseMotionController.class), 1200, 840);
        stage.setTitle("Welcome to Mouse Motion Analyzer");
        stage.setScene(scene);
        stage.show();
    }
}