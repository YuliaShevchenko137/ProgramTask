package com.netcracker.java.YuliaShevchenko.lab1.model;

import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Class Task.
 * Realization of of some task that you can add, change or delete a user.
 * @author Yulia Shevchenko.
 */

public class Task implements Cloneable, Serializable {

    /**
     * serialVersionUID.
     * Version control for serialization.
     */

    private static final long serialVersionUID = 42L;

    /**
     * title.
     * Title ot the current task.
     */

    private String title;

    /**
     * active.
     * Activity of the current task.
     */

    private boolean active;

    /**
     * repeated.
     * Repeated of the current task.
     */

    private boolean repeated;

    /**
     * start.
     * Start date of the current task.
     */

    private Date start;

    /**
     * end.
     * End date of the current task.
     */

    private Date end;

    /**
     * interval.
     * Object type CreateInterval.
     * It creates string view repetition interval of the current task.
     */

    private CreateInterval interval;

    /**
     * threadTask.
     * Thread for alert user.
     */

    private ThreadTask threadTask;

    /**
     * Method Task(String title, Date time).
     * Constructor creation non recurring task and thread for alert.
     * @param   titles title task.
     * @param   times time performance.
     */

    public Task(final String titles, final Date times) {
        this.setTitle(titles);
        this.setTime(times);
        this.interval = new CreateInterval();
        this.setThreadTask(new ThreadTask(this));
    }

    /**
     *  Method Task(String title, Date start, Date end,  CreateInterval interval).
     * Constructor creation repeated task and thread for alert.
     * @param titles title task.
     * @param starts start date performance.
     * @param ends end date performance.
     * @param intervals repetition interval.
     */

    public Task(final String titles, final Date starts,
                final Date ends, final CreateInterval intervals) {
        this.setTitle(titles);
        this.interval = intervals;
        this.setTime(starts, ends);
        this.setThreadTask(new ThreadTask(this));
    }

    /**
     * Method getTitle().
     *  Getter for title of the task.
     * @return  title of the current task.
     */

    public final String getTitle() {
        return this.title;
    }

    /**
     * Method getCreateInterval().
     * Getter for interval.
     * @return object type CreateInterval wih information about interval.
     */

    public final CreateInterval getCreateInterval() {
        return this.interval;
    }

    /**
     * Method setTitle(String title).
     * Setter for title of the task.
     * @param   titles new title.
     */

    public final void setTitle(final String titles) {
        this.title = titles;
    }

    /**
     * Method isActive().
     * Verification tasks on activity.
     * @return  active of the task.
     */

    public final boolean isActive() {
        return this.active;
    }

    /**
     * Method setActive(boolean active).
     * Setter for active of the task.
     * @param   actives new active of the task.
     */

    public final void setActive(final boolean actives) {
        this.active = actives;
    }

    /**
     * Method getStart().
     * Getter for start date of the task.
     * @return  start date.
     */

    public final Date getStart() {
        return this.start;
    }

    /**
     * Method getEnd().
     * Getter for end date of the task.
     * @return  end date.
     */

    public final Date getEnd() {
        return this.end;
    }

    /**
     * Метод isRepeated().
     * Verification task on repeated.
     * @return repeated of the current task.
     */

    public final boolean isRepeated() {
        return this.repeated;
    }

    /**
     * Method clone().
     * Create clone of the current task: {@link Object#clone()}.
     * @return  copy current object.
     * @throws  CloneNotSupportedException if object nonclonability.
     */

    @Override
    public final Task clone() throws CloneNotSupportedException {
        return (Task) super.clone();
    }

    /**
     * Method setTime(Date time).
     * Set a date value for non recurring task {@link #setTime(Date, Date)}.
     * @param time time of the notification.
     */

    public final void setTime(final Date time) {
        this.setTime(time, time);
    }

    /**
     * Метод setTime(Date start, Date end).
     * Set a date value for repeated task.
     * @param starts stand date notification.
     * @param ends end date notification.
     */

    private void setTime(final Date starts, final Date ends) {
        this.setStart((Date) starts.clone());
        this.setEnd((Date) ends.clone());
    }

    /**
     * Method nextTimeAfter(Date current).
     * Search date when task will be performed after current.
     * @param current start date for search.
     * @return time to next performed.
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
     * Method equals(Object obj).
     * equals two tasks: {@link Object#equals(Object)}.
     * @param obj the object of comparison .
     * @return true, if objects is identical, or false.
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
     * Method hashCode().
     * use for hashing date: {@link Object#hashCode()}.
     * @return hashcode of the current task.
     */

    @Override
    public final int hashCode() {
        return this.title.hashCode()
                + this.interval.hashCode()
                + this.start.hashCode()
                + this.end.hashCode();
    }

    /**
     * Method getInterval().
     * Getter for text representation of the repetition interval.
     * @return repetition interval.
     */

    public final String getInterval() {
        return this.interval.getInterval();
    }

    /**
     * Method setStart(Date start).
     * Setter for start date of the task.
     * @param starts new start date.
     */

    public final void setStart(final Date starts) {
        this.start = starts;
    }

    /**
     * Method setEnd(Date end).
     * Setter for end date of the task.
     * @param ends new end.
     */

    public final void setEnd(final Date ends) {
        this.end = ends;
    }

    /**
     * Method setRepeated(boolean repeated).
     * Setter for repeated of the task.
     * @param repeateds new repeated.
     */

    public final void setRepeated(final boolean repeateds) {
        this.repeated = repeateds;
    }

    /**
     * Method getThreadTask().
     * Getter for thread for alert.
     * @return thread.
     */

    public final ThreadTask getThreadTask() {
        return this.threadTask;
    }

    /**
     * Method setThreadTask(ThreadTask threadTask).
     * Setter for thread for alert user.
     * @param threadTasks new thread.
     */

    public final void setThreadTask(final ThreadTask threadTasks) {
        this.threadTask = threadTasks;
    }
}
