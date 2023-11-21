package com.example.finalxcmeettracker;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class MeetScreen extends Application {
    // Global variables for storing the screen size
    public static final int screenWidth = (int) Screen.getPrimary().getBounds().getWidth() - 100;
    public static final int screenHeight = (int) Screen.getPrimary().getBounds().getHeight() - 200;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("meet-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), screenWidth, screenHeight);
        stage.setTitle("Meet Screen");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
