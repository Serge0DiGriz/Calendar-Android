package com.example.calendar;

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
        View item = inflater.inflate(layout, parent, false);
        EditText start = item.findViewById(R.id.edit_startTime),
                end = item.findViewById(R.id.edit_endTime),
                speciality = item.findViewById(R.id.edit_specialty),
                course = item.findViewById(R.id.edit_year_specialty);

        Lesson lesson = lessons.get(position);
        start.setText(lesson.start);
        end.setText(lesson.end);
        speciality.setText(lesson.specialty);
        course.setText(lesson.course);

        return item;
    }
}

class Lesson {
    public String start, end, specialty, course;

    public Lesson(String start, String end, String specialty, String course) {
        this.start = start;
        this.end = end;
        this.specialty = specialty;
        this.course = course;
    }

}
