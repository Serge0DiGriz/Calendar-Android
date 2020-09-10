package com.example.calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddLessonDialog extends DialogFragment {
    int weekday;

    public AddLessonDialog(int weekday) {
        this.weekday = weekday;
    }

    @NonNull @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View dialogLayout = LayoutInflater.from(getContext())
                .inflate(R.layout.add_lesson, null);
        final EditText speciality = dialogLayout.findViewById(R.id.edit_specialty),
                course = dialogLayout.findViewById(R.id.edit_course),
                start_time = dialogLayout.findViewById(R.id.edit_startTime),
                end_time = dialogLayout.findViewById(R.id.edit_endTime);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.setTitle("Добавить пару").setView(dialogLayout)
                .setPositiveButton("Готово", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        WeekActivity.db.insert(new Lesson(WeekActivity.universityName,
                                speciality.getText().toString(),
                                Integer.parseInt(course.getText().toString()), 0,
                                start_time.getText().toString(), end_time.getText().toString(),
                                1, weekday));
                    }
                })
                .create();
    }

}
