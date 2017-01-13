package ru.nsu.plotnikovccfit.reminder.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.nsu.plotnikovccfit.reminder.DialogFragment.INotificationDialogPresent;
import ru.nsu.plotnikovccfit.reminder.DialogFragment.NotificationDialogFragment;
import ru.nsu.plotnikovccfit.reminder.Model.Notification;
import ru.nsu.plotnikovccfit.reminder.Model.NotificationFrequency;
import ru.nsu.plotnikovccfit.reminder.Model.NotificationType;
import ru.nsu.plotnikovccfit.reminder.Model.TaskStatus;
import ru.nsu.plotnikovccfit.reminder.R;
import ru.nsu.plotnikovccfit.reminder.Model.Task;


public class TaskActivity extends AppCompatActivity implements INotificationDialogPresent {

    private Task task;
    private FragmentManager fragmentManager;
    private TaskActivityMode mode;
    private ArrayList<TaskStatus> statusArrayList;

    MenuItem save;
    MenuItem delete;
    MenuItem edit;
    MenuItem addNotification;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title)
    EditText titleEditText;
    @BindView(R.id.description)
    EditText descriptionEditText;
    @BindView(R.id.date)
    EditText dateEditText;
    @BindView(R.id.statusSpinner)
    Spinner statusSpinner;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task, menu);
        save = menu.findItem(R.id.save);
        edit = menu.findItem(R.id.edit);
        addNotification = menu.findItem(R.id.notification);
        delete = menu.findItem(R.id.delete);

        configureToolbar();
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                mode = TaskActivityMode.EDIT;
                configureToolbar();
                return true;
            case R.id.undo:
                onBackPressed();
                return true;
            case R.id.save:
                mode = TaskActivityMode.PRESENT;
                configureToolbar();
                saveResult();
                return true;
            case R.id.delete:
                showAlertDialog();
                return true;
            case R.id.notification:
                showNotificationDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        task = (Task) bundle.get(Task.TASK_TAG);

        statusArrayList = new ArrayList<>();
        statusArrayList.add(TaskStatus.ACTIVE);
        statusArrayList.add(TaskStatus.COMPLETED);

        ArrayAdapter<TaskStatus> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statusArrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);

        if (task == null) {
            mode = TaskActivityMode.CREATE;
            task = new Task();
            task.setStatus(TaskStatus.ACTIVE);
        } else {
            mode = TaskActivityMode.PRESENT;

            titleEditText.setText(task.getTitle());
            descriptionEditText.setText(task.getDescription());
            dateEditText.setText(task.getNotification().getDate().toString());
        }
    }

    private void saveResult() {

        if (!isSavingAllowed()) {
            return;
        }
        Intent intent = new Intent();

        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        TaskStatus status = statusArrayList.get(statusSpinner.getSelectedItemPosition());

        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);

        //TODO Засетить оставшиеся филды

        intent.putExtra(Task.TASK_TAG, task);
        setResult(RESULT_OK, intent);

        finish();
    }

    private void configureToolbar() {
        switch (mode) {
            case CREATE:
                setEditToolbar();
                break;
            case PRESENT:
                setPresentToolbar();
                break;
            case EDIT:
                setEditToolbar();
                break;
        }
    }

    private void setEditToolbar() {
        toolbar.setTitle(mode.toString());
        toolbar.setBackgroundColor(Color.GRAY);

        save.setVisible(true);
        edit.setVisible(false);
        addNotification.setVisible(true);
        delete.setVisible(false);

        titleEditText.setEnabled(true);
        descriptionEditText.setEnabled(true);
        dateEditText.setEnabled(true);
        statusSpinner.setEnabled(true);
    }

    private void setPresentToolbar() {
        toolbar.setTitle(mode.toString());

        save.setVisible(false);
        edit.setVisible(true);
        addNotification.setVisible(false);
        delete.setVisible(true);

        titleEditText.setEnabled(false);
        descriptionEditText.setEnabled(false);
        dateEditText.setEnabled(false);
        statusSpinner.setEnabled(false);
    }


    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete?").setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showNotificationDialog() {
        if (task.getNotification() == null) {
            Notification notification = new Notification("Simple", new Date(), NotificationType.PUSH_NOTIFICATION, NotificationFrequency.NONE);
            task.setNotification(notification);
        }

        fragmentManager = getSupportFragmentManager();

        if (fragmentManager.findFragmentByTag(NOTIFICATION_TAG) == null) {
            NotificationDialogFragment notificationDialogFragment = NotificationDialogFragment.newInstance(task.getNotification());
            notificationDialogFragment.show(fragmentManager, NOTIFICATION_TAG);
        }
    }

    public void confirm(Notification newNotification) {
        task.setNotification(newNotification);
        dateEditText.setText(task.getNotification().getDate().toString());
    }

    public void delete() {
        task.setNotification(null);
    }

    private boolean isSavingAllowed() {
        return (titleEditText.getText().toString().isEmpty() || descriptionEditText.getText().toString().isEmpty());
    }

    enum TaskActivityMode {
        PRESENT,
        EDIT,
        CREATE;
    }
}
