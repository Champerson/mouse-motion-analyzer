package com.havrylchenko.mousemotionanalyzer;

import javafx.application.Application;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.spring.SpringFxWeaver;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.havrylchenko.mousemotionanalyzer")
public class MouseMotionAnalyzerApp {

    public static void main(String[] args) {
        Application.launch(MouseMotionAnalyzerFXApp.class, args);
    }

    @Bean
    public FxWeaver fxWeaver(ConfigurableApplicationContext applicationContext) {
        return new SpringFxWeaver(applicationContext);
    }
}
