package com.example.finalxcmeettracker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ResultsController {
    @FXML
    private Label athleteLabel, teamLabel;
    @FXML
    private Button returnButton, tabulateResultsButton;

    @FXML
    protected void onTabulateResultsButtonClick() {
        Heap<Athlete> heap = MeetController.athleteHeap;
        athleteLabel.setText(athleteLabel.getText() + "\n" + "Place | Time | Name | Team");
        int i = 1;
        while (!heap.isEmpty()) {
            Athlete athlete = heap.pop();
            athlete.setPlacement(i);
            athleteLabel.setText(athleteLabel.getText() + "\n" + i + " | " + formatTime(athlete.getTime()) + " | " + athlete.getName() + " | " + athlete.getTeam().getName());
            i++;
        }
        InformationController.meet.calculateTeamScores();
        // Sort the team scores
        teamLabel.setText(teamLabel.getText() + "\n" + "Score | Avg Time | Team");
        Heap<Team> teamHeap = InformationController.meet.getTeamHeap();
        i = 1;
        while (!teamHeap.isEmpty()) {
            Team team = teamHeap.pop();
            team.setPlacement(i);
            teamLabel.setText(teamLabel.getText() + "\n" + i + " | " + formatTime(team.getAverageTime()) + " | " + team.getName());
            i++;
        }
    }
    @FXML
    protected void onReturnButtonClick() {
        Stage stage = (Stage) athleteLabel.getScene().getWindow();
        stage.setScene(Main.meetScene);
        stage.show();
    }

    private String formatTime(long time) {
        int hours = (int) (time / 3600000);
        time %= 3600000;
        int minutes = (int) (time / 60000);
        time %= 60000;
        int seconds = (int) (time / 1000);
        time %= 1000;
        return String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds) + "." + time;
    }
}
