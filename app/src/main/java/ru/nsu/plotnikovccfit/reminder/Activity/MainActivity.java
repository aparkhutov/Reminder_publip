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

        adapter = initAdapter();
        viewPager.setAdapter(adapter);

        fab.setOnClickListener(getFabOnClickListener());
        viewPager.addOnPageChangeListener(getPageChangeListener());

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // dirty hack
        adapter = initAdapter();
        viewPager.setAdapter(adapter);
    }

    private ViewPagerAdapter initAdapter() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        active = new TasksFragment();
        completed = new TasksFragment();
        adapter.addFragment(active, "Active");
        adapter.addFragment(completed, "Completed");
        return adapter;
    }

    private View.OnClickListener getFabOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TaskActivity.class);
                startActivity(intent);
            }
        };
    }

    private ViewPager.OnPageChangeListener getPageChangeListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }
}
