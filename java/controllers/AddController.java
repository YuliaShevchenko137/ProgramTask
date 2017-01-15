package controllers;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import model.OperationForTime;
import model.Task;
import org.apache.log4j.Logger;


public class AddController {

    @FXML
    private TextField taskName;
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
    private DatePicker dateStart;
    @FXML
    private DatePicker dateEnd;

    @FXML
    private RadioButton activeTrue;
    @FXML
    private RadioButton activeFalse;

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
    private CheckBox checkboxrepeated;

    private static final Logger logger = Logger.getLogger(AddController.class);

    private String str1 = "";

    Task T;

    boolean bool = false;

    @FXML
    public void initialize() {
        this.timeStart.setPromptText("HH:mm:ss");
        this.timeEnd.setPromptText("HH:mm:ss");
        this.dateStart.setValue(LocalDate.now());
        this.dateEnd.setValue(LocalDate.now());
        final ToggleGroup group = new ToggleGroup();
        this.activeTrue.setToggleGroup(group);
        this.activeFalse.setToggleGroup(group);
        this.year.setText("0");
        this.month.setText("0");
        this.day.setText("0");
        this.hour.setText("0");
        this.minute.setText("0");
        this.second.setText("0");
    }

    private Task createRepeatedTask() throws ParseException {
        String title = this.taskName.getText();
        String str = this.dateStart.getValue().toString() + " " + this.timeStart.getText();
        Date start;
        try {
            start = OperationForTime.parseDate(str);
        }
        catch(ParseException e){
            logger.error(e.getMessage(), e);
            if(this.checkboxrepeated.isSelected()) str1+="Неправильно введено время начала задачи\n";
            else str1+="Неправильно введено время задачи\n";
            start = new Date(0);
        }
        Date end;
        str = this.dateEnd.getValue().toString() + " " + this.timeEnd.getText();
        try {
            end = OperationForTime.parseDate(str);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            str1 += "Неправильно введено время конца задачи\n";
            end = new Date(0);
        }
        if (end.before(start) || end.equals(start)) {
            logger.warn("end.before(start)");
            str1 += "Конец выполнения задачи перед его началом или они соврадают\n";
        }
        int intervalYear = Integer.parseInt(this.year.getText());
        int intervalMonth = Integer.parseInt(this.month.getText());
        int intervalDay = Integer.parseInt(this.day.getText());
        int intervalHour = Integer.parseInt(this.hour.getText());
        int intervalMinute = Integer.parseInt(this.minute.getText());
        int intervalSecond = Integer.parseInt(this.second.getText());
        if (intervalMonth<0 || intervalMonth >11) {
            logger.warn("intervalMonth<0 || intervalMonth >11");
            str1 += "Неверно указано количество месяцев \n";
        }
        if (intervalDay<0 || intervalDay >29) {
            logger.warn("intervalDay<0 || intervalDay >29");
            str1 += "Неверно указано количество дней \n";
        }
        if (intervalHour<0 || intervalHour >23) {
            logger.warn("intervalHour<0 || intervalHour >23");
            str1 += "Неверно указано количество часов \n";
        }
        if (intervalMinute<0 || intervalMinute >59) {
            logger.warn("intervalMinute<0 || intervalMinute >59");
            str1 += "Неверно указано количество минут \n";
        }
        if (intervalSecond<0 || intervalSecond >59) {
            logger.warn("intervalSecond<0 || intervalSecond >59");
            str1 += "Неверно указано количество секунд \n";
        }
        if("".equals(str1)) {
            Task task = new Task(title, start, end, intervalYear, intervalMonth, intervalDay, intervalHour, intervalMinute, intervalSecond);
            task.setRepeated(true);
            task.getInterval();
            if (this.activeTrue.isSelected()) task.setActive(true);
            return task;
        } else {
            return null;
        }
    }

    private Task createNoRepeatedTask() throws ParseException {
        String title = this.taskName.getText();
        String str = this.dateStart.getValue().toString() + " " + this.timeStart.getText();
        Date start;
        try{
            start = OperationForTime.parseDate(str);
        }
        catch(ParseException e){
            logger.error(e.getMessage(), e);
            if(this.checkboxrepeated.isSelected()) str1+="Неправильно введено время начала задачи\n";
            else str1+="Неправильно введено время задачи\n";
            start = new Date(0);
        }
        if("".equals(str1)) {
            Task task = new Task(title, start);
            task.setRepeated(false);
            task.getInterval();
            if (this.activeTrue.isSelected()) task.setActive(true);
            return task;
        } else {
            return null;
        }
    }

    private Task createTask() throws ParseException {
        logger.debug("add task\n");
        if(this.checkboxrepeated.isSelected()) {
            return createRepeatedTask();
        } else {
            return createNoRepeatedTask();
        }
    }

    public void actionOk(ActionEvent actionEvent) throws ParseException {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        T=createTask();
        if("".equals(str1)) {
            bool = true;
            stage.close();
        }
        else {
            error.setText(str1);
            str1= "";
        }
    }

    public void actionCancel(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        this.taskName.setText("");
        this.timeStart.setText("");
        this.timeEnd.setText("");
        this.year.setText("0");
        this.month.setText("0");
        this.day.setText("0");
        this.hour.setText("0");
        this.minute.setText("0");
        this.second.setText("0");
        this.error.setText("");
        this.dateStart.setValue(LocalDate.now());
        this.dateEnd.setValue(LocalDate.now());
        stage.close();
    }

    public void repeated() {
        if (!this.checkboxrepeated.isSelected()){
            this.labelStart.setText("Время");
            info.visibleObj(false, this.labelEnd, this.dateEnd,
                    this.timeEnd, this.labelInterval,
                    this.year, this.month, this.day,
                    this.hour, this.minute, this.second,
                    this.labelYear, this.labelMonth, this.labelDay,
                    this.labelHour, this.labelMinute, this.labelSecond);
        } else{
            this.labelStart.setText("Начало");
            info.visibleObj(true, this.labelEnd, this.dateEnd,
                    this.timeEnd, this.labelInterval,
                    this.year, this.month, this.day,
                    this.hour, this.minute, this.second,
                    this.labelYear, this.labelMonth, this.labelDay,
                    this.labelHour, this.labelMinute, this.labelSecond);
        }
    }
}
