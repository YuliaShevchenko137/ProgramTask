package com.netcracker.java.YuliaShevchenko.lab1.model;


import java.text.SimpleDateFormat;

public class Constants {

    private static final int startSize = 10;

    private static final int nulls = 0;
    private static final int three = 3;
    private static final int ten = 10;
    private static final int eleven = 11;
    private static final int twentythree = 23;
    private static final int twentynine = 29;
    private static final int fiftynine = 59;

    private static final int second = 1000;
    private static final int halfhour = 30 * 60 * 1000;

    private static final String formatTime = "HH:mm:ss";
    private static final String regexp = "[ \\t\\n]+";

    private static SimpleDateFormat dateFormat =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final String space = " ";
    private static final String enter = "\n";

    private static final String title = "Title";
    private static final String time = "Time";
    private static final String start = "Start";
    private static final String end = "End";
    private static final String interval = "Interval";
    private static final String active = "Active";
    private static final String countTask = "Count tasks: ";
    private static final String apply = "Apply";
    private static final String change = "Change";
    private static final String at = "at";
    private static String from = "from";
    private static String inactive = "inactive";

    private static final String errorinterval
            = "interval = 0 for repeated task";
    private static final String errorcountmonth
            = "incorrect number of months";
    private static final String errorcountday
            = "incorrect number of days";
    private static final String errorcounthour
            = "incorrect number of hours";
    private static final String errorcountminute
            = "incorrect number of minutes";
    private static final String errorcountsecond
            = "incorrect number of seconds";
    private static final String errorTime
            = "Incorrect time";
    private static final String errorStart
            = "Incorrect time of start";
    private static final String errorEnd
            = "Incorrect time of end";
    private static final String errorEarlierTime
            = "End of execution earlier than the beginning,"
            + " or they are identical";


    /**
     * Start size of ArrayTaskList.
     */
    public static int getStartSize() {
        return startSize;
    }

    /**
     * Zero.
     */
    public static int getNulls() {
        return nulls;
    }

    public static int getTen() {
        return ten;
    }

    public static String getSpace() {
        return space;
    }

    public static String getErrorinterval() {
        return errorinterval;
    }

    public static String getErrorcountmonth() {
        return errorcountmonth;
    }

    public static String getErrorcountday() {
        return errorcountday;
    }

    public static String getErrorcounthour() {
        return errorcounthour;
    }

    public static String getErrorcountminute() {
        return errorcountminute;
    }

    public static String getErrorcountsecond() {
        return errorcountsecond;
    }

    public static String getFormatTime() {
        return formatTime;
    }

    public static String getErrorTime() {
        return errorTime;
    }

    public static String getErrorStart() {
        return errorStart;
    }

    public static String getErrorEnd() {
        return errorEnd;
    }

    public static String getEnter() {
        return enter;
    }

    public static String getErrorEarlierTime() {
        return errorEarlierTime;
    }

    public static int getEleven() {
        return eleven;
    }

    public static int getTwentythree() {
        return twentythree;
    }

    public static int getTwentynine() {
        return twentynine;
    }

    public static int getFiftynine() {
        return fiftynine;
    }

    public static String getTime() {
        return time;
    }

    public static String getStart() {
        return start;
    }

    public static String getRegexp() {
        return regexp;
    }

    public static String getCountTask() {
        return countTask;
    }

    public static String getChange() {
        return change;
    }

    public static String getApply() {
        return apply;
    }

    public static int getThree() {
        return three;
    }

    public static String getTitle() {
        return title;
    }

    public static String getEnd() {
        return end;
    }

    public static String getInterval() {
        return interval;
    }

    public static String getActive() {
        return active;
    }

    public static SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public static void setDateFormat(SimpleDateFormat dateFormat) {
        Constants.dateFormat = dateFormat;
    }

    public static String getAt() {
        return at;
    }

    public static String getFrom() {
        return from;
    }

    public static void setFrom(String from) {
        Constants.from = from;
    }

    public static String getInactive() {
        return inactive;
    }

    public static void setInactive(String inactive) {
        Constants.inactive = inactive;
    }

    public static int getSecond() {
        return second;
    }

    public static int getHalfhour() {
        return halfhour;
    }
}
