package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    ListView listView_days, listView_lessons;
    DayAdapterOld adapter_days;
    LessonAdapter adapter_lessons;
    ArrayList<String> days = new ArrayList<>();
    ArrayList<String> lessons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

//        listView_days = findViewById(R.id.list_days);
//        initList();
//        adapter_days = new DayAdapterOld(this,R.layout.day_page,R.id.name_day_add,days);
//        listView_days.setAdapter(adapter_days);
//
//        listView_lessons = findViewById(R.id.list_lessons);
//        adapter_lessons = new LessonAdapter(this,R.layout.lesson,lessons);
//        listView_lessons.setAdapter(adapter_lessons);
    }

    private void initList(){
        days.add("Понедельник");
        days.add("Вторник");
        days.add("Среда");
        days.add("Четверг");
        days.add("Пятница");
        days.add("Суббота");
        lessons.add("1");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.save_university){
            finish();
        } else if(view.getId() == R.id.btn_cancel){
            finish();
        }
    }
}