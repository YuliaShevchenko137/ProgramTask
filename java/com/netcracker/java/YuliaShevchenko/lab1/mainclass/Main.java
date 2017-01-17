package com.netcracker.java.YuliaShevchenko.lab1.mainclass;

import com.netcracker.java.YuliaShevchenko.lab1.controllers.MainController;
import com.netcracker.java.YuliaShevchenko.lab1.model.Task;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class Main.
 * Start application.
 */

public final class Main extends Application {

    /**
     * Width window.
     */

    private final int width = 900;

    /**
     * Heigth window.
     */

    private final int height = 500;

    /**
     * Method start(final Stage primaryStage).
     * open the main window.
     * @param primaryStage the scene of the main window.
     * @throws IOException appears when opening a file.
     */

    @Override
    public void start(final Stage primaryStage) throws IOException {
        FXMLLoader mainfxmlLoader = new FXMLLoader();
        mainfxmlLoader.setLocation(getClass().getResource("../view/main.fxml"));
        Parent root = mainfxmlLoader.load();
        primaryStage.setTitle("Task Manager");
        MainController mainController = mainfxmlLoader.getController();
        primaryStage.setScene(new Scene(root, this.width, this.height));
        primaryStage.setMinHeight(this.height);
        primaryStage.setMinWidth(this.width);
        primaryStage.show();
        primaryStage.setOnCloseRequest(closeEvent -> {
            ObservableList<Task> obs = mainController.getObs().getObs();
            for (Task t : obs) {
                t.getThreadTask().setFinish();
            }
        });
    }
}
