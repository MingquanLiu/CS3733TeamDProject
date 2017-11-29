package edu.wpi.cs3733.programname;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("boundary/home_screen.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("The best application");
        primaryStage.setScene(new Scene(root, 1500, 750));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }
}
