package com.example.cs2340_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class splash_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Hides the action bar, accounts for exception
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        //Changes the screen from splash_activity to Main Activity
        // Use loop since Handler is depreciated
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent i = new Intent(splash_activity.this, calendar_view.class);
            startActivity(i);
            finish();
        }, 3000);


    }
}