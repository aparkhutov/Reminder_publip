package ru.nsu.plotnikovccfit.reminder.Model;

/**
 * Created by sergey-plotnikov on 30.12.16.
 */
public enum NotificationFrequency {
    NONE("Без повтора"),
    EVERYDAY("Каждый день"),
    EVERY_FIVE_DAYS("Пн - Пт");

    private String name;

    NotificationFrequency(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}


