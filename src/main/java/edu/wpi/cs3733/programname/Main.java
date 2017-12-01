package edu.wpi.cs3733.programname;

import edu.wpi.cs3733.programname.boundary.TestingController;
import edu.wpi.cs3733.programname.database.CsvReader;
import edu.wpi.cs3733.programname.database.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
//import org.junit.Test;

import java.io.IOException;

import static edu.wpi.cs3733.programname.database.DBTables.createAllTables;


public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        DBConnection dbConnection = setupDB();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("boundary/home_screen.fxml"));
//        loader.setController(new TestingController(dbConnection));
//        Parent root = loader.load();
//        primaryStage.setTitle("The best application");
//        primaryStage.setScene(new Scene(root, 1500, 750));
//        primaryStage.show();
        showDialog(dbConnection);

    }

    public Stage showDialog(DBConnection dbConnection) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/fxml/home_screen.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(
                        (Pane) loader.load()
                )
        );
        loader.<TestingController>getController().initData(dbConnection);
        stage.show();
        return stage;
    }

    public DBConnection setupDB() throws IOException{
        DBConnection dbConnection = new DBConnection();
        dbConnection.setDBConnection();
        CsvReader mCsvReader = new CsvReader();
        createAllTables(dbConnection);
        mCsvReader.insertNodes(dbConnection.getConnection(),mCsvReader.getListOfNodes(dbConnection.getConnection()));
        mCsvReader.insertEdges(dbConnection.getConnection(),mCsvReader.getListOfEdges(dbConnection.getConnection()));
        mCsvReader.insertEmployees(dbConnection.getConnection(),mCsvReader.getListOfEmployees(dbConnection.getConnection()));
        mCsvReader.insertServiceRequests(dbConnection.getConnection(),mCsvReader.getListOfServiceRequests(dbConnection.getConnection()));
        return dbConnection;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
