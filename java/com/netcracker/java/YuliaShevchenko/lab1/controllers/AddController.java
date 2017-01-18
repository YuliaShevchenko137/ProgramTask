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
import javafx.stage.Stage;
import org.apache.log4j.Logger;

/**
 * Class AddController.
 * Adding windows controller.
 */

public class AddController {

    /**
     * taskName.
     * Title of the task.
     */

    @FXML
    private TextField taskName;

    /**
     * timeStart.
     * Start time of the task.
     */

    @FXML
    private TextField timeStart;

    /**
     * timeEnd.
     * End time of the task.
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
     * dateStart.
     * Start date of the task.
     */

    @FXML
    private DatePicker dateStart;

    /**
     * dateEnd.
     * End date of the task.
     */

    @FXML
    private DatePicker dateEnd;

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
     * error.
     * label error message when data incorrect.
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
     *  checkboxrepeated.
     *  CheckBox: repeated task.
     */

    @FXML
    private CheckBox checkboxrepeated;

    /**
     * logger.
     * It is used to register error.
     */

    private static final Logger logger = Logger.getLogger(AddController.class);

    /**
     * str1.
     * On the basis of this string displays an error message.
     */

    private String str1 = "";

    /**
     * T.
     * New task.
     */

    Task T;

    /**
     * bool.
     * True, if data correct and new task is created.
     */

    boolean bool = false;

    /**
     * Method initialize().
     * Executed when a controller is loaded.
     */

    @FXML
    public void initialize() {
        this.timeStart.setPromptText(Constants.getFormatTime());
        this.getTimeEnd().setPromptText(Constants.getFormatTime());
        this.dateStart.setValue(LocalDate.now());
        this.getDateEnd().setValue(LocalDate.now());
        this.startValueInterval();

    }

    /**
     * Method startValueInterval().
     * It sets the initial value of the interval component.
     */

    private void startValueInterval() {
        this.getYear().setText(String.valueOf(Constants.getNulls()));
        this.getMonth().setText(String.valueOf(Constants.getNulls()));
        this.getDay().setText(String.valueOf(Constants.getNulls()));
        this.getHour().setText(String.valueOf(Constants.getNulls()));
        this.getMinute().setText(String.valueOf(Constants.getNulls()));
        this.getSecond().setText(String.valueOf(Constants.getNulls()));
    }

    /**
     * Method createRepeatedTask().
     * Create repeated task.
     * @return new task.
     */

    private Task createRepeatedTask() {
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

    /**
     * Method createNoRepeatedTask().
     * Create non recurring task.
     * @return new task.
     */

    private Task createNoRepeatedTask() {
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

    /**
     * Method createTask().
     * If checkboxrepeated is selected, invokes the createRepeatedTask().
     * If checkboxrepeated is not selected, invokes the
     * createNoRepeatedTask().
     * @return new task.
     */

    private Task createTask() {
        logger.debug("add task\n");
        if(this.getCheckboxrepeated().isSelected()) {
            return createRepeatedTask();
        } else {
            return createNoRepeatedTask();
        }
    }

    /**
     * Method actionOk(ActionEvent actionEvent).
     * Save task, if data is correct.
     * @param actionEvent button press.
     */

    public void actionOk(ActionEvent actionEvent) {
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

    /**
     * Method actionCancel(ActionEvent actionEvent).
     * Close the window.
     * @param actionEvent button press.
     */

    public void actionCancel(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        this.taskName.setText("");
        this.timeStart.setText("");
        this.getTimeEnd().setText("");
        this.startValueInterval();
        this.error.setText("");
        this.dateStart.setValue(LocalDate.now());
        this.getDateEnd().setValue(LocalDate.now());
        stage.close();
    }

    /**
     * Method repeated().
     * Change showing window when user press CheckBox Repeated.
     */

    public void repeated() {
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
