package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

public class MainController {

    private CollectionsTasks obs;

    @FXML
    private TableView<Task> taskTable;

    @FXML
    private TableColumn<Task, String> taskName;
    @FXML
    private TableColumn<Task, Boolean> taskActive;

    @FXML
    private Label labelSize;
    @FXML
    private Label currentTaskName;
    @FXML
    private Label currentTaskStart;
    @FXML
    private Label currentTaskEnd;
    @FXML
    private Label currentTaskInterval;
    @FXML
    private Label currentTaskActive;
    @FXML
    private Label error;
    @FXML
    private Label labelStart;
    @FXML
    private Label labelEnd;
    @FXML
    private Label labelInterval;
    @FXML
    private Label labelYear;
    @FXML
    private Label labelMonth;
    @FXML
    private Label labelDay;
    @FXML
    private Label labelHour;
    @FXML
    private Label labelMinute;
    @FXML
    private Label labelSecond;

    @FXML
    private Button change;
    @FXML
    private Button apply;

    @FXML
    private GridPane gridView;
    @FXML
    private GridPane gridChange;

    @FXML
    private TextField taskNameField;
    @FXML
    private TextField timeStart;
    @FXML
    private TextField timeEnd;
    @FXML
    private TextField year;
    @FXML
    private TextField month;
    @FXML
    private TextField day;
    @FXML
    private TextField hour;
    @FXML
    private TextField minute;
    @FXML
    private TextField second;
    @FXML
    private TextField calendarTimeStart;
    @FXML
    private TextField calendarTimeEnd;

    @FXML
    private DatePicker dateStart;
    @FXML
    private DatePicker dateEnd;
    @FXML
    private DatePicker calendarDateEnd;
    @FXML
    private DatePicker calendarDateStart;

    @FXML
    private RadioButton activeTrue;
    @FXML
    private RadioButton activeFalse;

    @FXML
    private CheckBox checkboxrepeated;

    private int countChanges = -1;

    @FXML
    public void initialize() throws ParseException, IOException, ClassNotFoundException {
        obs= new CollectionsTasks();
        TaskList tasks = new ArrayTaskList();
        TaskIO.readText(tasks, new File("temp.txt"));
        getObs().setTasks(tasks);
        this.taskTable.setItems(getObs().getObs());
        this.taskName.setCellValueFactory(new PropertyValueFactory<>("title"));
        this.taskActive.setCellValueFactory(new PropertyValueFactory<>("active"));
        final ToggleGroup group = new ToggleGroup();
        this.activeTrue.setToggleGroup(group);
        this.activeFalse.setToggleGroup(group);
        this.taskTable.setItems(this.getObs().getObs());
        this.taskTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldValue, newValue) -> showTaskDetails(newValue));
        this.gridChange.setVisible(false);
        this.gridView.setVisible(true);
        showNoting();
        this.apply.setVisible(false);
        this.apply.setText("Применить");
        this.change.setText("Изменить");
        this.labelSize.setText("Количество задач: " + getObs().getObs().size());
    }

    private void showNoting(){
        this.currentTaskName.setText("");
        this.currentTaskStart.setText("");
        this.currentTaskEnd.setText("");
        this.currentTaskInterval.setText("");
        this.currentTaskActive.setText("");
    }

    private void showTaskDetails(Task task) {
        if (task != null) {
            this.currentTaskName.setText(task.getTitle());
            this.currentTaskStart.setText(String.valueOf(task.getStart()));
            this.currentTaskEnd.setText(String.valueOf(task.getEnd()));
            this.currentTaskInterval.setText(task.getInterval());
            this.currentTaskActive.setText(String.valueOf(task.isActive()));
        } else{
            showNoting();
        }
    }

    private LocalDate dateToLocalDate(Date date){
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Instant instant = date.toInstant();
        return instant.atZone(defaultZoneId).toLocalDate();
    }

    private void showDetailsForChange(Task task) {
        this.taskNameField.setText(task.getTitle());
        String str = String.valueOf(task.getStart());
        String[] words = str.split("[ \\t\\n]+");
        this.dateStart.setValue(dateToLocalDate(task.getStart()));
        this.timeStart.setText(words[3]);
        this.dateEnd.setValue(dateToLocalDate(task.getEnd()));
        str = String.valueOf(task.getStart());
        words = str.split("[ \\t\\n]+");
        this.timeEnd.setText(words[3]);
        final ToggleGroup group = new ToggleGroup();
        activeTrue.setToggleGroup(group);
        activeFalse.setToggleGroup(group);
        this.activeTrue.setSelected(task.isActive());
        this.activeFalse.setSelected(!task.isActive());
        this.checkboxrepeated.setSelected(task.isRepeated());
        this.year.setText(String.valueOf(task.getIntervalYear()));
        this.month.setText(String.valueOf(task.getIntervalMonth()));
        this.day.setText(String.valueOf(task.getIntervalDay()));
        this.minute.setText(String.valueOf(task.getIntervalMinute()));
        this.hour.setText(String.valueOf(task.getIntervalHour()));
        this.second.setText(String.valueOf(task.getIntervalSecond()));
        task.getInterval();
        this.checkboxrepeated.setSelected(task.isRepeated());
        if(!task.isRepeated()){
            this.labelStart.setText("Время");
            visibleObj(false);
        } else{
            this.labelStart.setText("Начало");
            visibleObj(true);
        }
    }

    private void visibleObj(boolean bool) {
        this.labelEnd.setVisible(bool);
        this.dateEnd.setVisible(bool);
        this.timeEnd.setVisible(bool);
        this.labelInterval.setVisible(bool);
        this.year.setVisible(bool);
        this.month.setVisible(bool);
        this.day.setVisible(bool);
        this.hour.setVisible(bool);
        this.minute.setVisible(bool);
        this.second.setVisible(bool);
        this.labelYear.setVisible(bool);
        this.labelMonth.setVisible(bool);
        this.labelDay.setVisible(bool);
        this.labelHour.setVisible(bool);
        this.labelMinute.setVisible(bool);
        this.labelSecond.setVisible(bool);
    }

    public void add(ActionEvent actionEvent) throws IOException, ParseException {
        Stage addStage = new Stage();
        FXMLLoader addfxmlLoader = new FXMLLoader();
        addfxmlLoader.setLocation(getClass().getResource("../view/add.fxml"));
        Parent root = addfxmlLoader.load();
        AddController addController = addfxmlLoader.getController();
        addStage.setTitle("Добавление задачи");
        int minHeight = 450;
        int minWidth = 350;
        addStage.setMinHeight(minHeight);
        addStage.setMinWidth(minWidth);
        addStage.setResizable(false);
        addStage.setScene(new Scene(root));
        addStage.initModality(Modality.WINDOW_MODAL);
        addStage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        addStage.showAndWait();
        if(addController.bool){
           getObs().add(addController.T);
           this.taskTable.setItems(this.getObs().getObs());
           this.labelSize.setText("Количество задач: " + getObs().getObs().size());
           TaskIO.writeText(getObs().getTasks(), new File("temp.txt"));
        }
    }

    public void remove() throws IOException {
        Task t = this.taskTable.getSelectionModel().getSelectedItem();
        if (t == null){
            return;
        }
        Alert delete = new Alert(Alert.AlertType.CONFIRMATION);
        delete.setTitle("Удаление");
        delete.setHeaderText("Вы уверены?");
        Optional<ButtonType> result = delete.showAndWait();
        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            t.getThreadTask().stop();
            getObs().remove(t);
            TaskIO.writeText(getObs().getTasks(), new File("temp.txt"));
            this.labelSize.setText("Количество задач: " + getObs().getObs().size());
        }
    }

    public void calendar(ActionEvent actionEvent) throws IOException, ClassNotFoundException, ParseException {
        LocalDate dateStart = this.calendarDateStart.getValue();
        LocalDate dateEnd = this.calendarDateEnd.getValue();
        String timestart = this.calendarTimeStart.getText();
        String timeend = this.calendarTimeEnd.getText();
        if(dateStart == null || dateEnd == null ||
                "".equals(timestart) || "".equals(timeend)) return;
        Stage calendarStage = new Stage();
        FXMLLoader calendarfxmlLoader = new FXMLLoader();
        calendarfxmlLoader.setLocation(getClass().getResource("../view/calendar.fxml"));
        Parent root = calendarfxmlLoader.load();
        CalendarController calendarController = calendarfxmlLoader.getController();
        calendarStage.setTitle("Календарь");
        calendarStage.setMaxHeight(450);
        calendarStage.setMinWidth(350);
        calendarStage.setScene(new Scene(root));
        calendarStage.initModality(Modality.WINDOW_MODAL);
        calendarStage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        Date dateSt = TaskIO.parseDate(dateStart + " " + timestart);
        Date dateEn = TaskIO.parseDate(dateEnd + " " + timeend);
        Map<Date, java.util.Set<Task>> maps = Tasks.calendar(this.getObs().getObs(), dateSt, dateEn);
        calendarController.setMaps(maps);
        calendarController.fillingTable(maps);
        calendarStage.showAndWait();
    }

    private CollectionsTasks getObs() {
        return obs;
    }

    public void repeated() {
        if (!this.checkboxrepeated.isSelected()){
            this.labelStart.setText("Время");
            visibleObj(false);
        } else{
            this.labelStart.setText("Начало");
            visibleObj(true);
        }
    }

    public void applyChanges() throws CloneNotSupportedException, ParseException, IOException {
        if(this.countChanges == -1){
            this.countChanges = this.taskTable.getSelectionModel().getSelectedIndex();
        }
        Task task = this.getObs().getObs().get(this.countChanges);
        Task task1 = task.clone();
        String str1 = "";
        this.getObs().getObs().remove(task);
        if(this.checkboxrepeated.isSelected()) {
            changeRepeatedTask(task, task1);
        } else{
            str1 = changeNoRepeatedTask(task, task1);
        }
        taskTable.setItems(this.getObs().getObs());
        if("".equals(str1)){
            this.gridChange.setVisible(false);
            this.gridView.setVisible(true);
            task.getInterval();
            this.getObs().getObs().add(task);
            TaskIO.writeText(getObs().getTasks(), new File("temp.txt"));
            this.countChanges = -1;
            this.error.setText("");
        } else {
            this.error.setVisible(true);
            this.getObs().getObs().add(task1);
            this.error.setText(str1);
        }
    }

    private String changeRepeatedTask(Task task, Task task1) {
        String str1 = "";
        task.setTitle(this.taskNameField.getText());
        task.setActive(this.activeTrue.isSelected());
        try{
            task.setStart(TaskIO.parseDate(this.dateStart.getValue() + " " + this.timeStart.getText()));
        } catch (ParseException e){
            task.setStart(task1.getStart());
            str1 += "Неверное время начала \n";
        }
        try{
            task.setEnd(TaskIO.parseDate(this.dateEnd.getValue() + " " + this.timeEnd.getText()));
        } catch(ParseException e) {
            task.setEnd(task1.getEnd());
            str1 += "Неверное время конца \n";
        }
        task.setIntervalYear(Integer.parseInt(this.year.getText()));
        task.setIntervalMonth(Integer.parseInt(this.month.getText()));
        task.setIntervalDay(Integer.parseInt(this.day.getText()));
        task.setIntervalHour(Integer.parseInt(this.hour.getText()));
        task.setIntervalMinute(Integer.parseInt(this.minute.getText()));
        task.setIntervalSecond(Integer.parseInt(this.second.getText()));
        if(Integer.parseInt(this.month.getText()) < 0 || Integer.parseInt(this.month.getText()) >11) {
            str1 += "Неверно указано количество месяцев \n";
        }
        if(Integer.parseInt(this.day.getText())<0 || Integer.parseInt(this.day.getText()) >29) {
            str1 += "Неверно указано количество дней \n";
        }
        if(Integer.parseInt(this.hour.getText())<0 || Integer.parseInt(this.hour.getText()) >23) {
            str1 += "Неверно указано количество часов \n";
        }
        if(Integer.parseInt(this.minute.getText()) <0 || Integer.parseInt(this.minute.getText()) >59) {
            str1 += "Неверно указано количество минут \n";
        }
        if(Integer.parseInt(this.second.getText())<0 || Integer.parseInt(this.second.getText()) >59) {
            str1 += "Неверно указано количество секунд \n";
        }
        return str1;
    }

    private String changeNoRepeatedTask(Task task, Task task1) throws ParseException {
        String str = "";
        task.setTitle(this.taskNameField.getText());
        task.setActive(this.activeTrue.isSelected());
        try {
            task.setTime(TaskIO.parseDate(this.dateStart.getValue() + " " + this.timeStart.getText()));
        } catch (ParseException e){
            str += "Неверное время \n";
            task.setTime(task1.getStart());
        }
        return str;
    }

    public void changeTask() throws ParseException, CloneNotSupportedException {
        if (this.getObs().getObs().size() != 0) {
            showDetailsForChange(taskTable.getSelectionModel().getSelectedItem());
            this.gridChange.setVisible(true);
            this.gridView.setVisible(false);
            this.change.setVisible(false);
            this.apply.setVisible(true);
        }
    }
}
