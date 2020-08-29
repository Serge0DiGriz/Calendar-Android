package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Dialog dialog = builder.setTitle("Добавление института")
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

}
