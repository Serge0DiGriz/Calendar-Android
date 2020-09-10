package com.example.calendar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddLessonDialog extends DialogFragment {
    private int weekday;
    private boolean[] states = {false, false, false, false};

    public AddLessonDialog(int weekday) {
        this.weekday = weekday;
    }

    @NonNull @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View dialogLayout = LayoutInflater.from(getContext())
                .inflate(R.layout.add_lesson, null);
        final EditText start_timeView = dialogLayout.findViewById(R.id.edit_startTime),
                end_timeView = dialogLayout.findViewById(R.id.edit_endTime),
                specialityView = dialogLayout.findViewById(R.id.edit_specialty),
                courseView = dialogLayout.findViewById(R.id.edit_course);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                AlertDialog dialog = (AlertDialog) getDialog();
                if (dialog != null)
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(
                            start_timeView.getText().length() != 0 &&
                            end_timeView.getText().length() != 0 &&
                            specialityView.getText().length() != 0 &&
                            courseView.getText().length() != 0);
            }
        };
        start_timeView.addTextChangedListener(watcher);
        end_timeView.addTextChangedListener(watcher);
        specialityView.addTextChangedListener(watcher);
        courseView.addTextChangedListener(watcher);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.setTitle("Добавить пару").setView(dialogLayout)
                .setPositiveButton("Готово", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        String start_time = start_timeView.getText().toString()
                                        .replace('-', ':')
                                        .replace('/', ':'),
                                end_time = end_timeView.getText().toString()
                                        .replace('-', ':')
                                        .replace('/', ':'),
                                speciality = specialityView.getText().toString(),
                                course = courseView.getText().toString();

                        String[] sTime = start_time.split(":"),
                                eTime = end_time.split(":");

                        for (int i=0; i<2; i++) {
                            if (sTime[i].length() == 1)
                                sTime[i] = "0" + sTime[i];
                            if (eTime[i].length() == 1)
                                eTime[i] = "0" + eTime[i];
                        }

                        WeekActivity.db.insert(new Lesson(WeekActivity.universityName,
                                speciality, Integer.parseInt(course), 0,
                                sTime[0] + ":" + sTime[1],
                                eTime[0] + ":" + eTime[1],
                                1, weekday));

                        LessonCursorAdapter adapter;
                        switch (weekday) {
                            case 0: adapter = WeekActivity.MondayAdapter; break;
                            case 1: adapter = WeekActivity.TuesdayAdapter; break;
                            case 2: adapter = WeekActivity.WednesdayAdapter; break;
                            case 3: adapter = WeekActivity.ThursdayAdapter; break;
                            case 4: adapter = WeekActivity.FridayAdapter; break;
                            default: adapter = WeekActivity.SaturdayAdapter; break;
                        }

                        adapter.changeCursor(WeekActivity.db.select(WeekActivity.selectColumns,
                                new String[]{WeekActivity.universityName, String.valueOf(weekday)},
                                LessonsDB.COLUMN_START_TIME));
                    }
                }).create();
    }

    @Override
    public void onResume() {
        super.onResume();
        AlertDialog dialog = (AlertDialog) getDialog();
        if (dialog != null) dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
    }
}
