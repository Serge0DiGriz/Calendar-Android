package com.example.calendar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Objects;

public class DayPageFragment extends Fragment {
    int pageNum;

    public static DayPageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt("pageNum", page);

        DayPageFragment fragment = new DayPageFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        pageNum = getArguments() != null ? getArguments().getInt("pageNum") : 0;
        super.onCreate(savedInstanceState);
    }

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View page = inflater.inflate(R.layout.day_page, container, false);
        SimpleCursorAdapter adapter = WeekActivity.cursorAdapters.get(pageNum);

        ListView lessonsView = page.findViewById(R.id.list_lessons);
        lessonsView.setAdapter(adapter);

        Button button = page.findViewById(R.id.add_pair);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addLesson(WeekActivity.cursorAdapters.get(pageNum));
            }
        });

        return page;
    }

    private void addLesson(SimpleCursorAdapter adapter) {
        new AddLessonDialog(pageNum).show(getChildFragmentManager(), "custom");
        adapter.swapCursor(WeekActivity.db.select(WeekActivity.selectColumns,
                new String[]{WeekActivity.universityName, String.valueOf(pageNum)},
                LessonsDB.COLUMN_START_TIME));
        adapter.notifyDataSetChanged();
    }

}
