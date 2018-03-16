package ru.tele2.mur51.terranova.mymoney.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import ru.tele2.mur51.terranova.mymoney.fragments.MyMoneyPChartFragment;

/**
 * Created by Allteran on 23.02.2018.
 */

public class MyMoneyPagerAdapter extends FragmentStatePagerAdapter {
    private List<MyMoneyPChartFragment> mFragments = new ArrayList<>();

    public MyMoneyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addPage (MyMoneyPChartFragment fragment) {
        mFragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
