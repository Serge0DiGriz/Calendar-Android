package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class WeekActivity extends AppCompatActivity {
    public static ArrayList<DayAdapter> dayAdapters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        Intent intent = getIntent();
        String universityName = intent.getStringExtra("university");
        Toast.makeText(this, universityName, Toast.LENGTH_SHORT).show();

        for (int i=0; i<6; i++) {
            ArrayList<LessonDayAdapter> list = new ArrayList<>();
            list.add(new LessonDayAdapter("", "", "", ""));
            dayAdapters.add(new DayAdapter(this, R.layout.add_lesson, list));
        }

        ViewPager pager = findViewById(R.id.weekPager);
        pager.setAdapter(new WeekPagerAdapter(getSupportFragmentManager(), this));

    }
}
