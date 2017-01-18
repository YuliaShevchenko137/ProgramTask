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
        this.getTimeEnd().setPromptText(Constants.getFormatTime());
        this.dateStart.setValue(LocalDate.now());
        this.getDateEnd().setValue(LocalDate.now());
        final ToggleGroup group = new ToggleGroup();
        this.activeTrue.setToggleGroup(group);
        this.activeFalse.setToggleGroup(group);
        this.getYear().setText(String.valueOf(Constants.getNulls()));
        this.getMonth().setText(String.valueOf(Constants.getNulls()));
        this.getDay().setText(String.valueOf(Constants.getNulls()));
        this.getHour().setText(String.valueOf(Constants.getNulls()));
        this.getMinute().setText(String.valueOf(Constants.getNulls()));
        this.getSecond().setText(String.valueOf(Constants.getNulls()));
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
        str = this.getDateEnd().getValue().toString() + Constants.getSpace() + this.getTimeEnd().getText();
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
        int intervalYear = Integer.parseInt(this.getYear().getText());
        int intervalMonth = Integer.parseInt(this.getMonth().getText());
        int intervalDay = Integer.parseInt(this.getDay().getText());
        int intervalHour = Integer.parseInt(this.getHour().getText());
        int intervalMinute = Integer.parseInt(this.getMinute().getText());
        int intervalSecond = Integer.parseInt(this.getSecond().getText());
        if (intervalMonth < Constants.getNulls() || intervalMonth > Constants.getEleven()) {
            logger.warn(Constants.getErrorcountmonth());
            str1 += Constants.getErrorcountmonth() + " \n";
        }
        if (intervalDay < Constants.getNulls() || intervalDay > Constants.getTwentynine()) {
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
        if(this.getCheckboxrepeated().isSelected()) {
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
        this.getTimeEnd().setText("");
        this.getYear().setText(String.valueOf(Constants.getNulls()));
        this.getMonth().setText(String.valueOf(Constants.getNulls()));
        this.getDay().setText(String.valueOf(Constants.getNulls()));
        this.getHour().setText(String.valueOf(Constants.getNulls()));
        this.getMinute().setText(String.valueOf(Constants.getNulls()));
        this.getSecond().setText(String.valueOf(Constants.getNulls()));
        this.error.setText("");
        this.dateStart.setValue(LocalDate.now());
        this.getDateEnd().setValue(LocalDate.now());
        stage.close();
    }

    public void repeated() {
        new InfoClass(this);
    }

    public TextField getTimeEnd() {
        return timeEnd;
    }

    public TextField getYear() {
        return year;
    }

    public TextField getMonth() {
        return month;
    }

    public TextField getDay() {
        return day;
    }

    public TextField getHour() {
        return hour;
    }

    public TextField getMinute() {
        return minute;
    }

    public TextField getSecond() {
        return second;
    }

    public DatePicker getDateEnd() {
        return dateEnd;
    }

    public Label getLabelStart() {
        return labelStart;
    }

    public Label getLabelEnd() {
        return labelEnd;
    }

    public Label getLabelInterval() {
        return labelInterval;
    }

    public Label getLabelYear() {
        return labelYear;
    }

    public Label getLabelMonth() {
        return labelMonth;
    }

    public Label getLabelDay() {
        return labelDay;
    }

    public Label getLabelHour() {
        return labelHour;
    }

    public Label getLabelMinute() {
        return labelMinute;
    }

    public Label getLabelSecond() {
        return labelSecond;
    }

    public CheckBox getCheckboxrepeated() {
        return checkboxrepeated;
    }
}
