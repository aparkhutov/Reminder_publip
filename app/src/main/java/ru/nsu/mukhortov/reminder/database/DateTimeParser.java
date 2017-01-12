package ru.nsu.mukhortov.reminder.database;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeParser {

    public Date parseStringToDate(String dateInString){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;

        try
        {
            date = formatter.parse(dateInString);
            System.out.println("DATE: " + date);
            System.out.println(formatter.format(date));

        }catch (Exception e){
            return null;
        }

        return date;
    }

    public Time parseStringToTime(String timeInString){
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Time time = null;

        try {
            time = new Time(formatter.parse(timeInString).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        return time;
    }

    public String dateToString(Date date){
        String stringDate;
        try {
            stringDate = date.getDay() + "-" + date.getMonth() + "-" + date.getYear();
        }catch (Exception e){
            return null;
        }

        return stringDate;
    }

    public String timeToString(Time time){
        String stringTime;
        try {
            stringTime = time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds();
        }catch (Exception e){
            return null;
        }
        return stringTime;
    }
}
