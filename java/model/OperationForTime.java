package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Класс OperationForTime.
 * Вспомагательный класс для
 * обработки времени.
 */

public class OperationForTime {

    /**
     * Метод parseDate(String str).
     * создание даты по входной строке.
     * @param str строка.
     * @return дата.
     * @throws ParseException преобраз. даты.
     */

    public static Date parseDate(final String str) throws ParseException {
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

    public static LocalDateTime plusTime(final LocalDateTime date,
                                         final Task t) {
        LocalDateTime dates = date;
        if (t.getIntervalYear() != 0) {
            dates = dates.plusYears(t.getIntervalYear());
        }
        if (t.getIntervalMonth() != 0) {
            dates = dates.plusMonths(t.getIntervalMonth());
        }
        if (t.getIntervalDay() != 0) {
            dates = dates.plusDays(t.getIntervalDay());
        }
        if (t.getIntervalMinute() != 0) {
            dates = dates.plusMinutes(t.getIntervalMinute());
        }
        if (t.getIntervalHour() != 0) {
            dates = dates.plusHours(t.getIntervalHour());
        }
        if (t.getIntervalSecond() != 0) {
            dates = dates.plusSeconds(t.getIntervalSecond());
        }
        return dates;
    }

    /**
     * Метод minusTime(LocalDateTime date, Task t).
     * Отнимает от даты интервал из задачи.
     * @param date дата.
     * @param t задача для выбора интервала.
     * @return новая дата.
     */

    public static LocalDateTime minusTime(final LocalDateTime date,
                                          final Task t) {
        LocalDateTime dates = date;
        if (t.getIntervalYear() != 0) {
            dates = dates.minusYears(t.getIntervalYear());
        }
        if (t.getIntervalMonth() != 0) {
            dates = dates.minusMonths(t.getIntervalMonth());
        }
        if (t.getIntervalDay() != 0) {
            dates = dates.minusDays(t.getIntervalDay());
        }
        if (t.getIntervalMinute() != 0) {
            dates = dates.minusMinutes(t.getIntervalMinute());
        }
        if (t.getIntervalHour() != 0) {
            dates = dates.minusHours(t.getIntervalHour());
        }
        if (t.getIntervalSecond() != 0) {
            dates = dates.minusSeconds(t.getIntervalSecond());
        }
        return dates;
    }

    /**
     * Метод dateToLocalDate(Date date).
     * преобразовует Date в LocalDateTime.
     * @param date дата типа Date.
     * @return дата типа LocalDate.
     */

    public static LocalDate dateToLocalDate(Date date){
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Instant instant = date.toInstant();
        return instant.atZone(defaultZoneId).toLocalDate();
    }
}
