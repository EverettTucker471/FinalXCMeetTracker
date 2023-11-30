package com.example.finalxcmeettracker;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

public class MeetController {
    @FXML
    private Label currentTimeLabel;
    @FXML
    private Button returnButton, stopMeetButton, beginMeetButton, enterMeetInformationButton, addTimeButton, viewResultsButton, undoButton;
    @FXML
    private ScrollPane boxesQueue;
    public static Heap<Athlete> athleteHeap;
    private Stack<BoxContainer> undoStack;
    private long timestamp;
    public Timer meetTimer;
    private VBox boxes;

    public MeetController() {
        // TODO: Change the initial size parameter to function with the number of runners.
        athleteHeap = new Heap<>(10);
        undoStack = new Stack<>();
        meetTimer = new Timer();
        boxes = new VBox(8);
    }

    @FXML
    protected void onReturnButtonClick() {
        Stage stage = (Stage) currentTimeLabel.getScene().getWindow(); // getting the current stage, should be the same always
        stage.setScene(Main.splashScene);
        stage.show();
    }

    @FXML
    protected void onEnterMeetInformationButtonClick() {
        Stage stage = (Stage) currentTimeLabel.getScene().getWindow();
        stage.setScene(Main.informationScene);
        stage.show();
    }

    /**
     * This method cancels the Meet Timer forever, it cannot be undone.
     * We shouldn't theoretically need to stop the meet timer until the meet is finished.
     */
    @FXML
    protected void onStopMeetButtonClick() {
        meetTimer.cancel();
        addTimeButton.setVisible(false);
    }
    @FXML
    protected void onBeginMeetButtonClick() {
        timestamp = System.currentTimeMillis();

        addTimeButton.setVisible(true);

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

    @FXML
    protected void onAddTimeButtonClick() {
        BoxContainer box = new BoxContainer(System.currentTimeMillis() - timestamp);
        undoStack.push(box);
        boxes.getChildren().add(box);
        boxesQueue.setContent(boxes);
    }

    @FXML
    protected void onViewResultsButtonClick() {
        Stage stage = (Stage) currentTimeLabel.getScene().getWindow();
        stage.setScene(Main.resultsScene);
        stage.show();
    }

    @FXML
    protected void onUndoButtonClick() {
        if (!undoStack.isEmpty()) {
            BoxContainer box = undoStack.pop();
            boxes.getChildren().remove(box);
            boxesQueue.setContent(boxes);
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
