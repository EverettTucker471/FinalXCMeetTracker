package com.example.finalxcmeettracker;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;

public class MeetController1 {
    private class boxContainer extends Parent {
        public double time;
        public HBox container;
        public Text boxLabel;
        public TextField boxTimeSlot;
        public Button boxAdd;

        public boxContainer(double time) {
            container = new HBox(5);
            boxLabel = new Text("ID for time of " + getFormattedTime());
            boxTimeSlot = new TextField("");
            boxAdd = new Button("Add");
            container.getChildren().addAll(boxLabel, boxTimeSlot, boxAdd);
            getChildren().add(container);
            boxAdd.setOnAction(new AddButtonListener());
        }

        public class AddButtonListener implements EventHandler<ActionEvent> {
            public void handle(ActionEvent event ){
                Button source = (Button) event.getSource();
                boxContainer boxContainer = (boxContainer) source.getParent().getParent();
                boxes.getChildren().remove(boxContainer);
            }
        }
    }



    private long timestamp;
    public final Timer meetTimer = new Timer();

    public MeetController() {
        boxes = new VBox(8);
        boxPane = new ScrollPane();
        boxPane.setContent(boxes);
        VBox prnt = (VBox)returnButton.getParent();
        prnt.getChildren().add(1, boxPane);
    }

    @FXML
    protected void onReturnButtonClick() {
        Stage stage = (Stage) currentTimeLabel.getScene().getWindow(); // getting the current stage, should be the same always
        stage.setScene(Main.splashScene);
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
            boxes = new VBox(8);
            boxPane.setContent(boxes);
        } catch (IllegalStateException e) {
            System.out.println("Cannot start a meet that has been cancelled");
        }
    }
    @FXML
    protected void onAddTimeButtonClick() {
        boxes.getChildren().add(new boxContainer(System.currentTimeMillis() - timestamp));
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
}
