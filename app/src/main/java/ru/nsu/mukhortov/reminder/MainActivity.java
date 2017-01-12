package ru.nsu.mukhortov.reminder;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    TasksFragment active;
    TasksFragment completed;
    private static DatabaseHelper databaseHelper;
    private static SQLiteDatabase database;

    static final String TASK_NAME = "task_name";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ArrayList<String> task = data.getStringArrayListExtra("task");

        databaseHelper.addTask(task.get(0), task.get(1),
                "null", "null", "null", "null");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabmain);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TaskActivity.class);
                intent.putExtra(TASK_NAME, "new task");
                startActivityForResult(intent, 1);

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());


        active = new TasksFragment();
        completed = new TasksFragment();


        HashMap<Integer, Task> tasks = new HashMap<>();
        databaseHelper = new DatabaseHelper(getApplicationContext());

        database = databaseHelper.getReadableDatabase();
        active.setDatabase(database);
        active.setDatabaseHelper(databaseHelper);

      //  completed.setDatabase(database);
      //  completed.setDatabaseHelper(databaseHelper);
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


}
