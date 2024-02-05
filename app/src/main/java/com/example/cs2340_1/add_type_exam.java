package com.example.cs2340_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.cs2340_1.Utils.ButtonClickHandler;
import com.example.cs2340_1.Utils.date_picker;
import com.example.cs2340_1.Utils.time_picker;

import java.text.DateFormat;
import java.util.Calendar;

public class add_type_exam extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_type_exam);
        //Creating ButtonClickHandler object
        ButtonClickHandler click_handler = new ButtonClickHandler(this);
        Button button = findViewById(R.id.add_exam_exit_button);
        click_handler.setupButtonClick(button, add_type.class);

        // NOTIFICATION
        Button noti_button = findViewById(R.id.add_exam_notification_button);
        noti_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_noti();
            }
        });


        // DATE PICKER
        Button date_button = (Button) findViewById(R.id.exam_datePicker);
        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment date_picker = new date_picker();
                date_picker.show(getSupportFragmentManager(), "date picker");
            }
        });

        // TIME PICKER
        Button time_button = (Button) findViewById(R.id.exam_timePicker);
        time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 DialogFragment time_picker = new time_picker();
                 time_picker.show(getSupportFragmentManager(), "time picker");
            }
        });
    }
    private void add_noti() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),
                "Exam Notification");
        builder.setSmallIcon(R.drawable.notification_icon);
        builder.setContentTitle("This is a title~");
        builder.setContentText("This is a description.");
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        //Create intent to show notification
        Intent intent = new Intent(getApplicationContext(), notification_activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("Data", "Some value");
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_MUTABLE);
        builder.setContentIntent(pendingIntent);

        //Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String date_string = DateFormat.getDateInstance().format(calendar.getTime());

        TextView textView = (TextView) findViewById(R.id.exam_datePicker_textView);
        textView.setText(date_string);
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView textView = (TextView) findViewById(R.id.exam_timePicker_textView);
        textView.setText(update_hour_of_day(hourOfDay) + ":" + minute + " " + isAMorPM(hourOfDay));
    }

    /**
     * Formats for AM or PM
     * @param hourOfday takes in to calculate
     * @return A string: if >=12 PM, else AM
     */
    public String isAMorPM (int hourOfday) {
        if (hourOfday >= 12) {
            return "PM";
        } else {
            return "AM";
        }
    }

    /**
     * Updates to an AM/PM format
     * @param hourOfDay input variable
     * @return an int of the AM PM format
     */
    public int update_hour_of_day(int hourOfDay) {
        if (hourOfDay >= 13) {
            return hourOfDay % 12;
        } else {
            return hourOfDay;
        }
    }
}