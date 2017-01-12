package ru.nsu.mukhortov;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ru.nsu.mukhortov.reminder.R;
import ru.nsu.mukhortov.reminder.model.Task;

import java.util.List;

/**
 * Created by ivan on 12/01/2017.
 */
public class TasksAdapter extends ArrayAdapter<Task> {
    public TasksAdapter(Context context, int resource, List<Task> tasks) {
        super(context, resource, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_item, parent, false);
        }

        TextView titleTextView = (TextView) convertView.findViewById(R.id.title_text_view);
        TextView descriptionTextView = (TextView) convertView.findViewById(R.id.description_text_view);

        titleTextView.setText(task.getTitle());
        descriptionTextView.setText(task.getDescription());
        return convertView;
    }
}
