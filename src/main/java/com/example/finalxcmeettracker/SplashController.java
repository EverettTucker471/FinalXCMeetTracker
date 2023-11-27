package com.example.finalxcmeettracker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SplashController {
    @FXML
    private Label welcomeText; // Use some attribute to get the current screen for closing
    @FXML
    private Button startMeetButton;
    @FXML
    private Button loadMeetButton;
    @FXML
    protected void onStartMeetButtonClick() {
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        stage.setScene(Main.meet_scene);
        stage.show();
    }
    @FXML
    protected void onLoadMeetButtonClick() {}
}