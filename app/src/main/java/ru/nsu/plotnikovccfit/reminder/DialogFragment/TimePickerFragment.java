package ru.nsu.plotnikovccfit.reminder.DialogFragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

import static ru.nsu.plotnikovccfit.reminder.DialogFragment.INotificationDialogPresent.NOTIFICATION_TAG;

/**
 * Created by sergey-plotnikov on 09.01.17.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private static final String DATE_KEY = "ru.nsu.plotnikov.ccfit.reminder.TimePickerFragment.DATE";
    private Calendar calendar;

    public static TimePickerFragment newInstance(@NonNull Date date) {

        Bundle bundle = new Bundle();
        bundle.putSerializable(DATE_KEY, date);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        calendar = Calendar.getInstance();
        final Date date = ((Date) getArguments().get(DATE_KEY));
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        return new TimePickerDialog(getContext(), this, hour, minute, true);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        NotificationDialogFragment notificationDialogFragment = (NotificationDialogFragment) getActivity().getSupportFragmentManager().findFragmentByTag(NOTIFICATION_TAG);
        notificationDialogFragment.setDate(calendar.getTime());
    }
}
