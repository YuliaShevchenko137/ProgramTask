package com.netcracker.java.YuliaShevchenko.lab1.model;


import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Class TaskIO.
 * Reading and writing to a file.
 */

public final class TaskIO {

    /**
     * logger.
     * It is used to register error.
     */

    private static final Logger logger = Logger.getLogger(TaskIO.class);

    /**
     * st.
     * Symbol ".
     */

    private static String st = "\"";

    /**
     * leftbrasket.
     * Symbol [.
     */

    private static String leftbrasket = " [";

    /**
     * rightbrasket.
     * Symbol ].
     */

    private static String rightbrasket = "] ";

    /**
     * Empty constructor TaskIO().
     */

    private TaskIO() {

    }

    /**
     * Method write(TaskList tasks, Writer out).
     * Write task list to stream.
     * @param tasks task list.
     * @param out stream for write.
     */

    private static void write(final TaskList tasks,
                              final Writer out) {
        Iterator<Task> i = tasks.iterator();
        try {
            for (; i.hasNext();) {
                Task t = i.next();
                String s = createMessage(t);
                out.write(s);
            }
            out.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Method createMessage(final Task t).
     * Create a message that describes the current task.
     * @param t current task.
     * @return results message .
     */

    private static String createMessage(final Task t) {
        String str = st + t.getTitle();
        if (!t.isRepeated()) {
            str += st + Constants.getSpace() + Constants.getAt() + leftbrasket
                    + Constants.getDateFormat().format(t.getStart())
                    + rightbrasket;
            if (t.isActive()) {
                str += Constants.getInactive() + Constants.getSemicolon()
                        + Constants.getEnter();
            } else {
                str += Constants.getSemicolon() + Constants.getEnter();
            }
        } else {
            String interval = t.getInterval();
            str += st + Constants.getSpace() + Constants.getFrom() + leftbrasket
                    + Constants.getDateFormat().format(t.getStart()) + "] to ["
                    + Constants.getDateFormat().format(t.getEnd())
                    + "] every [" + interval + rightbrasket;
            if (t.isActive()) {
                str += Constants.getSpace() + Constants.getInactive()
                        + Constants.getSemicolon() + Constants.getEnter();
            } else {
                str += Constants.getSemicolon() + Constants.getEnter();
            }
        }
        return str;
    }

    /**
     * Method takeText(Reader in).
     * Character by character read from the stream.
     * @param in stream for reading.
     * @return created text.
     */

    private static String takeText(final Reader in) {
        String str = "";
        try {
            int a = in.read();
            while (a != -1) {
                str += (char) a;
                a = in.read();
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return str;
    }

    /**
     * Method createNoRepeatedTask(String[] words).
     * Create non recurring task.
     * @param words array of the words which describes the task.
     * @return created task.
     */

    private static Task createNoRepeatedTask(
            final String[] words) {
        int s = Constants.getNulls();
        for (int i = Constants.getNulls(); i < words.length; i++) {
            if (Constants.getAt().equals(words[i])) {
                s = i;
                break;
            }
        }
        String title = "";
        for (int i = Constants.getNulls(); i < s; i++) {
            title += words[i];
        }
        title = title.substring(Constants.getOne(),
                title.length() - Constants.getTwo());
        boolean actives = (Constants.getInactive()
                + Constants.getSemicolon()).equals(
                        words[words.length - Constants.getOne()]);
        String date = words[s + Constants.getOne()] + Constants.getSpace()
                + words[s + Constants.getTwo()];
        date = date.substring(Constants.getOne(), date.length()
                - Constants.getTwo());
        Task task = new Task(title, OperationForTime.parseDate(date));
        task.setRepeated(false);
        task.setActive(actives);
        return task;
    }

    /**
     * Method createRepeatedTask(String[] words).
     * Create repeated task.
     * @param words array of the words which describes the task.
     * @return created task.
     */

    private static Task createRepeatedTask(
            final String[] words) {
        int s = Constants.getNulls();
        for (int i = Constants.getNulls(); i < words.length; i++) {
            if (Constants.getFrom().equals(words[i])) {
                s = i;
                break;
            }
        }
        String title = "";
        for (int i = Constants.getNulls(); i < s; i++) {
            title += words[i];
        }
        title = title.substring(Constants.getOne(), title.length()
                - Constants.getOne());
        String start = words[s + Constants.getOne()] + Constants.getSpace()
                + words[s + Constants.getTwo()];
        int count = Constants.getTwo();
        start = start.substring(Constants.getOne(), start.length()
                - Constants.getOne());
        String end = words[s + Constants.getTwo() * count]
                + Constants.getSpace() + words[s
                + Constants.getTwo() * count + Constants.getOne()];
        end = end.substring(Constants.getOne(), end.length()
                - Constants.getOne());
        count++;
        final int intervalYear = Integer.parseInt(words[s
                + Constants.getTwo() * count
                + Constants.getOne()].substring(Constants.getOne()));
        count++;
        final int intervalMonth = Integer.parseInt(words[s
                + Constants.getTwo() * count + Constants.getOne()]);
        count++;
        final int intervalDay = Integer.parseInt(words[s
                + Constants.getTwo() * count + Constants.getOne()]);
        count++;
        final int intervalHour = Integer.parseInt(words[s
                + Constants.getTwo() * count + Constants.getOne()]);
        count++;
        final int intervalMinute = Integer.parseInt(words[s
                + Constants.getTwo() * count + Constants.getOne()]);
        count++;
        final int intervalSeconds = Integer.parseInt(words[s
                + Constants.getTwo() * count + Constants.getOne()]);
        CreateInterval interval = new CreateInterval(intervalYear,
                intervalMonth, intervalDay, intervalHour,
                intervalMinute, intervalSeconds);
        Task task = new Task(title, OperationForTime.parseDate(start),
                OperationForTime.parseDate(end), interval);
        task.setRepeated(true);
        task.setActive((Constants.getInactive()
                + Constants.getSemicolon()).equals(words[words.length
                - Constants.getOne()]));
        return task;
    }

    /**
     * Method read(TaskList tasks, Reader in).
     * Read from the stream.
     * @param tasks task list to which you add a new task.
     * @param in stream for read.
     */

    private static void read(final TaskList tasks, final Reader in) {
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
     * Method writeText(TaskList tasks, File file).
     * Write to the file.
     * @param tasks task list.
     * @param file file for write.
     */

    public static void writeText(final TaskList tasks, final File file) {
        try {
            Writer out = new FileWriter(file, false);
            write(tasks, out);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

    }

    /**
     * Method readText(TaskList tasks, final File file).
     * Reading from a file.
     * @param tasks task list.
     * @param file file for reading.
     */

    public static void readText(final TaskList tasks, final File file) {
        if (file.exists() || file.length() != Constants.getNulls()) {
            try {
                Reader in = new FileReader(file);
                read(tasks, in);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }

        }
    }

    /**
     * Method write(final Map map, final Writer out).
     * Write map to the stream.
     * @param map map with tasks.
     * @param out stream for writing.
     */

    private static void writeMaps(final Map<Date, Set<Task>> map,
                                  final Writer out) {
        try {
            Set<Date> dates = map.keySet();
            for (Date date : dates) {
                String str = date + ":" + Constants.getSpace()
                        + Constants.getEnter();
                out.write(str);
                Set<Task> tasks = map.get(date);
                for (Task t : tasks) {
                    out.write(createMessage(t));
                }
            }
            out.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Метод writeMap(final Map maps, final File file).
     * Write map to the file.
     * @param maps map with tasks.
     * @param file file for writing.
     */

    public static void writeMap(final Map<Date, Set<Task>> maps,
                                final File file) {
        Writer out = null;
        try {
            out = new FileWriter(file);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        writeMaps(maps, out);
    }
}
