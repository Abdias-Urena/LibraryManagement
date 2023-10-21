package com.librarymanagement;
import Controller.InitPreloader;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

/**
 * The type Launcher preloader.
 */
public class LauncherPreloader extends Preloader {

    private Stage preloaderStage;
    private Scene scene;

    /**
     * Instantiates a new Launcher preloader.
     */
    public LauncherPreloader() {
    }

    @Override
    public void init() throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Loading.fxml")));
        scene = new Scene(root);

    }

    @Override
    public void start(Stage primaryStage) {

        this.preloaderStage = primaryStage;

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification info){
        if(info instanceof ProgressNotification){
            InitPreloader.lblLoadingg.setText("Loading " + ((ProgressNotification) info).getProgress() * 100 + " % ");
            //System.out.println("Progreso@: " + ((ProgressNotification)info).getProgress());
            InitPreloader.statprogressBar.setProgress(((ProgressNotification)info).getProgress());
        }
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        StateChangeNotification.Type type  = info.getType();
        switch(type){
            case BEFORE_START:
                System.out.println("BEFORE_START");
                preloaderStage.hide();
                break;
        }
    }
}
