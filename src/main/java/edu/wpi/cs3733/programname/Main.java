package edu.wpi.cs3733.programname;

import edu.wpi.cs3733.programname.boundary.EmployeeManager;
import edu.wpi.cs3733.programname.boundary.ServiceRequestManager;
import edu.wpi.cs3733.programname.boundary.TestingController;
import edu.wpi.cs3733.programname.database.CsvReader;
import edu.wpi.cs3733.programname.database.DBConnection;
import edu.wpi.cs3733.programname.database.RunScript;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
//import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        checkOrMake();
        DBConnection dbConnection = setupDB();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("boundary/home_screen.fxml"));
//        loader.setController(new TestingController(dbConnection));
//        Parent root = loader.load();
//        primaryStage.setTitle("The best application");
//        primaryStage.setScene(new Scene(root, 1500, 750));
//        primaryStage.show();
//        showDialog(dbConnection);
        ManageController manageController = new ManageController(dbConnection);

        showDialog(manageController);
    }


    public Stage showDialog(ManageController manageController) throws IOException {
        FXMLLoader loader = new FXMLLoader(
        );
        loader.setLocation(getClass().getResource(
                "/fxml/test.fxml"
        ));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(
                        (Pane) loader.load()
                )
        );
        loader.<TestingController>getController().initManager(manageController);
        stage.show();
        return stage;
    }

    private DBConnection setupDB() throws IOException {
        DBConnection dbConnection = new DBConnection();
        dbConnection.setDBConnection();
        CsvReader mCsvReader = new CsvReader();
        RunScript run = new RunScript();
        run.runScript(dbConnection.getConnection());
        mCsvReader.insertNodes(dbConnection.getConnection(), mCsvReader.getListOfNodes(dbConnection.getConnection()));
        mCsvReader.insertEdges(dbConnection.getConnection(), mCsvReader.getListOfEdges(dbConnection.getConnection()));
        mCsvReader.insertEmployees(dbConnection.getConnection(), mCsvReader.getListOfEmployees(dbConnection.getConnection()));
        mCsvReader.insertServiceRequests(dbConnection.getConnection(), mCsvReader.getListOfServiceRequests(dbConnection.getConnection()));
        return dbConnection;
    }

    public static void main(String[] args) {
        launch(args);
    }


    private void checkOrMake() throws Exception {
        if (new File("floorMaps").mkdirs()) {
            try {
                String jarPath = ExportResource("/img/Floor_0.png");
                System.out.println("*****" + jarPath + "*****");
                System.out.println("*****" + jarPath + "*****");
                System.out.println("*****" + "*****");
                ExportResource("Floor_1.png");
                ExportResource("Floor_2.png");
                ExportResource("Floor_3.png");
                ExportResource("Floor_-1.png");
                ExportResource("Floor_-2.png");
                ExportResource("realPic.png");

//                copyFile(new File(this.getClass().getResource("/img/Floor_0.png").toURI()),
//                        new File("floorMaps/Floor_0.png"));
//                copyFile(new File(this.getClass().getResource("/img/Floor_1.png").toURI()), new File("floorMaps/Floor_1.png"));
//                copyFile(new File(this.getClass().getResource("/img/Floor_2.png").toURI()), new File("floorMaps/Floor_2.png"));
//                copyFile(new File(this.getClass().getResource("/img/Floor_3.png").toURI()), new File("floorMaps/Floor_3.png"));
//                copyFile(new File(this.getClass().getResource("/img/Floor_-1.png").toURI()), new File("floorMaps/Floor_-1.png"));
//                copyFile(new File(this.getClass().getResource("/img/Floor_-2.png").toURI()), new File("floorMaps/Floor_-2.png"));
//                copyFile(new File(this.getClass().getResource("/img/realPic.png").toURI()), new File("floorMaps/realPic.png"));
            } catch (SecurityException se) {
                //handle it
                se.getStackTrace();
            } catch (Exception e) {
                e.getStackTrace();
            }
        } else
            System.out.println("???");
    }

    /**
     * Export a resource embedded into a Jar file to the local file path.
     *
     * @param resourceName ie.: "/SmartLibrary.dll"
     * @return The path to the exported resource
     * @throws Exception
     *
     * thank you to user Ordiel on stack overflow for the structure
     */

    public String ExportResource(String resourceName) throws Exception {
        InputStream stream = null;
        OutputStream resStreamOut = null;
        String jarFolder = "";
        try {
            stream = Main.class.getResourceAsStream("/img/" + resourceName);//note that each / is a directory down in the "jar tree" been the jar the root of the tree
            if(stream == null) {
                throw new Exception();
            }

            int readBytes;
            byte[] buffer = new byte[4096];
            jarFolder = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath().replace('\\', '/');
            System.out.println(jarFolder + "********");
            resStreamOut = new FileOutputStream(jarFolder + "floorMaps/" + resourceName);
            while ((readBytes = stream.read(buffer)) > 0) {
                resStreamOut.write(buffer, 0, readBytes);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            stream.close();
            resStreamOut.close();
        }

        return jarFolder + resourceName;
    }

    public void copyFile(File sourceFile, File destinationFile) {
        try {
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            FileOutputStream fileOutputStream = new FileOutputStream(
                    destinationFile);

            int bufferSize;
            byte[] bufffer = new byte[512];
            while ((bufferSize = fileInputStream.read(bufffer)) > 0) {
                fileOutputStream.write(bufffer, 0, bufferSize);
            }
            fileInputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}