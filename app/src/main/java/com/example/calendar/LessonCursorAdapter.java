package com.example.calendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class LessonCursorAdapter extends CursorAdapter {

    private static class ViewHolder {
        TextView time, speciality, course;
        ViewHolder(TextView time, TextView speciality, TextView course) {
            this.time = time;
            this.speciality = speciality;
            this.course = course;
        }
        void setText(String time, String speciality, String course) {
            this.time.setText(time);
            this.speciality.setText(speciality);
            this.course.setText(course);
        }
    }

    public LessonCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams")
        View lessonView = inflater.inflate(R.layout.lesson_view, null, true);
        lessonView.setTag(new ViewHolder(
                (TextView) lessonView.findViewById(R.id.item_time),
                (TextView) lessonView.findViewById(R.id.item_specialty),
                (TextView) lessonView.findViewById(R.id.item_course)));
        return lessonView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();
        String start_time = cursor.getString(cursor.getColumnIndex(LessonsDB.COLUMN_START_TIME)),
                end_time = cursor.getString(cursor.getColumnIndex(LessonsDB.COLUMN_END_TIME)),
                speciality = cursor.getString(cursor.getColumnIndex(LessonsDB.COLUMN_SPECIALITY)),
                course = cursor.getString(cursor.getColumnIndex(LessonsDB.COLUMN_COURSE));
        holder.setText(String.format("%s - %s", start_time, end_time),
                speciality, course + " курс");
    }
}
