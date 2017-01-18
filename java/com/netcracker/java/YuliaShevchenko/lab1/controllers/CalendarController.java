package com.netcracker.java.YuliaShevchenko.lab1.controllers;

import com.netcracker.java.YuliaShevchenko.lab1.model.Constants;
import com.netcracker.java.YuliaShevchenko.lab1.model.Task;
import com.netcracker.java.YuliaShevchenko.lab1.model.TaskIO;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;
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
import org.apache.log4j.Logger;

/**
 * Class CalendarController.
 * Realization of the calendar.
 */

public class CalendarController {

    /**
     * logger.
     * It is used to register error.
     */

    private static final Logger logger = Logger.getLogger(CalendarController.class);

    /**
     * listView.
     * List dates.
     */

    @FXML
    private ListView<Date> listView;

    /**
     * taskTable.
     * Table of the tasks for the current date.
     */

    @FXML
    private TableView<Task> taskTable;

    /**
     * taskName.
     * Column of the title of the task.
     */

    @FXML
    private TableColumn<Task, String> taskName;

    /**
     * taskStart.
     * Column of the start date of the task.
     */

    @FXML
    private TableColumn<Task, Date> taskStart;

    /**
     * taskEnd.
     * Column of the end date of the task.
     */

    @FXML
    private TableColumn<Task, Date> taskEnd;

    /**
     * taskInterval.
     * Column of the repetition interval of the task.
     */

    @FXML
    private TableColumn<Task, String> taskInterval;

    /**
     * taskActive.
     * Column of the activity of the task.
     */

    @FXML
    private TableColumn<Task, Boolean> taskActive;

    /**
     * Map with date and tasks set.
     */

    private Map<Date, Set<Task>> maps;

    /**
     * Height window.
     */

    private final int height = 500;

    /**
     * Width window.
     */

    private final int width = 150;

    /**
     * Empty constructor.
     */

    public CalendarController() {

    }

    /**
     * Method initialize().
     * Executed when a controller is loaded.
     */

    @FXML
    public final void initialize() {
        this.taskName.setText(Constants.getTitle());
        this.taskName.setCellValueFactory(
                new PropertyValueFactory<>("title"));
        this.taskStart.setText(Constants.getStart());
        this.taskStart.setCellValueFactory(
                new PropertyValueFactory<>("start"));
        this.taskEnd.setText(Constants.getEnd());
        this.taskEnd.setCellValueFactory(
                new PropertyValueFactory<>("end"));
        this.taskInterval.setText(Constants.getInterval());
        this.taskInterval.setCellValueFactory(
                new PropertyValueFactory<>("interval"));
        this.taskActive.setText(Constants.getActive());
        this.taskActive.setCellValueFactory(
                new PropertyValueFactory<>("active"));
        this.taskName.setText("");
        this.taskStart.setText("");
        this.taskEnd.setText("");
        this.taskInterval.setText("");
        this.taskActive.setText("");
        this.listView.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) ->
                        this.showTaskDetails(newValue));
    }

    /**
     * Method showTaskDetails(Date date).
     * Executed when you select a date. Filling a table.
     * @param date selected date.
     */

    private void showTaskDetails(final Date date) {
        Set<Task> task = this.maps.get(date);
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        for (Task t : task) {
            tasks.add(t);
        }
        this.taskTable.setItems(tasks);
    }

    /**
     * Method safe(ActionEvent actionEvent).
     * Saving map at file.
     * @param actionEvent button press.
     */

    public final void safe(final ActionEvent actionEvent) {
        FXMLLoader filechoosefxmlLoader = new FXMLLoader();
        Stage fileChooserStage = new Stage();
        filechoosefxmlLoader.setLocation(getClass().
                getResource("../view/filechooser.fxml"));
        Parent root = null;
        try {
            root = filechoosefxmlLoader.load();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        FileChooserController fileChooserController =
                filechoosefxmlLoader.getController();
        fileChooserStage.setTitle("Choose file");
        fileChooserStage.setMaxHeight(this.height);
        fileChooserStage.setMinWidth(this.width);
        fileChooserStage.setResizable(false);
        fileChooserStage.setScene(new Scene(root));
        fileChooserStage.initModality(Modality.WINDOW_MODAL);
        fileChooserStage.initOwner(((Node) actionEvent.
                getSource()).getScene().getWindow());
        fileChooserStage.showAndWait();
        File f = fileChooserController.getSelectedFile();
        TaskIO.writeMap(this.maps, f);
    }

    /**
     * Method fillingList(final Map map).
     * Filling by key map list.
     * @param map map with key.
     */

    final void fillingList(final Map<Date, Set<Task>> map) {
        Set<Date> dateSet = map.keySet();
        ObservableList<Date> dateObs = FXCollections.observableArrayList();
        for (Date t : dateSet) {
            dateObs.add(t);
        }
        this.listView.setItems(dateObs);
    }

    /**
     * Method setMaps(Map maps).
     * Setter for map.
     * @param map new map.
     */

    final void setMaps(final Map<Date, Set<Task>> map) {
        this.maps = map;
    }
}
