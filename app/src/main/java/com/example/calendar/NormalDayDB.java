package com.example.calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.LinkedList;

public class NormalDayDB extends SQLiteOpenHelper {

    private Context context;
    private SQLiteDatabase db;

    public NormalDayDB(@Nullable Context context) {
        super(context, "Normal_DaysDB", null, 1);
        db = getWritableDatabase();
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Normal_DaysDB(\n" +
                "  university TEXT,\n" +
                "  semester INTEGER,\n" +
                "  number_week INTEGER,\n" +
                "  weekday TEXT,\n" +
                "  start_time TIME,\n" +
                "  end_time TIME,\n" +
                "  specialty TEXT,\n" +
                "  year_specialty INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(OneLesson oneLesson) {
        ContentValues cv = new ContentValues();
        cv.put("university", oneLesson.getUniversity());
        cv.put("semester", oneLesson.getSemester());
        cv.put("number_week", oneLesson.getNumber_week());
        cv.put("weekday", oneLesson.getWeekday());
        cv.put("start_time", oneLesson.getStart_time());
        cv.put("end_time", oneLesson.getEnd_time());
        cv.put("specialty", oneLesson.getSpecialty());
        cv.put("year_specialty", oneLesson.getYear_specialty());
        db.insert("Normal_DaysDB", null, cv);
    }

    public void update(OneLesson oneLesson, String whereClause, String[] whereArgs) {
        ContentValues cv = new ContentValues();
        cv.put("university", oneLesson.getUniversity());
        cv.put("semester", oneLesson.getSemester());
        cv.put("number_week", oneLesson.getNumber_week());
        cv.put("weekday", oneLesson.getWeekday());
        cv.put("start_time", oneLesson.getStart_time());
        cv.put("end_time", oneLesson.getEnd_time());
        cv.put("specialty", oneLesson.getSpecialty());
        cv.put("year_specialty", oneLesson.getYear_specialty());
        db.update("Normal_DaysDB", cv, whereClause, whereArgs);
    }

    public void delete(String whereClause, String[] whereArgs) {
        db.delete("Normal_DaysDB", whereClause, whereArgs);
    }

    public LinkedList<OneLesson> getAll(){
        LinkedList<OneLesson> lessons = new LinkedList<>();
        Cursor cursor = db.query("Normal_DaysDB", null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            OneLesson oneLesson = new OneLesson();
            oneLesson.setUniversity(cursor.getString(0));
            oneLesson.setSemester(cursor.getInt(1));
            oneLesson.setNumber_week(cursor.getInt(2));
            oneLesson.setWeekday(cursor.getString(3));
            oneLesson.setStart_time(cursor.getString(4));
            oneLesson.setEnd_time(cursor.getString(5));
            oneLesson.setSpecialty(cursor.getString(6));
            oneLesson.setYear_specialty(cursor.getInt(7));
            lessons.add(oneLesson);
            cursor.moveToNext();
        }
        return lessons;
    }
    public LinkedList<OneLesson> getCustom(String whereClause, String[] whereArgs) {
        LinkedList<OneLesson> lessons = new LinkedList<>();
        Cursor cursor = db.query("Normal_DaysDB", null, whereClause, whereArgs, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            OneLesson oneLesson = new OneLesson();
            oneLesson.setUniversity(cursor.getString(0));
            oneLesson.setSemester(cursor.getInt(1));
            oneLesson.setNumber_week(cursor.getInt(2));
            oneLesson.setWeekday(cursor.getString(3));
            oneLesson.setStart_time(cursor.getString(4));
            oneLesson.setEnd_time(cursor.getString(5));
            oneLesson.setSpecialty(cursor.getString(6));
            oneLesson.setYear_specialty(cursor.getInt(7));
            lessons.add(oneLesson);
            cursor.moveToNext();
        }
        return lessons;
    }
}


