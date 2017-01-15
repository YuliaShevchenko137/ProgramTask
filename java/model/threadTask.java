package model;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static java.lang.Thread.sleep;


public class threadTask{

    private Task task;

    private boolean finish = false;
    private boolean firstAlert = false;

    private Thread thread;

    private Date nowTime(){
        return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    public threadTask(Task tas) {
        this. task = tas;
        this.thread = new Thread(() -> {
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
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        for (Task thisTask : tasks) {
                            Platform.runLater(() -> {
                                Alert inf = new Alert(Alert.AlertType.INFORMATION);
                                inf.setTitle("Напоминание");
                                inf.setHeaderText("В " + date + " необходимо выполнить: " + thisTask.getTitle());
                                inf.show();
                            });
                            firstAlert = true;
                        }
                    }
                }
            }
        });
        thread.start();
        }


    public void setFinish() {
        this.finish =true;
    }
}
