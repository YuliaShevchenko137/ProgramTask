package model;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TaskIO {

    private static void write(TaskList tasks, Writer out) throws IOException {
        Iterator<Task> i = tasks.iterator();
        for(; i.hasNext();){
            Task t = i.next();
            String s = createMessage(t);
            out.write(s);
        }
        out.close();
    }

    private static String createMessage(Task t) {
        String str = "\"" + t.getTitle();
        if(!t.isRepeated()){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            str+="\" at [" + dateFormat.format(t.getStart()) + "]";
            if(t.isActive()) str+=" inactive;" + "\n";
            else str += ";" + "\n";
        }
        else{
            String interval = t.getInterval();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            str+="\" from [" + dateFormat.format(t.getStart()) + "] to [" + dateFormat.format(t.getEnd()) + "] every [" + interval + "] ";
            if(t.isActive()) str+=" inactive;" + "\n";
            else str += ";" + "\n";
        }
        return str;
    }

    public static Date parseDate(String str) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(str);
    }

    private static String takeText(Reader in) throws IOException {
        int a = in.read();
        String str = "";
        while(a != -1){
            str += (char) a;
            a = in.read();
        }
        return str;
    }

    private static Task createNoRepeatedTask(String[] words) throws ParseException {
        int st = 0;
        for (int i = 0; i < words.length; i++) {
            if ("at".equals(words[i])) {
                st = i;
                break;
            }
        }
        String title = "";
        for (int i = 0; i < st; i++) {
            title += words[i];
        }
        title = title.replace("\"\"", "**").replace("\"", "").replace("**","\"");
        boolean active = "inactive;".equals(words[words.length - 1]);
        String date = (words[st + 1] + " " + words[st + 2]);
        date = date.substring(1, date.length()-2);
        Task task = new Task(title, parseDate(date));
        task.setRepeated(false);
        task.setActive(active);
        return task;
    }

    private static Task createRepeatedTask(String[] words) throws ParseException {
        int st = 0;
        for (int i = 0; i < words.length; i++) {
            if ("from".equals(words[i])) {
                st = i;
                break;
            }
        }
        String title = "";
        for (int i = 0; i < st; i++) {
            title += words[i];
        }
        title = title.replace("\"\"", "**").replace("\"", "").replace("**","\"");
        boolean active = false;
        if ("inactive;".equals(words[words.length - 1])) active = true;
        String start = words[st + 1] + " " + words[st + 2];
        start = start.substring(1, start.length() - 2);
        String end = words[st + 4] + " " + words[st + 5];
        end = end.substring(1, end.length()-2);
        int intervalYear = Integer.parseInt(words[st + 7].substring(1));
        int intervalMonth = Integer.parseInt(words[st + 9]);
        int intervalDay = Integer.parseInt(words[st + 11]);
        int intervalHour = Integer.parseInt(words[st + 13]);
        int intervalMinute = Integer.parseInt(words[st + 15]);
        int intervalSeconds = Integer.parseInt(words[st + 17]);
        Task task = new Task(title, parseDate(start), parseDate(end), intervalYear,
                intervalMonth, intervalDay, intervalHour, intervalMinute, intervalSeconds);
        task.setRepeated(true);
        task.setActive(active);
        return task;
    }

    private static void read(TaskList tasks, Reader in) throws IOException, ParseException {
        String str = takeText(in);
        String[] lines = str.split("\\n");
        if(tasks == null) tasks = new ArrayTaskList();
        for(String Str : lines) {
            String[] words = Str.split(" ");
            for (String word : words) {
                if ("from".equals(word)) {
                    tasks.add(createRepeatedTask(words));
                    break;
                }
                if ("at".equals(word)) {
                    tasks.add(createNoRepeatedTask(words));
                    break;
                }
            }
        }
    }

    public static void writeText(TaskList tasks, File file) throws IOException {
        Writer out = new FileWriter(file, false);
        write(tasks, out);
    }

    public static void readText(TaskList tasks, File file) throws IOException, ParseException {
        if(file.exists() || file.length() != 0){
            Reader in = new FileReader(file);
            read(tasks, in);
        }
    }

    private static void write(Map<Date, Set<Task>> map, Writer out) throws IOException {
        Set<Date> dates = map.keySet();
        for(Date date : dates){
            String str = date + ": \n";
            out.write(str);
            Set<Task> tasks = map.get(date);
            for(Task t : tasks) out.write(createMessage(t));
        }
        out.close();
    }

    public static void writeMap(Map<Date, Set<Task>> maps, File file) throws IOException {
        Writer out = new FileWriter(file);
        write(maps, out);
    }
}
