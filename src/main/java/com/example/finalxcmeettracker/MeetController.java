package com.example.finalxcmeettracker;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

/**
 * Controls the meet scene
 */
public class MeetController {
    public static Heap<Athlete> athleteHeap;
    private Stack<BoxContainer> undoStack;
    private long timestamp;
    public Timer meetTimer;
    private Stage stage;
    public boolean started;
    public boolean initialized;
    private Button startStopMeetButton, addTimeButton, viewResultsButton, undoButton, homeButton;
    private VBox buttonPane, boxes;
    public Label currentTimeLabel;

    /**
     * Resets the meet scene
     */
    public void reset() {
        started = false;

        // display the relevant buttons
        buttonPane.getChildren().removeAll(startStopMeetButton, addTimeButton, undoButton, viewResultsButton);
        buttonPane.getChildren().add(0, startStopMeetButton);

        // clear old data
        boxes.getChildren().clear();
        currentTimeLabel.setText("Meet has not started");

        // works with stage
        stage.setScene(Main.meetScene);
        stage.setWidth(640);
        stage.setHeight(480);
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * Initializes the meet scene
     * @param stage the stage used after launch
     */
    public void init(Stage stage) {
        started = false;
        initialized = true;
        this.stage = stage;
        athleteHeap = new Heap<>(10);
        undoStack = new Stack<>();
        meetTimer = new Timer();

        // make layout
        StackPane layout = new StackPane();
        layout.setMaxSize(640, 480);
        layout.setStyle(Main.layoutStyle);

        // make label
        currentTimeLabel = new Label("Meet has not started");
        currentTimeLabel.setStyle(Main.subtitleLabelStyle);

        // create and organize boxes
        boxes = new VBox(8);
        boxes.setStyle("-fx-padding: 10;");
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxSize(layout.getMaxWidth() - 80, (layout.getMaxHeight() - 80) * 1 / 3);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(boxes);
        VBox boxAndLabelPane = new VBox(20);
        boxAndLabelPane.getChildren().addAll(scrollPane, currentTimeLabel);

        // create buttons
        startStopMeetButton = new Button("Start");
        addTimeButton = new Button("Add Time");
        viewResultsButton = new Button("View Results");
        undoButton = new Button("Undo Last Action");
        homeButton = new Button("Cancel Meet and Return Home");

        startStopMeetButton.setStyle(Main.buttonStyle);
        addTimeButton.setStyle(Main.buttonStyle);
        viewResultsButton.setStyle(Main.buttonStyle);
        undoButton.setStyle(Main.buttonStyle);
        homeButton.setStyle(Main.buttonStyle);

        startStopMeetButton.setOnAction(new StartStopMeetHandler());
        addTimeButton.setOnAction(new AddTimeHandler());
        viewResultsButton.setOnAction(new ViewResultsHandler());
        undoButton.setOnAction(new UndoHandler());
        homeButton.setOnAction(new ReturnHandler());

        // organize buttons
        buttonPane = new VBox(8);
        buttonPane.setMaxSize(layout.getMaxWidth() - 80, (layout.getHeight() - 80) * 1 / 3);
        buttonPane.getChildren().addAll(startStopMeetButton, homeButton);

        // organize nodes
        StackPane glass = new StackPane();
        glass.setStyle(Main.glassStyle);
        glass.setMaxSize(layout.getMaxWidth() - 40, layout.getMaxHeight() - 40);
        glass.getChildren().addAll(boxAndLabelPane, currentTimeLabel, buttonPane);
        StackPane.setAlignment(boxAndLabelPane, Pos.TOP_CENTER);
        StackPane.setAlignment(currentTimeLabel, Pos.CENTER);
        StackPane.setAlignment(buttonPane, Pos.BOTTOM_CENTER);
        layout.getChildren().addAll(glass);

        Main.meetScene = new Scene(layout, 640, 480);
    }

    private class ReturnHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            Main.splashController.reset();
        }
    }

    private class StartStopMeetHandler implements EventHandler<ActionEvent> {

        /**
         * This method cancels the Meet Timer forever, it cannot be undone.
         * We shouldn't theoretically need to stop the meet timer until the meet is finished.
         *
         * @param actionEvent stop meet event
         */
        @Override
        public void handle(ActionEvent actionEvent) {
            if (started) {
                meetTimer.cancel();
                currentTimeLabel.setText("Meet finished");

                // display the relevant buttons
                buttonPane.getChildren().removeAll(startStopMeetButton, addTimeButton, undoButton);
                buttonPane.getChildren().add(0, viewResultsButton);
            } else {
                timestamp = System.currentTimeMillis();
                started = true;

                // add the relevant buttons
                buttonPane.getChildren().addAll(1, Arrays.asList(addTimeButton, undoButton));
                startStopMeetButton.setText("Stop Meet");

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
        }
    }

    private class AddTimeHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            BoxContainer box = new BoxContainer(System.currentTimeMillis() - timestamp);
            undoStack.push(box);
            boxes.getChildren().add(box);
        }
    }

    private class ViewResultsHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            stage.setScene(Main.resultsScene);
            stage.setHeight(Main.screenHeight);
            stage.setWidth(Main.screenWidth);
            stage.setMaximized(true);
            stage.show();
        }
    }

    private class UndoHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            if (!undoStack.isEmpty()) {
                BoxContainer box = undoStack.pop();
                boxes.getChildren().remove(box);
            }
        }
    }

    /**
     * Updates the time label
     */
    public void updateTimer() {
        currentTimeLabel.setText(getFormattedTime());
    }

    /**
     * Formats the time
     *
     * @return time in the format HH:MM:SS.sss
     */
    public String getFormattedTime() {
        long delta_time = System.currentTimeMillis() - timestamp;
        int hours = (int) (delta_time / 3600000);
        delta_time %= 3600000;
        int minutes = (int) (delta_time / 60000);
        delta_time %= 60000;
        int seconds = (int) (delta_time / 1000);
        delta_time %= 1000;
        return String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds) + "." + delta_time;
    }

    private class BoxContainer extends Parent {
        public double time;
        public HBox container;
        public Text boxLabel;
        public TextField boxTimeSlot;
        public Button boxSubmit;

        public BoxContainer(double time) {
            this.time = time;
            container = new HBox(5);
            boxLabel = new Text("ID for time of " + getFormattedTime());
            boxTimeSlot = new TextField("");
            boxSubmit = new Button("Submit");
            container.getChildren().addAll(boxLabel, boxTimeSlot, boxSubmit);
            getChildren().add(container);
            boxSubmit.setOnAction(new AddButtonListener());
        }

        public class AddButtonListener implements EventHandler<ActionEvent> {
            public void handle(ActionEvent event) {
                Button source = (Button) event.getSource();
                BoxContainer boxContainer = (BoxContainer) source.getParent().getParent();
                int bibNumber = Integer.parseInt(boxContainer.boxTimeSlot.getText());
                Athlete athlete = InformationController.meet.getAndRemoveAthlete(bibNumber);
                if (athlete == null) {
                    throw new RuntimeException("Athlete with bib number " + bibNumber + " is not in the meet or has already finished.");
                }
                athlete.finish((long) boxContainer.time);
                athleteHeap.push(athlete);
                boxes.getChildren().remove(boxContainer);
            }
        }
    }
}
