package com.example.finalxcmeettracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    // Global variables for storing the screen size
    public static final int screenWidth = (int) Screen.getPrimary().getBounds().getWidth() - 100;
    public static final int screenHeight = (int) Screen.getPrimary().getBounds().getHeight() - 200;
    public static Scene splashScene;
    public static Scene meetScene;
    @Override
    public void start(Stage stage) throws IOException {
        splashScene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("splashScreen.fxml"))), screenWidth, screenHeight);
        meetScene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("meetScreen.fxml"))), screenWidth, screenHeight);

        stage.setScene(meetScene);
        stage.show();
        MeetController meetController = new MeetController();
    }

    public static void main(String[] args) {
        launch();
    }
}