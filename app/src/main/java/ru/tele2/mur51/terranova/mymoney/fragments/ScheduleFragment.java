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

import io.realm.Realm;
import ru.tele2.mur51.terranova.mymoney.R;
import ru.tele2.mur51.terranova.mymoney.adapters.ScheduleAdapter;
import ru.tele2.mur51.terranova.mymoney.entities.WorkDay;
import ru.tele2.mur51.terranova.mymoney.helpers.RealmHelper;

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
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.schedule_recycler);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView (c) Google
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Realm realm = Realm.getDefaultInstance();
        RealmHelper realmHelper = new RealmHelper();
        if (getArguments() != null) {
            ScheduleAdapter adapter = new ScheduleAdapter(realmHelper.getSchedule(realm,
                    getArguments().getInt(ARG_POS_ID), getArguments().getString(ARG_DATE)));
            recyclerView.setAdapter(adapter);
        }


    }


}
