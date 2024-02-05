package com.example.cs2340_1;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class to_do_activity extends AppCompatActivity {

    private EditText taskEditText;
    private RecyclerView tasksRecyclerView;
    private TaskAdapter taskAdapter;
    private ArrayList<String> tasks;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskEditText = findViewById(R.id.taskEditText);
        Button addButton = findViewById(R.id.addButton);
        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);

        tasks = new ArrayList<>();
        taskAdapter = new TaskAdapter(this, tasks, new TaskAdapter.TaskItemListener() {
            @Override
            public void onEditClicked(int position) {
                editTask(position);
            }

            @Override
            public void onDeleteClicked(int position) {
                deleteTask(position);
            }
        });

        tasksRecyclerView.setAdapter(taskAdapter);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button navigateButton = findViewById(R.id.navigateButton);

        navigateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start ClassDetailsActivity
                Intent intent = new Intent(to_do_activity.this, calendar_view.class);
                startActivity(intent); // Start the new activity
            }
        });


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = taskEditText.getText().toString();
                if (!task.isEmpty()) {
                    tasks.add(task);
                    taskAdapter.notifyItemInserted(tasks.size() - 1);
                    taskEditText.setText("");
                }
            }
        });
    }

    private void editTask(final int position) {
        final EditText taskEdit = new EditText(to_do_activity.this);
        taskEdit.setText(tasks.get(position));
        android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(to_do_activity.this)
                .setTitle("Edit Task")
                .setMessage("Update your task")
                .setView(taskEdit)
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String task = taskEdit.getText().toString();
                        tasks.set(position, task);
                        taskAdapter.notifyItemChanged(position);
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }

    private void deleteTask(final int position) {
        tasks.remove(position);
        taskAdapter.notifyItemRemoved(position);
    }
}
