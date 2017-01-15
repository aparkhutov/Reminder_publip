package ru.nsu.plotnikovccfit.reminder.Model;





import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.Date;

public class Task extends SugarRecord implements Serializable {
    public static final String TASK_TAG = "ru.nsu.ccfit.plotnikov.reminder.Task.TASK";

    String title;
    String description;
    TaskStatus status;
    Date deadline;
    Notification notification;

    public Task() {

    }

    public Task(String title, String description, TaskStatus status, Date deadline, Notification notification) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.deadline = deadline;
        this.notification = notification;
    }

    public Task(String title, String description, TaskStatus status, Date deadline) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.deadline = deadline;
    }

    public Task(String title, String description, TaskStatus status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Date getDeadline() {
        return deadline;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}
