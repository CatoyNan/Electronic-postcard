package com.example.demo.utils;
import java.util.Calendar;

public class DateUtil {
    public static String getCurrentYear(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        return Integer.toString(year);
    }

    public static String getCurrnetMonth(){
        Calendar calendar=Calendar.getInstance();
        int month=calendar.get(Calendar.MONTH)+1;
        return Integer.toString(month);
    }
    public static String getCurrnetDate(){
        Calendar calendar=Calendar.getInstance();
        int date=calendar.get(Calendar.DATE);
        return Integer.toString(date);
    }
    public static String getTime(){
       String time = DateUtil.getCurrentYear()+"/"+DateUtil.getCurrnetMonth()+"/"+DateUtil.getCurrnetDate();
       return  time;
    }


}
