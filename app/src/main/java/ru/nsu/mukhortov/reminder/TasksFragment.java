package ru.nsu.mukhortov.reminder;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ivan on 09.01.17.
 */
public class TasksFragment extends Fragment {

    public static DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }

    public static void setDatabaseHelper(DatabaseHelper databaseHelper) {
        TasksFragment.databaseHelper = databaseHelper;
    }

    public static SQLiteDatabase getDatabase() {
        return database;
    }

    public static void setDatabase(SQLiteDatabase database) {
        TasksFragment.database = database;
    }

    private static DatabaseHelper databaseHelper;
    private static SQLiteDatabase database;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    static HashMap<Integer, Task> tasks;
    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";


    int pageNumber;

    static TasksFragment newInstance(int page){
        TasksFragment tasksFragment = new TasksFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARGUMENT_PAGE_NUMBER, page);
        tasksFragment.setArguments(bundle);
        return tasksFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tasks = databaseHelper.getTasks();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tasksfragment, container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        registerForContextMenu(mRecyclerView);

        mAdapter = new TaskAdapter(tasks);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }
}
