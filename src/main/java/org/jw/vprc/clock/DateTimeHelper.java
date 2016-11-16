package org.jw.vprc.clock;

import org.joda.time.DateTime;

public class DateTimeHelper {

    public static int getNumericMonth(String month) {
        switch(month.toUpperCase()){
            case "JANUARY":
            case "JAN":
                return 1;
            case "FEBRUARY":
            case "FEB":
                return 2;
            case "MARCH":
            case "MAR":
                return 3;
            case "APRIL":
            case "APR":
                return 4;
            case "MAY":
                return 5;
            case "JUNE":
            case "JUN":
                return 6;
            case "JULY":
            case "JUL":
                return 7;
            case "AUGUST":
            case "AUG":
                return 8;
            case "SEPTEMBER":
            case "SEP":
                return 9;
            case "OCTOBER":
            case "OCT":
                return 10;
            case "NOVEMBER":
            case "NOV":
                return 11;
            case "DECEMBER":
            case "DEC":
                return 12;
        }
        return 0;
    }

    public static String getTotalHours(int minutes) {
        StringBuilder hoursBuilder = new StringBuilder();
        int completeHours = minutes / 60;
        int remainingMinutes = minutes % 60;

        hoursBuilder.append(completeHours);
        if(remainingMinutes > 0){
            hoursBuilder.append(".").append(remainingMinutes);
        }

        return hoursBuilder.toString();
    }

    public static int getTotalMinutes(String totalHoursStringValue) {
        String[] tokens = totalHoursStringValue.split("\\.");
        int totalMinutes = Integer.parseInt(tokens[0]) * 60;
        if(tokens.length == 2){
            totalMinutes += Integer.parseInt(tokens[1]);
        }

        return totalMinutes;
    }

    public static DateTime getStartOfMonthAtStartOfDay(Clock clock, String month) {
        return clock.now().withMonthOfYear(getNumericMonth(month)).withDayOfMonth(1).withTimeAtStartOfDay();
    }

    public static DateTime getEndOfMonth(DateTime startDateOfMonth) {
        return startDateOfMonth.plusMonths(1).withDayOfMonth(1).minusDays(1).withTime(23,59,59,999);
    }
}
