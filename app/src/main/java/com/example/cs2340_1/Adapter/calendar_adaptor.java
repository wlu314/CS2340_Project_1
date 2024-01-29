package com.example.cs2340_1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340_1.R;

import java.util.ArrayList;

public class calendar_adaptor extends RecyclerView.Adapter<calendar_view_holder> {
    private final ArrayList<String> days_month;
    private final OnItemListener onItemListener;
    public calendar_adaptor(ArrayList<String> days_month, OnItemListener onItemListener) {
        this.days_month = days_month;
        this.onItemListener = onItemListener;
    }
    @NonNull
    @Override
    public calendar_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166);
        return new calendar_view_holder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull calendar_view_holder holder, int position) {
        holder.day_of_the_month.setText(days_month.get(position));
    }

    @Override
    public int getItemCount() {
        return days_month.size();
    }
    public interface OnItemListener {
        void onItemClick(int position, String dayText);
    }
}
