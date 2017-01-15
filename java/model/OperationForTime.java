package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Класс OperationForTime.
 * Вспомагательный класс для обработки времени.
 */

public class OperationForTime {

    public static Date parseDate(String str) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(str);
    }

    /**
     * Метод localDateTimeToDate(LocalDateTime localDateTime).
     * преобразовует LocalDateTime в Date.
     * @param localDateTime дата типа LocalDateTime.
     * @return дата типа Date.
     */

    public static Date localDateTimeToDate(final LocalDateTime localDateTime) {
        Instant instant = localDateTime.
                atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * Метод dateToLocalDateTime(Date date).
     * преобразовует Date в LocalDateTime.
     * @param date дата типа Date.
     * @return дата типа LocalDateTime.
     */

    public static LocalDateTime dateToLocalDateTime(final Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * Метод plusTime(LocalDateTime date, Task t).
     * Добавляет к дате интервал из задачи.
     * @param date дата.
     * @param t задача для выбора интервала.
     * @return новая дата.
     */

    public static LocalDateTime plusTime(LocalDateTime date, Task t){
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

    /**
     * Метод minusTime(LocalDateTime date, Task t).
     * Отнимает от даты интервал из задачи.
     * @param date дата.
     * @param t задача для выбора интервала.
     * @return новая дата.
     */

    public static LocalDateTime minusTime(LocalDateTime date, Task t){
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
