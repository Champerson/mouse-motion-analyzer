package com.havrylchenko.mousemotionanalyzer.controller;

import com.havrylchenko.mousemotionanalyzer.model.MotionStorage;
import com.havrylchenko.mousemotionanalyzer.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.havrylchenko.mousemotionanalyzer.util.MotionAnalyzerUtil.compareMatrices;
import static com.havrylchenko.mousemotionanalyzer.util.MotionAnalyzerUtil.createCharacterMatrix;

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
    private Button selectMatrixForComprassionButton;
    @FXML
    private Pagination matricesPagination;
    @FXML
    private ProgressBar diagnosticSessionProgressionBar;
    @FXML
    private Label compressionResultOfTwoMatricesLabel;
    @FXML
    private Label welcomeLabel;
    private String username;

    private int[][] selectedMatrix1;
    private int[][] selectedMatrix2;
    private boolean isFirstMatrixSelected = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        compressionResultOfTwoMatricesLabel.setVisible(false);
        logoutButton.setOnAction(event -> switchToMainScene());
        startDiagnosticButton.setOnAction(event -> startNewDiagnostic());
        selectMatrixForComprassionButton.setOnAction(event -> selectMatrixForComparison());
    }

    private void startNewDiagnostic() {
        userService.startMotionCapturing(username, 1);
        populateMatricesPagination();
    }

    private void populateMatricesPagination() {
        List<MotionStorage> matrices = userService.findAllMotionsStoragesByUsername(username);

        if (matrices == null || matrices.isEmpty()) {
            return;
        }

        matricesPagination.setPageCount(matrices.size());

        matricesPagination.setPageFactory(pageIndex -> {
            MotionStorage motionStorage = matrices.get(pageIndex);

            List<Character> characterListByX = motionStorage.getCharacterListByX();
            List<Character> characterListByY = motionStorage.getCharacterListByY();

            int[][] matrix = createCharacterMatrix(characterListByX, characterListByY);

            return createMatrixPage(matrix);
        });
    }

    private GridPane createMatrixPage(int[][] matrix) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                Label label = new Label(String.valueOf(matrix[i][j]));
                label.setMinWidth(30);
                label.setPrefWidth(30);
                label.setMaxWidth(30);
                label.setAlignment(Pos.CENTER);
                gridPane.add(label, j, i);
            }
        }

        return gridPane;
    }

    private void selectMatrixForComparison() {
        int pageIndex = matricesPagination.getCurrentPageIndex();
        MotionStorage motionStorage = userService.findAllMotionsStoragesByUsername(username).get(pageIndex);

        List<Character> characterListByX = motionStorage.getCharacterListByX();
        List<Character> characterListByY = motionStorage.getCharacterListByY();

        int[][] matrix = createCharacterMatrix(characterListByX, characterListByY);

        if (!isFirstMatrixSelected) {
            selectedMatrix1 = matrix;
            isFirstMatrixSelected = true;
        } else {
            selectedMatrix2 = matrix;
            double result = compareMatrices(selectedMatrix1, selectedMatrix2);
            String roundedResult = String.format("%.2f", result);
            compressionResultOfTwoMatricesLabel.setText("Compression result: " + roundedResult);
            compressionResultOfTwoMatricesLabel.setVisible(true);
            isFirstMatrixSelected = false;
            selectedMatrix1 = null;
            selectedMatrix2 = null;
        }
    }

    private void switchToMainScene() {
        compressionResultOfTwoMatricesLabel.setVisible(false);
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        Scene scene = new Scene(fxWeaver.loadView(MainSceneController.class));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void setUserInformation(String username) {
        setUsername(username);
        welcomeLabel.setText("Welcome " + username + "!");
        populateMatricesPagination();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
