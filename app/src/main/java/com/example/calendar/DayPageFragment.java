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

        LessonCursorAdapter adapter;
        switch (pageNum) {
            case 0: adapter = WeekActivity.MondayAdapter; break;
            case 1: adapter = WeekActivity.TuesdayAdapter; break;
            case 2: adapter = WeekActivity.WednesdayAdapter; break;
            case 3: adapter = WeekActivity.ThursdayAdapter; break;
            case 4: adapter = WeekActivity.FridayAdapter; break;
            default: adapter = WeekActivity.SaturdayAdapter; break;
        }

        ListView lessonsView = page.findViewById(R.id.list_lessons);
        lessonsView.setAdapter(adapter);

        Button button = page.findViewById(R.id.add_pair);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddLessonDialog dialog = new AddLessonDialog(pageNum);
                dialog.show(getChildFragmentManager(), "custom");
            }
        });

        return page;
    }

    @Override
    public void onResume() {
        LessonCursorAdapter adapter;
        switch (pageNum) {
            case 0: adapter = WeekActivity.MondayAdapter; break;
            case 1: adapter = WeekActivity.TuesdayAdapter; break;
            case 2: adapter = WeekActivity.WednesdayAdapter; break;
            case 3: adapter = WeekActivity.ThursdayAdapter; break;
            case 4: adapter = WeekActivity.FridayAdapter; break;
            default: adapter = WeekActivity.SaturdayAdapter; break;
        }
        if (adapter.getCount() == 0)
            new AddLessonDialog(pageNum).show(getChildFragmentManager(), "custom");
        super.onResume();
    }
}
