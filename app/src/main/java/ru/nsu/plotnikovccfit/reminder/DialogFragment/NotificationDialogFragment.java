package ru.nsu.plotnikovccfit.reminder.DialogFragment;

import android.support.v7.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import junit.framework.Assert;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.nsu.plotnikovccfit.reminder.Model.Notification;
import ru.nsu.plotnikovccfit.reminder.Model.NotificationFrequency;
import ru.nsu.plotnikovccfit.reminder.Model.NotificationType;
import ru.nsu.plotnikovccfit.reminder.R;

public class NotificationDialogFragment extends DialogFragment {

    private static final String NOTIFICATION_KEY = "ru.nsu.plotnikov.ccfit.reminder.NotificationDialogFragment.NOTIFICATION";

    @BindView(R.id.emailRadioButton) RadioButton emailRadioButton;
    @BindView(R.id.systemRadioButton) RadioButton systemRadioButton;
    @BindView(R.id.frequencySpinner) Spinner frequencySpinner;
    @BindView(R.id.dateTextView) TextView dateTextView;
    @BindView(R.id.timeTextView) TextView timeTextView;
    @BindView(R.id.deleteButton) Button deleteButton;
    @BindView(R.id.cancelButton) Button cancelButton;
    @BindView(R.id.okButton) Button okButton;
    @BindView(R.id.dateButton) Button dateButton;
    @BindView(R.id.timeButton) Button timeButton;

    private Notification notification;

    private Date date;
    private NotificationType type;
    private NotificationFrequency frequency;

    private ArrayList<NotificationFrequency> frequencies;
    private SimpleDateFormat simpleDateFormat;
    private SimpleDateFormat simpleTimeFormat;


    public static NotificationDialogFragment newInstance(@NonNull Notification notification) {
        Assert.assertNotNull(notification);

        Bundle bundle = new Bundle();
        bundle.putSerializable(NOTIFICATION_KEY, notification);

        NotificationDialogFragment fragment = new NotificationDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle saveInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_notification, null);
        ButterKnife.bind(this, view);

        notification = (Notification) getArguments().get(NOTIFICATION_KEY);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(notification.getStatus()).setView(view);

        configureDialog();

        return builder.create();
    }

    private Notification getNewNotification() {
        String status = notification.getStatus();

        if (systemRadioButton.isChecked()) {
            type = NotificationType.PUSH_NOTIFICATION;
        }
        if (emailRadioButton.isChecked()) {
            type = NotificationType.EMAIL;
        }

        frequency = frequencies.get(frequencySpinner.getSelectedItemPosition());
        Notification newNotification = new Notification(status,date, type, frequency);
        return newNotification;
    }

    private void configureDialog() {
        date = notification.getDate();

        simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        simpleTimeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        dateTextView.setText(simpleDateFormat.format(date));
        timeTextView.setText(simpleTimeFormat.format(date));

        frequencies = new ArrayList<>();
        frequencies.add(NotificationFrequency.NONE);
        frequencies.add(NotificationFrequency.EVERYDAY);
        frequencies.add(NotificationFrequency.EVERY_FIVE_DAYS);

        ArrayAdapter<NotificationFrequency> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, frequencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frequencySpinner.setAdapter(adapter);
    }


    public void setDate(Date date) {
        this.date = date;
        dateTextView.setText(simpleDateFormat.format(date));
        timeTextView.setText(simpleTimeFormat.format(date));
    }

    @OnClick({ R.id.okButton, R.id.cancelButton, R.id.deleteButton })
    public void close(Button button) {
        INotificationDialogPresent activity = (INotificationDialogPresent) getActivity();
        if (button == okButton) {
            activity.confirm(getNewNotification());
        }
        if (button == deleteButton) {
            activity.delete();
        }
        dismiss();
    }

    @OnClick({ R.id.dateButton, R.id.timeButton })
    public void showPickerDialogFragment(Button button) {
        DialogFragment picker;
        String TAG;

        if (button == dateButton) {
            picker = DatePickerFragment.newInstance(date);
            TAG = "datePicker";
        } else {
            picker = TimePickerFragment.newInstance(date);
            TAG = "timePicker";
        }

        picker.show(getChildFragmentManager(), TAG);
    }

}