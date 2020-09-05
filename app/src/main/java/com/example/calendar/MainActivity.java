package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;


public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;

    private NormalDaysDB normalDaysDB;
    private CorrectedDayDB correctedDayDB;
    SharedPreferences list_university;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //list_university = getSharedPreferences("ListUniversity", MODE_PRIVATE);
        //setUniversity("ИРГАУ");
        //setUniversity("ИГУ");
        //deleteUniversity("ИГУ");
        //Log.d("AAAA", "" + getUniversity().length);

        //normalDaysDB = new NormalDaysDB(this);
        //normalDaysDB.insert(new OneLesson("ИГУ", 1, 1, "Monday","8:00", "9:30", "Энергетики", 1));
        //normalDaysDB.insert(new OneLesson("ИГУ", 1, 2, "Monday","8:00", "9:30", "Энергетики", 1));
        //normalDaysDB.delete("number_week = ?", new String[] {"2"});
        //normalDaysDB.delete("number_week = ?", new String[] {"1"});
        //Log.d("AAAA", "" + normalDaysDB.getCustom("university = ?", new String[] {"ИГУ"}));

        //correctedDayDB = new CorrectedDayDB(this);
        //correctedDayDB.insert(new CorrectedLesson("ИГУ", "2020-09-01","8:00", "9:30", "Энергетики", 1));
        //correctedDayDB.delete("data_lesson = ?", new String[] {"2020-09-01"});
        //Log.d("AAAA", "" + correctedDayDB.getAll());



        ListView university = findViewById(R.id.list_university);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        university.setAdapter(adapter);
        university.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, WeekActivity.class);
                intent.putExtra("university", adapterView.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });
    }

    public void exit(View view) {
        finish();
    }

    public void add(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText entry = new EditText(this);
        entry.setHint("Введите название института");
        entry.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        final TextView text = new TextView(this);
        text.setText("Добавление института");
        text.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Dialog dialog = builder.setCustomTitle(text)
                .setView(entry)
                .setNegativeButton("Отмена", null)
                .setPositiveButton("Готово", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapter.add(entry.getText().toString());
                        adapter.notifyDataSetChanged();
                    }
                }).create();
        dialog.show();
    }

    public void setUniversity(String University){
        SharedPreferences.Editor prefEditor = list_university.edit();
        prefEditor.putString(University, "");
        prefEditor.apply();
    }

    public void deleteUniversity(String University){
        SharedPreferences.Editor prefEditor = list_university.edit();
        prefEditor.remove(University);
        prefEditor.apply();
    }

    public String[] getUniversity(){
       Map<String,?> names = list_university.getAll();
       Set<String> keys = names.keySet();
       String[] strings = keys.toArray(new String[keys.size()]);
       return strings;
    }
}
