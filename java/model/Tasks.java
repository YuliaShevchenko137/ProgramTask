package model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class Tasks{

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
                    start1 = plusTime(start1, t);
                }
            }
            instant = Instant.ofEpochMilli(t.getEnd().getTime());
            LocalDateTime end1 = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            if(t.getEnd().after(end)){
                while(end1.isAfter(LocalDateTime.ofInstant(Instant.ofEpochMilli(end.getTime()), ZoneId.systemDefault()))){
                    end1 = minusTime(end1, t);
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
                start1 = plusTime(start1, t);
            }
        }
        return res;
    }
    
    static LocalDateTime plusTime(LocalDateTime date, Task t){
        if(t.getIntervalYear() != 0) {
            date = date.plusYears(t.getIntervalYear());
        }
        if(t.getIntervalMonth() != 0) {
            date = date.plusMonths(t.getIntervalMonth());
        }
        if(t.getIntervalDay() != 0) {
            date = date.plusDays(t.getIntervalDay());
        }
        if(t.getIntervalMinute() != 0) {
            date = date.plusMinutes(t.getIntervalMinute());
        }
        if(t.getIntervalHour() != 0) {
            date = date.plusHours(t.getIntervalHour());
        }
        if(t.getIntervalSecond() != 0) {
            date = date.plusSeconds(t.getIntervalSecond());
        }
        return date;
    }

    private static LocalDateTime minusTime(LocalDateTime date, Task t){
        if(t.getIntervalYear() != 0) {
            date = date.minusYears(t.getIntervalYear());
        }
        if(t.getIntervalMonth() != 0) {
            date = date.minusMonths(t.getIntervalMonth());
        }
        if(t.getIntervalDay() != 0) {
            date = date.minusDays(t.getIntervalDay());
        }
        if(t.getIntervalMinute() != 0) {
            date = date.minusMinutes(t.getIntervalMinute());
        }
        if(t.getIntervalHour() != 0) {
            date = date.minusHours(t.getIntervalHour());
        }
        if(t.getIntervalSecond() != 0) {
            date = date.minusSeconds(t.getIntervalSecond());
        }
        return date;
    }
}
