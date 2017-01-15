package ru.nsu.plotnikovccfit.reminder;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.List;

import ru.nsu.plotnikovccfit.reminder.Activity.TaskActivity;
import ru.nsu.plotnikovccfit.reminder.Model.Task;
import ru.nsu.plotnikovccfit.reminder.Model.TaskStatus;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private List<Task> tasks;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTextView;
        private TextView descriptionTextView;
        private TextView dateTextView;
        private CardView cardView;

        ViewHolder(final View view) {
            super(view);
            cardView =  (CardView) view.findViewById(R.id.card_view);
            titleTextView = (TextView) cardView.findViewById(R.id.title);
            descriptionTextView = (TextView) cardView.findViewById(R.id.description);
            dateTextView = (TextView) cardView.findViewById(R.id.date);
        }

        public void setOnClickListener(View.OnClickListener listener) {
            cardView.setOnClickListener(listener);
        }
    }

    TaskAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task holderTask = tasks.get(position);
        final int pos = position;
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "OnClickClackBANGBANGBANG", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), TaskActivity.class);
                intent.putExtra(Task.TASK_TAG, tasks.get(pos));
                v.getContext().startActivity(intent);
            }
        });

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        if (holderTask.getStatus() == TaskStatus.ACTIVE) {
            holder.titleTextView.setTextColor(Color.RED);
            holder.cardView.setBackgroundColor(Color.LTGRAY);
        } else {
            holder.titleTextView.setTextColor(Color.GRAY);
            holder.cardView.setBackgroundColor(Color.WHITE);
        }

        holder.titleTextView.setText(holderTask.getTitle());
        holder.descriptionTextView.setText(holderTask.getDescription());
//        holder.dateTextView.setText(simpleDateFormat.format(holderTask.getNotification().getDate()));
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