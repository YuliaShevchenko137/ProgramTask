package com.netcracker.java.YuliaShevchenko.lab1.model;

import java.text.SimpleDateFormat;

/**
 * Class Constants.
 * Helper class with the constants
 */

public final class Constants {

    /**
     * startSize.
     * the initial length of the array.
     */

    private static final int startSize = 10;

    /**
     * 0.
     */

    private static final int nulls = 0;

    /**
     * 1.
     */

    private static final int one = 1;

    /**
     * 2.
     */

    private static final int two = 2;

    /**
     * 3.
     */

    private static final int three = 3;

    /**
     * 10.
     */

    private static final int ten = 10;

    /**
     * 11.
     */

    private static final int eleven = 11;

    /**
     * 23.
     */

    private static final int twentythree = 23;

    /**
     * 29.
     */

    private static final int twentynine = 29;

    /**
     * 59.
     */

    private static final int fiftynine = 59;

    /**
     * 1 second.
     */

    private static final int second = 1000;

    /**
     * 30 minutes.
     */

    private static final int halfhour = 30 * 60 * 1000;

    /**
     * Time Format.
     */

    private static final String formatTime = "HH:mm:ss";

    /**
     * Regular expression.
     */

    private static final String regexp = "[ \\t\\n]+";

    /**
     * DateTime format.
     */

    private static SimpleDateFormat dateFormat =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Space.
     */

    private static final String space = " ";

    /**
     * Line break.
     */

    private static final String enter = "\n";

    /**
     * Semicolon.
     */

    private static final String semicolon = ";";

    /**
     * Title.
     */

    private static final String title = "Title";

    /**
     * Time.
     */

    private static final String time = "Time";

    /**
     * Start.
     */

    private static final String start = "Start";

    /**
     * End.
     */

    private static final String end = "End";

    /**
     * Interval.
     */

    private static final String interval = "Interval";

    /**
     * Active.
     */

    private static final String active = "Active";

    /**
     * Count Task: .
     */

    private static final String countTask = "Count tasks: ";

    /**
     * Apply.
     */

    private static final String apply = "Apply";

    /**
     * Change.
     */

    private static final String change = "Change";

    /**
     * at.
     */

    private static final String at = "at";

    /**
     * from.
     */

    private static final String from = "from";

    /**
     * inactive.
     */

    private static final String inactive = "inactive";

    /**
     * Incorrect interval.
     */

    private static final String errorinterval
            = "interval = 0 for repeated task";

    /**
     * incorrect count of months.
     */

    private static final String errorcountmonth
            = "incorrect number of months";

    /**
     * incorrect count of days.
     */

    private static final String errorcountday
            = "incorrect number of days";

    /**
     * incorrect count of hours.
     */

    private static final String errorcounthour
            = "incorrect number of hours";

    /**
     * incorrect count of minutes.
     */

    private static final String errorcountminute
            = "incorrect number of minutes";

    /**
     * incorrect count of seconds.
     */

    private static final String errorcountsecond
            = "incorrect number of seconds";

    /**
     * incorrect time.
     */

    private static final String errorTime
            = "Incorrect time";

    /**
     * incorrect start time.
     */

    private static final String errorStart
            = "Incorrect time of start";
    /**
     * incorrect end time.
     */

    private static final String errorEnd
            = "Incorrect time of end";

    /**
     * End time earlier start time.
     */

    private static final String errorEarlierTime
            = "End of execution earlier than the beginning"
            + " or they are identical";

    /**
     * Empty constructor Constants().
     */

    private Constants() {

    }

    /**
     * Method getStartSize().
     * Return start size of ArrayTaskList.
     * @return current startSize.
     */

    public static int getStartSize() {
        return startSize;
    }

    /**
     * Method getNulls().
     * Getter for zero.
     * @return 0.
     */
    public static int getNulls() {
        return nulls;
    }

    /**
     * Method getTen().
     * Getter for ten.
     * @return 10.
     */

    public static int getTen() {
        return ten;
    }

    /**
     * Method getSpace().
     * Getter for space.
     * @return space.
     */

    public static String getSpace() {
        return space;
    }

    /**
     * Method getErrorinterval().
     * Getter for error of the incorrect interval.
     * @return current errorinterval.
     */

    public static String getErrorinterval() {
        return errorinterval;
    }

    /**
     * Method getErrorcountmonth().
     * Getter for error of the incorrect count of months.
     * @return current errorcountmonth.
     */

    public static String getErrorcountmonth() {
        return errorcountmonth;
    }

    /**
     * Method getErrorcountday().
     * Getter for error of the incorrect count of days.
     * @return current errorcountday.
     */

    public static String getErrorcountday() {
        return errorcountday;
    }

    /**
     * Method getErrorcounthour().
     * Getter for error of the incorrect count of hours.
     * @return current errorcounthour.
     */

    public static String getErrorcounthour() {
        return errorcounthour;
    }

    /**
     * Method getErrorcountminute().
     * Getter for error of the incorrect count of minutes.
     * @return current errorcountminute.
     */

    public static String getErrorcountminute() {
        return errorcountminute;
    }

    /**
     * Method getErrorcountsecond().
     * Getter for error of the incorrect count of seconds.
     * @return current errorcountsecond.
     */

    public static String getErrorcountsecond() {
        return errorcountsecond;
    }

    /**
     * Method getFormatTime().
     * Getter for fime formar.
     * @return current formaTtime.
     */

    public static String getFormatTime() {
        return formatTime;
    }

    /**
     * Method getErrorTime().
     * Getter for error of the incorrect time.
     * @return current errorTime.
     */

    public static String getErrorTime() {
        return errorTime;
    }

    /**
     * Method getErrorStart().
     * Getter for error of the incorrect start time.
     * @return current errorStart.
     */

    public static String getErrorStart() {
        return errorStart;
    }

    /**
     * Method getErrorEnd().
     * Getter for error of the incorrect end time.
     * @return current errorEnd.
     */

    public static String getErrorEnd() {
        return errorEnd;
    }

    /**
     * Method getEnter().
     * Getter for line break.
     * @return enter.
     */

    public static String getEnter() {
        return enter;
    }

    /**
     * Method getErrorEarlierTime().
     * Getter for error of the end time earlier start time.
     * @return errorEarlierTime.
     */

    public static String getErrorEarlierTime() {
        return errorEarlierTime;
    }

    /**
     * Method getEleven().
     * Getter for eleven.
     * @return 11.
     */

    public static int getEleven() {
        return eleven;
    }

    /**
     * Method getTwentythree().
     * Getter for twentythree.
     * @return 23.
     */

    public static int getTwentythree() {
        return twentythree;
    }

    /**
     * Method getTwentynine().
     * Getter for twentynine.
     * @return 29.
     */

    public static int getTwentynine() {
        return twentynine;
    }

    /**
     * Method getFiftynine().
     * Getter for fiftynine.
     * @return 59.
     */

    public static int getFiftynine() {
        return fiftynine;
    }

    /**
     * Method getTime().
     * Getter for "Time".
     * @return Time.
     */

    public static String getTime() {
        return time;
    }

    /**
     * Method getStart().
     * Getter for "Start".
     * @return Start.
     */

    public static String getStart() {
        return start;
    }

    /**
     * Method getRegexp().
     * Getter for regular expression.
     * @return current regexp.
     */

    public static String getRegexp() {
        return regexp;
    }

    /**
     * Method getCountTask().
     * Getter for "Count task: ".
     * @return "Count task: ".
     */

    public static String getCountTask() {
        return countTask;
    }

    /**
     * Method getChange().
     * Getter for "Change".
     * @return Change.
     */

    public static String getChange() {
        return change;
    }

    /**
     * Method getApply().
     * Getter for "Apply".
     * @return Apply.
     */

    public static String getApply() {
        return apply;
    }

    /**
     * Method getThree().
     * Getter for three.
     * @return 3.
     */

    public static int getThree() {
        return three;
    }

    /**
     * Method getTitle().
     * Getter for "Title".
     * @return Title.
     */

    public static String getTitle() {
        return title;
    }

    /**
     * Method getEnd().
     * Getter for "End".
     * @return End.
     */

    public static String getEnd() {
        return end;
    }

    /**
     * Method getInterval().
     * Getter for "Interval".
     * @return Interval.
     */

    public static String getInterval() {
        return interval;
    }

    /**
     * Method getActive().
     * Getter for "Active".
     * @return Active.
     */

    public static String getActive() {
        return active;
    }

    /**
     * Method getDateFormat().
     * Getter for DateTime format.
     * @return current datetime format.
     */

    public static SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    /**
     * Method getAt().
     * Getter for "at".
     * @return at.
     */

    public static String getAt() {
        return at;
    }

    /**
     * Method getFrom().
     * Getter for "from".
     * @return from.
     */

    public static String getFrom() {
        return from;
    }

    /**
     * Method getInactive().
     * Getter for "inactive".
     * @return inactive.
     */

    public static String getInactive() {
        return inactive;
    }

    /**
     * Method getSecond().
     * Getter for 1 second.
     * @return 1000 ms.
     */

    public static int getSecond() {
        return second;
    }

    /**
     * Method getHalfhour().
     * Getter for 30 minutes.
     * @return 30 * 60 * 1000 ms.
     */

    public static int getHalfhour() {
        return halfhour;
    }

    /**
     * Method getSemicolon().
     * Getter for ";".
     * @return ;.
     */

    public static String getSemicolon() {
        return semicolon;
    }

    /**
     * Method getOne().
     * Getter for one.
     * @return 1.
     */

    public static int getOne() {
        return one;
    }

    /**
     * Method getTwo().
     * Getter for two.
     * @return 2.
     */

    public static int getTwo() {
        return two;
    }
}
