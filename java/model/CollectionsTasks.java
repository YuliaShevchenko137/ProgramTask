package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class CollectionsTasks {

    private ObservableList<Task> obs;

    private TaskList tasks;

    public CollectionsTasks(){
        this.obs = FXCollections.observableArrayList();
        this.tasks = new ArrayTaskList();
    }

    public void add(Task t){
        this.obs.add(t);
        this.tasks.add(t);
    }

    public void remove(Task t) {
        this.obs.remove(t);
        this.tasks.remove(t);
    }

    public ObservableList<Task> getObs() {
        return obs;
    }

    public TaskList getTasks(){
        return tasks;
    }

    public void setTasks(TaskList tasks) {
        this.tasks = tasks;
        for (Task t :tasks) {
            obs.add(t);
        }
    }
}
