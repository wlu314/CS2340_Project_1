package com.example.cs2340_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cs2340_1.Utils.ButtonClickHandler;
import com.example.cs2340_1.Utils.date_picker;
import com.example.cs2340_1.Utils.time_picker;

public class add_type_assignment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_type_assignment);

        //Creating ButtonClickHandler object
        ButtonClickHandler click_handler = new ButtonClickHandler(this);
        Button button = findViewById(R.id.assignment_exit_button);
        click_handler.setupButtonClick(button, add_type.class);

        // DATE PICKER
        Button date_button = (Button) findViewById(R.id.assignment_datePicker);
        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment date_picker = new date_picker();
                date_picker.show(getSupportFragmentManager(), "date picker");
            }
        });

        // TIME PICKER
        Button time_button = (Button) findViewById(R.id.assignment_timePicker);
        time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment time_picker = new time_picker();
                time_picker.show(getSupportFragmentManager(), "time picker");
            }
        });

    }
}