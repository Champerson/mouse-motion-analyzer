module com.havrylchenko.mousemotionanalyzer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.github.kwhat.jnativehook;
    requires spring.boot.autoconfigure;
    requires spring.core;
    requires spring.boot;
    requires java.sql;
    requires jakarta.persistence;
    requires spring.context;
    requires net.rgielen.fxweaver.core;
    requires net.rgielen.fxweaver.spring;
    requires spring.beans;
    requires org.slf4j;

    opens com.havrylchenko.mousemotionanalyzer to javafx.fxml, spring.core, net.rgielen.fxweaver.core;
    exports com.havrylchenko.mousemotionanalyzer;
    exports com.havrylchenko.mousemotionanalyzer.controller;
    opens com.havrylchenko.mousemotionanalyzer.controller to javafx.fxml, spring.core, net.rgielen.fxweaver.core;
    exports com.havrylchenko.mousemotionanalyzer.listener;
    opens com.havrylchenko.mousemotionanalyzer.listener to javafx.fxml, spring.core, net.rgielen.fxweaver.core;
}