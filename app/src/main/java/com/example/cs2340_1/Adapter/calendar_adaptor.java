package com.example.cs2340_1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340_1.R;

import java.util.ArrayList;

/**
 * Extends: RecyclerView.Adapter is an adapter used to manage the data of individual items
 */
public class calendar_adaptor extends RecyclerView.Adapter<calendar_view_holder> {
    //List of string representing the days of the month
    private final ArrayList<String> days_month;
    //OnItemListener interface for item click events
    private final OnItemListener onItemListener;
    //Constructor
    public calendar_adaptor(ArrayList<String> days_month, OnItemListener onItemListener) {
        this.days_month = days_month;
        this.onItemListener = onItemListener;
    }
    /**
     * Overrides: RecyclerView.Adapter, called when RecyclerView needs a new ViewHolder
     * NonNull: Returns a nonnull value
     */
    @NonNull
    @Override
    public calendar_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflates the layout for each item in Calendar Recycler View
        //, uses the "activity_calendar_cell"
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_calendar_cell, parent, false);
        //Adjust the height of the view to be 1/6 of the parent's height.
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166);
        //Creates instance of the class
        return new calendar_view_holder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull calendar_view_holder holder, int position) {
        //Binds the data to a particular ViewHolder at a given position.
        //Sets the day to the corresponding day from list
        holder.day_of_the_month.setText(days_month.get(position));
    }

    @Override
    //Returns total number of the Array
    public int getItemCount() {
        return days_month.size();
    }
    //Called when an item in RecyclerView is clicked and it's location
    public interface OnItemListener {
        void onItemClick(int position, String dayText);
    }
}
