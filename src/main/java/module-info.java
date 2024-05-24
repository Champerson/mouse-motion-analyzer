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
    requires org.apache.commons.lang3;
    requires spring.boot.starter.data.jpa;
    requires spring.data.jpa;
    requires spring.orm;
    requires com.zaxxer.hikari;
    requires java.persistence;
    requires org.jboss.logging;
    requires java.instrument;
    requires jakarta.transaction;
    requires org.hibernate.commons.annotations;
    requires com.fasterxml.classmate;
    requires jakarta.xml.bind;
    requires net.bytebuddy;
    requires jakarta.cdi;
    requires org.apache.commons.codec;

    opens com.havrylchenko.mousemotionanalyzer to
            javafx.fxml,
            spring.core,
            org.hibernate.orm.core,
            net.rgielen.fxweaver.core,
            org.apache.commons.lang3;

    opens com.havrylchenko.mousemotionanalyzer.service to
            javafx.fxml,
            spring.core,
            org.hibernate.orm.core,
            net.rgielen.fxweaver.core,
            org.apache.commons.lang3;

    opens com.havrylchenko.mousemotionanalyzer.controller to
            javafx.fxml,
            spring.core,
            org.hibernate.orm.core,
            net.rgielen.fxweaver.core,
            org.apache.commons.lang3;

    opens com.havrylchenko.mousemotionanalyzer.listener to
            javafx.fxml,
            spring.core,
            org.hibernate.orm.core,
            net.rgielen.fxweaver.core,
            org.apache.commons.lang3;

    opens com.havrylchenko.mousemotionanalyzer.util to
            javafx.fxml,
            spring.core,
            org.hibernate.orm.core,
            net.rgielen.fxweaver.core,
            org.apache.commons.lang3;

    opens com.havrylchenko.mousemotionanalyzer.config to
            javafx.fxml,
            spring.core,
            org.hibernate.orm.core,
            net.rgielen.fxweaver.core,
            org.apache.commons.lang3;

    opens com.havrylchenko.mousemotionanalyzer.model to
            javafx.fxml,
            spring.core,
            org.hibernate.orm.core,
            net.rgielen.fxweaver.core,
            org.apache.commons.lang3;

    exports com.havrylchenko.mousemotionanalyzer;
    exports com.havrylchenko.mousemotionanalyzer.util;
    exports com.havrylchenko.mousemotionanalyzer.model;
    exports com.havrylchenko.mousemotionanalyzer.event;
    exports com.havrylchenko.mousemotionanalyzer.config;
    exports com.havrylchenko.mousemotionanalyzer.service;
    exports com.havrylchenko.mousemotionanalyzer.listener;
    exports com.havrylchenko.mousemotionanalyzer.controller;
    exports com.havrylchenko.mousemotionanalyzer.repository;
}