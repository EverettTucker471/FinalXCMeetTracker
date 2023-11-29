package com.example.finalxcmeettracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    // Global variables for storing the screen size, might not be necessary
    public static final int screenWidth = (int) Screen.getPrimary().getBounds().getWidth() - 100;
    public static final int screenHeight = (int) Screen.getPrimary().getBounds().getHeight() - 200;
    public static Scene splashScene;
    public static Scene meetScene;
    public static Scene informationScene;
    @Override
    public void start(Stage stage) throws IOException {
        splashScene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("splash-screen.fxml"))), screenWidth, screenHeight);
        meetScene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("meet-screen.fxml"))), screenWidth, screenHeight);
        informationScene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("information-screen.fxml"))), screenWidth, screenHeight);

        stage.setScene(splashScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}