package com.example.cs2340_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.cs2340_1.Utils.btn_click_handler;

public class add_type extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_type);
        //Creating btn_click_handler object
        btn_click_handler click_handler = new btn_click_handler(this);



        // Button IDs
        int[] id_arr = {R.id.add_type_back_button, R.id.type_exam_button, R.id.type_assignment_button, R.id.type_course_button};
        // Activity Classes
        Class<?>[] activity_class_arr = {calendar_view.class, add_type_exam.class, add_type_assignment.class, add_type_course.class};
        // Initiate Buttons
        for (int i = 0; i < id_arr.length; i++) {
            Button button = findViewById(id_arr[i]);
            click_handler.setupButtonClick(button, activity_class_arr[i]);
        }
    }
}