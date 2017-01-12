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
import android.widget.EditText;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.nsu.plotnikovccfit.reminder.DialogFragment.INotificationDialogPresent;
import ru.nsu.plotnikovccfit.reminder.DialogFragment.NotificationDialogFragment;
import ru.nsu.plotnikovccfit.reminder.Model.Notification;
import ru.nsu.plotnikovccfit.reminder.Model.NotificationFrequency;
import ru.nsu.plotnikovccfit.reminder.Model.NotificationType;
import ru.nsu.plotnikovccfit.reminder.R;
import ru.nsu.plotnikovccfit.reminder.Model.Task;


public class TaskActivity extends AppCompatActivity implements INotificationDialogPresent {

    private Task task;
    private FragmentManager fragmentManager;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task, menu);
        save = menu.findItem(R.id.save);
        edit = menu.findItem(R.id.edit);
        addNotification = menu.findItem(R.id.notification);
        delete = menu.findItem(R.id.delete);

        setDefaultToolbar();
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        save.setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                setEditToolbar();
                return true;
            case R.id.undo:
                onBackPressed();
                return true;
            case R.id.save:
                setDefaultToolbar();
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

        titleEditText.setText(task.getTitle());
        descriptionEditText.setText(task.getDescription());
        dateEditText.setText(task.getNotification().getDate().toString());
    }

    private void saveResult() {
        Intent intent = new Intent();
        task.setTitle(titleEditText.getText().toString());
        task.setDescription(descriptionEditText.getText().toString());

        //TODO Засетить оставшиеся филды

        intent.putExtra("task", task);
        setResult(RESULT_OK, intent);

        finish();
    }

    private void setDefaultToolbar() {
        int color = ((ColorDrawable) toolbar.getBackground()).getColor();
        toolbar.setTitle("Presenting Mode");
        toolbar.setBackgroundColor(color);

        save.setVisible(false);
        addNotification.setVisible(false);
        edit.setVisible(true);
        delete.setVisible(true);

        titleEditText.setEnabled(false);
        descriptionEditText.setEnabled(false);
        dateEditText.setEnabled(false);
    }

    private void setEditToolbar() {
        toolbar.setTitle("Editing Mode");
        toolbar.setBackgroundColor(Color.GRAY);

        save.setVisible(true);
        edit.setVisible(false);
        addNotification.setVisible(true);
        delete.setVisible(false);

        titleEditText.setEnabled(true);
        descriptionEditText.setEnabled(true);
        dateEditText.setEnabled(true);
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
}
