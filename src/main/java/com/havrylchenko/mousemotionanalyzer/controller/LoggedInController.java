package com.havrylchenko.mousemotionanalyzer.controller;

import com.havrylchenko.mousemotionanalyzer.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("loggedInScene.fxml")
public class LoggedInController implements Initializable {

    @Autowired
    private FxWeaver fxWeaver;
    @Autowired
    private UserService userService;

    @FXML
    private Button startDiagnosticButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button selectSessionButton;
    @FXML
    private Pagination matricesPagination;
    @FXML
    private Label compressionResultOfTwoMatricesLabel;
    @FXML
    private Label welcomeLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logoutButton.setOnAction(event -> switchToMainScene());
        startDiagnosticButton.setOnAction(event -> startNewDiagnostic());
    }

    private void startNewDiagnostic() {
        userService.startMotionCapturing();
    }

    private void switchToMainScene() {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        Scene scene = new Scene(fxWeaver.loadView(MainSceneController.class));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void setUserInformation(String username) {
        welcomeLabel.setText("Welcome " + username + "!");
    }


}
