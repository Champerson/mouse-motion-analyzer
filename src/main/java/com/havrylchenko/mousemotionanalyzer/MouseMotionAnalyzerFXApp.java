package com.havrylchenko.mousemotionanalyzer;

import com.havrylchenko.mousemotionanalyzer.event.StageReadyEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;


@SpringBootApplication(scanBasePackages = "com.havrylchenko.mousemotionanalyzer")
public class MouseMotionAnalyzerFXApp extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        ApplicationContextInitializer<GenericApplicationContext> initializer =
                appContext -> {
                    appContext.registerBean(Application.class, () -> MouseMotionAnalyzerFXApp.this);
                    appContext.registerBean(Parameters.class, this::getParameters);
                };
        this.context = new SpringApplicationBuilder()
                .sources(MouseMotionAnalyzerApp.class)
                .initializers(initializer)
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage primaryStage) {
        context.publishEvent(new StageReadyEvent(primaryStage));
    }

    @Override
    public void stop() {
        this.context.close();
        Platform.exit();
    }
}