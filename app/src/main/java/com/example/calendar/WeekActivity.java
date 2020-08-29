package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
            ArrayList<Lesson> list = new ArrayList<>();
            list.add(new Lesson("", "", "", ""));
            dayAdapters.add(new DayAdapter(this, R.layout.lesson, list));
        }

        ViewPager pager = findViewById(R.id.weekPager);
        pager.setAdapter(new WeekPagerAdapter(getSupportFragmentManager(), this));
    }
}
