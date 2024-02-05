package com.example.cs2340_1.Utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;

import com.example.cs2340_1.R;
import com.example.cs2340_1.add_type_assignment;
import com.example.cs2340_1.add_type_course;
import com.example.cs2340_1.add_type_exam;
import com.example.cs2340_1.calendar_view;

public class ButtonClickHandler {

    private final Context context;
    private Button back_button;
    public ButtonClickHandler(Context context) {
        this.context = context;
    }
    /**
     * This button helps set up the button click without multiple methods.
     * @param button This is the button that is used
     * @param activityClass Activity class that's parsed in Intent
     */
    public void setupButtonClick(Button button, Class<?> activityClass) {
        button.setOnClickListener(v -> openActivity(activityClass));
    }
    /**
     * This initializes the intent and starts activity.
     * @param activity_class Activity class that's parsed in Intent
     */
    private void openActivity(Class<?> activity_class) {
        Intent intent = new Intent(context, activity_class);
        context.startActivity(intent);
    }
}





