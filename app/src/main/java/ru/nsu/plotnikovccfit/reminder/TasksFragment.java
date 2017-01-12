package ru.nsu.plotnikovccfit.reminder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;
import java.util.HashMap;

import ru.nsu.plotnikovccfit.reminder.Model.Notification;
import ru.nsu.plotnikovccfit.reminder.Model.NotificationFrequency;
import ru.nsu.plotnikovccfit.reminder.Model.NotificationType;
import ru.nsu.plotnikovccfit.reminder.Model.Task;
import ru.nsu.plotnikovccfit.reminder.Model.TaskStatus;

/**
 * Created by ivan on 09.01.17.
 */
public class TasksFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    static HashMap<Integer, Task> tasks;
    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

    public static TasksFragment newInstance(int page) {
        TasksFragment tasksFragment = new TasksFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARGUMENT_PAGE_NUMBER, page);
        tasksFragment.setArguments(bundle);
        return tasksFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tasks = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            Notification notification = new Notification("Simple", new Date(), NotificationType.PUSH_NOTIFICATION, NotificationFrequency.NONE);
            Task task = new Task("Title", "Description", TaskStatus.ACTIVE, new Date(), notification);
            tasks.put(i, task);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tasksfragment, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        registerForContextMenu(mRecyclerView);

        mAdapter = new TaskAdapter(tasks);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }
}
