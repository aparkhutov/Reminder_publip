package ru.nsu.mukhortov.reminder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import ru.nsu.mukhortov.TasksAdapter;
import ru.nsu.mukhortov.reminder.model.Task;

import java.util.Arrays;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewById(R.id.list_view)
    ListView listView;
    private TasksAdapter tasksAdapter;

    @AfterViews
    void initListView() {
        Task task = new Task("Батя", "В здании");
        Task task1 = new Task("Батя", "В здании");
        Task task2 = new Task("Батя", "В здании");
        Task task3 = new Task("Батя", "В здании");
        tasksAdapter = new TasksAdapter(this, R.layout.task_item, Arrays.asList(task, task1, task2, task3));
        listView.setAdapter(tasksAdapter);
    }
}
