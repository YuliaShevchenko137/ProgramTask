package com.netcracker.java.YuliaShevchenko.lab1.controllers;

import com.netcracker.java.YuliaShevchenko.lab1.model.Constants;
import com.netcracker.java.YuliaShevchenko.lab1.model.CreateInterval;
import com.netcracker.java.YuliaShevchenko.lab1.model.OperationForTime;
import com.netcracker.java.YuliaShevchenko.lab1.model.Task;
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
        this.timeStart.setPromptText(Constants.getFormatTime());
        this.timeEnd.setPromptText(Constants.getFormatTime());
        this.dateStart.setValue(LocalDate.now());
        this.dateEnd.setValue(LocalDate.now());
        final ToggleGroup group = new ToggleGroup();
        this.activeTrue.setToggleGroup(group);
        this.activeFalse.setToggleGroup(group);
        this.year.setText(String.valueOf(Constants.getNulls()));
        this.month.setText(String.valueOf(Constants.getNulls()));
        this.day.setText(String.valueOf(Constants.getNulls()));
        this.hour.setText(String.valueOf(Constants.getNulls()));
        this.minute.setText(String.valueOf(Constants.getNulls()));
        this.second.setText(String.valueOf(Constants.getNulls()));
    }

    private Task createRepeatedTask() throws ParseException {
        String title = this.taskName.getText();
        String str = this.dateStart.getValue().toString() + Constants.getSpace() + this.timeStart.getText();
        Date start;
        try {
            start = OperationForTime.parseDate(str);
        } catch(ParseException e) {
            logger.error(Constants.getErrorStart(), e);
            str1 += Constants.getErrorStart() + Constants.getEnter();
            start = new Date(Constants.getNulls());
        }
        Date end;
        str = this.dateEnd.getValue().toString() + Constants.getSpace() + this.timeEnd.getText();
        try {
            end = OperationForTime.parseDate(str);
        } catch (ParseException e) {
            logger.error(Constants.getErrorEnd(), e);
            str1 += Constants.getErrorEnd() + Constants.getEnter();
            end = new Date(Constants.getNulls());
        }
        if (end.before(start) || end.equals(start)) {
            logger.warn(Constants.getErrorEarlierTime());
            str1 += Constants.getErrorEarlierTime() + Constants.getEnter();
        }
        int intervalYear = Integer.parseInt(this.year.getText());
        int intervalMonth = Integer.parseInt(this.month.getText());
        int intervalDay = Integer.parseInt(this.day.getText());
        int intervalHour = Integer.parseInt(this.hour.getText());
        int intervalMinute = Integer.parseInt(this.minute.getText());
        int intervalSecond = Integer.parseInt(this.second.getText());
        if (intervalMonth < Constants.getNulls() || intervalMonth > Constants.getEleven()) {
            logger.warn(Constants.getErrorcountmonth());
            str1 += Constants.getErrorcountmonth() + " \n";
        }
        if (intervalDay < Constants.getNulls() || intervalDay > Constants.getTwentythree()) {
            logger.warn(Constants.getErrorcountday());
            str1 += Constants.getErrorcountday() + " \n";
        }
        if (intervalHour < Constants.getNulls() || intervalHour > Constants.getTwentythree()) {
            logger.warn(Constants.getErrorcounthour());
            str1 += Constants.getErrorcounthour() + " \n";
        }
        if (intervalMinute < Constants.getNulls() || intervalMinute > Constants.getFiftynine()) {
            logger.warn(Constants.getErrorcountminute());
            str1 += Constants.getErrorcountminute() + " \n";
        }
        if (intervalSecond < Constants.getNulls() || intervalSecond > Constants.getFiftynine()) {
            logger.warn(Constants.getErrorcountsecond());
            str1 += Constants.getErrorcountsecond() + " \n";
        }
        if (intervalYear == Constants.getNulls() && intervalMonth == Constants.getNulls()
                && intervalDay == Constants.getNulls() && intervalHour == Constants.getNulls()
                && intervalMinute == Constants.getNulls() && intervalSecond == Constants.getNulls()) {
            logger.warn(Constants.getErrorinterval());
            str1 += Constants.getErrorinterval();
        }
        if ("".equals(str1)) {
            CreateInterval interval = new CreateInterval(intervalYear, intervalMonth, intervalDay, intervalHour, intervalMinute, intervalSecond);
            Task task = new Task(title, start, end, interval);
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
        try {
            start = OperationForTime.parseDate(str);
        } catch(ParseException e) {
            logger.error(e.getMessage(), e);
            str1+=Constants.getErrorTime() + Constants.getEnter();
            start = new Date(Constants.getNulls());
        }
        if ("".equals(str1)) {
            Task task = new Task(title, start);
            task.setRepeated(false);
            task.getInterval();
            task.setActive(this.activeTrue.isSelected());
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
        this.year.setText(String.valueOf(Constants.getNulls()));
        this.month.setText(String.valueOf(Constants.getNulls()));
        this.day.setText(String.valueOf(Constants.getNulls()));
        this.hour.setText(String.valueOf(Constants.getNulls()));
        this.minute.setText(String.valueOf(Constants.getNulls()));
        this.second.setText(String.valueOf(Constants.getNulls()));
        this.error.setText("");
        this.dateStart.setValue(LocalDate.now());
        this.dateEnd.setValue(LocalDate.now());
        stage.close();
    }

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
}
