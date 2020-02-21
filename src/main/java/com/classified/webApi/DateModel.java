package com.classified.webApi;

import com.google.gson.annotations.Expose;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class DateModel {
    @Expose
    private String day;
    @Expose
    private String month;
    @Expose
    private String abrMonth;
    @Expose
    private String year;
    @Expose
    private String dayOfMonth;
    private Calendar calendar;

    private HashMap<Integer, String> days;
    private HashMap<Integer, String> months;
    private HashMap<Integer, String> abrMonths;

    public DateModel(String day, String month, String year, String dayOfMonth) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.dayOfMonth = dayOfMonth;
    }

    public DateModel() {
        calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        days = new HashMap<Integer, String>();
        months = new HashMap<Integer, String>();
        abrMonths = new HashMap<Integer, String>();

        days.put(1, "Sunday");
        days.put(2, "Monday");
        days.put(3, "Tuesday");
        days.put(4, "Wednesday");
        days.put(5, "Thursday");
        days.put(6, "Friday");
        days.put(7, "Saturday");

        months.put(1, "January");
        months.put(2, "February");
        months.put(3, "March");
        months.put(4, "April");
        months.put(5, "May");
        months.put(6, "June");
        months.put(7, "July");
        months.put(8, "August");
        months.put(9, "September");
        months.put(10, "October");
        months.put(11, "November");
        months.put(12, "December");

        abrMonths.put(1, "JAN");
        abrMonths.put(2, "FEB");
        abrMonths.put(3, "MAR");
        abrMonths.put(4, "APR");
        abrMonths.put(5, "MAY");
        abrMonths.put(6, "JUN");
        abrMonths.put(7, "JUL");
        abrMonths.put(8, "AUG");
        abrMonths.put(9, "SEP");
        abrMonths.put(10, "OCT");
        abrMonths.put(11, "NOV");
        abrMonths.put(12, "DEC");

        year = String.valueOf(calendar.get(Calendar.YEAR));
        month = months.get(calendar.get(Calendar.MONTH));
        day = days.get(calendar.get(Calendar.DAY_OF_WEEK));
        dayOfMonth = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        abrMonth = abrMonths.get(calendar.get(Calendar.MONTH));
    }

    public String getDay() {
        return day;
    }

    public String getAbrMonth() {
        return abrMonth;
    }

    public void setAbrMonth(String abrMonth) {
        this.abrMonth = abrMonth;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(String dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    @Override
    public String toString() {
        return "DateModel{" +
                "day='" + day + '\'' +
                ", month='" + month + '\'' +
                ", abrMonth='" + abrMonth + '\'' +
                ", year='" + year + '\'' +
                ", dayOfMonth='" + dayOfMonth + '\'' +
                ", calendar=" + calendar +
                '}';
    }
}
