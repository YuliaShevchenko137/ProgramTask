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
        if (isRepeated()) {
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
     * Метод - конструктор Task(String title, Date time).
     * создает экземпляр неповторяющейся задачи
     * и поток для оповещения пользователя.
     * @param   title название задачи.
     * @param   time время.
     * @throws  ParseException преобразование даты.
     */

    public Task(String title, Date time) throws ParseException {
        setTitle(title);
        setTime(time);
        this.interval = getInterval();
        this.threadTask = new threadTask(this);
        this.thread = new Thread(getThreadTask());
        thread.start();
    }

    /**
     * Метод - конструктор Task(String title, Date start, Date end,
     * int intervalYear, int intervalMonth, int intervalDay,
     * int intervalHour, int intervalMinute, int intervalSecond).
     * создает экземпляр повторяющейся задачи и поток для оповещения
     * пользователя.
     * @param title название задачи.
     * @param start время начала выполнения.
     * @param end время конца выполнения.
     * @param intervalYear количество лет между выполнением задачи.
     * @param intervalMonth количество месяцев между выполнением задачи.
     * @param intervalDay количество дней между выполнением задачи.
     * @param intervalHour количество часов между выполнением задачи.
     * @param intervalMinute количество минут между выполнением задачи.
     * @param intervalSecond количество секунд между выполнением задачи.
     * @throws ParseException из-за преобразования даты в
     * {@link #setTime(Date, Date, int, int, int, int, int, int)}.
     */

    public Task(String title, Date start, Date end, int intervalYear,
                int intervalMonth, int intervalDay, int intervalHour,
                int intervalMinute, int intervalSecond) throws ParseException {
        setTitle(title);
        setTime(start, end, intervalYear, intervalMonth, intervalDay,
                intervalHour, intervalMinute, intervalSecond);
        this.interval = getInterval();
        this.threadTask = new threadTask(this);
        this.thread = new Thread(getThreadTask());
        thread.start();
    }

    /**
     * Метод setTime(Date time).
     * устанавливает значение времени для неповторяющейся задачи использую
     * {@link #setTime(Date, Date, int, int, int, int, int, int)}.
     * @param time время исполнения задачи.
     * @throws ParseException из-за преобразования клонированных дат.
     */

    public void setTime(Date time) throws ParseException {
        setTime(time, time, 0,0,0,0,0,0);
    }

    /**
     * Метод setTime(Date start, Date end, int intervalYear, int intervalMonth,
     * int intervalDay, int intervalHour, int intervalMinute,
     * int intervalSecond).
     * устанавливает время для повторяющейся задачи.
     * @param start время начала выполнения.
     * @param end время конца выполнения.
     * @param intervalYear количество лет между выполнением задачи.
     * @param intervalMonth количество месяцев между выполнением задачи.
     * @param intervalDay количество дней между выполнением задачи.
     * @param intervalHour количество часов между выполнением задачи.
     * @param intervalMinute количество минут между выполнением задачи.
     * @param intervalSecond количество секунд между выполнением задачи.
     * @throws ParseException из-за преобразования клонорованных дат.
     */

    private void setTime(Date start, Date end, int intervalYear,
                         int intervalMonth, int intervalDay, int intervalHour,
                         int intervalMinute, int intervalSecond)
            throws ParseException {
        this.setStart((Date) start.clone());
        this.setEnd((Date) end.clone());
        this.setIntervalYear(intervalYear);
        this.setIntervalMonth(intervalMonth);
        this.setIntervalDay(intervalDay);
        this.setIntervalHour(intervalHour);
        this.setIntervalMinute(intervalMinute);
        this.setIntervalSecond(intervalSecond);
    }

    /**
     * Метод nextTimeAfter(Date current).
     * ищет, когда, после заданного времени, задача исполнится в следующий раз.
     * @param current время, после которого необходимо искать выполнение задачи.
     * @return время, когда задачи выполнится после передаваемого.
     */

    public Date nextTimeAfter(Date current) {
        if (!this.active){
            return null;
        }

        if(current.before(this.start)) return start;
        if(current.after(this.end)) return null;
        LocalDateTime start1 = dateToLocalDateTime(this.start);
        LocalDateTime end1 = dateToLocalDateTime(this.end);
        LocalDateTime current1 = dateToLocalDateTime(current);
        do{
            start1 = Tasks.plusTime(start1, this);
        }while (start1.isBefore(current1)||start1.equals(current1));
        if (start1.isBefore(end1)||start1.equals(end1)){
            return localDateTimeToDate(current1);
        }
        return new Date(0);
    }

    /**
     * Метод localDateTimeToDate(LocalDateTime localDateTime).
     * преобразовует LocalDateTime в Date.
     * @param localDateTime дата типа LocalDateTime.
     * @return дата типа Date.
     */

    private Date localDateTimeToDate(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * Метод dateToLocalDateTime(Date date).
     * преобразовует Date в LocalDateTime.
     * @param date дата типа Date.
     * @return дата типа LocalDateTime.
     */

    private LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * Метод equals(Object obj).
     * сравнивает задачи: {@link Object#equals(Object)}.
     * @param obj обьект для проверки.
     * @return true, если обьекты одинаковые, или false, если они отличаются.
     */

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        else if(obj == this) return true;
        else{
            Task task = (Task) obj;
            boolean a2 = this.start.equals(task.getStart());
            boolean a3 = isActive() == task.isActive();
            boolean a4 = getRepeatInterval().equals(task.getRepeatInterval());
            boolean a = this.end.equals(task.getEnd());
            return a & a2 & a3 & a4;
        }

    }

    /**
     * Метод hashCode().
     * используется для хеширования данных: {@link Object#hashCode()}.
     * @return хеш-код текущей задачи.
     */

    @Override
    public int hashCode() {
        return this.title.hashCode() + getIntervalYear() * 2 +
                this.start.hashCode() * 3 + this.end.hashCode() * 4 +
                getIntervalMonth() * 5 + getIntervalDay() * 6 +
                getIntervalHour() * 7 + getIntervalMinute() * 8 +
                getIntervalSecond() * 9;
    }

    /**
     * Метод getInterval().
     * при помощи метода {@link #getRepeatInterval()} заполняет поле интервал.
     * @return значение интервала повторения.
     */

    public String getInterval() {
        interval =  getRepeatInterval();
        return interval;
    }

    /**
     * Метод getIntervalYear().
     * возвращает количество лет, через которое повторится задача.
     * @return количество лет между выполнением задачи.
     */

    public int getIntervalYear() {
        return intervalYear;
    }

    /**
     * Метод getIntervalMonth().
     * возвращает количество месяцев, через которое повторится задача.
     * @return количество месяцев между выполнением задачи.
     */

    public int getIntervalMonth() {
        return intervalMonth;
    }

    /**
     * Метод getIntervalDay().
     * возвращает количество дней, через которое повторится задача.
     * @return количество дней между выполнением задачи.
     */

    public int getIntervalDay() {
        return intervalDay;
    }

    /**
     * Метод getIntervalHour().
     * возвращает количество часов, через которое повторится задача.
     * @return количество часов между выполнением задачи.
     */

    public int getIntervalHour() {
        return intervalHour;
    }

    /**
     * Метод getIntervalMinute().
     * возвращает количество минут, через которое повторится задача.
     * @return количество минут между выполнением задачи.
     */

    public int getIntervalMinute() {
        return intervalMinute;
    }

    /**
     * Метод getIntervalSecond().
     * возвращает количество секунд, через которое повторится задача.
     * @return количество секунд между выполнением задачи.
     */

    public int getIntervalSecond() {
        return intervalSecond;
    }

    /**
     * Метод setIntervalYear(int intervalYear).
     * устанавливает количество лет между повторениями.
     * @param intervalYear количество лет.
     */

    public void setIntervalYear(int intervalYear) {
        this.intervalYear = intervalYear;
    }

    /**
     * Метод setIntervalMonth(int intervalMonth).
     * устанавливает количество месяцев между повторениями.
     * @param intervalMonth количество месяцев.
     */

    public void setIntervalMonth(int intervalMonth) {
        this.intervalMonth = intervalMonth;
    }

    /**
     * Метод setIntervalDay(int intervalDay).
     * устанавливает количество дней между повторениями.
     * @param intervalDay количество дней.
     */

    public void setIntervalDay(int intervalDay) {
        this.intervalDay = intervalDay;
    }

    /**
     * Метод setIntervalHour(int intervalHour).
     * устанавливает количество часов между повторениями.
     * @param intervalHour количество лет.
     */

    public void setIntervalHour(int intervalHour) {
        this.intervalHour = intervalHour;
    }

    /**
     * Метод setIntervalMinute(int intervalMinute).
     * устанавливает количество минут между повторениями.
     * @param intervalMinute количество минут.
     */

    public void setIntervalMinute(int intervalMinute) {
        this.intervalMinute = intervalMinute;
    }

    /**
     * Метод setIntervalSecond(int intervalSecond).
     * устанавливает количество секунд между повторениями.
     * @param intervalSecond количество лет.
     */

    public void setIntervalSecond(int intervalSecond) {
        this.intervalSecond = intervalSecond;
    }

    /**
     * Метод setStart(Date start).
     * устанавливает время начала исполнения.
     * @param start дата начала.
     */

    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * Метод setEnd(Date end).
     * устанавливает когда исполнение задачи будет закончени.
     * @param end дата конца исполнения.
     */

    public void setEnd(Date end) {
        this.end = end;
    }

    /**
     * Метод setRepeated(boolean repeated).
     * устанавливает повторяемость задачи.
     * @param repeated повторяемость задачи.
     */

    public void setRepeated(boolean repeated) {
        this.repeated = repeated;
    }

    /**
     * Метод getThreadTask().
     * возвращает обьект, который будет создавать окна уведомления.
     * @return обьект, на основе которого был создан поток
     * для уведомления о приближении задачи.
     */

    public threadTask getThreadTask() {
        return threadTask;
    }
}
