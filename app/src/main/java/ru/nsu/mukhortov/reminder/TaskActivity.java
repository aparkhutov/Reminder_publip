package ru.nsu.mukhortov.reminder;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.InetAddress;

public class TaskActivity extends AppCompatActivity {
    static final String TASK_NAME = "task_name";
    Toolbar toolbar;
    int color;
    String taskName;
    MenuItem save;
    MenuItem delete;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task, menu);
        save = menu.findItem(R.id.ok);
        delete = menu.findItem(R.id.delete);
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
            case R.id.undo:
                onBackPressed();
                return true;
            case R.id.ok:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow((findViewById(R.id.card_view)).getWindowToken(), 0);
                toolbar.setBackgroundColor(color);
                save.setVisible(false);
                delete.setVisible(true);
                toolbar.setTitle(taskName);
                return true;
            case R.id.delete:
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
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        Bundle data = getIntent().getExtras();
        taskName = data.getString(TASK_NAME);
        toolbar.setTitle(taskName);
        color =  ((ColorDrawable)toolbar.getBackground()).getColor();
        TextView nameTextView = (TextView)findViewById(R.id.taskname);
        nameTextView.setText(taskName);
        setSupportActionBar(toolbar);

        final EditText tv = (EditText)findViewById(R.id.taskdescription);
        tv.setFocusable(false);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbar.setTitle("Editing");
                toolbar.setBackgroundColor(Color.GRAY);
                save.setVisible(true);
                delete.setVisible(false);
                tv.setFocusable(true);
                tv.setFocusableInTouchMode(true);


            }
        });



    }

}
