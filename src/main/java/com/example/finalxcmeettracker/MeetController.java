package com.example.finalxcmeettracker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MeetController {
    @FXML
    private Label currentTimeLabel;
    @FXML
    private Button goodbyeButton;
    @FXML
    private Button beginMeetButton;
    @FXML
    protected void onGoodbyeButtonClick() {
        Stage stage = (Stage) currentTimeLabel.getScene().getWindow(); // getting the current stage, should be the same always
        stage.setScene(Main.splash_scene);
        stage.show();
    }
    @FXML
    protected void onBeginMeetButtonClick() {}
    public void setCurrentTimeLabel(String s) {
        currentTimeLabel.setText(s);
    }
}
