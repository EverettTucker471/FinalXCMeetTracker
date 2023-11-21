package com.example.finalxcmeettracker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MeetController {
    @FXML
    public Label welcomeText;
    @FXML
    public Button goodbyeButton;
    @FXML
    protected void onGoodbyeButtonClick() throws IOException {
        (new SplashScreen()).start(new Stage()); // Starting the new screen
        ((Stage) goodbyeButton.getScene().getWindow()).close(); // Closing the old screen
    }
}
