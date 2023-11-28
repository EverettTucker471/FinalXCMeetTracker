package com.example.finalxcmeettracker;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class MeetScreen extends Application {
    public void addBox(Scene scene, double time) {
        boxes.getChildren().add(new boxContainer(time));
    }

    VBox boxes = new VBox(8);
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("meet-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SplashScreen.screenWidth, SplashScreen.screenHeight);
        stage.setTitle("Meet Screen");

        ScrollPane boxesQueue = (ScrollPane) scene.lookup("#boxesQueue");

        Button addTime = (Button) scene.lookup("#addTime");
        EventHandler<ActionEvent> event = null;
        event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                addBox(scene, 0);
                System.out.println("running");
            }
        };
        addTime.setOnAction(event);

        boxesQueue.setContent(boxes);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
