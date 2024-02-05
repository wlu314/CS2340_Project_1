package com.example.cs2340_1.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340_1.R;


/**
 * Extends the RecyclerView.ViewHolder
 * Implements OnClickListener
 * This Class represents a ViewHolder for the items
 *  and handles clicked Events
 */
public class calendar_view_holder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public final TextView day_of_the_month;
    //Imported from Calendar's Adapter
    private final calendar_adaptor.OnItemListener onItemListener;

    /**
     * Constructor for calendar_view_holder.
     * @param itemView Takes in ViewItem
     * @param onItemListener from calendar_adapter class
     */
    public calendar_view_holder(@NonNull View itemView, calendar_adaptor.OnItemListener onItemListener) {
        super(itemView);
        day_of_the_month = itemView.findViewById(R.id.daily_cell);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener((View.OnClickListener) this);
    }

    /**
     * Method is called when a item is clicked.
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition(), (String) day_of_the_month.getText());
    }
}
