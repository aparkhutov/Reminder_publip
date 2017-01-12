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
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewById(R.id.list_view)
    ListView listView;
    private TasksAdapter tasksAdapter;

    @AfterViews
    void initListView() {
        List<Task> tasks = Task.listAll(Task.class);
        if (tasks.size() == 0) {
            Task task = new Task("Батя", "В здании");
            Task task1 = new Task("Батя", "В здании1");
            Task task2 = new Task("Батя", "В здании2");
            Task task3 = new Task("Батя", "В здании3");
            task.save();
            task1.save();
            task2.save();
            task3.save();
            tasks = Arrays.asList(task, task1, task2, task3);
        }
        tasksAdapter = new TasksAdapter(this, R.layout.task_item, tasks);
        listView.setAdapter(tasksAdapter);
    }
}
