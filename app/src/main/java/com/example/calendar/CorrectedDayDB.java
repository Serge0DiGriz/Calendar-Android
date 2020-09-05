package com.example.calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.LinkedList;

public class CorrectedDayDB extends SQLiteOpenHelper {

    private Context context;
    private SQLiteDatabase db;

    public CorrectedDayDB(@Nullable Context context) {
        super(context, "CorrectedDayDB", null, 1);
        db = getWritableDatabase();
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS CorrectedDayDB(\n" +
                "  university TEXT,\n" +
                "  data_lesson DATA,\n" +
                "  start_time TIME,\n" +
                "  end_time TIME,\n" +
                "  specialty TEXT,\n" +
                "  year_specialty INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(CorrectedLesson correctedLesson) {
        ContentValues cv = new ContentValues();
        cv.put("university", correctedLesson.getUniversity());
        cv.put("data_lesson", correctedLesson.getData_lesson());
        cv.put("start_time", correctedLesson.getStart_time());
        cv.put("end_time", correctedLesson.getEnd_time());
        cv.put("specialty", correctedLesson.getSpecialty());
        cv.put("year_specialty", correctedLesson.getYear_specialty());
        db.insert("CorrectedDayDB", null, cv);
    }

    public void update(CorrectedLesson correctedLesson, String whereClause, String[] whereArgs) {
        ContentValues cv = new ContentValues();
        cv.put("university", correctedLesson.getUniversity());
        cv.put("data_lesson", correctedLesson.getData_lesson());
        cv.put("start_time", correctedLesson.getStart_time());
        cv.put("end_time", correctedLesson.getEnd_time());
        cv.put("specialty", correctedLesson.getSpecialty());
        cv.put("year_specialty", correctedLesson.getYear_specialty());
        db.update("CorrectedDayDB", cv, whereClause, whereArgs);
    }

    public void delete(String whereClause, String[] whereArgs) {
        db.delete("CorrectedDayDB", whereClause, whereArgs);
    }

    public LinkedList<CorrectedLesson> getAll() {
        LinkedList<CorrectedLesson> correctedLessons = new LinkedList<>();
        Cursor cursor = db.query("CorrectedDayDB", null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CorrectedLesson correctedLesson = new CorrectedLesson();
            correctedLesson.setUniversity(cursor.getString(0));
            correctedLesson.setData_lesson(cursor.getString(1));
            correctedLesson.setStart_time(cursor.getString(2));
            correctedLesson.setEnd_time(cursor.getString(3));
            correctedLesson.setSpecialty(cursor.getString(4));
            correctedLesson.setYear_specialty(cursor.getInt(5));
            correctedLessons.add(correctedLesson);
            cursor.moveToNext();
        }
        return correctedLessons;
    }

    public LinkedList<CorrectedLesson> getCustom(String whereClause, String[] whereArgs) {
        LinkedList<CorrectedLesson> correctedLessons = new LinkedList<>();
        Cursor cursor = db.query("CorrectedDayDB", null, whereClause, whereArgs, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CorrectedLesson correctedLesson = new CorrectedLesson();
            correctedLesson.setUniversity(cursor.getString(0));
            correctedLesson.setData_lesson(cursor.getString(1));
            correctedLesson.setStart_time(cursor.getString(2));
            correctedLesson.setEnd_time(cursor.getString(3));
            correctedLesson.setSpecialty(cursor.getString(4));
            correctedLesson.setYear_specialty(cursor.getInt(5));
            correctedLessons.add(correctedLesson);
            cursor.moveToNext();
        }
        return correctedLessons;
    }

}
