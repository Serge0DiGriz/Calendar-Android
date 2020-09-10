package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class WeekActivity extends AppCompatActivity {
    static LessonsDB db;
    static String universityName;
    static String[] selectColumns = {
            LessonsDB.COLUMN_UNIVERSITY,
            LessonsDB.COLUMN_WEEKDAY};
    static LessonCursorAdapter
            MondayAdapter, TuesdayAdapter, WednesdayAdapter,
            ThursdayAdapter, FridayAdapter, SaturdayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        Intent intent = getIntent();
        universityName = intent.getStringExtra("university");

        db = new LessonsDB(this);
        setAdapters();

        ViewPager pager = findViewById(R.id.weekPager);
        pager.setAdapter(new WeekPagerAdapter(getSupportFragmentManager(), this));
    }

    private void setAdapters() {
        MondayAdapter = new LessonCursorAdapter(this, db.select(
                selectColumns, new String[]{universityName, "0"},
                LessonsDB.COLUMN_START_TIME), 0);
        TuesdayAdapter = new LessonCursorAdapter(this, db.select(
                selectColumns, new String[]{universityName, "1"},
                LessonsDB.COLUMN_START_TIME), 0);
        WednesdayAdapter = new LessonCursorAdapter(this, db.select(
                selectColumns, new String[]{universityName, "2"},
                LessonsDB.COLUMN_START_TIME), 0);
        ThursdayAdapter = new LessonCursorAdapter(this, db.select(
                selectColumns, new String[]{universityName, "3"},
                LessonsDB.COLUMN_START_TIME), 0);
        FridayAdapter = new LessonCursorAdapter(this, db.select(
                selectColumns, new String[]{universityName, "4"},
                LessonsDB.COLUMN_START_TIME), 0);
        SaturdayAdapter = new LessonCursorAdapter(this, db.select(
                selectColumns, new String[]{universityName, "5"},
                LessonsDB.COLUMN_START_TIME), 0);
    }

}
