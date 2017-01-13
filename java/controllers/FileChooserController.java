package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileChooserController {
    @FXML
    private TextField path;

    private FileChooser fileChooser = new FileChooser();

    private File selectedFile = null;

    public void chooseFile(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        this.fileChooser.setTitle("Open Resource File");
        this.fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt", "*.doc"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        this.selectedFile = fileChooser.showOpenDialog(stage);
        this.path.setText(String.valueOf(getSelectedFile()));
    }

    public void actionOk(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void actionCancel(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    File getSelectedFile() {
        return selectedFile;
    }
}
