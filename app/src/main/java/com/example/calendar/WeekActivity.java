package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class WeekActivity extends AppCompatActivity {
    static LessonsDB db;
    static String universityName;
    static String[] selectColumns = {
            LessonsDB.COLUMN_UNIVERSITY,
            LessonsDB.COLUMN_WEEKDAY};
    static ArrayList<SimpleCursorAdapter> cursorAdapters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        Intent intent = getIntent();
        universityName = intent.getStringExtra("university");

        db = new LessonsDB(this);
        Log.d("Check-DB-all", MainActivity.cursorToList(
                db.select(null, null, null)).toString());

        String[] itemValues = {LessonsDB.COLUMN_START_TIME, LessonsDB.COLUMN_END_TIME,
                LessonsDB.COLUMN_SPECIALITY, LessonsDB.COLUMN_COURSE};
        int[] itemViews = {R.id.item_start_time, R.id.item_end_time,
                R.id.item_specialty, R.id.item_course};
        for (int i=0; i<6; i++) {
            Cursor cursor = db.select(
                    selectColumns, new String[]{universityName, String.valueOf(i)},
                    LessonsDB.COLUMN_START_TIME);
            Log.d("Check-DB-day-select", MainActivity.cursorToList(cursor).toString());
            cursorAdapters.add(new SimpleCursorAdapter(this, R.layout.lesson_view, cursor,
                            itemValues, itemViews,0));
        }

        ViewPager pager = findViewById(R.id.weekPager);
        pager.setAdapter(new WeekPagerAdapter(getSupportFragmentManager(), this));
    }
}
