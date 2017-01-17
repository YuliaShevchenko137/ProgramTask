package com.netcracker.java.YuliaShevchenko.lab1.model;

import java.io.Serializable;

import java.text.ParseException;

import java.time.LocalDateTime;

import java.util.Date;
/**
 * Класс Task.
 * Класс, который описывает задачу.
 * Cвойства: <b>title</b>, <b>start</b>,
 * <b>end</b>, <b>active</b>, <b>repeated</b>, <b>intervalYear</b>,
 * <b>intervalMonth</b>, <b>intervalDay</b>, <b>intervalHour</b>,
 * <b>intervalMinute</b>, <b>intervalSecond</b> и <b>thread</b>.
 * @author Yulia Shevchenko.
 */

public class Task implements Cloneable, Serializable {

    /**
     * Version control for serialization.
     */

    private static final long serialVersionUID = 42L;

    /**Поле названия задачи.*/

    private String title;

    /**Поля активности.*/

    private boolean active;

    /**Поле повторяемости.*/

    private boolean repeated;

    /**Поля начала выполнения задачи.
     */

    private Date start;

    /**Поля конца выполнения задачи.
     */

    private Date end;

    /**
     * текстовое представление интервала
     * задачи.
     */

    private CreateInterval interval;

    /**
     * Потом оповещения.
     */

    private ThreadTask threadTask;


    /**
     * Метод - конструктор Task(String title, Date time).
     * создает экземпляр неповторяющейся
     * задачи и поток для оповещения
     * пользователя.
     * @param   titles название задачи.
     * @param   times время.
     * @throws  ParseException преобраз. даты.
     */

    public Task(final String titles, final Date times)
            throws ParseException {
        this.setTitle(titles);
        this.setTime(times);
        this.interval = new CreateInterval();
        this.setThreadTask(new ThreadTask(this));
    }

    /**
     * Метод - конструктор Task(String title, Date start,
     * Date end, int intervalYear, int intervalMonth,
     * int intervalDay, int intervalHour,
     * int intervalMinute, int intervalSecond).
     * создает экземпляр задачи и
     * поток для оповещения пользователя.
     * @param titles название задачи.
     * @param starts время начала выполнения.
     * @param ends время конца выполнения.
     * @param intervals interval
     * @throws ParseException из-за преобразования даты в
     * {@link #setTime(Date, Date)}.
     */

    public Task(final String titles, final Date starts,
                final Date ends, final CreateInterval intervals)
            throws ParseException {
        this.setTitle(titles);
        this.interval = intervals;
        this.setTime(starts, ends);
        this.setThreadTask(new ThreadTask(this));
    }

    /**
     *Метод getTitle().
     *  возвращает название задачи.
     * @return  имя задачи.
     */

    public final String getTitle() {
        return this.title;
    }

    public final CreateInterval getCreateInterval() {
        return this.interval;
    }

    /**
     * Метод setTitle(String title).
     * устанавливает название задачи.
     * @param   titles поле с названием задачи.
     */

    public final void setTitle(final String titles) {
        this.title = titles;
    }

    /**
     * Метод isActive().
     * проверяет активна ли задача.
     * @return  возваращает активность.
     */

    public final boolean isActive() {
        return this.active;
    }

    /**
     * Метод setActive(boolean active).
     * устанавливает активность задачи.
     * @param   actives активность задачи.
     */

    public final void setActive(final boolean actives) {
        this.active = actives;
    }

    /**
     * Метод getStart().
     * возвращает время начала задачи.
     * @return  время начала віполнения задачи.
     */

    public final Date getStart() {
        return this.start;
    }

    /**
     * Метод getEnd().
     * возвращает время конца задачи.
     * @return  время окончания выполнения задачи.
     */

    public final Date getEnd() {
        return this.end;
    }

    /**
     * Метод isRepeated().
     * проверяет повторяемость задачи.
     * @return  повторяемость задачи.
     */

    public final boolean isRepeated() {
        return this.repeated;
    }

    /**
     * Метод clone().
     * создает копию текущей задачи
     * {@link Object#clone()}.
     * @return  копию текущей задачи.
     * @throws  CloneNotSupportedException no clone.
     */

    @Override
    public final Task clone() throws CloneNotSupportedException {
        return (Task) super.clone();
    }

    /**
     * Метод setTime(Date time).
     * устанавливает значение времени для
     * неповторяющейся задачи.
     * {@link #setTime(Date, Date)}.
     * @param time время исполнения задачи.
     * @throws ParseException преобраз. дат.
     */

    public final void setTime(final Date time) throws ParseException {
        this.setTime(time, time);
    }

    /**
     * Метод setTime(Date start, Date end, int intervalYear,
     * int intervalMonth, int intervalDay, int intervalHour,
     * int intervalMinute, int intervalSecond).
     * устанавливает время для
     * повторяющейся задачи.
     * @param starts время начала выполнения.
     * @param ends время конца выполнения.
     * @throws ParseException преобраз. дат.
     */

    private void setTime(final Date starts, final Date ends)
            throws ParseException {
        this.setStart((Date) starts.clone());
        this.setEnd((Date) ends.clone());
    }

    /**
     * Метод nextTimeAfter(Date current).
     * ищет, когда, после заданного времени,
     * задача исполнится в следующий раз.
     * @param current время.
     * @return время след. повторения.
     */

    public final  Date nextTimeAfter(final Date current) {
        Date a = null;
        boolean b = false;
        if (!this.active) {
            a = null;
            b = true;
        } else if (current.before(this.start)) {
            a = this.start;
            b = true;
        } else if (current.after(this.end)) {
            a = null;
            b = true;
        } else if (!b) {
            LocalDateTime start1 =
                    OperationForTime.dateToLocalDateTime(this.start);
            LocalDateTime end1 =
                    OperationForTime.dateToLocalDateTime(this.end);
            LocalDateTime current1 =
                    OperationForTime.dateToLocalDateTime(current);
            do {
                start1 = OperationForTime.plusTime(start1, this);
            } while (start1.isBefore(current1) || start1.equals(current1));
            if (start1.isBefore(end1) || start1.equals(end1)) {
                a =  OperationForTime.localDateTimeToDate(current1);
            }
        }
        return a;
    }

    /**
     * Метод equals(Object obj).
     * сравнивает задачи: {@link Object#equals(Object)}.
     * @param obj обьект для проверки.
     * @return true, если обьекты одинаковые; false.
     */

    @Override
    public final boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        } else if (obj == this) {
            return true;
        } else {
            Task task = (Task) obj;
            boolean a2 = this.start.equals(task.getStart());
            boolean a3 = this.isActive() == task.isActive();
            boolean a4 = this.interval.
                    equals(task.getInterval());
            boolean a = this.end.equals(task.getEnd());
            return a & a2 & a3 & a4;
        }

    }

    /**
     * Метод hashCode().
     * используется для хеширования данных:
     * {@link Object#hashCode()}.
     * @return хеш-код текущей задачи.
     */

    @Override
    public final int hashCode() {
        return this.title.hashCode()
                + this.interval.hashCode()
                + this.start.hashCode()
                + this.end.hashCode();
    }

    /**
     * Метод getInterval().
     * @return значение интервала повторения.
     */

    public final String getInterval() {
        return this.interval.getInterval();
    }

    /**
     * Метод setStart(Date start).
     * устанавливает время начала исполнения.
     * @param starts дата начала.
     */

    public final void setStart(final Date starts) {
        this.start = starts;
    }

    /**
     * Метод setEnd(Date end).
     * устанавливает когда исполнение задачи
     * будет закончени.
     * @param ends дата конца исполнения.
     */

    public final void setEnd(final Date ends) {
        this.end = ends;
    }

    /**
     * Метод setRepeated(boolean repeated).
     * устанавливает повторяемость задачи.
     * @param repeateds повторяемость задачи.
     */

    public final void setRepeated(final boolean repeateds) {
        this.repeated = repeateds;
    }

    /**
     * Метод getThreadTask().
     * возвращает обьект, который будет
     * создавать окна уведомления.
     * @return Runnable - обьект.
     */

    public final ThreadTask getThreadTask() {
        return this.threadTask;
    }

    /**
     * Метод setThreadTask(ThreadTask threadTask).
     * Связывает поток с задачей.
     * @param threadTasks новый поток.
     */

    public final void setThreadTask(final ThreadTask threadTasks) {
        this.threadTask = threadTasks;
    }
}
