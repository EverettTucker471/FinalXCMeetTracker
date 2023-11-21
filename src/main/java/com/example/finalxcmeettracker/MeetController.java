package com.example.finalxcmeettracker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MeetController {
    @FXML
    public Label welcomeText;

    @FXML
    public static Button goodbyeButton;

    @FXML
    protected void onGoodbyeButtonClick() {
        welcomeText.setText("Goodbye from Everett.");
    }

}
