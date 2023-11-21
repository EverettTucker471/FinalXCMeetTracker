package com.example.finalxcmeettracker;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

// These should be imported into every new controller
import static com.example.finalxcmeettracker.SplashScreen.screenHeight;
import static com.example.finalxcmeettracker.SplashScreen.screenWidth;

public class SplashController {
    @FXML
    private Label welcomeText; // Use some attribute to get the current screen for closing
    @FXML
    private Button startMeetButton;
    @FXML
    protected void onStartMeetButtonClick() throws IOException {
        MeetScreen m = new MeetScreen();
        m.start((Stage) welcomeText.getScene().getWindow());
    }
}