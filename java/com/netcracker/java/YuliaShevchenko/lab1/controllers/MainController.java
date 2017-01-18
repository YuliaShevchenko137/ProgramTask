package com.netcracker.java.YuliaShevchenko.lab1.controllers;

import com.netcracker.java.YuliaShevchenko.lab1.model.ArrayTaskList;
import com.netcracker.java.YuliaShevchenko.lab1.model.CollectionsTasks;
import com.netcracker.java.YuliaShevchenko.lab1.model.Constants;
import com.netcracker.java.YuliaShevchenko.lab1.model.OperationForTime;
import com.netcracker.java.YuliaShevchenko.lab1.model.Task;
import com.netcracker.java.YuliaShevchenko.lab1.model.TaskIO;
import com.netcracker.java.YuliaShevchenko.lab1.model.Tasks;
import com.netcracker.java.YuliaShevchenko.lab1.model.ThreadTask;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Class MainController.
 * Controller of the main window.
 */

public final class MainController {

    /**
     * obs.
     * Wrapper of the task list.
     */

    private CollectionsTasks obs;

    /**
     * taskTable.
     * Table with tasks.
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
     * taskActive.
     * Column of the activity of the task.
     */

    @FXML
    private TableColumn<Task, Boolean> taskActive;

    /**
     * labelSize.
     * Label with count tasks.
     */

    @FXML
    private Label labelSize;

    /**
     * currentTaskName.
     * Title of the current task.
     */

    @FXML
    private Label currentTaskName;

    /**
     * currentTaskStart.
     * Start date of the current task.
     */

    @FXML
    private Label currentTaskStart;

    /**
     * currentTaskEnd.
     * End date on the current task.
     */

    @FXML
    private Label currentTaskEnd;

    /**
     * currentTaskInterval.
     * Repeated interval of the current task.
     */

    @FXML
    private Label currentTaskInterval;

    /**
     * currentTaskActive.
     * Activity of the current task.
     */

    @FXML
    private Label currentTaskActive;

    /**
     * error.
     * label error message when data changes.
     */

    @FXML
    private Label error;

    /**
     * labelStart.
     * Label start.
     */

    @FXML
    private Label labelStart;

    /**
     * labelEnd.
     * Label End.
     */

    @FXML
    private Label labelEnd;

    /**
     * labelInterval.
     * Label Interval.
     */

    @FXML
    private Label labelInterval;

    /**
     * labelYear.
     * Label years.
     */

    @FXML
    private Label labelYear;

    /**
     * labelMonth.
     * Label months.
     */

    @FXML
    private Label labelMonth;

    /**
     * labelDay.
     * Label days.
     */

    @FXML
    private Label labelDay;

    /**
     * labelHour.
     * Label hours.
     */

    @FXML
    private Label labelHour;

    /**
     * labelMinute.
     * Label minutes.
     */

    @FXML
    private Label labelMinute;

    /**
     * labelSecond.
     * Label seconds.
     */

    @FXML
    private Label labelSecond;

    /**
     * change.
     * Button Change.
     */

    @FXML
    private Button change;

    /**
     * apply.
     * Button Apply.
     */

    @FXML
    private Button apply;

    /**
     * gridView.
     * Table to show data.
     */

    @FXML
    private GridPane gridView;

    /**
     * gridChange.
     * Table to change data.
     */

    @FXML
    private GridPane gridChange;

    /**
     * taskNameField.
     * TextField change title of the task.
     */

    @FXML
    private TextField taskNameField;

    /**
     * timeStart.
     * Start time of the changing task.
     */

    @FXML
    private TextField timeStart;

    /**
     * timeEnd.
     * End time of the changing task.
     */

    @FXML
    private TextField timeEnd;

    /**
     * year.
     * Years in interval repeating.
     */

    @FXML
    private TextField year;

    /**
     * month.
     * Months in interval repeating.
     */

    @FXML
    private TextField month;

    /**
     * day.
     * Days in interval repeating.
     */

    @FXML
    private TextField day;

    /**
     * hour.
     * Hours in interval repeating.
     */

    @FXML
    private TextField hour;

    /**
     * minute.
     * Minutes in interval repeating.
     */

    @FXML
    private TextField minute;

    /**
     * second.
     * Years in interval repeating.
     */

    @FXML
    private TextField second;

    /**
     * calendarTimeStart.
     * Start time of the calendar.
     */

    @FXML
    private TextField calendarTimeStart;

    /**
     * calendarTimeEnd.
     * End time of the calendar.
     */

    @FXML
    private TextField calendarTimeEnd;

    /**
     * dateStart.
     * Start date of the changing task.
     */

    @FXML
    private DatePicker dateStart;

    /**
     * dateEnd.
     * End date of the changing task.
     */

    @FXML
    private DatePicker dateEnd;

    /**
     * calendarDateEnd.
     * End date of the calendar.
     */

    @FXML
    private DatePicker calendarDateEnd;

    /**
     * calendarDateStart.
     * Start date of the calendar.
     */

    @FXML
    private DatePicker calendarDateStart;

    /**
     * activeTrue.
     * RadioButton: active task.
     */

    @FXML
    private RadioButton activeTrue;

    /**
     * activeFalse.
     * RadioButton: inactive task.
     */

    @FXML
    private RadioButton activeFalse;

    /**
     * checkboxrepeated.
     *  CheckBox: repeated task.
     */

    @FXML
    private CheckBox checkboxrepeated;

    /**
     * countChanges.
     * Index selected task
     */

    private int countChanges = -1;

    /**
     * minHeight.
     * Minimal height window.
     */

    private final int minHeight = 450;

    /**
     * minWidth.
     * Minimal width window.
     */

    private final int minWidth = 350;

    /**
     * temp.
     * File for reading ot writing.
     */

    private final File temp = new File("temp.txt");

    /**
     * Empty constructor.
     */

    public MainController() {

    }

    /**
     * Method initialize().
     * Executed when a controller is loaded.
     * @throws ParseException appears when converting dates.
     * @throws IOException appears when opening a file.
     */

    @FXML
    public void initialize() throws ParseException, IOException {
        this.obs = new CollectionsTasks();
        ArrayTaskList tasks = new ArrayTaskList();
        TaskIO.readText(tasks, this.temp);
        this.obs.setTasks(tasks);
        this.taskTable.setItems(this.obs.getObs());
        this.taskName.setCellValueFactory(
                new PropertyValueFactory<>("title"));
        this.taskActive.setCellValueFactory(
                new PropertyValueFactory<>("active"));
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
        this.labelSize.setText(Constants.getCountTask()
                + this.obs.getObs().size());
    }

    /**
     * Method showNoting().
     * Called when no task is selected.
     */

    private void showNoting() {
        this.currentTaskName.setText("");
        this.currentTaskStart.setText("");
        this.currentTaskEnd.setText("");
        this.currentTaskInterval.setText("");
        this.currentTaskActive.setText("");
    }

    /**
     * Method showTaskDetails(Task task).
     * Show details of the selected task.
     * @param task selected task.
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
     * Show data fow changing task.
     * @param task task for changing.
     */

    private void showDetailsForChange(final Task task) {
        this.taskNameField.setText(task.getTitle());
        String str = String.valueOf(task.getStart());
        String[] words = str.split(Constants.getRegexp());
        this.dateStart.setValue(OperationForTime.dateToLocalDate(
                task.getStart()));
        this.timeStart.setText(words[Constants.getThree()]);
        this.getDateEnd().setValue(OperationForTime.dateToLocalDate(
                task.getEnd()));
        str = String.valueOf(task.getStart());
        words = str.split(Constants.getRegexp());
        this.getTimeEnd().setText(words[Constants.getThree()]);
        this.activeTrue.setSelected(task.isActive());
        this.activeFalse.setSelected(!task.isActive());
        this.getCheckboxrepeated().setSelected(task.isRepeated());
        this.getYear().setText(String.valueOf(
                task.getCreateInterval().getIntervalYear()));
        this.getMonth().setText(String.valueOf(
                task.getCreateInterval().getIntervalMonth()));
        this.getDay().setText(String.valueOf(
                task.getCreateInterval().getIntervalDay()));
        this.getMinute().setText(String.valueOf(
                task.getCreateInterval().getIntervalMinute()));
        this.getHour().setText(String.valueOf(
                task.getCreateInterval().getIntervalHour()));
        this.getSecond().setText(String.valueOf(
                task.getCreateInterval().getIntervalSecond()));
        task.getInterval();
        this.getCheckboxrepeated().setSelected(task.isRepeated());
        this.repeatedTask();
    }

    /**
     * Method add(ActionEvent actionEvent).
     * Create new window Add task and add new task to the task list.
     * @param actionEvent button press.
     * @throws IOException appears when opening a file.
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
        if (addController.isBool()) {
            this.obs.add(addController.getNewTask());
            this.taskTable.setItems(this.obs.getObs());
            this.labelSize.setText(Constants.getCountTask()
                    + this.obs.getObs().size());
            TaskIO.writeText(this.obs.getTasks(), this.temp);
        }
    }

    /**
     * Method remove().
     * Call notification about the removal of the problem and
     * remove it in the event of confirmation.
     * @throws IOException appears when writing a file.
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
            this.labelSize.setText(Constants.getCountTask()
                    + this.obs.getObs().size());
        }
    }

    /**
     * Method calendar(ActionEvent actionEvent).
     * Create new window Calendar for a selected interval of the time.
     * @param actionEvent button press.
     * @throws IOException appears when opening a file.
     * @throws ParseException appears when converting dates.
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
        calendarController.fillingList(maps);
        calendarStage.showAndWait();
    }

    /**
     * Method getObs().
     * Getteg for wrapper task list.
     * @return ObservableList for task list.
     */

    public CollectionsTasks getObs() {
        return this.obs;
    }

    /**
     * Method applyChanges().
     * If the data is correct, it changes the current task.
     * @throws CloneNotSupportedException if object nonclonability.
     * @throws ParseException appears when converting dates.
     * @throws IOException appears when opening a file.
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
        if (this.getCheckboxrepeated().isSelected()) {
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
     * Method changeRepeatedTask(Task task, Task task1).
     * Change repeated task.
     * @param task current task.
     * @param task1 in the case of incorrect data.
     * @return error message.
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
            task.setEnd(OperationForTime.parseDate(this.getDateEnd().getValue()
                    + Constants.getSpace() + this.getTimeEnd().getText()));
        } catch (ParseException e) {
            task.setEnd(task1.getEnd());
            str1 += Constants.getErrorEnd() + Constants.getEnter();
        }
        task.getCreateInterval().setIntervalYear(
                Integer.parseInt(this.getYear().getText()));
        task.getCreateInterval().setIntervalMonth(
                Integer.parseInt(this.getMonth().getText()));
        task.getCreateInterval().setIntervalDay(
                Integer.parseInt(this.getDay().getText()));
        task.getCreateInterval().setIntervalHour(
                Integer.parseInt(this.getHour().getText()));
        task.getCreateInterval().setIntervalMinute(
                Integer.parseInt(this.getMinute().getText()));
        task.getCreateInterval().setIntervalSecond(
                Integer.parseInt(this.getSecond().getText()));
        if (Integer.parseInt(this.getMonth().getText()) < Constants.getNulls()
                || Integer.parseInt(this.getMonth().getText())
                > Constants.getEleven()) {
            str1 +=  Constants.getErrorcountmonth() + Constants.getEnter();
        }
        if (Integer.parseInt(this.getDay().getText()) < Constants.getNulls()
                || Integer.parseInt(this.getDay().getText())
                > Constants.getTwentynine()) {
            str1 += Constants.getErrorcountday() + Constants.getEnter();
        }
        if (Integer.parseInt(this.getHour().getText()) < Constants.getNulls()
                || Integer.parseInt(this.getHour().getText())
                > Constants.getTwentythree()) {
            str1 += Constants.getErrorcounthour() + Constants.getEnter();
        }
        if (Integer.parseInt(this.getMinute().getText()) < Constants.getNulls()
                || Integer.parseInt(this.getMinute().getText())
                > Constants.getFiftynine()) {
            str1 += Constants.getErrorcountminute() + Constants.getEnter();
        }
        if (Integer.parseInt(this.getSecond().getText()) < Constants.getNulls()
                || Integer.parseInt(this.getSecond().getText())
                > Constants.getFiftynine()) {
            str1 += Constants.getErrorcountsecond() + Constants.getEnter();
        }
        return str1;
    }

    /**
     * Метод changeNoRepeatedTask(Task task, Task task1).
     * Change no recurring task.
     * @param task current task.
     * @param task1 in the case of incorrect data.
     * @return error message.
     */

    private String changeNoRepeatedTask(final Task task, final Task task1) {
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
     * Method changeTask().
     * Change task.
     * @throws CloneNotSupportedException if object non clonability.
     */

    public void changeTask() throws CloneNotSupportedException {
        if (this.getObs().getObs().size() != Constants.getNulls()) {
            this.showDetailsForChange(
                    this.taskTable.getSelectionModel().getSelectedItem());
            this.gridChange.setVisible(true);
            this.gridView.setVisible(false);
            this.change.setVisible(false);
            this.apply.setVisible(true);
        }
    }

    /**
     * Method repeatedTask().
     * Change showing window when user press CheckBox Repeated.
     */

    public void repeatedTask() {
        new InfoClass(this);
    }

    /**
     * Method getLabelStart().
     * Getter for labelStart.
     * @return Label start.
     */

    public Label getLabelStart() {
        return this.labelStart;
    }

    /**
     * Method for getLabelEnd().
     * Getter for labelEnd.
     * @return Label End.
     */

    public Label getLabelEnd() {
        return this.labelEnd;
    }

    /**
     * Method getLabelInterval().
     * Getter for labelInterval.
     * @return Label Interval.
     */

    public Label getLabelInterval() {
        return this.labelInterval;
    }

    /**
     * Method getLabelYear().
     * Getter for labelYear.
     * @return Label years.
     */

    public Label getLabelYear() {
        return this.labelYear;
    }

    /**
     * Method getLabelMonth().
     * Getter for labelMonth.
     * @return Label months.
     */

    public Label getLabelMonth() {
        return this.labelMonth;
    }

    /**
     * Method getLabelDay().
     * Getter for labelDay.
     * @return Label days.
     */

    public Label getLabelDay() {
        return this.labelDay;
    }

    /**
     * Method getLabelHour().
     * Getter for labelHour.
     * @return Label hours.
     */

    public Label getLabelHour() {
        return this.labelHour;
    }

    /**
     * Method getLabelMinute().
     * Getter for labelMinute.
     * @return Label minutes.
     */

    public Label getLabelMinute() {
        return this.labelMinute;
    }

    /**
     * Method getLabelSecond().
     * Getter for labelSecond.
     * @return Label seconds.
     */

    public Label getLabelSecond() {
        return this.labelSecond;
    }

    /**
     * Method getTimeEnd().
     * Getter for timeEnd.
     * @return TextField timeEnd.
     */

    public TextField getTimeEnd() {
        return this.timeEnd;
    }

    /**
     * Method getYear().
     * Getter for year.
     * @return TextField year.
     */

    public TextField getYear() {
        return this.year;
    }

    /**
     * Method getMonth().
     * Getter for month.
     * @return TextField month.
     */

    public TextField getMonth() {
        return this.month;
    }

    /**
     * Method getDay().
     * Getter for day.
     * @return TextField day.
     */

    public TextField getDay() {
        return this.day;
    }

    /**
     * Method getHour().
     * Getter for hour.
     * @return TextField hour.
     */

    public TextField getHour() {
        return this.hour;
    }

    /**
     * Method getMinute().
     * Getter for minute.
     * @return TextField minute.
     */

    public TextField getMinute() {
        return this.minute;
    }

    /**
     * Method getSecond().
     * Getter for second.
     * @return TextField second.
     */

    public TextField getSecond() {
        return this.second;
    }

    /**
     * Method getDateEnd().
     * Getter for dateEnd.
     * @return DatePicker dateEnd.
     */

    public DatePicker getDateEnd() {
        return this.dateEnd;
    }

    /**
     * Method getCheckboxrepeated().
     * Getter for checkboxrepeated.
     * @return CheckBox checkboxrepeated.
     */

    public CheckBox getCheckboxrepeated() {
        return this.checkboxrepeated;
    }
}
