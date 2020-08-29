package com.example.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DayAdapterOld extends ArrayAdapter<String> {

    private Context context;
    private List<String> days;

    public DayAdapterOld(Context context, int resource, int textViewResource, List<String> objects){
        super(context, resource, textViewResource, objects);
        this.context = context;
        days = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.day_page, null);
//        TextView name_day = view1.findViewById(R.id.name_day_add);
//        name_day.setText(days.get(position));
        return view1;
    }
}
