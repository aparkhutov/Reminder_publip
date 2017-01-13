package ru.nsu.plotnikovccfit.reminder;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import ru.nsu.plotnikovccfit.reminder.Activity.TaskActivity;
import ru.nsu.plotnikovccfit.reminder.Model.Task;
import ru.nsu.plotnikovccfit.reminder.Model.TaskStatus;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private static ArrayList<Task> tasks;
    private static Task task;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        TextView dateTextView;
        CardView cardView;

        ViewHolder(final View view) {
            super(view);

            cardView = (CardView) view.findViewById(R.id.card_view);
            titleTextView = (TextView) cardView.findViewById(R.id.title);
            descriptionTextView = (TextView) cardView.findViewById(R.id.description);
            dateTextView = (TextView) cardView.findViewById(R.id.date);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), TaskActivity.class);
                    task = tasks.get(getPosition());
                    intent.putExtra(Task.TASK_TAG, task);
                    v.getContext().startActivity(intent);
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return false;
                }
            });

        }
    }

    TaskAdapter(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        task = tasks.get(position);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        if (task.getStatus() == TaskStatus.ACTIVE) {
            holder.titleTextView.setTextColor(Color.RED);
            holder.cardView.setBackgroundColor(Color.LTGRAY);
        } else {
            holder.titleTextView.setTextColor(Color.GRAY);
            holder.cardView.setBackgroundColor(Color.WHITE);
        }

        holder.titleTextView.setText(task.getTitle());
        holder.descriptionTextView.setText(task.getDescription());
        holder.dateTextView.setText(simpleDateFormat.format(task.getNotification().getDate()));
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