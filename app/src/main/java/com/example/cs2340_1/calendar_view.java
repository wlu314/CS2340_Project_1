package com.example.cs2340_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cs2340_1.Adapter.calendar_adaptor;
import com.example.cs2340_1.Utils.ButtonClickHandler;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * This class handles item click events from calendar_adaptor
 */
public class calendar_view extends AppCompatActivity implements calendar_adaptor.OnItemListener{
    // Dynamically displayed the MMMM YYYY format
    private TextView monthly_text;
    // RecyclerView that is dynamically updated
    private RecyclerView calendar_recycler_view;
    // Current selected date
    private LocalDate current_date;

    /**
     * Called when activity is first created. Sets the content to the XML
     * Initializes widgets
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);

        //Accessed to layout
        calendar_recycler_view = findViewById(R.id.calendar_recycler_view);
        monthly_text = findViewById(R.id.month_textview);

        // current_date is the current date
        current_date = LocalDate.now();

        //Display calendar
        setMonthView();


        // SET UP ButtonClickHandler
        ButtonClickHandler click_handler = new ButtonClickHandler(this);
        // TO DO LIST BUTTON
        Button to_do_list_button = findViewById(R.id.to_do_list_btn);
        // ADD NEW CLASS BUTTON
        Button calendar_add_button = findViewById(R.id.add_event_type);
        // WEEKLY VIEW BUTTON

        // CLASS SCHEDULE BUTTON

        click_handler.setupButtonClick(to_do_list_button, to_do_activity.class);
        click_handler.setupButtonClick(calendar_add_button, add_type.class);
    }

    private void setMonthView() {
        monthly_text.setText(month_year_from_date(current_date)); //Sets Monthly Text
        ArrayList<String> days_in_month = generateDaysInMonthArray(current_date);

        calendar_adaptor calendar_adapter = new calendar_adaptor(days_in_month, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendar_recycler_view.setLayoutManager(layoutManager);
        calendar_recycler_view.setAdapter(calendar_adapter);
    }

    /**
     * This method generates ArrayList of Strings representing the days in them month.
     * @param date LocalDate package
     * @return the Array
     */
    private ArrayList<String> generateDaysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate firstOfMonth = date.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i <= 42; i++) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("");
            } else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }

        return daysInMonthArray;
    }
    private String month_year_from_date(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    public void month_back_button(View view) {
        current_date = current_date.minusMonths(1);
        setMonthView();
    }
    public void next_month_action(View view) {
        current_date = current_date.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText) {
        if(dayText.equals("")) {
            String message = "selected date" + dayText + " " + month_year_from_date(current_date);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }
}