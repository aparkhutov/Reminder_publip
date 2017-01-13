package ru.nsu.mukhortov.reminder;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import ru.nsu.mukhortov.reminder.database.DatabaseHelper;
import ru.nsu.mukhortov.reminder.database.Task;
import ru.nsu.mukhortov.reminder.fragments.TasksFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    TasksFragment active;
    TasksFragment completed;
    private static DatabaseHelper databaseHelper;
    private static SQLiteDatabase database;

    ViewPagerAdapter adapter;

    static final String TASK_NAME = "task_name";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Task task = data.getParcelableExtra("task");
        databaseHelper.addTask(task.getName(), task.getDescription(),
                "null", "null", "null", "null");

        databaseHelper = new DatabaseHelper(getApplicationContext());

        database = databaseHelper.getWritableDatabase();
        active.setDatabase(database);
        active.setDatabaseHelper(databaseHelper);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        databaseHelper = new DatabaseHelper(this);

        String string = "2022-02-13";
        DateFormat format = new SimpleDateFormat("yyyy-m-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("DATE: " + date); // Sat Jan 02 00:00:00 GMT 2010


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabmain);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TaskActivity.class);
                Task task = new Task("new task", "details", "null", "null", "null", "null");

                intent.putExtra(TASK_NAME, task);
                startActivityForResult(intent, 1);


            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());


        active = new TasksFragment();
        completed = new TasksFragment();


        HashMap<Integer, Task> tasks = new HashMap<>();
        databaseHelper = new DatabaseHelper(getApplicationContext());

        database = databaseHelper.getWritableDatabase();
        active.setDatabase(database);
        active.setDatabaseHelper(databaseHelper);

        adapter.addFragment(active, "Active");
        adapter.addFragment(completed, "Completed");

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        databaseHelper = new DatabaseHelper(this);
        database = databaseHelper.getWritableDatabase();
        active.setDatabase(database);
        active.setDatabaseHelper(databaseHelper);

        adapter.addFragment(active, "Active");
        adapter.addFragment(completed, "Completed");

        viewPager.setAdapter(adapter);
    }

}
