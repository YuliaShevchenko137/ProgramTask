package controllers;

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
import model.Task;
import model.TaskIO;

/**
 * Класс CalendarController.
 * Реализация отображения списка задач
 * в виде календаря.
 */

public class CalendarController {

    /**
     * Список с временем выполнения задач.
     */

    @FXML
    private ListView<Date> listView;

    /**
     * Таблица задач за текущее время.
     */

    @FXML
    private TableView<Task> taskTable;

    /**
     * Колонка названия задачи.
     */

    @FXML
    private TableColumn<Task, String> taskName;

    /**
     * Колонка времени начала задачи.
     */

    @FXML
    private TableColumn<Task, Date> taskStart;

    /**
     * Колонка времени конца задачи.
     */

    @FXML
    private TableColumn<Task, Date> taskEnd;

    /**
     * Колонка интервала повторения.
     */

    @FXML
    private TableColumn<Task, String> taskInterval;

    /**
     * Колонка активности.
     */

    @FXML
    private TableColumn<Task, Boolean> taskActive;

    /**
     * Карта, по которой строится список.
     */

    private Map<Date, Set<Task>> maps;

    /**
     * Высота окна.
     */

    private final int heigth = 500;

    /**
     * Ширина окна.
     */

    private final int width = 150;

    /**
     * Метод initialize().
     * Выполняется при загрузке контроллера.
     */

    @FXML
    public final void initialize() {
        this.taskName.setCellValueFactory(
                new PropertyValueFactory<>("title"));
        this.taskStart.setCellValueFactory(
                new PropertyValueFactory<>("start"));
        this.taskEnd.setCellValueFactory(
                new PropertyValueFactory<>("end"));
        this.taskInterval.setCellValueFactory(
                new PropertyValueFactory<>("interval"));
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
     * Метод showTaskDetails(Date date).
     * Выполняется при выборе даты.
     * Заполнение таблицы.
     * @param date выбраная дата.
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
     * Метод safe(ActionEvent actionEvent).
     * СОхраняет карту в файл.
     * @param actionEvent нажатие кнопки.
     * @throws IOException открытие файла.
     */

    public final void safe(final ActionEvent actionEvent) throws IOException {
        FXMLLoader filechoosefxmlLoader = new FXMLLoader();
        Stage fileChooserStage = new Stage();
        filechoosefxmlLoader.setLocation(getClass().
                getResource("../view/filechooser.fxml"));
        Parent root = filechoosefxmlLoader.load();
        FileChooserController fileChooserController =
                filechoosefxmlLoader.getController();
        fileChooserStage.setTitle("Выбор файла");
        fileChooserStage.setMaxHeight(this.heigth);
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
     * Метод fillingTable(final Map map).
     * Заполнение списка дат.
     * @param map карта для выбора дат.
     */

    final void fillingTable(final Map<Date, Set<Task>> map) {
        Set<Date> dateSet = map.keySet();
        ObservableList<Date> dateObs = FXCollections.observableArrayList();
        for (Date t : dateSet) {
            dateObs.add(t);
        }
        this.listView.setItems(dateObs);
    }

    /**
     * Метод setMaps(Map maps).
     * ПРивязывает карту к контроллеру.
     * @param map карта.
     */

    final void setMaps(final Map<Date, Set<Task>> map) {
        this.maps = map;
    }
}
