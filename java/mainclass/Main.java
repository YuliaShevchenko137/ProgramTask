package mainclass;

import java.io.IOException;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 * Класс Main.
 * Запуск приложения.
 */

public class Main extends Application {

    /**
     * Ширина экрана.
     */

    private static final int width = 900;

    /**
     * Длинна экрана.
     */

    private static final int height = 500;

    /**
     * Метод start(final Stage primaryStage).
     * открывает основное окно программы.
     * @param primaryStage сцена основного окна.
     * @throws IOException возникает при открытии fxml-файла.
     */

    @Override
    public final void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(
                "../view/main.fxml"));
        primaryStage.setTitle("Task Manager");
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.setMinHeight(height);
        primaryStage.setMinWidth(width);
        primaryStage.show();
    }

    /**
     *Метод main(String[] args).
     * запускает приложение.
     * @param args стандартный набор аргументов.
     */

    public static void main(String[] args) {
        launch(args);
    }
}
