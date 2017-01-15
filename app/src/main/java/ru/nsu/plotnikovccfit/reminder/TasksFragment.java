package ru.nsu.plotnikovccfit.reminder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.nsu.plotnikovccfit.reminder.Model.Task;

/**
 * Created by ivan on 09.01.17.
 */
public class TasksFragment extends Fragment {
    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Task> tasks;
    private static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

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
        tasks = Task.listAll(Task.class);
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tasksfragment, container, false);
        ButterKnife.bind(this, view);
        mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        registerForContextMenu(mRecyclerView);

        mAdapter = new TaskAdapter(tasks);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }
}

//        tasks.add(new Task("Диплом", "Написать первую главу", TaskStatus.COMPLETED, new Date(), notification));
//        tasks.add(new Task("Диалог создания нотификации", "Режимы создания/просмотра/редактирования", TaskStatus.COMPLETED, new Date(), notification));
//        tasks.add(new Task("Молоко", "Сходить в магазин за молоком", TaskStatus.ACTIVE, new Date(), notification));
//        tasks.add(new Task("Диплом", "Написать вторую главу", TaskStatus.COMPLETED, new Date(), notification));
//        tasks.add(new Task("Диплом", "Практика", TaskStatus.ACTIVE, new Date(), notification));
//        tasks.add(new Task("Уведомление", "Диалог создания и редактирования уведомления", TaskStatus.COMPLETED, new Date(), notification));
//        tasks.add(new Task("Техн. предпринимательство", "Поставить оценку", TaskStatus.COMPLETED, new Date(), notification));
//        tasks.add(new Task("Test", "Description", TaskStatus.COMPLETED, new Date(), notification));
