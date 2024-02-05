package com.example.cs2340_1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<String> tasks;
    private LayoutInflater inflater;
    private TaskItemListener listener;

    public interface TaskItemListener {
        void onEditClicked(int position);
        void onDeleteClicked(int position);
    }

    public TaskAdapter(Context context, List<String> tasks, TaskItemListener listener) {
        this.tasks = tasks;
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(itemView);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        String task = tasks.get(position);
        holder.taskTextView.setText(task);
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEditClicked(position);
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskTextView;
        ImageButton editButton, deleteButton;

        TaskViewHolder(View itemView) {
            super(itemView);
            taskTextView = itemView.findViewById(R.id.taskTextView);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
