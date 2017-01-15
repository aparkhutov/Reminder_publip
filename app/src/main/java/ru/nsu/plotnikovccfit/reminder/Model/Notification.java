package ru.nsu.plotnikovccfit.reminder.Model;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sergey-plotnikov on 09.01.17.
 */
public class Notification extends SugarRecord implements Serializable {
    String status;
    Date date;
    NotificationType type;
    NotificationFrequency frequency;

    public Notification() {
    }

    public Notification(String status, Date date, NotificationType type, NotificationFrequency frequency) {
        this.status = status;
        this.date = date;
        this.type = type;
        this.frequency = frequency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public NotificationFrequency getFrequency() {
        return frequency;
    }

    public void setFrequency(NotificationFrequency frequency) {
        this.frequency = frequency;
    }
}
