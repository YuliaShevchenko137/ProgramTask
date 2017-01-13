package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.Set;


public class CalendarController {
    @FXML
    private ListView<Date> listView;

    @FXML
    private TableView<Task> taskTable;

    @FXML
    private TableColumn<Task, String> taskName;
    @FXML
    private TableColumn<Task, Date> taskStart;
    @FXML
    private TableColumn<Task, Date> taskEnd;
    @FXML
    private TableColumn<Task, String> taskInterval;
    @FXML
    private TableColumn<Task, Boolean> taskActive;

    private Map<Date, Set<Task>> maps;

    @FXML
    public void initialize() throws IOException, ParseException {
        this.taskName.setCellValueFactory(new PropertyValueFactory<>("title"));
        this.taskStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        this.taskEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        this.taskInterval.setCellValueFactory(new PropertyValueFactory<>("interval"));
        this.taskActive.setCellValueFactory(new PropertyValueFactory<>("active"));
        this.taskName.setText("");
        this.taskStart.setText("");
        this.taskEnd.setText("");
        this.taskInterval.setText("");
        this.taskActive.setText("");
        this.listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showTaskDetails(newValue));
    }

    private void showTaskDetails(Date date) {
        Set<Task> task = this.maps.get(date);
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        for(Task t:task) tasks.add(t);
        this.taskTable.setItems(tasks);
    }

    public void safe(ActionEvent actionEvent) throws IOException, ParseException {
        FXMLLoader filechoosefxmlLoader = new FXMLLoader();
        Stage fileChooserStage = new Stage();
        filechoosefxmlLoader.setLocation(getClass().getResource("../view/filechooser.fxml"));
        Parent root = filechoosefxmlLoader.load();
        FileChooserController fileChooserController = filechoosefxmlLoader.getController();
        fileChooserStage.setTitle("Выбор файла");
        fileChooserStage.setMaxHeight(500);
        fileChooserStage.setMinWidth(150);
        fileChooserStage.setResizable(false);
        fileChooserStage.setScene(new Scene(root));
        fileChooserStage.initModality(Modality.WINDOW_MODAL);
        fileChooserStage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        fileChooserStage.showAndWait();
        File f = fileChooserController.getSelectedFile();
        TaskIO.writeMap(maps, f);
    }

    void fillingTable(Map<Date, Set<Task>> maps) {
        Set<Date> dateSet = maps.keySet();
        ObservableList<Date> dateObs = FXCollections.observableArrayList();
        for(Date t : dateSet) dateObs.add(t);
        this.listView.setItems(dateObs);
    }

    void setMaps(Map<Date, Set<Task>> maps) {
        this.maps = maps;
    }
}
