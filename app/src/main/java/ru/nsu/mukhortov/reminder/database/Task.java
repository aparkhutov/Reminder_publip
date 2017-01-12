package ru.nsu.mukhortov.reminder.database;

import java.sql.Time;
import java.util.Date;

public class Task {
    private int id;
    private String name;
    private String description;
    private String status;
    private Date finish_day;
    private Time finish_time;
    private Time notice_time;

    public Task(int id, String name, String description, String status, Date finish_day, Time finish_time, Time notice_time){
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.finish_day = finish_day;
        this.finish_time = finish_time;
        this.notice_time = notice_time;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String getStatus(){
        return status;
    }

    public Date getFinishDay(){
        return finish_day;
    }

    public Time getFinishTime(){
        return finish_time;
    }

    public Time getNoticeTime(){
        return notice_time;
    }
}
