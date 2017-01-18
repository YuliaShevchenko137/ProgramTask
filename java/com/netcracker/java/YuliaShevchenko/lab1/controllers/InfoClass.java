package com.netcracker.java.YuliaShevchenko.lab1.controllers;

import com.netcracker.java.YuliaShevchenko.lab1.model.Constants;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Class InfoClass.
 * Use to show / hide details for changing.
 */

public final class InfoClass {

    /**
     * repeated.
     * Repeated Task.
     */

    private CheckBox repeated;

    /**
     * labelEnd.
     * Label End
     */

    private Label labelEnd;

    /**
     * labelInterval.
     * Label Interval.
     */

    private Label labelInterval;

    /**
     * labelYear.
     * Label years.
     */

    private Label labelYear;

    /**
     * labelMonth.
     * Label months.
     */

    private Label labelMonth;

    /**
     * labelDay.
     * Label days.
     */

    private Label labelDay;

    /**
     * labelHour.
     * Label hours.
     */

    private Label labelHour;

    /**
     * labelMinute.
     * Label minutes.
     */

    private Label labelMinute;

    /**
     * labelSecond.
     * Label seconds.
     */

    private Label labelSecond;

    /**
     * timeEnd.
     * End time of the changing task.
     */

    private TextField timeEnd;

    /**
     * year.
     * Years in interval repeating.
     */

    private TextField year;

    /**
     * month.
     * Months in interval repeating.
     */

    private TextField month;

    /**
     * day.
     * Days in interval repeating
     */

    private TextField day;

    /**
     * hour.
     * Hours in interval repeating.
     */

    private TextField hour;

    /**
     * minute.
     * Minutes in interval repeating.
     */

    private TextField minute;

    /**
     * second.
     * Years in interval repeating.
     */

    private TextField second;

    /**
     * dateEnd.
     * End date of the changing task.
     */

    private DatePicker dateEnd;

    /**
     * labelStart.
     * Label start.
     */

    private Label labelStart;

    /**
     * Constructor InfoClass().
     * @param addController controller Add window.
     */

    public InfoClass(final AddController addController) {
        this.setTextField(addController.getYear(), addController.getMonth(),
                addController.getDay(), addController.getHour(),
                addController.getMinute(), addController.getSecond());
        this.setLabelsInterval(addController.getLabelYear(),
                addController.getLabelMonth(), addController.getLabelDay(),
                addController.getLabelHour(), addController.getLabelMinute(),
                addController.getLabelSecond());
        this.setObj(addController.getLabelEnd(), addController.getDateEnd(),
                addController.getTimeEnd(), addController.getLabelInterval(),
                addController.getLabelStart());
        this.repeated(addController.getCheckboxrepeated());
    }

    /**
     * Constructor InfoClass().
     * @param mainController controller main window.
     */

    public InfoClass(final MainController mainController) {
        this.setTextField(mainController.getYear(), mainController.getMonth(),
                mainController.getDay(), mainController.getHour(),
                mainController.getMinute(), mainController.getSecond());
        this.setLabelsInterval(mainController.getLabelYear(),
                mainController.getLabelMonth(), mainController.getLabelDay(),
                mainController.getLabelHour(), mainController.getLabelMinute(),
                mainController.getLabelSecond());
        this.setObj(mainController.getLabelEnd(), mainController.getDateEnd(),
                mainController.getTimeEnd(), mainController.getLabelInterval(),
                mainController.getLabelStart());
        this.repeated(mainController.getCheckboxrepeated());
    }

    /**
     * Method setTextField(final TextField years, final TextField months,
     * final TextField days, final TextField hours,
     * final TextField minutes, final TextField seconds).
     * Set TextField of the main scene.
     * @param years TextField year.
     * @param months years TextField month.
     * @param days years TextField day.
     * @param hours years TextField hours.
     * @param minutes years TextField minutes.
     * @param seconds years TextField seconds.
     */

    public void setTextField(final TextField years,
                             final TextField months,
                             final TextField days,
                             final TextField hours,
                             final TextField minutes,
                             final TextField seconds) {
        this.year = years;
        this.month = months;
        this.day = days;
        this.hour = hours;
        this.minute = minutes;
        this.second = seconds;
    }

    /**
     * Method setLabelsInterval(final Label labelYears, final Label labelMonths,
     * final Label labelDays, final Label labelHours,
     * final Label labelMinutes, final Label labelSeconds).
     * Set labels of the main scene.
     * @param labelYears label years.
     * @param labelMonths label months.
     * @param labelDays label days.
     * @param labelHours label hours.
     * @param labelMinutes label minutes.
     * @param labelSeconds label seconds.
     */

    public void setLabelsInterval(final Label labelYears,
                                  final Label labelMonths,
                                  final Label labelDays,
                                  final Label labelHours,
                                  final Label labelMinutes,
                                  final Label labelSeconds) {
        this.labelYear = labelYears;
        this.labelMonth = labelMonths;
        this.labelDay = labelDays;
        this.labelHour = labelHours;
        this.labelMinute = labelMinutes;
        this.labelSecond = labelSeconds;
    }

    /**
     * Method setObj( final Label labelEnds, final DatePicker dateEnds,
     * final TextField timeEnds, final Label labelIntervals,
     * final Label labelStarts).
     * Set labelEnd, date and time end and labelInterval.
     * @param labelEnds Label End.
     * @param dateEnds DatePicker End date.
     * @param timeEnds TextField End time.
     * @param labelIntervals Label Interval.
     * @param labelStarts label Start / Time.
     */

    public void setObj(final Label labelEnds,
                       final DatePicker dateEnds,
                       final TextField timeEnds,
                       final Label labelIntervals,
                       final Label labelStarts) {
        this.labelEnd = labelEnds;
        this.dateEnd = dateEnds;
        this.timeEnd = timeEnds;
        this.labelInterval = labelIntervals;
        this.labelStart = labelStarts;
    }

    /**
     * Method repeated().
     * Show / hide elements.
     */

    private void repeated(final CheckBox repeated) {
        if (!repeated.isSelected()) {
            this.labelStart.setText(Constants.getTime());
            this.visibleObj(false);
            this.visibleTextFieldInterval(false);
            this.visibleLabelsInterval(false);
        } else {
            this.labelStart.setText(Constants.getStart());
            this.visibleObj(true);
            this.visibleTextFieldInterval(true);
            this.visibleLabelsInterval(true);
        }
    }

    /**
     * Method visibleObj(boolean bool, Label labelEnd, DatePicker dateEnd,
     * TextField timeEnd, Label labelInterval).
     * Show / Hide end date, time, label End and label Interval.
     * @param bool show / hide.
     */

    private void visibleObj(final boolean bool) {
        this.labelEnd.setVisible(bool);
        this.dateEnd.setVisible(bool);
        this.timeEnd.setVisible(bool);
        this.labelInterval.setVisible(bool);

    }

    /**
     * Method visibleTextFieldInterval(boolean bool, TextField year,
     * TextField month, TextField day, TextField hour, TextField minute,
     * TextField second).
     * Show / hide text field of the part of interval.
     * @param bool show / hide.
     */

    private void visibleTextFieldInterval(final boolean bool) {
        this.year.setVisible(bool);
        this.month.setVisible(bool);
        this.day.setVisible(bool);
        this.hour.setVisible(bool);
        this.minute.setVisible(bool);
        this.second.setVisible(bool);
    }

    /**
     * Method visibleLabelsInterval(final boolean bool, Label labelYear,
     * Label labelMonth, Label labelDay, Label labelHour, Label labelMinute,
     * Label labelSecond).
     * Show / hide labels parts of the interval.
     * @param bool show / hide.
     */

    private void visibleLabelsInterval(final boolean bool) {
        this.labelYear.setVisible(bool);
        this.labelMonth.setVisible(bool);
        this.labelDay.setVisible(bool);
        this.labelHour.setVisible(bool);
        this.labelMinute.setVisible(bool);
        this.labelSecond.setVisible(bool);
    }
}
