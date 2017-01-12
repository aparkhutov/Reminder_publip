package ru.nsu.plotnikovccfit.reminder.DialogFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

import static ru.nsu.plotnikovccfit.reminder.DialogFragment.INotificationDialogPresent.NOTIFICATION_TAG;


/**
 * Created by sergey-plotnikov on 09.01.17.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private static final String DATE_KEY = "ru.nsu.plotnikov.ccfit.reminder.DatePickerFragment.DATE";
    private Calendar calendar;

    public static DatePickerFragment newInstance(@NonNull Date date) {

        Bundle bundle = new Bundle();
        bundle.putSerializable(DATE_KEY, date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        calendar = Calendar.getInstance();
        final Date date = (Date) getArguments().get(DATE_KEY);
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getContext(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        calendar.set(year, month, day);

        NotificationDialogFragment notificationDialogFragment = (NotificationDialogFragment) getActivity().getSupportFragmentManager().findFragmentByTag(NOTIFICATION_TAG);
        notificationDialogFragment.setDate(calendar.getTime());
    }
}