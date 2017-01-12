package ru.nsu.plotnikovccfit.reminder.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sergey-plotnikov on 09.01.17.
 */

public class Notification implements Serializable {

    private String status;
    private Date date;
    private NotificationType type;
    private NotificationFrequency frequency;

    public Notification(String status, Date date, NotificationType type, NotificationFrequency frequency) {
        this.status = status;
        this.date = date;
        this.type = type;
        this.frequency = frequency;
    }

    public Date getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public NotificationFrequency getFrequency() {
        return frequency;
    }

    public NotificationType getType() {
        return type;
    }
}
