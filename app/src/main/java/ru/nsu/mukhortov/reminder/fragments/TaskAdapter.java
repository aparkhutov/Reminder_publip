package ru.nsu.mukhortov.reminder.fragments;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import ru.nsu.mukhortov.reminder.R;
import ru.nsu.mukhortov.reminder.TaskActivity;
import ru.nsu.mukhortov.reminder.database.Task;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private static HashMap<Integer,Task> tasks;
    static final String TASK_NAME = "task_name";
    static Task task;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.task);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), mTextView.getText(),
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(v.getContext(), TaskActivity.class);

                    ArrayList<String>taskInfo = new ArrayList<String>();
                    taskInfo.add(task.getName());
                    taskInfo.add(task.getDescription());
                    intent.putExtra(TASK_NAME, taskInfo);
                    v.getContext().startActivity(intent);

                }
            });
            v.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    return false;
                }
            });

        }


    }

    public TaskAdapter(HashMap<Integer,Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    //    DatabaseHelper databaseHelper = new
        task = tasks.get(position);
        holder.mTextView.setText(task.getName());


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }




}