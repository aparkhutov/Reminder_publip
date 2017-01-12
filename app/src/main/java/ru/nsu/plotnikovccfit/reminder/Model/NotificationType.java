package ru.nsu.plotnikovccfit.reminder.Model;

/**
 * Created by sergey-plotnikov on 30.12.16.
 */
public enum NotificationType {
    EMAIL("Электронная почта"),
    PUSH_NOTIFICATION("Push-уведомления");

    private String name;

    NotificationType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

