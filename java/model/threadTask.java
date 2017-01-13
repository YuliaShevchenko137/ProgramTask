package model;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static java.lang.Thread.sleep;

public class threadTask implements Runnable {

    private Task task;

    private boolean finish = false;
    private boolean firstAlert = false;

    threadTask(Task task) {
        this.task = task;
    }

    private Date nowTime(){
        return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    public void run() {
        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
            if(nowTime().after(task.getEnd())) {
                return;
            }
            List<Task> t = new ArrayList<>();
            t.add(task);
            Map<Date, Set<Task>> map = Tasks.calendar(t, nowTime(), task.getEnd());
            if(map == null){
                return;
            }
            for(Date date : map.keySet()){
                firstAlert = false;
                while(!finish && !firstAlert) {
                    long countMSecond = date.getTime() - nowTime().getTime();
                    long halfHour = 1000 * 60 * 30;
                    Set<Task> tasks = map.get(date);
                    if (countMSecond > halfHour) {
                        try {
                            sleep(1000*60);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        for (Task thisTask : tasks) {
                            createAlert(date, thisTask);
                            firstAlert = true;
                        }
                    }
                }
            }
        });
    }

    private void createAlert(Date date, Task task){
        Alert delete = new Alert(Alert.AlertType.INFORMATION);
        delete.setTitle("Напоминание");
        delete.setHeaderText("В " + date + " необходимо выполнить: " + task.getTitle());
        delete.showAndWait();
    }

    public void stop() {
        this.finish =true;
    }
}
