package ru.nsu.plotnikovccfit.reminder;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;

import ru.nsu.plotnikovccfit.reminder.Activity.TaskActivity;
import ru.nsu.plotnikovccfit.reminder.Model.Task;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private static HashMap<Integer, Task> tasks;
    private static Task task;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        TextView dateTextView;

        ViewHolder(final View view) {
            super(view);

            CardView cardView = (CardView) view.findViewById(R.id.card_view);
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

    TaskAdapter(HashMap<Integer, Task> tasks) {
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
        holder.titleTextView.setText(task.getTitle());
        holder.descriptionTextView.setText(task.getDescription());
        holder.dateTextView.setText(task.getNotification().getDate().toString());
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