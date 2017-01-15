package model;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Класс TaskIO.
 * Работа с файлами.
 * Чтение и запись.
 */

public class TaskIO {

    /**
     * Символ ".
     */

    private static String st = "\"";

    /**
     * Символ переноса строки.
     */

    private static String enter = "\n";

    /**
     * Маркер неповторения.
     */

    private static String at = "at";

    /**
     * Маркер повторения.
     */

    private static String from = "from";

    /**
     * Формат даты.
     */

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Пустой конструктор TaskIO().
     */

    private TaskIO(){

    }

    /**
     * Метод write(TaskList tasks, Writer out).
     * Запить списка задач в поток.
     * @param tasks список задач.
     * @param out поток для записи.
     * @throws IOException рабоат с файлами.
     */

    private static void write(final TaskList tasks, final Writer out) throws IOException {
        Iterator<Task> i = tasks.iterator();
        for ( ; i.hasNext(); ) {
            Task t = i.next();
            String s = createMessage(t);
            out.write(s);
        }
        out.close();
    }

    /**
     * Метод createMessage(final Task t).
     * Создание сообщения про текущую задачу.
     * @param t задача.
     * @return строка - результат.
     */

    private static String createMessage(final Task t) {
        String str = st + t.getTitle();
        if (!t.isRepeated()) {
            str += st + " " + at + " [" + dateFormat.format(t.getStart()) + "]";
            if (t.isActive()) {
                str += " inactive;" + enter;
            } else {
                str += ";" + enter;
            }
        } else {
            String interval = t.getInterval();
            str += st + " " + from +" [" + dateFormat.format(t.getStart()) + "] to ["
                    + dateFormat.format(t.getEnd()) + "] every ["
                    + interval + "] ";
            if (t.isActive()) {
                str += " inactive;" + enter;
            } else {
                str += ";" + enter;
            }
        }
        return str;
    }

    /**
     * Метод takeText(Reader in).
     * Посимвольный выбор информации в текст.
     * @param in поток.
     * @return текст из потока.
     * @throws IOException чтение из потока.
     */

    private static String takeText(final Reader in)
            throws IOException {
        int a = in.read();
        String str = "";
        while (a != -1) {
            str += (char) a;
            a = in.read();
        }
        return str;
    }

    /**
     *Метод createNoRepeatedTask(String[] words).
     * Создание неповторяющейся задачи.
     * @param words набор слов о задаче.
     * @return созданая задача.
     * @throws ParseException преобразование дат.
     */

    private static Task createNoRepeatedTask(
            final String[] words) throws ParseException {
        int s = 0;
        for (int i = 0; i < words.length; i++) {
            if (at.equals(words[i])) {
                s = i;
                break;
            }
        }
        String title = "";
        for (int i = 0; i < s; i++) {
            title += words[i];
        }
        title = title.replace(" " + st, " ");
        boolean active = "inactive;".equals(words[words.length - 1]);
        String date = (words[s + 1] + " " + words[s + 2]);
        date = date.substring(1, date.length() - 2);
        Task task = new Task(title, OperationForTime.parseDate(date));
        task.setRepeated(false);
        task.setActive(active);
        return task;
    }

    /**
     *Метод createRepeatedTask(String[] words).
     * Создание повторяющейся задачи.
     * @param words набор слов о задаче.
     * @return созданая задача.
     * @throws ParseException преобразование дат.
     */

    private static Task createRepeatedTask(
            final String[] words) throws ParseException {
        int st = 0;
        for (int i = 0; i < words.length; i++) {
            if (from.equals(words[i])) {
                st = i;
                break;
            }
        }
        String title = "";
        for (int i = 0; i < st; i++) {
            title += words[i];
        }
        title = title.replace(" " + st, " ");
        boolean active = false;
        if ("inactive;".equals(words[words.length - 1])) {
            active = true;
        }
        int count = 1;
        String start = words[st + 2 * count - 1] + " " + words[st + 2 * count];
        count++;
        start = start.substring(1, start.length() - 2);
        String end = words[st + 2 * count] + " " + words[st + 2 * count + 1];
        end = end.substring(1, end.length()-2);
        count++;
        int intervalYear = Integer.parseInt(words[st + 2 * count + 1].substring(1));
        count++;
        int intervalMonth = Integer.parseInt(words[st + 2 * count + 1]);
        count++;
        int intervalDay = Integer.parseInt(words[st + 2 * count + 1]);
        count++;
        int intervalHour = Integer.parseInt(words[st + 2 * count + 1]);
        count++;
        int intervalMinute = Integer.parseInt(words[st + 2 * count + 1]);
        count++;
        int intervalSeconds = Integer.parseInt(words[st + 2 * count + 1]);
        Task task = new Task(title, OperationForTime.parseDate(start),
                OperationForTime.parseDate(end), intervalYear, intervalMonth,
                intervalDay, intervalHour, intervalMinute, intervalSeconds);
        task.setRepeated(true);
        task.setActive(active);
        return task;
    }

    /**
     * Метод read(TaskList tasks, Reader in).
     * Чтение из файла.
     * @param tasks список задач для записи.
     * @param in поток для записи.
     * @throws IOException работа с потоком.
     * @throws ParseException преобразование дат.
     */

    private static void read(TaskList tasks, Reader in) throws IOException, ParseException {
        String str = takeText(in);
        String[] lines = str.split(enter);
        if(tasks == null) tasks = new ArrayTaskList();
        for(String Str : lines) {
            String[] words = Str.split(" ");
            for (String word : words) {
                if (from.equals(word)) {
                    tasks.add(createRepeatedTask(words));
                    break;
                }
                if (at.equals(word)) {
                    tasks.add(createNoRepeatedTask(words));
                    break;
                }
            }
        }
    }

    /**
     * Метод writeText(TaskList tasks, File file).
     * Записть списка задач в файл.
     * @param tasks список задач.
     * @param file файл для записи.
     * @throws IOException работа с файлами.
     */

    public static void writeText(final TaskList tasks,
                                 final File file) throws IOException {
        Writer out = new FileWriter(file, false);
        write(tasks, out);
    }

    /**
     * Метод readText(TaskList tasks, final File file).
     * Считывание задач из файла.
     * @param tasks список задач.
     * @param file файл для чтения.
     * @throws IOException работа с файлами.
     * @throws ParseException преобразование дат.
     */

    public static void readText(TaskList tasks,
                                final File file) throws IOException, ParseException {
        if(file.exists() || file.length() != 0){
            Reader in = new FileReader(file);
            read(tasks, in);
        }
    }

    /**
     * Метод write(final Map map, final Writer out).
     * Запись карты в поток.
     * @param map карта с задачами.
     * @param out поток для записи.
     * @throws IOException работа с потоками.
     */

    private static void write(final Map<Date, Set<Task>> map,
                              final Writer out) throws IOException {
        Set<Date> dates = map.keySet();
        for(Date date : dates){
            String str = date + ": \n";
            out.write(str);
            Set<Task> tasks = map.get(date);
            for(Task t : tasks) out.write(createMessage(t));
        }
        out.close();
    }

    /**
     * Метод writeMap(final Map maps, final File file).
     * Запись карты в файл.
     * @param maps карта с задачами.
     * @param file файл для записи.
     * @throws IOException работа с файлами.
     */

    public static void writeMap(final Map<Date, Set<Task>> maps,
                                final File file) throws IOException {
        Writer out = new FileWriter(file);
        write(maps, out);
    }
}
