package com.example.cs2340_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.os.Bundle;
import android.widget.Button;

import com.example.cs2340_1.Adapter.to_do_adapter;
import com.example.cs2340_1.Model.to_do_model;
import com.example.cs2340_1.Utils.btn_click_handler;

import java.util.ArrayList;
import java.util.List;

public class to_do_activity extends AppCompatActivity {
    private RecyclerView todolistRecyclerView;
    private to_do_adapter adapter;
    private List<to_do_model> to_do_list;

    private Button back_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        to_do_list = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_list_main);

        //Hides the action bar
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        todolistRecyclerView = findViewById(R.id.tasks_recycler_view);
        todolistRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new to_do_adapter(this);
        todolistRecyclerView.setAdapter(adapter);

        to_do_model task1 = new to_do_model();
        task1.setTask("Testing");
        task1.setStatus(0);
        task1.setId(1);

        to_do_list.add(task1);
        to_do_list.add(task1);
        to_do_list.add(task1);
        to_do_list.add(task1);
        to_do_list.add(task1);

        adapter.setTasks(to_do_list);

        //Back to Calendar View Button
        btn_click_handler click_handler = new btn_click_handler(this);
        back_button = (Button) findViewById(R.id.todolist_back_btn);
        click_handler.setupButtonClick(back_button, calendar_view.class);

    }
}