package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;

    private final String UNIVERSITY_PREFERENCE_KEY = "UniversityList";
    private SharedPreferences preferences;
    private ArrayList<String> universities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //normalDaysDB = new NormalDayDB(this);
        //normalDaysDB.insert(new OneLesson("ИГУ", 1, 1, "Monday","8:00", "9:30", "Энергетики", 1));
        //normalDaysDB.insert(new OneLesson("ИГУ", 1, 2, "Monday","8:00", "9:30", "Энергетики", 1));
        //normalDaysDB.delete("number_week = ?", new String[] {"2"});
        //normalDaysDB.delete("number_week = ?", new String[] {"1"});
        //Log.d("AAAA", ""+normalDaysDB.getAll());
        //Log.d("AAAA", "" + normalDaysDB.getCustom("university = ?", new String[] {"ИГУ"}));

        //correctedDayDB = new CorrectedDayDB(this);
        //correctedDayDB.insert(new CorrectedLesson("ИГУ", "2020-09-01","8:00", "9:30", "Энергетики", 1));
        //correctedDayDB.delete("data_lesson = ?", new String[] {"2020-09-01"});
        //Log.d("AAAA", "" + correctedDayDB.getAll());

        preferences = getSharedPreferences("MainPreferences", MODE_PRIVATE);
        universities = new ArrayList<>(Objects.requireNonNull(
                preferences.getStringSet(UNIVERSITY_PREFERENCE_KEY, new HashSet<String>())));
        ListView university = findViewById(R.id.list_university);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, universities);
        university.setAdapter(adapter);
        university.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, WeekActivity.class);
                intent.putExtra("university", adapterView.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });
        university.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String university = adapterView.getItemAtPosition(i).toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                Dialog dialog = builder.setTitle("Удалить институт  «" + university + "»?")
                        .setNegativeButton("Отмена", null)
                        .setPositiveButton("Удалить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                adapter.remove(university);
                                adapter.notifyDataSetChanged();
                            }
                        }).create();
                dialog.show();
                return true;
            }
        });
    }

    public void exit(View view) {
        finish();
    }

    public void add(View view) {
        final EditText entry = new EditText(this);
        entry.setHint("Введите название института");
        entry.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView text = new TextView(this);
        text.setText("Добавление института");
        text.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Dialog dialog = builder.setCustomTitle(text).setView(entry)
                .setNegativeButton("Отмена", null)
                .setPositiveButton("Готово", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String university = entry.getText().toString();
                        if (!universities.contains(university)) {
                            adapter.add(university);
                            adapter.notifyDataSetChanged();
                        } else
                            Toast.makeText(getApplicationContext(),
                                    "Этот институт уже в списке", Toast.LENGTH_SHORT).show();
                    }
                }).create();
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(UNIVERSITY_PREFERENCE_KEY, new HashSet<>(universities));
        editor.apply();

        super.onDestroy();
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

    void testDataBase() {
        LessonsDB lessonsDB = new LessonsDB(this);

        Log.d("TestDB", String.valueOf(lessonsDB.select(
                new String[]{LessonsDB.COLUMN_UNIVERSITY},
                new String[]{universities.get(0)},
                LessonsDB.COLUMN_ID).getCount()));

        lessonsDB.insert(new Lesson(universities.get(0), "полиглоты", 10,
                10, "1", "2", 10, 10));
        lessonsDB.insert(new Lesson(universities.get(0), "полиглоты", 5,
                10, "1", "2", 10, 10));
        lessonsDB.insert(new Lesson(universities.get(0), "бегемоты", 10,
                10, "1", "2", 10, 10));

        Cursor cursor = lessonsDB.select(
                new String[]{LessonsDB.COLUMN_UNIVERSITY},
                new String[]{universities.get(0)},
                LessonsDB.COLUMN_ID);
        Log.d("TestDB", cursorToList(cursor).toString());

        Log.d("TestDB", String.valueOf(lessonsDB.select(
                new String[]{LessonsDB.COLUMN_UNIVERSITY},
                new String[]{universities.get(0)},
                LessonsDB.COLUMN_ID).getCount()));
        Log.d("TestDB", String.valueOf(lessonsDB.select(
                new String[]{LessonsDB.COLUMN_UNIVERSITY, LessonsDB.COLUMN_SPECIALITY},
                new String[]{universities.get(0), "полиглоты"},
                LessonsDB.COLUMN_ID).getCount()));
        Log.d("TestDB", String.valueOf(lessonsDB.select(
                new String[]{LessonsDB.COLUMN_UNIVERSITY, LessonsDB.COLUMN_SPECIALITY},
                new String[]{universities.get(0), "бегемоты"},
                LessonsDB.COLUMN_ID).getCount()));

        Log.d("TestDB", String.valueOf(lessonsDB.select(
                new String[]{LessonsDB.COLUMN_UNIVERSITY, LessonsDB.COLUMN_COURSE},
                new String[]{universities.get(0), "10"},
                LessonsDB.COLUMN_ID).getCount()));

        lessonsDB.update(new Lesson(universities.get(0), "бегемоты", 5,
                10, "1", "2", 10, 10), 1);

        Log.d("TestDB", String.valueOf(lessonsDB.select(
                new String[]{LessonsDB.COLUMN_UNIVERSITY},
                new String[]{universities.get(0)},
                LessonsDB.COLUMN_ID).getCount()));
        Log.d("TestDB", String.valueOf(lessonsDB.select(
                new String[]{LessonsDB.COLUMN_UNIVERSITY, LessonsDB.COLUMN_SPECIALITY},
                new String[]{universities.get(0), "полиглоты"},
                LessonsDB.COLUMN_ID).getCount()));
        Log.d("TestDB", String.valueOf(lessonsDB.select(
                new String[]{LessonsDB.COLUMN_UNIVERSITY, LessonsDB.COLUMN_SPECIALITY},
                new String[]{universities.get(0), "бегемоты"},
                LessonsDB.COLUMN_ID).getCount()));

        lessonsDB.delete(1);

        Log.d("TestDB", String.valueOf(lessonsDB.select(
                new String[]{LessonsDB.COLUMN_UNIVERSITY},
                new String[]{universities.get(0)},
                LessonsDB.COLUMN_ID).getCount()));
        Log.d("TestDB", String.valueOf(lessonsDB.select(
                new String[]{LessonsDB.COLUMN_UNIVERSITY, LessonsDB.COLUMN_SPECIALITY},
                new String[]{universities.get(0), "полиглоты"},
                LessonsDB.COLUMN_ID).getCount()));
        Log.d("TestDB", String.valueOf(lessonsDB.select(
                new String[]{LessonsDB.COLUMN_UNIVERSITY, LessonsDB.COLUMN_SPECIALITY},
                new String[]{universities.get(0), "бегемоты"},
                LessonsDB.COLUMN_ID).getCount()));

        lessonsDB.deleteAll();
        lessonsDB.close();
    }
}
