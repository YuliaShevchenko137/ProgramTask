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

public final class Main extends Application {

    /**
     * Конструктор.
     */
    private Main() {
        this.width = 900;
        this.height = 500;
    }

    /**
     * Ширина экрана.
     */

    private int width;

    /**
     * Длинна экрана.
     */

    private int height;

    /**
     * Метод start(final Stage primaryStage).
     * открывает основное окно программы.
     * @param primaryStage сцена основного окна.
     * @throws IOException возникает при открытии
     * файла.
     */

    @Override
    public final void start(final Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(
                "../view/main.fxml"));
        primaryStage.setTitle("Task Manager");
        primaryStage.setScene(new Scene(root, this.width, this.height));
        primaryStage.setMinHeight(this.height);
        primaryStage.setMinWidth(this.width);
        primaryStage.show();
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
