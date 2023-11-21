package com.example.finalxcmeettracker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class SplashController {
    @FXML
    private Label welcomeText; // Use some attribute to get the current screen for closing
    @FXML
    private Button startMeetButton;
    @FXML
    protected void onStartMeetButtonClick() throws IOException {
        (new MeetScreen()).start(new Stage());
        ((Stage) welcomeText.getScene().getWindow()).close(); // this could be any attribute, but I am using welcomeText
    }
}