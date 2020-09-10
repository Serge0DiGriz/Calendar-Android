package com.example.calendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DayAdapter extends ArrayAdapter<Lesson> {
    private LayoutInflater inflater;
    private int layout;
    private List<Lesson> lessons;


    public DayAdapter(@NonNull Context context, int resource, List<Lesson> items) {
        super(context, resource, items);
        inflater = LayoutInflater.from(context);
        layout = resource;
        lessons = items;
    }

    @NonNull @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        @SuppressLint("ViewHolder") View item = inflater.inflate(layout, parent, false);

        return item;
    }
}

//class LessonDayAdapter {
//    public String start, end, specialty, course;
//
//    public LessonDayAdapter(String start, String end, String specialty, String course) {
//        this.start = start;
//        this.end = end;
//        this.specialty = specialty;
//        this.course = course;
//    }
//
//}
