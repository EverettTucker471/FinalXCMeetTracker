package com.example.finalxcmeettracker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SplashController {
    private Stage stage;

    private class LoadMeetHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
        }
    }

    private class StartMeetHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            stage.setScene(Main.meetScene);
            stage.show();
        }
    }

    public void init(Stage stage) {
        // gets main stage
        this.stage = stage;

        // handles background image
        final ImageView imageView = new ImageView(
                new Image("C:\\Users\\legoe\\IdeaProjects\\FinalXCMeetTracker\\src\\main\\resources\\com\\example\\finalxcmeettracker\\meetAppBackground.jpg")
        );
        imageView.setFitHeight(480);
        imageView.setFitWidth(640);

        // label
        Label label = new Label("Cross Country Meet Tracker");
        label.setStyle("-fx-font: 40 \"montserrat\"; -fx-padding: 30; -fx-text-fill: rgba(255, 255, 255, 1)");

        // handles buttons
        String buttonStyle = "-fx-font: 20 \"montserrat\"; -fx-background-color: rgba(255, 255, 255, 1)";

        Button startMeetButton = new Button("Start Meet");
        Button loadMeetButton = new Button("Load Meet");
        Button helpButton = new Button("Help");

        startMeetButton.setOnAction(new StartMeetHandler());
        loadMeetButton.setOnAction(new LoadMeetHandler());

        startMeetButton.setStyle(buttonStyle);
        loadMeetButton.setStyle(buttonStyle);
        helpButton.setStyle(buttonStyle);

        // node organization
        StackPane glass = new StackPane();
        StackPane buttonHolder = new StackPane();
        buttonHolder.setMaxHeight(200);
        buttonHolder.setStyle("-fx-padding: 10");
        buttonHolder.getChildren().addAll(startMeetButton, loadMeetButton, helpButton);
        StackPane.setAlignment(startMeetButton, Pos.TOP_CENTER);
        StackPane.setAlignment(loadMeetButton, Pos.CENTER);
        StackPane.setAlignment(helpButton, Pos.BOTTOM_CENTER);
        glass.getChildren().addAll(label, buttonHolder);
        StackPane.setAlignment(label, Pos.TOP_CENTER);
        StackPane.setAlignment(buttonHolder, Pos.BOTTOM_CENTER);

        // background
        glass.setStyle("-fx-background-color: rgba(48, 40, 38, 0.5); -fx-background-radius: 10;");
        glass.setMaxWidth(imageView.getFitWidth() - 40);
        glass.setMaxHeight(imageView.getFitHeight() - 40);

        final StackPane layout = new StackPane();
        layout.getChildren().addAll(imageView, glass);
        layout.setStyle("-fx-background-color: rgba(52, 96, 148, 0.5); -fx-padding: 10;");

        // adds everything to scene
        Main.splashScene = new Scene(layout, 640, 480);
    }
}