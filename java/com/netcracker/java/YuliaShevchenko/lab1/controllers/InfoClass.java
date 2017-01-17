package com.netcracker.java.YuliaShevchenko.lab1.controllers;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Класс info.
 * Используется при переключении
 * повторяемости.
 */

public final class InfoClass {

    /**
     * Пустой конструктор InfoClass().
     */

    private InfoClass() {

    }

    /**
     * Метод visibleObj(boolean bool, Label labelEnd, DatePicker dateEnd,
     * TextField timeEnd, Label labelInterval).
     * Show / Hide end date, time, label End and label Interval.
     * @param bool show / hide.
     * @param labelEnd label End.
     * @param dateEnd end date.
     * @param timeEnd end time.
     * @param labelInterval label Interval.
     */

    public static void visibleObj(final boolean bool,
                                  final Label labelEnd,
                                  final DatePicker dateEnd,
                                  final TextField timeEnd,
                                  final Label labelInterval) {
        labelEnd.setVisible(bool);
        dateEnd.setVisible(bool);
        timeEnd.setVisible(bool);
        labelInterval.setVisible(bool);

    }

    /**
     * Method visibleTextFieldInterval(boolean bool, TextField year,
     * TextField month, TextField day, TextField hour, TextField minute,
     * TextField second).
     * Show / hide text field of the part of interval.
     * @param bool show / hide.
     * @param year text field of the years.
     * @param month text field of the months.
     * @param day text field of the days.
     * @param hour text field of the hours.
     * @param minute text field of the minutes.
     * @param second text field of the seconds.
     */

    public static void visibleTextFieldInterval(final boolean bool,
                                                final TextField year,
                                                final TextField month,
                                                final TextField day,
                                                final TextField hour,
                                                final TextField minute,
                                                final TextField second ) {
        year.setVisible(bool);
        month.setVisible(bool);
        day.setVisible(bool);
        hour.setVisible(bool);
        minute.setVisible(bool);
        second.setVisible(bool);
    }

    /**
     * Method visibleLabelsInterval(final boolean bool, Label labelYear,
     * Label labelMonth, Label labelDay, Label labelHour, Label labelMinute,
     * Label labelSecond).
     * Show / hide labels parts of the interval.
     * @param bool show / hide.
     * @param labelYear label of the years.
     * @param labelMonth label of the months.
     * @param labelDay label of the days.
     * @param labelHour label of the hours.
     * @param labelMinute label of the minutes.
     * @param labelSecond label of the seconds.
     */

    public static void visibleLabelsInterval(final boolean bool, final Label labelYear,
                                    final Label labelMonth,
                                    final Label labelDay,
                                    final Label labelHour,
                                    final Label labelMinute,
                                    final Label labelSecond) {
        labelYear.setVisible(bool);
        labelMonth.setVisible(bool);
        labelDay.setVisible(bool);
        labelHour.setVisible(bool);
        labelMinute.setVisible(bool);
        labelSecond.setVisible(bool);
    }
}
