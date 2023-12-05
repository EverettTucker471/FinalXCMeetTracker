package com.example.finalxcmeettracker;

import javafx.application.Application;
import javafx.application.HostServices;
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
    public static HostServices hostServices; // Used for opening websites from Javafx
    public static Scene splashScene;
    public static Scene meetScene;
    public static Scene informationScene;
    public static Scene resultsScene;
    public static String buttonStyle = "-fx-font: 20 \"montserrat\"; -fx-background-color: rgba(255, 255, 255, 1);";
    public static String layoutStyle = "-fx-background-color: rgba(52, 96, 148, 0.5); -fx-padding: 10;";
    public static String titleLabelStyle = "-fx-font: 40 \"montserrat\"; -fx-padding: 30; -fx-text-fill: rgba(255, 255, 255, 1);";
    public static String subtitleLabelStyle = "-fx-font: 20 \"montserrat\"; -fx-padding: 30; -fx-text-fill: rgba(255, 255, 255, 1);";
    public static String largeTextLabelStyle = "-fx-font: 20 \"montserrat\"; -fx-padding: 30 0 0 0; -fx-text-fill: rgba(255, 255, 255, 1);";
    public static String glassStyle = "-fx-background-color: rgba(48, 40, 38, 0.5); -fx-background-radius: 10; -fx-padding: 10;";
    public static String textFieldLargeStyle = "-fx-font: 20 \"montserrat\";";
    public static MeetController meetController;
    public static InformationController informationController;
    public static SplashController splashController;
    public static ResultsController resultsController;
    @Override
    public void start(Stage stage) {
        meetController = new MeetController();
        meetController.init(stage);

        informationController = new InformationController();
        informationController.init(stage);

        splashController = new SplashController();
        splashController.init(stage);

        resultsController = new ResultsController();
        resultsController.init(stage);

        hostServices = getHostServices();
        Main.splashController.reset();
    }

    public static void main(String[] args) {
        launch();
    }
}