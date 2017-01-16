package controllers;

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
     * Метод visibleObj(boolean bool, Label labelEnd,
     DatePicker dateEnd, TextField timeEnd,
     Label labelInterval, TextField year,
     TextField month, TextField day,
     TextField hour, TextField minute,
     TextField second, Label labelYear,
     Label labelMonth, Label labelDay,
     Label labelHour, Label labelMinute,
     Label labelSecond).
     * @param bool показать данные или нет.
     * @param labelEnd надпись Конец.
     * @param dateEnd дата конца.
     * @param timeEnd время конца.
     * @param labelInterval Надпись Интервал.
     * @param year год.
     * @param month месяц.
     * @param day день.
     * @param hour час.
     * @param minute минута.
     * @param second секунда.
     * @param labelYear Надпись Год.
     * @param labelMonth Надпись Месяц.
     * @param labelDay Надпись День.
     * @param labelHour Надпись Час.
     * @param labelMinute Надпись Минута.
     * @param labelSecond Надпись Секунда.
     */

    public static void visibleObj(final boolean bool,
                                  final Label labelEnd,
                                  final DatePicker dateEnd,
                                  final TextField timeEnd,
                                  final Label labelInterval,
                                  final TextField year,
                                  final TextField month,
                                  final TextField day,
                                  final TextField hour,
                                  final TextField minute,
                                  final TextField second,
                                  final Label labelYear,
                                  final Label labelMonth,
                                  final Label labelDay,
                                  final Label labelHour,
                                  final Label labelMinute,
                                  final Label labelSecond) {
        labelEnd.setVisible(bool);
        dateEnd.setVisible(bool);
        timeEnd.setVisible(bool);
        labelInterval.setVisible(bool);
        year.setVisible(bool);
        month.setVisible(bool);
        day.setVisible(bool);
        hour.setVisible(bool);
        minute.setVisible(bool);
        second.setVisible(bool);
        labelYear.setVisible(bool);
        labelMonth.setVisible(bool);
        labelDay.setVisible(bool);
        labelHour.setVisible(bool);
        labelMinute.setVisible(bool);
        labelSecond.setVisible(bool);
    }
}
