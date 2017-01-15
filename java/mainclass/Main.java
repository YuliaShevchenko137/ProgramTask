package mainclass;

import java.io.IOException;

import controllers.MainController;
import javafx.application.Application;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import model.Task;

/**
 * Класс Main.
 * Запуск приложения.
 */

public final class Main extends Application {

    /**
     * Ширина экрана.
     */

    private final int width = 900;

    /**
     * Длинна экрана.
     */

    private final int height = 500;

    /**
     * Метод start(final Stage primaryStage).
     * открывает основное окно программы.
     * @param primaryStage сцена основного окна.
     * @throws IOException oткрытиe файла.
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

    /**
     *Метод main(String[] args).
     * запускает приложение.
     * @param args стандартный набор аргументов.
     */

    public static void main(final String[] args) {
        launch(args);
    }
}
