package controllers;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Класс FileChooserController.
 * Контроллер окна выбора файла.
 */

public class FileChooserController {

    /**
     * path.
     * Тесттовое поле отображения
     * пути к файлу.
     */

    @FXML
    private TextField path;

    /**
     * fileChooser.
     * Используется для выбора файла.
     */

    private FileChooser fileChooser = new FileChooser();

    /**
     * selectedFile.
     * Выбраный файл.
     */

    private File selectedFile;

    /**
     * FileChooserController().
     * Конструктор класса.
     */

    private FileChooserController(){
        super();
    }

    /**
     * Метод chooseFile(ActionEvent actionEvent).
     * Вызывается при нажатии кнопки.
     * Служит для выбора файла из файл. системы.
     * @param actionEvent нажатие кнопки.
     */

    public final void chooseFile(final ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        this.fileChooser.setTitle("Open Resource File");
        this.fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt", "*.doc"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        this.selectedFile = this.fileChooser.showOpenDialog(stage);
        this.path.setText(String.valueOf(this.getSelectedFile()));
    }

    /**
     * Метод actionOk(ActionEvent actionEvent).
     * Закрывает окно, когда пользователь
     * подтвердил выбор файла.
     * @param actionEvent нажатие кнопки.
     */

    public final void actionOk(final ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    /**
     * Метод actionCancel(ActionEvent actionEvent).
     * Закрывает окно, когда пользователь
     * отменил выбор файла.
     * @param actionEvent нажатие кнопки.
     */

    public final void actionCancel(final ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    /**
     * Метод getSelectedFile().
     * Возвращает выбраный файл.
     * @return выбраный файл.
     */

    final File getSelectedFile() {
        return this.selectedFile;
    }
}
