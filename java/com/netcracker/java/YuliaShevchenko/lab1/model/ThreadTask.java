package com.netcracker.java.YuliaShevchenko.lab1.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 * Класс ThreadTask.
 * Реализация оповещения задачи.
 */

public class ThreadTask {

    /**
     * Задача для оповещения.
     */

    private Task task;

    /**
     * Закрытие потока.
     */

    private boolean finish;

    /**
     * Первое оповещение.
     */

    private boolean firstAlert;

    /**
     * Поток уведомления.
     */

    private Thread thread;

    /**
     * Конструктор ThreadTask(Task tas).
     * Создание потока.
     * @param tas задача для открытия потока.
     */

    public ThreadTask(final Task tas) {
        this.finish = false;
        this.firstAlert = false;
        this.task = tas;
        this.thread = new Thread(() -> {
            if (this.nowTime().after(this.task.getEnd())) {
                return;
            }
            List<Task> t = new ArrayList<>();
            t.add(this.task);
            Map<Date, Set<Task>> map =
                    Tasks.calendar(t, nowTime(), task.getEnd());
            if (map == null) {
                return;
            }
            for (Date date : map.keySet()) {
                this.firstAlert = false;
                while (!this.finish && !this.firstAlert) {
                    long countMSecond = date.getTime()
                            - this.nowTime().getTime();
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
     * Метод nowTime().
     * @return текущее время.
     */

    private Date nowTime() {
        return Date.from(LocalDateTime.now().
                atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Метод setFinish().
     * Необходим для завершения
     * потока.
     */

    public final void setFinish() {
        this.finish = true;
    }
}
