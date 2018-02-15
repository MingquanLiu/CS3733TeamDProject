package edu.wpi.cs3733.programname;

import edu.wpi.cs3733.programname.boundary.NewMainPageController;
import edu.wpi.cs3733.programname.commondata.AppSettings;
import edu.wpi.cs3733.programname.database.CsvReader;
import edu.wpi.cs3733.programname.database.DBConnection;
import edu.wpi.cs3733.programname.database.RunScript;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
//import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;


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
        String toUse = "home_screen";
        toUse = "test";
        FXMLLoader loader = new FXMLLoader(
        );
        loader.setLocation(getClass().getResource(
                "/fxml/" + toUse + ".fxml"
        ));
        Stage stage = new Stage(StageStyle
                .DECORATED);
        stage.setScene(
                new Scene(
                        (Pane) loader.load()
                )
        );
        stage.setTitle("BWH Kiosk");
        stage.setOnCloseRequest(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit Kiosk");
            alert.setHeaderText("You are about to exit!");
            alert.setContentText("Are you sure you want to close the kiosk? This will close all windows and service requests.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                System.exit(1);
            } else {
                event.consume();
            }
        });
        loader.<NewMainPageController>getController().initManager(manageController);
        loader.<NewMainPageController>getController().passStage(stage);
        initThread(loader.<NewMainPageController>getController());
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
        mCsvReader.insertMaps(dbConnection.getConnection(), mCsvReader.getBuildings(dbConnection.getConnection()));
        mCsvReader.insertEdges(dbConnection.getConnection(), mCsvReader.getListOfEdges(dbConnection.getConnection()));
        mCsvReader.insertEmployees(dbConnection.getConnection(), mCsvReader.getListOfEmployees(dbConnection.getConnection()));
        mCsvReader.insertServiceRequests(dbConnection.getConnection(), mCsvReader.getListOfServiceRequests(dbConnection.getConnection()));
        return dbConnection;
    }

    private Thread initThread(NewMainPageController controller) {
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }
        GlobalMouseListener example = new GlobalMouseListener();

        // Add the appropriate listeners.
        GlobalScreen.addNativeMouseListener(example);
        GlobalScreen.addNativeMouseMotionListener(example);
        GlobalScreen.addNativeKeyListener(new GlobalKeyListener());

        // Get the logger for "org.jnativehook" and set the level to off.
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        Thread thread = new Thread(new Runnable() {
            public void run() {
                Frame frame = null;

                while(true) {
                    // The world will never know why we need this line, but we do, so don't delete it
                    long a = System.currentTimeMillis();
                    // (I'm assuming it allows the internal timers to synchronize)
                    if((System.currentTimeMillis() - AppSettings.getInstance().getDelayTime()) > 0L) {
                        if(!AppSettings.getInstance().isSaveScreen()) {
                            frame = new Frame("Kiosk Screen Saver");
                            frame.setVisible(false);
                            frame.setUndecorated(true);
                            try {
                                frame.add(new Component() {
                                    BufferedImage img = ImageIO.read(getClass().getResource("/img/WaitnLoad.gif"));
                                    @Override
                                    public void paint(Graphics g) {
                                        super.paint(g);
                                        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                                    }
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                            GraphicsDevice gs = ge.getDefaultScreenDevice();
                            gs.setFullScreenWindow(frame);
                            frame.validate();
                            frame.setVisible(true);
//                            frame.setAlwaysOnTop(true);
                            frame.toFront();
                            AppSettings.getInstance().setSaveScreen(true);
                        }
                    } else {
                        if(AppSettings.getInstance().isSaveScreen()) {
                            FutureTask<Void> task = new FutureTask<>(() -> controller.reinitialize(), null);
                            Platform.runLater(task);
                            try {
                                task.get();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                            frame.dispose();
                            AppSettings.getInstance().setSaveScreen(false);
                        }
                    }
                }
            }
        });
        thread.start();
        return thread;
    }

    public static void main(String[] args) {

        launch(args);
    }


    private void checkOrMake() throws Exception {
        if (new File("floorMaps").mkdirs()) {
            try {
                String jarPath = ExportResource("/img/Floor_0.png");
                //System.out.println("*****" + jarPath + "*****");
                //System.out.println("*****" + jarPath + "*****");
                //System.out.println("*****" + "*****");
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
     * @throws Exception thank you to user Ordiel on stack overflow for the structure
     */

    public String ExportResource(String resourceName) throws Exception {
        InputStream stream = null;
        OutputStream resStreamOut = null;
        String jarFolder = "";
        try {
            stream = Main.class.getResourceAsStream("/img/" + resourceName);//note that each / is a directory down in the "jar tree" been the jar the root of the tree
            if (stream == null) {
                throw new Exception();
            }

            int readBytes;
            byte[] buffer = new byte[4096];
            jarFolder = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath().replace('\\', '/');
            //System.out.println(jarFolder + "********");
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