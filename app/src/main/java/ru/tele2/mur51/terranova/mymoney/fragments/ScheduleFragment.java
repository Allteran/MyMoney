package ru.tele2.mur51.terranova.mymoney.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.tele2.mur51.terranova.mymoney.R;
import ru.tele2.mur51.terranova.mymoney.adapters.ScheduleAdapter;
import ru.tele2.mur51.terranova.mymoney.entities.WorkDay;

/**
 * Created by Allteran on 03.03.2018.
 */

public class ScheduleFragment extends Fragment {
    public static final String ARG_DATE = "arg_month";
    public static final String ARG_POS_ID = "arg_pos_id";

    public static ScheduleFragment newInstance(String date, int posId) {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DATE, date);
        args.putInt(ARG_POS_ID, posId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.schedule_recycler);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView (c) Google
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ScheduleAdapter adapter = new ScheduleAdapter(dummySchedule());
        recyclerView.setAdapter(adapter);

    }

    private List<WorkDay> dummySchedule() {
        List<WorkDay> workDays = new ArrayList<>();
        //First item should be empty entity with only current month cuz it will be replaced with header
        workDays.add(new WorkDay(0,"","", "", "март"));
        workDays.add(new WorkDay(853307,"Пн","05.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Вт","06.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Ср","07.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Чт","08.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Пт","09.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Сб","10.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Вс","11.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Пн","12.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Вт","13.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Ср","14.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Чт","15.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Пт","16.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Сб","17.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Вс","18.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Пн","19.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Вт","20.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Ср","21.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Чт","22.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Пт","23.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Сб","24.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Вс","25.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Пн","26.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Вт","27.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Ср","28.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Чт","29.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Пт","30.03.2018", "Прозапас В.А.", "март"));
        workDays.add(new WorkDay(853307,"Сб","31.03.2018", "Прозапас В.А.", "март"));

        return workDays;
    }
}
