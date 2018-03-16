package ru.tele2.mur51.terranova.mymoney.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import ru.tele2.mur51.terranova.mymoney.entities.Salary;
import ru.tele2.mur51.terranova.mymoney.R;
import ru.tele2.mur51.terranova.mymoney.helpers.RealmHelper;
import ru.tele2.mur51.terranova.mymoney.utilities.Const;

import static android.view.ViewGroup.LayoutParams;

/**
 * Created by Allteran on 10/24/2017.
 */

public class MyMoneyPChartFragment extends Fragment {
    public static final String ARG_DATE = "report_date_pchart";
    public static final String ARG_USER = "logged_user_pchart";
    public static final String ARG_POS_ID = "pos_id_pchart";
    private PieChart mChart;

    private Realm mRealm;


    public MyMoneyPChartFragment() {
    }

    public static MyMoneyPChartFragment newInstance(String date, String user, int posId) {
        MyMoneyPChartFragment fragment = new MyMoneyPChartFragment();
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
        mRealm = Realm.getDefaultInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_money_pchart, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView reportDate = view.findViewById(R.id.report_date_textview);
        reportDate.setText(getArguments().getString(ARG_DATE));
        // setup the chart with grades and data
        // get max height of the screen and set PieChart layout params programmatically
        mChart = (PieChart) view.findViewById(R.id.pchart_whole_salary);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        LayoutParams params = mChart.getLayoutParams();
        params.height = (int) (displayMetrics.heightPixels * 0.6);
        mChart.setLayoutParams(params);

        if (getArguments() != null) {
            RealmHelper realmHelper = new RealmHelper();
            Salary salary = realmHelper.getSalary(mRealm,getArguments().getInt(ARG_POS_ID),
                    getArguments().getString(ARG_USER));
            //set data to PieChart
            List<PieEntry> pieEntries = new ArrayList<>();

            pieEntries.add(new PieEntry((float) salary.getRatePay(), "Отработанные часы"));
            pieEntries.add(new PieEntry((float) salary.getSimPay(), "Продажа Sim-карт"));
            pieEntries.add(new PieEntry((float) salary.getKpiPay(), "Выполнение KPI"));
            pieEntries.add(new PieEntry((float) salary.getServicesPay(), "Продажа услуг"));
            pieEntries.add(new PieEntry((float) salary.getEquipPay(), "Продажа оборудования"));
            pieEntries.add(new PieEntry((float) salary.getBonusPay(), "Премия"));

            PieDataSet dataSet = new PieDataSet(pieEntries, null);

            int colorRate = ContextCompat.getColor(getContext(), R.color.colorIndicatorRate);
            int colorSim = ContextCompat.getColor(getContext(), R.color.colorIndicatorSim);
            int colorKpi = ContextCompat.getColor(getContext(), R.color.colorIndicatorKpi);
            int colorServices = ContextCompat.getColor(getContext(), R.color.colorIndicatorServices);
            int colorEquip = ContextCompat.getColor(getContext(), R.color.colorIndicatorEquip);
            int colorBonus = ContextCompat.getColor(getContext(), R.color.colorIndicatorBonuses);

            dataSet.setColors(colorRate, colorSim, colorKpi, colorServices, colorEquip, colorBonus);

            PieData pieData = new PieData(dataSet);
            pieData.setDrawValues(false);

            mChart.setDrawEntryLabels(false); // we dont need to draw entry labels on chart
            NumberFormat formatter = new DecimalFormat("##,###");

            mChart.setCenterText(String.valueOf(formatter.format(salary.getWholeSalary()) +
                    " р."));
            mChart.setCenterTextSize(20f);
            mChart.setHoleRadius(60f);


            mChart.setDescription(null);
            mChart.setRotationEnabled(false);
            mChart.getLegend().setEnabled(false);
            mChart.setData(pieData);
            mChart.invalidate();
        }
    }


}
