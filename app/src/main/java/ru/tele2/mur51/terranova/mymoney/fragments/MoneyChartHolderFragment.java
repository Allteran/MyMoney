package ru.tele2.mur51.terranova.mymoney.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.tele2.mur51.terranova.mymoney.R;
import ru.tele2.mur51.terranova.mymoney.adapters.MyMoneyPagerAdapter;
import ru.tele2.mur51.terranova.mymoney.utilities.Const;

/**
 * Created by Allteran on 23.02.2018.
 */

public class MoneyChartHolderFragment extends Fragment {
    public static final String ARG_DATE = "report_date_pchart_holder";
    public static final String ARG_USER = "logged_user_pchart_holder";
    public static final String ARG_POS_ID = "pos_id_pchart_holder";

    public static MoneyChartHolderFragment newInstance(String date, int posId, String user) {
        MoneyChartHolderFragment fragment = new MoneyChartHolderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DATE, date);
        args.putString(ARG_USER, user);
        args.putInt(ARG_POS_ID, posId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_money_chart_holder, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        MyMoneyPagerAdapter adapter = new MyMoneyPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addPage(MyMoneyPChartFragment.newInstance("Январь", "79021335276",
                getArguments().getInt(ARG_POS_ID)));
        adapter.addPage(MyMoneyPChartFragment.newInstance("Декабрь", "79021335276",
                getArguments().getInt(ARG_POS_ID)));
        adapter.addPage(MyMoneyPChartFragment.newInstance("Ноябрь", "79021335276",
                getArguments().getInt(ARG_POS_ID)));
        adapter.addPage(MyMoneyPChartFragment.newInstance("Октябрь", "79021335276",
                getArguments().getInt(ARG_POS_ID)));
        adapter.addPage(MyMoneyPChartFragment.newInstance("Сентябрь", "79021335276",
                getArguments().getInt(ARG_POS_ID)));

        viewPager.setAdapter(adapter);
    }
}
