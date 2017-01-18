package com.netcracker.java.YuliaShevchenko.lab1.model;

/**
 * Class CreateInterval.
 * Helper class for creating repeated interval.
 */

public class CreateInterval {

    /**
     * intervalYear.
     * component interval in years.
     */

    private int intervalYear;

    /**
     * intervalMonth.
     * component interval in months.
     */

    private int intervalMonth;

    /**
     * intervalDay.
     * component interval in days.
     */

    private int intervalDay;

    /**
     * intervalHour.
     * component interval in hours.
     */

    private int intervalHour;

    /**
     * intervalMinute.
     * component interval in minutes.
     */

    private int intervalMinute;

    /**
     * intervalSecond.
     * component interval in seconds.
     */

    private int intervalSecond;

    /**
     *Method CreateInterval().
     * Constructor for non recurring task.
     */

    public CreateInterval() {
        this.setIntervalYear(Constants.getNulls());
        this.setIntervalMonth(Constants.getNulls());
        this.setIntervalDay(Constants.getNulls());
        this.setIntervalHour(Constants.getNulls());
        this.setIntervalMinute(Constants.getNulls());
        this.setIntervalSecond(Constants.getNulls());
    }

    /**
     * Method CreateInterval(final int year, final int month,
     * final int day, final int hour,
     * final int minute, final int second).
     * Constructor for repeated task.
     */

    public CreateInterval(final int year, final int month,
                          final int day, final int hour,
                          final int minute, final int second) {
        this.setIntervalYear(year);
        this.setIntervalMonth(month);
        this.setIntervalDay(day);
        this.setIntervalHour(hour);
        this.setIntervalMinute(minute);
        this.setIntervalSecond(second);
    }

    /**
     * Method setIntervalYear(int intervalYear).
     * Setter for intervalYear.
     * @param intervalYears new count of years in the interval.
     */

    public final void setIntervalYear(final int intervalYears) {
        this.intervalYear = intervalYears;
    }

    /**
     * Method setIntervalMonth(int intervalMonth).
     * Setter for intervalMonth.
     * @param intervalMonths new count of months in the interval.
     */

    public final void setIntervalMonth(final int intervalMonths) {
        this.intervalMonth = intervalMonths;
    }

    /**
     * Method setIntervalDay(int intervalDay).
     * Setter for intervalDay.
     * @param intervalDays new count of days in the interval.
     */

    public final void setIntervalDay(final int intervalDays) {
        this.intervalDay = intervalDays;
    }

    /**
     * Method setIntervalHour(int intervalHour).
     * Setter for intervalHour.
     * @param intervalHours new count of hours in the interval.
     */

    public final void setIntervalHour(final int intervalHours) {
        this.intervalHour = intervalHours;
    }

    /**
     * Method setIntervalMinute(int intervalMinute).
     * Setter for intervalMinutes.
     * @param intervalMinutes new count of minutes in the interval.
     */

    public final void setIntervalMinute(final int intervalMinutes) {
        this.intervalMinute = intervalMinutes;
    }

    /**
     * Method setIntervalSecond(int intervalSecond).
     * Setter for intervalSecond.
     * @param intervalSeconds new count of seconds in the interval.
     */

    public final void setIntervalSecond(final int intervalSeconds) {
        this.intervalSecond = intervalSeconds;
    }

    /**
     * Method getInterval.
     * Returns a textual representation of the interval.
     * @return string of the interval.
     */

    public String getInterval() {
        return this.getIntervalYear() + " year "
                + this.getIntervalMonth() + " month "
                + this.getIntervalDay() + " day "
                + this.getIntervalHour() + " hour "
                + this.getIntervalMinute() + " minute "
                + this.getIntervalSecond() + " second";
    }

    /**
     * Method getIntervalYear().
     * Getter for intervalYear.
     * @return current component interval in years.
     */

    public int getIntervalYear() {
        return this.intervalYear;
    }

    /**
     * Method getIntervalMonth().
     * Getter for intervalMonth.
     * @return current component interval in months.
     */

    public int getIntervalMonth() {
        return this.intervalMonth;
    }

    /**
     * Method getIntervalDay().
     * Getter for intervalDay.
     * @return current component interval in days.
     */

    public int getIntervalDay() {
        return this.intervalDay;
    }

    /**
     * Method getIntervalHour().
     * Getter for intervalHour.
     * @return current component interval in hours.
     */

    public int getIntervalHour() {
        return this.intervalHour;
    }

    /**
     * Method getIntervalMinute().
     * Getter for intervalMinute.
     * @return current component interval in minutes.
     */

    public int getIntervalMinute() {
        return this.intervalMinute;
    }

    /**
     * Method getIntervalSecond().
     * Getter for intervalSecond.
     * @return current component interval in seconds.
     */

    public int getIntervalSecond() {
        return intervalSecond;
    }
}
