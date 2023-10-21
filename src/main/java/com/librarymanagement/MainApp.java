package com.librarymanagement;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * The type Main app.
 */
public class MainApp extends Application {

    private static final int COUNT_LIMIT = 1;
    private double x = 0;
    private double y = 0;

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Dashboard.fxml")));
        Scene scene = new Scene(root);

        root.setOnMousePressed((MouseEvent event) -> {

            x = event.getSceneX();
            y = event.getSceneY();

        });

        root.setOnMouseDragged((MouseEvent event) -> {

            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(.8);

        });

        root.setOnMouseReleased((MouseEvent event) -> stage.setOpacity(1));

        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void init() throws Exception {
        for (int i = 1; i <= COUNT_LIMIT; i++) {
            double progress = (double) i / 10;
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
            Thread.sleep(1500);
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        LauncherImpl.launchApplication(MainApp.class, LauncherPreloader.class, args);

    }

}
