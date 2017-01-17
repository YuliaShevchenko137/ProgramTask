package com.netcracker.java.YuliaShevchenko.lab1.model;


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

public final class TaskIO {

    /**
     * Символ ".
     */

    private static String st = "\"";

    /**
     * Маркер [.
     */

    private static String leftbrasket = " [";

    /**
     * Маркер ].
     */

    private static String rightbrasket = "] ";

    /**
     * Пустой конструктор TaskIO().
     */

    private TaskIO() {

    }

    /**
     * Метод write(TaskList tasks, Writer out).
     * Запить списка задач в поток.
     * @param tasks список задач.
     * @param out поток для записи.
     * @throws IOException рабоат с файлами.
     */

    private static void write(final TaskList tasks,
                              final Writer out) throws IOException {
        Iterator<Task> i = tasks.iterator();
        for (; i.hasNext();) {
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
            str += st + Constants.getSpace() + Constants.getAt() + leftbrasket
                    + Constants.getDateFormat().format(t.getStart()) + rightbrasket;
            if (t.isActive()) {
                str += Constants.getInactive() + ";" + Constants.getEnter();
            } else {
                str += ";" + Constants.getEnter();
            }
        } else {
            String interval = t.getInterval();
            str += st + Constants.getSpace() + Constants.getFrom() + leftbrasket
                    + Constants.getDateFormat().format(t.getStart()) + "] to ["
                    + Constants.getDateFormat().format(t.getEnd()) + "] every ["
                    + interval + rightbrasket;
            if (t.isActive()) {
                str += Constants.getSpace() + Constants.getInactive() + ";" + Constants.getEnter();
            } else {
                str += ";" + Constants.getEnter();
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
            if (Constants.getAt().equals(words[i])) {
                s = i;
                break;
            }
        }
        String title = "";
        for (int i = 0; i < s; i++) {
            title += words[i];
        }
        title = title.substring(1, title.length() - 2);
        boolean actives = (Constants.getInactive() + ";").equals(words[words.length - 1]);
        String date = words[s + 1] + Constants.getSpace() + words[s + 2];
        date = date.substring(1, date.length() - 2);
        Task task = new Task(title, OperationForTime.parseDate(date));
        task.setRepeated(false);
        task.setActive(actives);
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
        int s = 0;
        for (int i = 0; i < words.length; i++) {
            if (Constants.getFrom().equals(words[i])) {
                s = i;
                break;
            }
        }
        String title = "";
        for (int i = 0; i < s; i++) {
            title += words[i];
        }
        title = title.substring(1, title.length() - 1);
        int count = 1;
        String start = words[s + 2 * count - 1] + Constants.getSpace()
                + words[s + 2 * count];
        count++;
        start = start.substring(1, start.length() - 2);
        String end = words[s + 2 * count] + Constants.getSpace()
                + words[s + 2 * count + 1];
        end = end.substring(1, end.length() - 2);
        count++;
        final int intervalYear = Integer.parseInt(words[s
                + 2 * count + 1].substring(1));
        count++;
        final int intervalMonth = Integer.parseInt(words[s + 2 * count + 1]);
        count++;
        final int intervalDay = Integer.parseInt(words[s + 2 * count + 1]);
        count++;
        final int intervalHour = Integer.parseInt(words[s + 2 * count + 1]);
        count++;
        final int intervalMinute = Integer.parseInt(words[s + 2 * count + 1]);
        count++;
        final int intervalSeconds = Integer.parseInt(words[s + 2 * count + 1]);
        CreateInterval interval = new CreateInterval(intervalYear, intervalMonth,
                intervalDay, intervalHour, intervalMinute, intervalSeconds);
        Task task = new Task(title, OperationForTime.parseDate(start),
                OperationForTime.parseDate(end), interval);
        task.setRepeated(true);
        task.setActive((Constants.getInactive() + ";").equals(words[words.length - 1]));
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

    private static void read(final TaskList tasks, final Reader in)
            throws IOException, ParseException {
        String str = takeText(in);
        String[] lines = str.split(Constants.getEnter());
        for (String strs : lines) {
            String[] words = strs.split(Constants.getSpace());
            for (String word : words) {
                if (Constants.getFrom().equals(word)) {
                    tasks.add(createRepeatedTask(words));
                    break;
                }
                if (Constants.getAt().equals(word)) {
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

    public static void writeText(final TaskList tasks, final File file)
            throws IOException {
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

    public static void readText(final TaskList tasks, final File file)
            throws IOException, ParseException {
        if (file.exists() || file.length() != 0) {
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

    private static void writeMaps(final Map<Date, Set<Task>> map,
                                  final Writer out)
            throws IOException {
        Set<Date> dates = map.keySet();
        for (Date date : dates) {
            String str = date + ":" + Constants.getSpace() + Constants.getEnter();
            out.write(str);
            Set<Task> tasks = map.get(date);
            for (Task t : tasks) {
                out.write(createMessage(t));
            }
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
                                final File file)
            throws IOException {
        Writer out = new FileWriter(file);
        writeMaps(maps, out);
    }
}
