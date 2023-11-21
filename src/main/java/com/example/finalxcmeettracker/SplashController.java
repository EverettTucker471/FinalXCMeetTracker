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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("meet-screen.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, screenWidth, screenHeight));
        stage.setMaximized(true);
        stage.setTitle("Meet Screen");
        ((Stage) welcomeText.getScene().getWindow()).close(); // this could be any attribute, but I am using welcomeText
        stage.show();
    }
}