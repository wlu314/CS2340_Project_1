package com.example.cs2340_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cs2340_1.Adapter.calendar_adaptor;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class calendar_view extends AppCompatActivity implements calendar_adaptor.OnItemListener{
    private TextView monthly_text;
    private RecyclerView calendar_recycler_view;
    private LocalDate selected_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);
        initWidgets();
        selected_date = LocalDate.now();
        setMonthView();

        //allows ImageButton to travel to "Main Activity"
        //Accesses to do List
        ImageButton to_do_list_button = (ImageButton) findViewById(R.id.to_do_list_btn);
        to_do_list_button.setOnClickListener(v -> open_to_do_list());
    }
    //Calendar View => To Do List
    public void open_to_do_list() {
        Intent intent = new Intent(this, to_do_activity.class);
        startActivity(intent);
    }
    private void initWidgets() {
        calendar_recycler_view = findViewById(R.id.calendar_recycler_view);
        monthly_text = findViewById(R.id.month_textview);
    }
    private void setMonthView() {
        monthly_text.setText(month_year_from_date(selected_date));
        ArrayList<String> days_in_month = days_in_month_array(selected_date);

        calendar_adaptor calendar_adapter = new calendar_adaptor(days_in_month, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendar_recycler_view.setLayoutManager(layoutManager);
        calendar_recycler_view.setAdapter(calendar_adapter);
    }

    private ArrayList<String> days_in_month_array(LocalDate date) {
        ArrayList<String> days_in_month_array= new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int days_in_month = yearMonth.lengthOfMonth();
        LocalDate first_of_month = selected_date.withDayOfMonth(1);
        int day_of_week = first_of_month.getDayOfWeek().getValue();

        for (int i = 1; i <= 42; i++) {
            if (i <= day_of_week || i > days_in_month + day_of_week) {
                days_in_month_array.add("");
            } else {
                days_in_month_array.add(String.valueOf(i - day_of_week));
            }
        }
        return days_in_month_array;
    }

    private String month_year_from_date(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    public void previous_month_action(View view) {
        selected_date = selected_date.minusMonths(1);
        setMonthView();
    }
    public void next_month_action(View view) {
        selected_date = selected_date.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText) {
        if(dayText.equals("")) {
            String message = "selected date" + dayText + " " + month_year_from_date(selected_date);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }
}