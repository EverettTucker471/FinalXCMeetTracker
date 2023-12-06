package com.example.finalxcmeettracker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controls the information scene
 */
public class InformationController {
    // Used to store all the information about the meet
    public static final Meet meet = new Meet();
    private Stage stage;
    private TextField enterTeamField, enterAthleteField;
    private ChoiceBox<String> selectTeamField;
    private VBox currentData;

    /**
     * Initializes the information scene
     * @param stage the stage used after launch
     */
    public void init(Stage stage) {
        this.stage = stage;

        // make layout
        StackPane layout = new StackPane();
        layout.setStyle(Main.layoutStyle);
        StackPane leftGlass = new StackPane();
        leftGlass.setStyle(Main.glassStyle);
        StackPane rightGlass = new StackPane();
        rightGlass.setStyle(Main.glassStyle);
        HBox glassHolder = new HBox(10);
        glassHolder.getChildren().addAll(leftGlass, rightGlass);
        layout.getChildren().add(glassHolder);

        // add components to left glass
        int leftGlassWidth = 400;
        Label enterTeamNameLabel = new Label("Enter Team Here: ");
        enterTeamNameLabel.setStyle("-fx-font: 20 \"montserrat\"; -fx-text-fill: rgba(255, 255, 255, 1);");

        enterTeamField = new TextField("Team name...");
        enterTeamField.setMaxWidth(leftGlassWidth);
        enterTeamField.setStyle(Main.textFieldLargeStyle);

        Button enterTeamButton = new Button("Enter Team");
        enterTeamButton.setStyle(Main.buttonStyle);
        enterTeamButton.setOnAction(new EnterTeamHandler());

        Label enterAthleteLabel = new Label("Enter Athlete Here: ");
        enterAthleteLabel.setStyle(Main.largeTextLabelStyle);

        selectTeamField = new ChoiceBox<>();
        selectTeamField.setStyle(Main.buttonStyle);

        enterAthleteField = new TextField("Athlete name...");
        enterAthleteField.setMaxWidth(leftGlassWidth);
        enterAthleteField.setStyle(Main.textFieldLargeStyle);

        Button enterAthleteButton = new Button("Enter Athlete");
        enterAthleteButton.setStyle(Main.buttonStyle);
        enterAthleteButton.setOnAction(new EnterAthleteHandler());

        VBox leftGlassHolder = new VBox(20);
        leftGlassHolder.getChildren().addAll(enterTeamNameLabel, enterTeamField, enterTeamButton, enterAthleteLabel, selectTeamField, enterAthleteField, enterAthleteButton);
        leftGlass.getChildren().add(leftGlassHolder);

        // adds components to right glass
        Label currentDataLabel = new Label("Current Meet Data: Bib # | Name | Team");
        currentDataLabel.setStyle("-fx-font: 20 \"montserrat\"; -fx-text-fill: rgba(255, 255, 255, 1);");

        VBox rightGlassHolder = new VBox(8);
        currentData = new VBox(8);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-padding: 5;");
        scrollPane.setMaxWidth(leftGlassWidth + 40);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setPrefHeight(Main.screenHeight - 100);
        scrollPane.setPrefWidth(Main.screenWidth);
        scrollPane.setContent(currentData);

        Button returnButton = new Button("Ready for Meet");
        returnButton.setStyle(Main.buttonStyle);
        returnButton.setOnAction(new ReturnHandler());

        rightGlassHolder.getChildren().addAll(currentDataLabel, scrollPane, returnButton);
        rightGlass.getChildren().add(rightGlassHolder);

        Main.informationScene = new Scene(layout, layout.getMaxWidth(), layout.getMaxHeight());
    }

    /**
     * Resets the meet scene
     */
    public void reset() {
        stage.setScene(Main.informationScene);
        stage.setWidth(Main.screenWidth - 800);
        stage.setHeight(Main.screenHeight);
        currentData.getChildren().clear();
        selectTeamField.getItems().clear();
        enterTeamField.setText("Team name...");
        enterAthleteField.setText("Athlete name...");
        stage.show();
    }

    private class EnterTeamHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            String teamName = enterTeamField.getText();
            enterTeamField.clear();
            Team team = new Team(teamName);
            meet.addTeam(team);
            selectTeamField.getItems().add(teamName);
        }
    }

    private class EnterAthleteHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            String athleteName = enterAthleteField.getText();
            Team team = meet.getTeam(selectTeamField.getSelectionModel().getSelectedIndex());
            enterAthleteField.clear();

            // Adding the team to the athlete
            Athlete athlete = new Athlete(athleteName, team);

            // Adding the athlete to the team
            team.addAthlete(athlete);

            athlete.setBibNumber(meet.getNumAthletes());

            // Adding this information to a label so that the organizer can check their information
            Label label = new Label(athlete.getBibNumber() + " | " + athlete.getName() + " | " + team.getName());
            label.setStyle("-fx-font: 10 \"montserrat\"; -fx-text-fill: rgba(0, 0, 0, 1);");
            currentData.getChildren().add(label);
        }
    }

    private class ReturnHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            meet.generateBibNumbers();
            Main.meetController.reset();
        }
    }

}
