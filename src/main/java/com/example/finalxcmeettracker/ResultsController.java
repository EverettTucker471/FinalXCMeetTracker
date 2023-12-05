package com.example.finalxcmeettracker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ResultsController {
    private Button returnButton, tabulateResultsButton;
    public boolean initialized;
    public VBox athleteBox, teamBox;
    public Stage stage;

    public class TabulateResultsHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            Heap<Athlete> heap = MeetController.athleteHeap;
            athleteBox.getChildren().add(new Label("Place | Time | Name | Team"));
            int i = 1;
            while (!heap.isEmpty()) {
                Athlete athlete = heap.pop();
                athlete.setPlacement(i);
                if (athlete.getFinished()) {
                    athleteBox.getChildren().add(new Label(i + " | " + formatTime(athlete.getTime()) + " | " + athlete.getName() + " | " + athlete.getTeam().getName()));
                    i++;
                }
            }
            InformationController.meet.calculateTeamScores();
            // Sort the team scores
            teamBox.getChildren().add(new Label("Score | Avg Time | Team"));
            Heap<Team> teamHeap = InformationController.meet.getTeamHeap();
            i = 1;
            while (!teamHeap.isEmpty()) {
                Team team = teamHeap.pop();
                team.setPlacement(i);
                teamBox.getChildren().add(new Label(i + " | " + formatTime(team.getAverageTime()) + " | " + team.getName()));
                i++;
            }
        }
    }
    public class ReturnHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            Main.splashController.reset();
        }
    }
    public void reset() {
        athleteBox.getChildren().clear();
        teamBox.getChildren().clear();

        stage.setScene(Main.meetScene);
        stage.setWidth(640);
        stage.setHeight(480);
        stage.setMaximized(true);
        stage.show();
    }

    public void init(Stage stage) {
        initialized = true;
        this.stage = stage;

        // make layout
        StackPane layout = new StackPane();
        layout.setMaxSize(640, 480);
        layout.setStyle(Main.layoutStyle);

        // add buttons
        tabulateResultsButton = new Button("Tabulate Meet Results");
        returnButton = new Button("Return");
        tabulateResultsButton.setStyle(Main.buttonStyle);
        returnButton.setStyle(Main.buttonStyle);

        // set button handlers
        tabulateResultsButton.setOnAction(new TabulateResultsHandler());
        returnButton.setOnAction(new ReturnHandler());

        // create and organize boxes
        athleteBox = new VBox(8);
        athleteBox.setMaxWidth(layout.getMaxWidth() - 80);
        athleteBox.setMaxHeight(layout.getMaxHeight() - 80);
        teamBox = new VBox(8);
        athleteBox.setStyle("-fx-padding: 10;");
        teamBox.setStyle("-fx-padding: 10;");
        ScrollPane athletePane = new ScrollPane();
        ScrollPane teamPane = new ScrollPane();
        athletePane.setMaxSize(layout.getMaxWidth() / 3 - 20, layout.getMaxHeight() - 200);
        athletePane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        athletePane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        athletePane.setContent(athleteBox);
        teamPane.setMaxSize(layout.getMaxWidth() / 3 - 20, layout.getMaxHeight() - 200);
        teamPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        teamPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        teamPane.setContent(teamBox);
        VBox leftBox = new VBox(20);
        leftBox.setMaxHeight(layout.getMaxHeight() - 40);
        leftBox.getChildren().addAll(athletePane);
        VBox rightBox = new VBox(20);
        rightBox.getChildren().addAll(teamPane, tabulateResultsButton, returnButton);
        rightBox.setMaxHeight(layout.getMaxHeight() - 40);

        // putting it together
        StackPane glass = new StackPane();
        glass.setStyle(Main.glassStyle);
        glass.setMaxSize(layout.getMaxWidth() - 40, layout.getMaxHeight() - 40);
        glass.getChildren().addAll(leftBox, rightBox);
        leftBox.setAlignment(Pos.TOP_LEFT);
        rightBox.setAlignment(Pos.TOP_RIGHT);
        layout.getChildren().add(glass);

        Main.resultsScene = new Scene(layout, 640, 480);

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
