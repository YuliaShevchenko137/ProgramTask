package com.netcracker.java.YuliaShevchenko.lab1.model;


public class CreateInterval {

    private int intervalYear;

    private int intervalMonth;

    private int intervalDay;

    private int intervalHour;

    private int intervalMinute;

    private int intervalSecond;

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
     *
     */

    public CreateInterval(){
        this.setIntervalYear(Constants.getNulls());
        this.setIntervalMonth(Constants.getNulls());
        this.setIntervalDay(Constants.getNulls());
        this.setIntervalHour(Constants.getNulls());
        this.setIntervalMinute(Constants.getNulls());
        this.setIntervalSecond(Constants.getNulls());
    }

    /**
     *
     */

    public CreateInterval (int year, int month,  int day,
                           int hour, int minute, int second) {
        this.setIntervalYear(year);
        this.setIntervalMonth(month);
        this.setIntervalDay(day);
        this.setIntervalHour(hour);
        this.setIntervalMinute(minute);
        this.setIntervalSecond(second);
    }

    /**
     *
     * @return
     */

    public String getInterval () {
        return this.getIntervalYear() + " year " + this.getIntervalMonth() + " month "
                + this.getIntervalDay() + " day " + this.getIntervalHour() + " hour "
                + this.getIntervalMinute() + " minute " + this.getIntervalSecond()
                + " second";
    }

    /**Составляющая интервала в годах.*/
    public int getIntervalYear() {
        return intervalYear;
    }

    /**Составляющая интервала в месяцах.*/
    public int getIntervalMonth() {
        return intervalMonth;
    }

    /**Составляющая интервала в днях.*/
    public int getIntervalDay() {
        return intervalDay;
    }

    /**Составляющая интервала в часах.*/
    public int getIntervalHour() {
        return intervalHour;
    }

    /**Составляющая интервала в минутах.*/
    public int getIntervalMinute() {
        return intervalMinute;
    }

    /**Составляющая интервала в секундах.*/
    public int getIntervalSecond() {
        return intervalSecond;
    }
}
