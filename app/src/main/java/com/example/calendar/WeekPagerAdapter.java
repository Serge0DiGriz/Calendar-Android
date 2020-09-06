package com.example.calendar;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class WeekPagerAdapter extends FragmentStatePagerAdapter {
    String[] week;

    public WeekPagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        week = context.getResources().getStringArray(R.array.week);
    }

    @NonNull @Override
    public Fragment getItem(int position) {
        return DayPageFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return week.length;
    }

    @Nullable @Override
    public CharSequence getPageTitle(int position) {
        return week[position];
    }

}
