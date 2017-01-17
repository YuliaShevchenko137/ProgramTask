package com.netcracker.java.YuliaShevchenko.lab1.controllers;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Class FileChooserController.
 * File selection window controller.
 */

public final class FileChooserController {

    /**
     * path.
     * TextField for write or show current file path.
     */

    @FXML
    private TextField path;

    /**
     * fileChooser.
     * User for select a file for device.
     */

    private FileChooser fileChooser = new FileChooser();

    /**
     * selectedFile.
     * Bind file to the controller.
     */

    private File selectedFile;

    /**
     * FileChooserController().
     * Empty constructor.
     */

    public FileChooserController() {

    }

    /**
     * Method chooseFile(ActionEvent actionEvent).
     * Called when a button is pressed.
     * Used to select the file.
     * @param actionEvent button press.
     */

    public void chooseFile(final ActionEvent actionEvent) {
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
     * Close window when the user confirms select a file.
     * @param actionEvent button press.
     */

    public void actionOk(final ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    /**
     * Method actionCancel(ActionEvent actionEvent).
     * Close the window when the user canceled select a file.
     * @param actionEvent button press.
     */

    public void actionCancel(final ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    /**
     * Method getSelectedFile().
     * Getter for selected file.
     * @return выбраный файл.
     */

    File getSelectedFile() {
        return this.selectedFile;
    }
}
