package com.example.finalxcmeettracker;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class InformationController {
    public static final Meet meet = new Meet();

    @FXML
    private Label enterTeamLabel;
    @FXML
    private TextField enterTeamField;
    @FXML
    private Button enterTeamButton;
    @FXML
    private Label enterAthleteLabel;
    @FXML
    private ChoiceBox<String> selectTeamField;
    @FXML
    private TextField enterAthleteField;
    @FXML
    private Button enterAthleteButton;
    @FXML
    private Label currentDataLabel;
    @FXML
    private Button returnButton;
    @FXML
    protected void onEnterTeamButtonClick() {
        String teamName = enterTeamField.getText();
        enterTeamField.clear();
        Team team = new Team(teamName);
        meet.addTeam(team);
        selectTeamField.getItems().add(teamName);
    }
    @FXML
    protected void onEnterAthleteButtonClick() {
        String athleteName = enterAthleteField.getText();
        Team team = meet.getTeam(selectTeamField.getSelectionModel().getSelectedIndex());
        enterAthleteField.clear();

        // Adding the team to the athlete
        Athlete athlete = new Athlete(athleteName, team);

        // Adding the athlete to the team
        team.addAthlete(athlete);

        // Adding this information to a label so that the organizer can check their information
        currentDataLabel.setText(currentDataLabel.getText() + "\n" + athleteName + " - " + team.getName());
    }

    @FXML
    protected void onReturnButtonClick() {
        meet.generateBibNumbers();
        Stage stage = (Stage) enterTeamLabel.getScene().getWindow();
        stage.setScene(Main.meetScene);
        stage.show();
    }

}
