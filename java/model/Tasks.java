package model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Класс Tasks.
 * Обработка списка задач.
 * Создание календаря.
 */

public class Tasks {

    /**
     * Метод incoming(Iterable tasks, Date start, Date end).
     * выбор задач, попадающих в интервал.
     * @param tasks список задач.
     * @param start начало интервала.
     * @param end конеч интервала.
     * @return список задач.
     */

    private static Iterable<Task> incoming(Iterable<Task> tasks, Date start, Date end) {
        List<Task> list = new ArrayList<>();
        for(Task t:tasks) {
            if(t.isActive()){
                Date s = t.nextTimeAfter(start);
                if(!new Date(0).equals(s)){
                    if(s.equals(end) || s.before(end)){
                        list.add(t);
                    }
                }
            }
        }
        return list;
    }

    public static SortedMap<Date, Set<Task>> calendar(Iterable<Task> tasks, Date start, Date end) {
        Iterable<Task> t1 = incoming(tasks, start, end);
        List<Task> tasks1 = (List<Task>) t1;
        SortedMap<Date, Set<Task>> res = new TreeMap<>();
        Set<Task> s;
        for(Task t:tasks1){
            if(!t.isRepeated()){
                if(!res.containsKey(t.getStart())){
                    s = new HashSet<>();
                    s.add(t);
                    res.put(t.getStart(), s);
                }
                else{
                    res.get(t.getStart()).add(t);
                }
            }
            Instant instant = Instant.ofEpochMilli(t.getStart().getTime());
            LocalDateTime start1 = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            if(t.getStart().before(start)){
                while(start1.isBefore(LocalDateTime.ofInstant(Instant.ofEpochMilli(start.getTime()), ZoneId.systemDefault()))){
                    start1 = OperationForTime.plusTime(start1, t);
                }
            }
            instant = Instant.ofEpochMilli(t.getEnd().getTime());
            LocalDateTime end1 = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            if(t.getEnd().after(end)){
                while(end1.isAfter(LocalDateTime.ofInstant(Instant.ofEpochMilli(end.getTime()), ZoneId.systemDefault()))){
                    end1 = OperationForTime.minusTime(end1, t);
                }
            }
            while(start1.isBefore(end1)){
                instant = start1.atZone(ZoneId.systemDefault()).toInstant();
                Date startT = Date.from(instant);
                if(!res.containsKey(startT)){
                    s = new HashSet<>();
                    s.add(t);
                    res.put(startT, s);
                }
                else{
                    res.get(startT).add(t);
                }
                start1 = OperationForTime.plusTime(start1, t);
            }
        }
        return res;
    }

}
