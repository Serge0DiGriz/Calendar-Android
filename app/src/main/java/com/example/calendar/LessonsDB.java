package com.example.calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.transform.Source;

public class LessonsDB extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    private final static String DATABASE_NAME = "lessons.db";
    final static String TABLE_NAME = "Lessons";

    final static String COLUMN_ID = "_id",
            COLUMN_UNIVERSITY = "university",
            COLUMN_SPECIALITY = "speciality",
            COLUMN_COURSE = "course",
            COLUMN_SEMESTER = "semester",
            COLUMN_START_TIME = "start_time",
            COLUMN_END_TIME = "end_time",
            COLUMN_WEEK = "week",
            COLUMN_WEEKDAY = "weekday",
            COLUMN_DATE = "date";

    LessonsDB(Context context) {
        super(context, DATABASE_NAME, null, 1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // 0
                COLUMN_UNIVERSITY + " TEXT NOT NULL, " + // 1
                COLUMN_SPECIALITY + " TEXT NOT NULL, " + // 2
                COLUMN_COURSE + " INTEGER NOT NULL, " + // 3
                COLUMN_SEMESTER + " INTEGER NOT NULL, " + // 4
                COLUMN_START_TIME + " TEXT NOT NULL, " + // 5
                COLUMN_END_TIME + " TEXT NOT NULL, " + // 6
                COLUMN_WEEK + " INTEGER NOT NULL, " + // 7
                COLUMN_WEEKDAY + " INTEGER NOT NULL, " + // 8
                COLUMN_DATE + " TEXT)"); // 9
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void insert(Lesson lesson) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_UNIVERSITY, lesson.getUniversity());
        values.put(COLUMN_SPECIALITY, lesson.getSpecialty());
        values.put(COLUMN_COURSE, lesson.getCourse());
        values.put(COLUMN_SEMESTER, lesson.getSemester());
        values.put(COLUMN_START_TIME, lesson.getStart_time());
        values.put(COLUMN_END_TIME, lesson.getEnd_time());
        values.put(COLUMN_WEEKDAY, lesson.getWeekday());
        values.put(COLUMN_WEEK, lesson.getWeek());
        if (lesson.getDate() != null)
            values.put(COLUMN_DATE, lesson.getDate());
        db.insert(TABLE_NAME, null, values);
    }

    public void delete(long id) {
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] { String.valueOf(id) });
    }

    public void deleteAll() {
        db.delete(TABLE_NAME, null, null);
    }

    public void update(Lesson lesson, long id) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_UNIVERSITY, lesson.getUniversity());
        values.put(COLUMN_SPECIALITY, lesson.getSpecialty());
        values.put(COLUMN_COURSE, lesson.getCourse());
        values.put(COLUMN_SEMESTER, lesson.getSemester());
        values.put(COLUMN_START_TIME, lesson.getStart_time());
        values.put(COLUMN_END_TIME, lesson.getEnd_time());
        values.put(COLUMN_WEEKDAY, lesson.getWeekday());
        values.put(COLUMN_WEEK, lesson.getWeek());
        if (lesson.getDate() != null)
            values.put(COLUMN_DATE, lesson.getDate());
        db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    public Cursor select(String[] columns, String[] values, String sortColumn) {
        if (columns == null || columns.length == 0)
            return db.query(TABLE_NAME, null, null,
                    null, null, null, sortColumn);
        else {
            StringBuilder selection = new StringBuilder(String.format("%s = ?", columns[0]));
            for (int i=1; i<columns.length; i++)
                selection.append(String.format(" AND %s = ?", columns[i]));
            return db.query(TABLE_NAME, null, selection.toString(), values,
                    null, null, sortColumn);
        }
    }

    static ArrayList<Lesson> cursorToList(Cursor cursor) {
        ArrayList<Lesson> list = new ArrayList<>();
        cursor.moveToFirst();
        while(cursor.moveToNext())
            list.add(new Lesson(cursor.getString(1), cursor.getString(2), cursor.getInt(3),
                    cursor.getInt(4), cursor.getString(5), cursor.getString(6),
                    cursor.getInt(7), cursor.getInt(8)));
        return list;
    }

}
