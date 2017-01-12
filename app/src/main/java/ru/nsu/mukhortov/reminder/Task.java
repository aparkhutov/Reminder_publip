package ru.nsu.mukhortov.reminder;

public class Task {
    private int id;
    private String name;
    private String description;
    private String status;
    private String finish_day;
    private String finish_time;
    private String notice_time;

    public Task(String name, String description, String status, String finish_day, String finish_time, String notice_time){
        //this.id = id;
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

    public String getFinishDay(){
        return finish_day;
    }

    public String getFinishTime(){
        return finish_time;
    }

    public String getNoticeTime(){
        return notice_time;
    }
}
