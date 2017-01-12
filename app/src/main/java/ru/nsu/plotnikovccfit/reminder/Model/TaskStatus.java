package ru.nsu.plotnikovccfit.reminder.Model;

/**
 * Created by sergey-plotnikov on 12.01.17.
 */

public enum TaskStatus {

    ACTIVE("В процессе"),
    COMPLETED("Завершено");

    private String name;

    TaskStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
