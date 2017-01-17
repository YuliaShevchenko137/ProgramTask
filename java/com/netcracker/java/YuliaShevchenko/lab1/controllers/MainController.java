package com.netcracker.java.YuliaShevchenko.lab1.controllers;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import com.netcracker.java.YuliaShevchenko.lab1.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Контроллер MainController.
 * Основное окно программы.
 */

public final class MainController {

    /**
     * Обертка списка задач.
     */

    private CollectionsTasks obs;

    /**
     * Таблица с задачами.
     */

    @FXML
    private TableView<Task> taskTable;

    /**
     * Столбец названия задачи.
     */

    @FXML
    private TableColumn<Task, String> taskName;

    /**
     * Столбец активности задачи.
     */

    @FXML
    private TableColumn<Task, Boolean> taskActive;

    /**
     * Натпись с кол-вом задач.
     */

    @FXML
    private Label labelSize;

    /**
     * Название выбранной задачи.
     */

    @FXML
    private Label currentTaskName;

    /**
     * Начало выбранной задачи.
     */

    @FXML
    private Label currentTaskStart;

    /**
     * Конец выбранной задачи.
     */

    @FXML
    private Label currentTaskEnd;

    /**
     * Интервал выбранной задачи.
     */

    @FXML
    private Label currentTaskInterval;

    /**
     * Активность выбранной задачи.
     */

    @FXML
    private Label currentTaskActive;

    /**
     * Сообщение об ошибке при изменении.
     */

    @FXML
    private Label error;

    /**
     * Надпись Начало.
     */

    @FXML
    private Label labelStart;

    /**
     * Надпись Конец.
     */

    @FXML
    private Label labelEnd;

    /**
     * Надпись интервал.
     */

    @FXML
    private Label labelInterval;

    /**
     * Надпись лет.
     */

    @FXML
    private Label labelYear;

    /**
     * Надпись месяцев.
     */

    @FXML
    private Label labelMonth;

    /**
     * Надпись дней.
     */

    @FXML
    private Label labelDay;

    /**
     * Надпись часов.
     */

    @FXML
    private Label labelHour;

    /**
     * Надпись минут.
     */

    @FXML
    private Label labelMinute;

    /**
     * Надпись секунд.
     */

    @FXML
    private Label labelSecond;

    /**
     * Кнопка Измения.
     */

    @FXML
    private Button change;

    /**
     * Кнопка Применения изменений.
     */

    @FXML
    private Button apply;

    /**
     * Таблица просмотра.
     */

    @FXML
    private GridPane gridView;

    /**
     * Таблица изменения.
     */

    @FXML
    private GridPane gridChange;

    /**
     * Поле изменения имени.
     */

    @FXML
    private TextField taskNameField;

    /**
     * Время начала именяемой задачи.
     */

    @FXML
    private TextField timeStart;

    /**
     * Время конца изменяемой задачи.
     */

    @FXML
    private TextField timeEnd;

    /**
     * Количество лет интервала.
     */

    @FXML
    private TextField year;

    /**
     * Количество месяцев интервала.
     */

    @FXML
    private TextField month;

    /**
     * Количество дней интервала.
     */

    @FXML
    private TextField day;

    /**
     * Количество часов интервала.
     */

    @FXML
    private TextField hour;

    /**
     * Количество минут интервала.
     */

    @FXML
    private TextField minute;

    /**
     * Количесво секунд интервала.
     */

    @FXML
    private TextField second;

    /**
     * Время начала календаря.
     */

    @FXML
    private TextField calendarTimeStart;

    /**
     * Время конца календаря.
     */

    @FXML
    private TextField calendarTimeEnd;

    /**
     * Дата начала изменяющейся задачи.
     */

    @FXML
    private DatePicker dateStart;

    /**
     * Дата конца изменяющейся задачи.
     */

    @FXML
    private DatePicker dateEnd;

    /**
     * Дата конца календаря.
     */

    @FXML
    private DatePicker calendarDateEnd;

    /**
     * Дата начала календаря.
     */

    @FXML
    private DatePicker calendarDateStart;

    /**
     * Переключатель: задача активна.
     */

    @FXML
    private RadioButton activeTrue;

    /**
     * Переключатель: задача неактивна.
     */

    @FXML
    private RadioButton activeFalse;

    /**
     * Индикатор повторяемости.
     */

    @FXML
    private CheckBox checkboxrepeated;

    /**
     * Индекс выбранной задачи.
     */

    private int countChanges = -1;

    /**
     * Минимальтая высота окна.
     */

    private final int minHeight = 450;

    /**
     * Минимальная ширина окна.
     */

    private final int minWidth = 350;

    /**
     * Файл для записи и считывания.
     */

    private final File temp = new File("temp.txt");

    /**
     * Метод MainController().
     * Пустой конструктор контроллера.
     */

    public MainController() {

    }

    /**
     * Метод initialize().
     * Выполняется при инициализации
     * главного окна.
     * @throws ParseException преобразование дат.
     * @throws IOException работа с файлом.
     */

    @FXML
    public void initialize() throws ParseException, IOException {
        this.obs = new CollectionsTasks();
        TaskList tasks = new ArrayTaskList();
        TaskIO.readText(tasks, this.temp);
        this.obs.setTasks(tasks);
        this.taskTable.setItems(this.obs.getObs());
        this.taskName.setCellValueFactory(
                new PropertyValueFactory<>("title"));
        this.taskActive.setCellValueFactory(
                new PropertyValueFactory<>("active"));
        final ToggleGroup group = new ToggleGroup();
        this.activeTrue.setToggleGroup(group);
        this.activeFalse.setToggleGroup(group);
        this.taskTable.setItems(this.getObs().getObs());
        this.taskTable.getSelectionModel().selectedItemProperty().addListener(
            (observableValue, oldValue,
             newValue) -> this.showTaskDetails(newValue));
        this.gridChange.setVisible(false);
        this.gridView.setVisible(true);
        this.showNoting();
        this.apply.setVisible(false);
        this.apply.setText(Constants.getApply());
        this.change.setText(Constants.getChange());
        this.labelSize.setText(Constants.getCountTask() + this.obs.getObs().size());
    }

    /**
     * Метод showNoting().
     * Вызывается когда ни одна задача
     * не выбрана.
     */

    private void showNoting() {
        this.currentTaskName.setText("");
        this.currentTaskStart.setText("");
        this.currentTaskEnd.setText("");
        this.currentTaskInterval.setText("");
        this.currentTaskActive.setText("");
    }

    /**
     * Метод showTaskDetails(Task task).
     * Отображение деталей текущей задачи.
     * @param task выбраная задача.
     */

    private void showTaskDetails(final Task task) {
        if (task != null) {
            this.currentTaskName.setText(task.getTitle());
            this.currentTaskStart.setText(String.valueOf(task.getStart()));
            this.currentTaskEnd.setText(String.valueOf(task.getEnd()));
            this.currentTaskInterval.setText(task.getInterval());
            this.currentTaskActive.setText(String.valueOf(task.isActive()));
        } else {
            this.showNoting();
        }
    }

    /**
     * Метод showDetailsForChange(Task task).
     * Отображает данные для инменения.
     * @param task задача для изменения.
     */

    private void showDetailsForChange(final Task task) {
        this.taskNameField.setText(task.getTitle());
        String str = String.valueOf(task.getStart());
        String[] words = str.split(Constants.getRegexp());
        this.dateStart.setValue(OperationForTime.dateToLocalDate(
                task.getStart()));
        this.timeStart.setText(words[Constants.getThree()]);
        this.dateEnd.setValue(OperationForTime.dateToLocalDate(
                task.getEnd()));
        str = String.valueOf(task.getStart());
        words = str.split(Constants.getRegexp());
        this.timeEnd.setText(words[Constants.getThree()]);
        final ToggleGroup group = new ToggleGroup();
        this.activeTrue.setToggleGroup(group);
        this.activeFalse.setToggleGroup(group);
        this.activeTrue.setSelected(task.isActive());
        this.activeFalse.setSelected(!task.isActive());
        this.checkboxrepeated.setSelected(task.isRepeated());
        this.year.setText(String.valueOf(task.getCreateInterval().getIntervalYear()));
        this.month.setText(String.valueOf(task.getCreateInterval().getIntervalMonth()));
        this.day.setText(String.valueOf(task.getCreateInterval().getIntervalDay()));
        this.minute.setText(String.valueOf(task.getCreateInterval().getIntervalMinute()));
        this.hour.setText(String.valueOf(task.getCreateInterval().getIntervalHour()));
        this.second.setText(String.valueOf(task.getCreateInterval().getIntervalSecond()));
        task.getInterval();
        this.checkboxrepeated.setSelected(task.isRepeated());
        this.repeated();
    }

    /**
     * Метод add(ActionEvent actionEvent).
     * Открывает окно добавления задачи и
     * добавляет задачу в список задач.
     * @param actionEvent нажатие кнопки.
     * @throws IOException открытие файла.
     */

    public void add(final ActionEvent actionEvent) throws IOException {
        Stage addStage = new Stage();
        FXMLLoader addfxmlLoader = new FXMLLoader();
        addfxmlLoader.setLocation(getClass().getResource("../view/add.fxml"));
        Parent root = addfxmlLoader.load();
        addStage.setTitle("Add task");
        addStage.setMinHeight(this.minHeight);
        addStage.setMinWidth(this.minWidth);
        addStage.setResizable(false);
        addStage.setScene(new Scene(root));
        addStage.initModality(Modality.WINDOW_MODAL);
        addStage.initOwner(((Node)
                actionEvent.getSource()).getScene().getWindow());
        addStage.showAndWait();
        AddController addController = addfxmlLoader.getController();
        if (addController.bool) {
            this.obs.add(addController.T);
            this.taskTable.setItems(this.obs.getObs());
            this.labelSize.setText(Constants.getCountTask() + this.obs.getObs().size());
            TaskIO.writeText(this.obs.getTasks(), this.temp);
        }
    }

    /**
     * Метод remove().
     * Вызов уведобления про удаление задачи.
     * @throws IOException запись данных в файл.
     */

    public void remove() throws IOException {
        Task t = this.taskTable.getSelectionModel().getSelectedItem();
        if (t == null) {
            return;
        }
        Alert delete = new Alert(Alert.AlertType.CONFIRMATION);
        delete.setTitle("Remove");
        delete.setHeaderText("Are you sure?");
        Optional<ButtonType> result = delete.showAndWait();
        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            t.getThreadTask().setFinish();
            this.obs.remove(t);
            TaskIO.writeText(this.obs.getTasks(), this.temp);
            this.labelSize.setText(Constants.getCountTask() + this.obs.getObs().size());
        }
    }

    /**
     * Метод calendar(ActionEvent actionEvent).
     * Открывает календарь для выбраного
     * времени.
     * @param actionEvent нажатие кнопки.
     * @throws IOException открытие файла.
     * @throws ParseException преобразование дат.
     */

    public void calendar(final ActionEvent actionEvent)
            throws IOException, ParseException {
        LocalDate dateStarts = this.calendarDateStart.getValue();
        LocalDate dateEnds = this.calendarDateEnd.getValue();
        String timestart = this.calendarTimeStart.getText();
        String timeend = this.calendarTimeEnd.getText();
        if (dateStarts == null
                || dateEnds == null
                || "".equals(timestart)
                || "".equals(timeend)) {
            return;
        }
        Stage calendarStage = new Stage();
        FXMLLoader calendarfxmlLoader = new FXMLLoader();
        calendarfxmlLoader.setLocation(
                getClass().getResource("../view/calendar.fxml"));
        Parent root = calendarfxmlLoader.load();
        calendarStage.setTitle("Calendar");
        calendarStage.setMinHeight(this.minHeight);
        calendarStage.setMinWidth(this.minWidth);
        calendarStage.setScene(new Scene(root));
        calendarStage.initModality(Modality.WINDOW_MODAL);
        calendarStage.initOwner(((Node)
                actionEvent.getSource()).getScene().getWindow());
        Date dateSt = OperationForTime.parseDate(dateStarts
                + Constants.getSpace() + timestart);
        Date dateEn = OperationForTime.parseDate(dateEnds
                + Constants.getSpace() + timeend);
        Map<Date, java.util.Set<Task>> maps
                = Tasks.calendar(this.obs.getObs(), dateSt, dateEn);
        CalendarController calendarController
                = calendarfxmlLoader.getController();
        calendarController.setMaps(maps);
        calendarController.fillingTable(maps);
        calendarStage.showAndWait();
    }

    /**
     * Метод getObs().
     * Возвращает обертку для списка задач.
     * @return обертка списка задач.
     */

    public CollectionsTasks getObs() {
        return this.obs;
    }

    /**
     * Метод repeated().
     * Изменение задачи.
     */

    public void repeated() {
        if (!this.checkboxrepeated.isSelected()) {
            this.labelStart.setText(Constants.getTime());
            InfoClass.visibleObj(false, this.labelEnd, this.dateEnd,
                    this.timeEnd, this.labelInterval);
            InfoClass.visibleTextFieldInterval(false,
                    this.year, this.month, this.day,
                    this.hour, this.minute, this.second);
            InfoClass.visibleLabelsInterval(false,
                    this.labelYear, this.labelMonth, this.labelDay,
                    this.labelHour, this.labelMinute, this.labelSecond);
        } else {
            this.labelStart.setText(Constants.getStart());
            InfoClass.visibleObj(true, this.labelEnd, this.dateEnd,
                    this.timeEnd, this.labelInterval);
            InfoClass.visibleTextFieldInterval(true,
                    this.year, this.month, this.day,
                    this.hour, this.minute, this.second);
            InfoClass.visibleLabelsInterval(true,
                    this.labelYear, this.labelMonth, this.labelDay,
                    this.labelHour, this.labelMinute, this.labelSecond);
        }
    }

    /**
     * Метод applyChanges().
     * Если данные введены коректно, то
     * переписывает текущую задачу.
     * @throws CloneNotSupportedException клонование задачи.
     * @throws ParseException преобразование дат.
     * @throws IOException запись в файл.
     */

    public void applyChanges() throws CloneNotSupportedException,
            ParseException, IOException {
        if (this.countChanges == -1) {
            this.countChanges
                    = this.taskTable.getSelectionModel().getSelectedIndex();
        }
        Task task = this.obs.getObs().get(this.countChanges);
        Task task1 = task.clone();
        String str1;
        this.obs.getObs().remove(task);
        if (this.checkboxrepeated.isSelected()) {
            str1 = this.changeRepeatedTask(task, task1);
        } else {
            str1 = this.changeNoRepeatedTask(task, task1);
        }
        if ("".equals(str1)) {
            this.gridChange.setVisible(false);
            this.gridView.setVisible(true);
            task.getInterval();
            task.getThreadTask().setFinish();
            task.setThreadTask(new ThreadTask(task));
            this.obs.getObs().add(task);
            TaskIO.writeText(this.obs.getTasks(), this.temp);
            this.countChanges = -1;
            this.change.setVisible(true);
            this.apply.setVisible(false);
            this.error.setText("");
            this.taskTable.setItems(this.obs.getObs());
        } else {
            this.error.setVisible(true);
            this.getObs().getObs().add(task1);
            this.error.setText(str1);
        }
    }

    /**
     * Метод changeRepeatedTask(Task task, Task task1).
     * Изменение повторяемой задачи.
     * @param task изменяемая задача.
     * @param task1 если данные некоректны.
     * @return сообщение об ошибках.
     */

    private String changeRepeatedTask(final Task task, final Task task1) {
        String str1 = "";
        task.setTitle(this.taskNameField.getText());
        task.setActive(this.activeTrue.isSelected());
        try {
            task.setStart(OperationForTime.parseDate(this.dateStart.getValue()
                    + Constants.getSpace() + this.timeStart.getText()));
        } catch (ParseException e) {
            task.setStart(task1.getStart());
            str1 += Constants.getErrorStart() + Constants.getEnter();
        }
        try {
            task.setEnd(OperationForTime.parseDate(this.dateEnd.getValue()
                    + Constants.getSpace() + this.timeEnd.getText()));
        } catch (ParseException e) {
            task.setEnd(task1.getEnd());
            str1 += Constants.getErrorEnd() + Constants.getEnter();
        }
        task.getCreateInterval().setIntervalYear(Integer.parseInt(this.year.getText()));
        task.getCreateInterval().setIntervalMonth(Integer.parseInt(this.month.getText()));
        task.getCreateInterval().setIntervalDay(Integer.parseInt(this.day.getText()));
        task.getCreateInterval().setIntervalHour(Integer.parseInt(this.hour.getText()));
        task.getCreateInterval().setIntervalMinute(Integer.parseInt(this.minute.getText()));
        task.getCreateInterval().setIntervalSecond(Integer.parseInt(this.second.getText()));
        if (Integer.parseInt(this.month.getText()) < Constants.getNulls()
                || Integer.parseInt(this.month.getText()) > Constants.getEleven()) {
            str1 +=  Constants.getErrorcountmonth() + Constants.getEnter();
        }
        if (Integer.parseInt(this.day.getText()) < Constants.getNulls()
                || Integer.parseInt(this.day.getText()) > Constants.getTwentythree()) {
            str1 += Constants.getErrorcountday() + Constants.getEnter();
        }
        if (Integer.parseInt(this.hour.getText()) < Constants.getNulls()
                || Integer.parseInt(this.hour.getText()) > Constants.getTwentythree()) {
            str1 += Constants.getErrorcounthour() + Constants.getEnter();
        }
        if (Integer.parseInt(this.minute.getText()) < Constants.getNulls()
                || Integer.parseInt(this.minute.getText()) > Constants.getFiftynine()) {
            str1 += Constants.getErrorcountminute() + Constants.getEnter();
        }
        if (Integer.parseInt(this.second.getText()) < Constants.getNulls()
                || Integer.parseInt(this.second.getText()) > Constants.getFiftynine()) {
            str1 += Constants.getErrorcountsecond() + Constants.getEnter();
        }
        return str1;
    }

    /**
     * Метод changeNoRepeatedTask(Task task, Task task1).
     * Изменение неповторяющейся задачи.
     * @param task изменяющаяся задача.
     * @param task1 при неправ. данных.
     * @return сообщение об ошибке.
     * @throws ParseException преобразование дат.
     */

    private String changeNoRepeatedTask(final Task task, final Task task1)
            throws ParseException {
        String str = "";
        task.setTitle(this.taskNameField.getText());
        task.setActive(this.activeTrue.isSelected());
        try {
            task.setTime(OperationForTime.parseDate(this.dateStart.getValue()
                    + Constants.getSpace() + this.timeStart.getText()));
        } catch (ParseException e) {
            str += Constants.getErrorTime() + Constants.getEnter();
            task.setTime(task1.getStart());
        }
        return str;
    }

    /**
     * Метод changeTask().
     * Изменение задачи.
     * @throws ParseException преобразование дат.
     * @throws CloneNotSupportedException при клонировании.
     */

    public void changeTask() throws ParseException, CloneNotSupportedException {
        if (this.getObs().getObs().size() != Constants.getNulls()) {
            this.showDetailsForChange(
                    this.taskTable.getSelectionModel().getSelectedItem());
            this.gridChange.setVisible(true);
            this.gridView.setVisible(false);
            this.change.setVisible(false);
            this.apply.setVisible(true);
        }
    }
}
