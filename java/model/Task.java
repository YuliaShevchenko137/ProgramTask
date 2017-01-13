package model;

import java.io.Serializable;

import java.text.ParseException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

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
     * контроль версий для сериализации {@value}.
     */

    private static final long serialVersionUID = 42L;

    /**Поле названия задачи.*/

    private String title;

    /**Составляющая интервала в годах.*/

    private int intervalYear;

    /**Составляющая интервала в месяцах.*/

    private int intervalMonth;

    /**Составляющая интервала в днях.*/

    private int intervalDay;

    /**Составляющая интервала в часах.*/

    private int intervalHour;

    /**Составляющая интервала в минутах.*/

    private int intervalMinute;

    /**Составляющая интервала в секундах.*/

    private int intervalSecond;

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

    /**текстовое представление интервала
     * задачи.
     */

    private String interval;

    /**
     * Поток для оповещения пользователя
     * о ближайшей задачею.
     */

    private Thread thread;

    /**Обьект, который создает оповещение.*/

    private threadTask threadTask;


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
        this.interval = this.getInterval();
        this.threadTask = new threadTask(this);
        this.thread = new Thread(this.getThreadTask());
        this.thread.start();
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
     * @param intervalYears количество лет.
     * @param intervalMonths количество месяцев.
     * @param intervalDays количество дней.
     * @param intervalHours количество часов.
     * @param intervalMinutes количество минут.
     * @param intervalSeconds количество секунд между.
     * @throws ParseException из-за преобразования даты в
     * {@link #setTime(Date, Date, int, int, int, int, int, int)}.
     */

    public Task(final String titles, final Date starts,
                final Date ends, final int intervalYears,
                final int intervalMonths, final int intervalDays,
                final int intervalHours, final int intervalMinutes,
                final int intervalSeconds) throws ParseException {
        this.setTitle(titles);
        this.setTime(starts, ends, intervalYears, intervalMonths,
                intervalDays, intervalHours, intervalMinutes,
                intervalSeconds);
        this.interval = this.getInterval();
        this.threadTask = new threadTask(this);
        this.thread = new Thread(this.getThreadTask());
        this.thread.start();
    }

    /**
     *Метод getTitle().
     *  возвращает название задачи.
     * @return  имя задачи.
     */

    public final String getTitle() {
        return this.title;
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
     * Метод getRepeatInterval().
     * возвращает текстовую строку интервала
     * повторения задачи.
     * @return  интервал полторения.
     */

    private String getRepeatInterval() {
        if (this.isRepeated()) {
            return this.getIntervalYear()
                    + " year "
                    + this.getIntervalMonth()
                    + " month "
                    + this.getIntervalDay()
                    + " day "
                    + this.getIntervalHour()
                    + " hour "
                    + this.getIntervalMinute()
                    + " minute "
                    + this.getIntervalSecond()
                    + " second";
        } else {
            return "0";
        }
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
     * {@link #setTime(Date, Date, int, int, int, int, int, int)}.
     * @param time время исполнения задачи.
     * @throws ParseException преобраз. дат.
     */

    public final void setTime(final Date time) throws ParseException {
        this.setTime(time, time, 0, 0, 0,
                0, 0, 0);
    }

    /**
     * Метод setTime(Date start, Date end, int intervalYear,
     * int intervalMonth, int intervalDay, int intervalHour,
     * int intervalMinute, int intervalSecond).
     * устанавливает время для
     * повторяющейся задачи.
     * @param starts время начала выполнения.
     * @param ends время конца выполнения.
     * @param intervalYears количество лет.
     * @param intervalMonths количество месяцев.
     * @param intervalDays количество дней.
     * @param intervalHours количество часов.
     * @param intervalMinutes количество минут.
     * @param intervalSeconds количество секунд.
     * @throws ParseException преобраз. дат.
     */

    private void setTime(final Date starts, final Date ends,
                         final int intervalYears, final int intervalMonths,
                         final int intervalDays, final int intervalHours,
                         final int intervalMinutes, final int intervalSeconds)
            throws ParseException {
        this.setStart((Date) starts.clone());
        this.setEnd((Date) ends.clone());
        this.setIntervalYear(intervalYears);
        this.setIntervalMonth(intervalMonths);
        this.setIntervalDay(intervalDays);
        this.setIntervalHour(intervalHours);
        this.setIntervalMinute(intervalMinutes);
        this.setIntervalSecond(intervalSeconds);
    }

    /**
     * Метод nextTimeAfter(Date current).
     * ищет, когда, после заданного времени,
     * задача исполнится в следующий раз.
     * @param current время.
     * @return время след. повторения.
     */

    public final  Date nextTimeAfter(final Date current) {
        if (!this.active) {
            return null;
        } else if (current.before(this.start)) {
            return this.start;
        } else if (current.after(this.end)) {
            return null;
        } else {
            LocalDateTime start1 = this.dateToLocalDateTime(this.start);
            LocalDateTime end1 = this.dateToLocalDateTime(this.end);
            LocalDateTime current1 = this.dateToLocalDateTime(current);
            do {
                start1 = Tasks.plusTime(start1, this);
            } while (start1.isBefore(current1) || start1.equals(current1));
            if (start1.isBefore(end1) || start1.equals(end1)) {
                return this.localDateTimeToDate(current1);
            } else {
                return new Date(0);
            }
        }
    }

    /**
     * Метод localDateTimeToDate(LocalDateTime localDateTime).
     * преобразовует LocalDateTime в Date.
     * @param localDateTime дата типа LocalDateTime.
     * @return дата типа Date.
     */

    private Date localDateTimeToDate(final LocalDateTime localDateTime) {
        Instant instant = localDateTime.
                atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * Метод dateToLocalDateTime(Date date).
     * преобразовует Date в LocalDateTime.
     * @param date дата типа Date.
     * @return дата типа LocalDateTime.
     */

    private LocalDateTime dateToLocalDateTime(final Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
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
            boolean a4 = this.getRepeatInterval().equals(task.getRepeatInterval());
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
                + this.getIntervalYear()
                + this.start.hashCode()
                + this.end.hashCode()
                + this.getIntervalMonth()
                + this.getIntervalDay()
                + this.getIntervalHour()
                + this.getIntervalMinute()
                + this.getIntervalSecond();
    }

    /**
     * Метод getInterval().
     * при помощи метода {@link #getRepeatInterval()}
     * заполняет поле интервал.
     * @return значение интервала повторения.
     */

    public final String getInterval() {
        this.interval =  this.getRepeatInterval();
        return this.interval;
    }

    /**
     * Метод getIntervalYear().
     * возвращает количество лет, через
     * которое повторится задача.
     * @return количество лет.
     */

    public final int getIntervalYear() {
        return this.intervalYear;
    }

    /**
     * Метод getIntervalMonth().
     * возвращает количество месяцев, через
     * которое повторится задача.
     * @return количество месяцев.
     */

    public final int getIntervalMonth() {
        return this.intervalMonth;
    }

    /**
     * Метод getIntervalDay().
     * возвращает количество дней, через
     * которое повторится задача.
     * @return количество дней.
     */

    public final int getIntervalDay() {
        return this.intervalDay;
    }

    /**
     * Метод getIntervalHour().
     * возвращает количество часов, через
     * которое повторится задача.
     * @return количество часов.
     */

    public final int getIntervalHour() {
        return this.intervalHour;
    }

    /**
     * Метод getIntervalMinute().
     * возвращает количество минут, через
     * которое повторится задача.
     * @return количество минут.
     */

    public final int getIntervalMinute() {
        return this.intervalMinute;
    }

    /**
     * Метод getIntervalSecond().
     * возвращает количество секунд, через
     * которое повторится задача.
     * @return количество секунд.
     */

    public final int getIntervalSecond() {
        return this.intervalSecond;
    }

    /**
     * Метод setIntervalYear(int intervalYear).
     * устанавливает количество лет
     * между повторениями.
     * @param intervalYears количество лет.
     */

    public final void setIntervalYear(final int intervalYears) {
        this.intervalYear = intervalYears;
    }

    /**
     * Метод setIntervalMonth(int intervalMonth).
     * устанавливает количество месяцев
     * между повторениями.
     * @param intervalMonths количество месяцев.
     */

    public final void setIntervalMonth(final int intervalMonths) {
        this.intervalMonth = intervalMonths;
    }

    /**
     * Метод setIntervalDay(int intervalDay).
     * устанавливает количество дней
     * между повторениями.
     * @param intervalDays количество дней.
     */

    public final void setIntervalDay(final int intervalDays) {
        this.intervalDay = intervalDays;
    }

    /**
     * Метод setIntervalHour(int intervalHour).
     * устанавливает количество часов
     * между повторениями.
     * @param intervalHours количество лет.
     */

    public final void setIntervalHour(final int intervalHours) {
        this.intervalHour = intervalHours;
    }

    /**
     * Метод setIntervalMinute(int intervalMinute).
     * устанавливает количество минут
     * между повторениями.
     * @param intervalMinutes количество минут.
     */

    public final void setIntervalMinute(final int intervalMinutes) {
        this.intervalMinute = intervalMinutes;
    }

    /**
     * Метод setIntervalSecond(int intervalSecond).
     * устанавливает количество секунд
     * между повторениями.
     * @param intervalSeconds количество лет.
     */

    public final void setIntervalSecond(final int intervalSeconds) {
        this.intervalSecond = intervalSeconds;
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

    public final threadTask getThreadTask() {
        return this.threadTask;
    }
}
