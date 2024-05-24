package com.havrylchenko.mousemotionanalyzer.controller;

import com.havrylchenko.mousemotionanalyzer.model.User;
import com.havrylchenko.mousemotionanalyzer.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

import static com.havrylchenko.mousemotionanalyzer.util.PasswordEncoder.encodePassword;

@Component
@FxmlView("mainScene.fxml")
public class MainSceneController implements Initializable {

    @Autowired
    private FxWeaver fxWeaver;
    @Autowired
    private UserService userService;
    @FXML
    private Button switchToSignupSceneButton;
    @FXML
    private Button loginButton;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label notRegisteredLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        switchToSignupSceneButton.setOnAction(event -> switchToSignupScene());
        loginButton.setOnAction(event -> loginUser());
    }

    private void loginUser() {
        var username = usernameTextField.getText();
        var password = encodePassword(passwordField.getText());

        User user = userService.getUserByUsername(username);

        if (user == null || !password.equals(user.getPassword())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Wrong credentials");
            alert.show();
        } else if (username.equals(user.getUsername()) && password.equals(user.getPassword()) ) {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene scene = new Scene(fxWeaver.loadView(LoggedInController.class));
            LoggedInController loggedInController = fxWeaver.getBean(LoggedInController.class);
            loggedInController.setUserInformation(username);
            stage.setScene(scene);
            stage.show();
        }
    }

    private void switchToSignupScene() {
        Stage stage = (Stage) switchToSignupSceneButton.getScene().getWindow();
        Scene scene = new Scene(fxWeaver.loadView(SignupController.class));
        stage.setScene(scene);
        stage.show();
    }
}