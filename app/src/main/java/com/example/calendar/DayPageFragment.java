package com.example.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class DayPageFragment extends Fragment {
    int pageNum;

    public DayPageFragment() { }

    public static DayPageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt("pageNum", page);

        DayPageFragment fragment = new DayPageFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] week = getResources().getStringArray(R.array.week);
        pageNum = getArguments() != null ? getArguments().getInt("pageNum") : 1;
    }

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View page = inflater.inflate(R.layout.day_page, container, false);
        final DayAdapter adapter = WeekActivity.dayAdapters.get(pageNum);

        ListView lessonsView = page.findViewById(R.id.list_lessons);
        lessonsView.setAdapter(adapter);

        Button button = page.findViewById(R.id.add_pair);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.add(new Lesson("", "", "", ""));
                adapter.notifyDataSetChanged();
            }
        });

        return page;
    }
}
