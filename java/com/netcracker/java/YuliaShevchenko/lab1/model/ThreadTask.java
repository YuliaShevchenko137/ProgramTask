package com.netcracker.java.YuliaShevchenko.lab1.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 * Class ThreadTask.
 * Realisation of alert the user of an approaching task.
 */

public class ThreadTask {

    /**
     * task.
     * Task for notification.
     */

    private Task task;

    /**
     * finish.
     * Use for close the thread.
     */

    private boolean finish;

    /**
     * firstAlert.
     * Indicates whether the first warning.
     */

    private boolean firstAlert;

    /**
     * thread.
     * Thread for notification.
     */

    private Thread thread;

    /**
     * Method ThreadTask(Task tas).
     * Constructor for creating thread.
     * @param tas current task.
     */

    public ThreadTask(final Task tas) {
        this.finish = false;
        this.firstAlert = false;
        this.task = tas;
        this.thread = new Thread(() -> {
            if (OperationForTime.nowTime().after(this.task.getEnd())) {
                return;
            }
            List<Task> t = new ArrayList<>();
            t.add(this.task);
            Map<Date, Set<Task>> map =
                    Tasks.calendar(t, OperationForTime.nowTime(), task.getEnd());
            if (map == null) {
                return;
            }
            for (Date date : map.keySet()) {
                this.firstAlert = false;
                while (!this.finish && !this.firstAlert) {
                    long countMSecond = date.getTime()
                            - OperationForTime.nowTime().getTime();
                    Set<Task> tasks = map.get(date);
                    if (countMSecond > Constants.getHalfhour()) {
                        try {
                            Thread.sleep(Constants.getSecond());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        for (Task thisTask : tasks) {
                            Platform.runLater(() -> {
                                Alert inf = new Alert(Alert.
                                        AlertType.INFORMATION);
                                inf.setTitle("Notification");
                                inf.setHeaderText("At " + date
                                        + " it is necessary to perform"
                                        + thisTask.getTitle());
                                inf.show();
                            });
                            this.firstAlert = true;
                        }
                    }
                }
            }
        });
        this.thread.start();
    }

    /**
     * Method setFinish().
     * Stopped the thread.
     */

    public final void setFinish() {
        this.finish = true;
    }
}
