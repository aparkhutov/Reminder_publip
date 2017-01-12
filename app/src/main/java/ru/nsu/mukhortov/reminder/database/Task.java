package ru.nsu.mukhortov.reminder.database;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable{
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(status);
        dest.writeString(finish_day);
        dest.writeString(finish_time);
        dest.writeString(notice_time);
        dest.writeInt(id);



    }

    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        public Task[] newArray(int size) {
            return new Task[size];
        }
    };


    private Task(Parcel in) {
        name = in.readString();
        description = in.readString();
        status = in.readString();
        finish_day = in.readString();
        finish_time = in.readString();
        notice_time = in.readString();
        id = in.readInt();
    }



}


