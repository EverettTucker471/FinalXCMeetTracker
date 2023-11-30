package com.example.finalxcmeettracker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

    public void reset() {
        stage.setWidth(640);
        stage.setHeight(480);
        stage.setScene(Main.splashScene);
        stage.show();
    }

    private class LoadMeetHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
        }
    }

    private class StartMeetHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            Main.informationController.reset();
        }
    }

    public void init(Stage stage) {
        // gets main stage
        this.stage = stage;

        // handles background image
        final ImageView imageView = new ImageView(
                new Image("C:\\Users\\vince\\IdeaProjects\\FinalXCMeetTracker\\src\\main\\resources\\com\\example\\finalxcmeettracker\\meetAppBackground.jpg")
        );
        imageView.setFitHeight(480);
        imageView.setFitWidth(640);

        // label
        Label label = new Label("Cross Country Meet Tracker");
        label.setStyle(Main.titleLabelStyle);

        // handles buttons
        Button startMeetButton = new Button("Start Meet");
        Button loadMeetButton = new Button("Load Meet");
        Button helpButton = new Button("Help");

        startMeetButton.setOnAction(new StartMeetHandler());
        loadMeetButton.setOnAction(new LoadMeetHandler());

        startMeetButton.setStyle(Main.buttonStyle);
        loadMeetButton.setStyle(Main.buttonStyle);
        helpButton.setStyle(Main.buttonStyle);

        // node organization
        StackPane glass = new StackPane();
        StackPane buttonHolder = new StackPane();
        buttonHolder.setMaxHeight(200);
        buttonHolder.setStyle("-fx-padding: 10;");
        buttonHolder.getChildren().addAll(startMeetButton, loadMeetButton, helpButton);
        StackPane.setAlignment(startMeetButton, Pos.TOP_CENTER);
        StackPane.setAlignment(loadMeetButton, Pos.CENTER);
        StackPane.setAlignment(helpButton, Pos.BOTTOM_CENTER);
        glass.getChildren().addAll(label, buttonHolder);
        StackPane.setAlignment(label, Pos.TOP_CENTER);
        StackPane.setAlignment(buttonHolder, Pos.BOTTOM_CENTER);

        // background
        glass.setStyle(Main.glassStyle);
        glass.setMaxWidth(imageView.getFitWidth() - 40);
        glass.setMaxHeight(imageView.getFitHeight() - 40);

        final StackPane layout = new StackPane();
        layout.getChildren().addAll(imageView, glass);
        layout.setStyle(Main.layoutStyle);

        // adds everything to scene
        Main.splashScene = new Scene(layout, 640, 480);
    }
}