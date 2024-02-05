package com.example.cs2340_1.Utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cs2340_1.Model.to_do_model;

import java.util.ArrayList;
import java.util.List;

public class to_do_handler extends SQLiteOpenHelper {
    private static final int Version = 1;
    private static final String NAME = "to_do_handler";
    private static final String TODO_TABLE = "to_do_table";
    private static final String ID = "id";
    private static final String TASK = "task";
    private static final String STATUS = "status";
    private static final String CREATE_TABLE = "CREATE TABLE " + TODO_TABLE + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TASK + " TEXT, " +
            STATUS + " INTEGER)";

    private SQLiteDatabase db;
    private to_do_handler(Context context) {
        super(context, NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TODO_TABLE);
        onCreate(db);
    }
    public void db_open() {
        db = this.getWritableDatabase();
    }
    public void add_task(to_do_model task) {
        ContentValues cv = new ContentValues();
        cv.put(TASK, task.getTask());
        cv.put(STATUS, 0);
        db.insert(TODO_TABLE, null, cv);
    }
    @SuppressLint("Range")
    public List<to_do_model> get_tasks() {
        List<to_do_model> task_list = new ArrayList<>();
        Cursor cursor = null;
        db.beginTransaction();
        try {
            cursor = db.query(TODO_TABLE, null, null, null, null, null , null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        to_do_model task = new to_do_model();
                        task.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                        task.setTask(cursor.getString(cursor.getColumnIndex(TASK)));
                        task.setStatus(cursor.getInt(cursor.getColumnIndex(STATUS)));
                        task_list.add(task);
                    } while (cursor.moveToNext());
                }
            }
        } finally {
            db.endTransaction();
             cursor.close();
        }
        db.endTransaction();
        return task_list;
    }
    public void delete_task(int id) {
        db.delete(TODO_TABLE, ID + "=?", new String[] {String.valueOf(id)});
    }
    public void update_status(int id, int status) {
        ContentValues cv = new ContentValues();
        cv.put(STATUS, status);
        db.update(TODO_TABLE, cv, ID + "=?", new String[] {String.valueOf(id)});
    }
    public void update_task(int id, String task) {
        ContentValues cv = new ContentValues();
        cv.put(STATUS, task);
        db.update(TODO_TABLE, cv, ID + "=?", new String[] {String.valueOf(id)});
    }

}

