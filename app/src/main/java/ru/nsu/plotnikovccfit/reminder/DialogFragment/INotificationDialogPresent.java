package ru.nsu.plotnikovccfit.reminder.DialogFragment;

import ru.nsu.plotnikovccfit.reminder.Model.Notification;

/**
 * Created by sergey-plotnikov on 10.01.17.
 */

public interface INotificationDialogPresent {
    String NOTIFICATION_TAG = "ru.nsu.plotnikov.ccfit.reminder.NotificationDialogFragment.NOTIFICATION";

    void confirm(Notification notification);
    void delete();
}
