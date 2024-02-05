package com.example.cs2340_1.Adapter;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340_1.to_do_activity;
import com.example.cs2340_1.Model.to_do_model;
import com.example.cs2340_1.R;

import java.util.List;

public class to_do_adapter extends RecyclerView.Adapter<to_do_adapter.ViewHolder> {
    private List<to_do_model> to_do_list;
    public to_do_activity activity;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item_view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.to_do_list_task_layout, parent, false);
        return new ViewHolder(item_view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        to_do_model item = to_do_list.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(to_boolean(item.getStatus()));
    }

    @Override
    public int getItemCount() {
        return to_do_list.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox task;
        ViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.toDoListCheckBox);
        }
    }
    //constructor
    public to_do_adapter(to_do_activity activity) {
        this.activity = activity;
    }
    private boolean to_boolean(int number) {
        return number != 0;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setTasks(List<to_do_model> to_do_list) {
        this.to_do_list = to_do_list;
        notifyDataSetChanged();
    }
}
