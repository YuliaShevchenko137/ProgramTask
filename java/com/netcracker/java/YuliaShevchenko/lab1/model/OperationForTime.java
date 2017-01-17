package com.netcracker.java.YuliaShevchenko.lab1.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Class OperationForTime.
 * Helper class to handle dates.
 */

public final class OperationForTime {

    /**
     * Empty constructor.
     */

    private OperationForTime() {

    }

    /**
     * Method parseDate(String str).
     * Creation date of a specified format
     * @param str format.
     * @return creation date.
     * @throws ParseException appears when converting dates.
     */

    public static Date parseDate(final String str) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(str);
    }

    /**
     * Method localDateTimeToDate(LocalDateTime localDateTime).
     * Conversion LocalDateTime in Date.
     * @param localDateTime date type LocalDateTime.
     * @return date type Date.
     */

    public static Date localDateTimeToDate(final LocalDateTime localDateTime) {
        Instant instant = localDateTime.
                atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * Method dateToLocalDateTime(Date date).
     * Conversion Date in LocalDateTime.
     * @param date date type Date.
     * @return date type LocalDateTime.
     */

    public static LocalDateTime dateToLocalDateTime(final Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * Method plusTime(LocalDateTime date, Task t).
     * Adds to the date of the repeat interval.
     * @param date current date.
     * @param t task for giving interval.
     * @return new date.
     */

    public static LocalDateTime plusTime(final LocalDateTime date,
                                         final Task t) {
        LocalDateTime dates = date;
        if (t.getCreateInterval().getIntervalYear() != 0) {
            dates = dates.plusYears(t.getCreateInterval().getIntervalYear());
        }
        if (t.getCreateInterval().getIntervalMonth() != 0) {
            dates = dates.plusMonths(t.getCreateInterval().getIntervalMonth());
        }
        if (t.getCreateInterval().getIntervalDay() != 0) {
            dates = dates.plusDays(t.getCreateInterval().getIntervalDay());
        }
        if (t.getCreateInterval().getIntervalMinute() != 0) {
            dates = dates.plusMinutes(t.getCreateInterval().getIntervalMinute());
        }
        if (t.getCreateInterval().getIntervalHour() != 0) {
            dates = dates.plusHours(t.getCreateInterval().getIntervalHour());
        }
        if (t.getCreateInterval().getIntervalSecond() != 0) {
            dates = dates.plusSeconds(t.getCreateInterval().getIntervalSecond());
        }
        return dates;
    }

    /**
     * Method minusTime(LocalDateTime date, Task t).
     * Subtracted from the the date of the repeat interval.
     * @param date current date.
     * @param t task for giving interval.
     * @return new date.
     */

    public static LocalDateTime minusTime(final LocalDateTime date,
                                          final Task t) {
        LocalDateTime dates = date;
        if (t.getCreateInterval().getIntervalYear() != 0) {
            dates = dates.minusYears(t.getCreateInterval().getIntervalYear());
        }
        if (t.getCreateInterval().getIntervalMonth() != 0) {
            dates = dates.minusMonths(t.getCreateInterval().getIntervalMonth());
        }
        if (t.getCreateInterval().getIntervalDay() != 0) {
            dates = dates.minusDays(t.getCreateInterval().getIntervalDay());
        }
        if (t.getCreateInterval().getIntervalMinute() != 0) {
            dates = dates.minusMinutes(t.getCreateInterval().getIntervalMinute());
        }
        if (t.getCreateInterval().getIntervalHour() != 0) {
            dates = dates.minusHours(t.getCreateInterval().getIntervalHour());
        }
        if (t.getCreateInterval().getIntervalSecond() != 0) {
            dates = dates.minusSeconds(t.getCreateInterval().getIntervalSecond());
        }
        return dates;
    }

    /**
     * Method dateToLocalDate(Date date).
     * Conversion Date in LocalDateTime.
     * @param date date type Date.
     * @return date type LocalDate.
     */

    public static LocalDate dateToLocalDate(final Date date) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Instant instant = date.toInstant();
        return instant.atZone(defaultZoneId).toLocalDate();
    }

    /**
     * Method nowTime().
     * @return current time.
     */

    public static Date nowTime() {
        return Date.from(LocalDateTime.now().
                atZone(ZoneId.systemDefault()).toInstant());
    }
}
