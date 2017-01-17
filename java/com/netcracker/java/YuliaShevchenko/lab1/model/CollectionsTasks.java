package com.netcracker.java.YuliaShevchenko.lab1.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class CollectionsTasks.
 * Used to wrap a list of tasks. It contains the object type ObservableList,
 * which is displayed in the tables.
 */

public class CollectionsTasks {

    /**
     * obs.
     * Wraper for TaskList.
     */

    private ObservableList<Task> obs;

    /**
     * tasks.
     * Current list of task.
     */

    private TaskList tasks;

    /**
     * Constructor CollectionsTasks().
     * Create new TaskList and wrapper for this class.
     */

    public CollectionsTasks() {
        this.obs = FXCollections.observableArrayList();
        this.tasks = new ArrayTaskList();
    }

    /**
     * Method add(Task t).
     * Add current task in TaskList and ObservableList.
     * @param t current task.
     */

    public final void add(final Task t) {
        this.obs.add(t);
        this.tasks.add(t);
    }

    /**
     * Method remove(Task t).
     * Delete current task from TaskList and wrapper.
     * @param t current task.
     */

    public final void remove(final Task t) {
        this.obs.remove(t);
        this.tasks.remove(t);
    }

    /**
     * Method getObs().
     * Getter for wrapper current TaskList.
     * @return ObservableList.
     */

    public final ObservableList<Task> getObs() {
        return this.obs;
    }

    /**
     * Method getTasks().
     * Getter for TaskList.
     * @return current TaskList.
     */

    public final TaskList getTasks() {
        return this.tasks;
    }

    /**
     * Method setTasks(TaskList tasks).
     * Setter for TaskList and create wrapper for this TaskList.
     * @param taskss current TaskList.
     */

    public final void setTasks(final TaskList taskss) {
        this.tasks = taskss;
        for (Task t : this.tasks) {
            this.obs.add(t);
        }
    }
}
