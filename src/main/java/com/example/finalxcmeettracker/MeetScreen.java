package com.example.finalxcmeettracker;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MeetScreen extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("meet-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SplashScreen.screenWidth, SplashScreen.screenHeight);
        stage.setTitle("Meet Screen");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
