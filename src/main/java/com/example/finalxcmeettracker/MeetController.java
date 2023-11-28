package com.example.finalxcmeettracker;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;

public class MeetController {
    @FXML
    private Label currentTimeLabel;
    @FXML
    private Button returnButton;
    @FXML
    private Button stopMeetButton;
    @FXML
    private Button beginMeetButton;
    private long timestamp;
    public final Timer meetTimer = new Timer();
    @FXML
    protected void onReturnButtonClick() {
        Stage stage = (Stage) currentTimeLabel.getScene().getWindow(); // getting the current stage, should be the same always
        stage.setScene(Main.splash_scene);
        stage.show();
    }

    /**
     * This method cancels the Meet Timer forever, it cannot be undone.
     * We shouldn't theoretically need to stop the meet timer until the meet is finished.
     */
    @FXML
    protected void onStopMeetButtonClick() {
        meetTimer.cancel();
    }
    @FXML
    protected void onBeginMeetButtonClick() {
        timestamp = System.currentTimeMillis();
        try {
            meetTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> updateTimer());
                }
            }, 0, 10); // Starts immediately with a 10 ms period between updates
        } catch (IllegalStateException e) {
            System.out.println("Cannot start a meet that has been cancelled");
        }
    }

    /**
     * Updates the time display with System.currentTimeMillis()
     * Formats the time as HH:MM:SS.sss
     */
    public void updateTimer() {
        long delta_time = System.currentTimeMillis() - timestamp;
        int hours = (int) (delta_time / 3600000);
        delta_time %= 3600000;
        int minutes = (int) (delta_time / 60000);
        delta_time %= 60000;
        int seconds = (int) (delta_time / 1000);
        delta_time %= 1000;
        currentTimeLabel.setText(String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds) + "." + delta_time);
    }
}
