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
@FxmlView("registerUserScene.fxml")
public class SignupController implements Initializable {

    @Autowired
    private FxWeaver fxWeaver;
    @Autowired
    private UserService userService;
    @FXML
    private Button signupButton;
    @FXML
    private Button switchToLoginSceneButton;
    @FXML
    private Label alreadyRegisteredLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        switchToLoginSceneButton.setOnAction(event -> switchToLoginScene());
        signupButton.setOnAction(event -> registerUser());
    }

    private void registerUser() {
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        if (userService.getUserByUsername(username) != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("User already exists!");
            alert.show();
        } else if (username == null || password == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Username and password can't be empty");
            alert.show();
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(encodePassword(password));

            userService.createUser(user);

            switchToLoggedInScene(username);
        }

    }

    private void switchToLoggedInScene(String username) {
        Stage stage = (Stage) signupButton.getScene().getWindow();
        Scene scene = new Scene(fxWeaver.loadView(LoggedInController.class));
        LoggedInController loggedInController = fxWeaver.getBean(LoggedInController.class);
        loggedInController.setUserInformation(username);
        stage.setScene(scene);
        stage.show();
    }

    private void switchToLoginScene() {
        Stage stage = (Stage) switchToLoginSceneButton.getScene().getWindow();
        Scene scene = new Scene(fxWeaver.loadView(MainSceneController.class));
        stage.setScene(scene);
        stage.show();
    }
}
