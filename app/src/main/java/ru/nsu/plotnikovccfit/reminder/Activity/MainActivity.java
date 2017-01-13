package ru.nsu.plotnikovccfit.reminder.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.nsu.plotnikovccfit.reminder.R;
import ru.nsu.plotnikovccfit.reminder.Model.Task;
import ru.nsu.plotnikovccfit.reminder.TasksFragment;
import ru.nsu.plotnikovccfit.reminder.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fabmain)
    FloatingActionButton fab;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    private ViewPagerAdapter adapter;
    private TasksFragment active;
    private TasksFragment completed;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TaskActivity.class);
                Task task = null;
                intent.putExtra(Task.TASK_TAG, task);
                startActivityForResult(intent, 1);
            }
        });

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        active = new TasksFragment();
        completed = new TasksFragment();

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
//        Intent intent = getIntent();
////        Task task = (Task) intent.getExtras().get(Task.TASK_TAG);
//        active.tasks.add(task);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(active, "Active");
        adapter.addFragment(completed, "Completed");
        viewPager.setAdapter(adapter);
    }
}
