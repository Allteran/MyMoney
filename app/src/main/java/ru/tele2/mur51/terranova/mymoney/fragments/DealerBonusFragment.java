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
import ru.tele2.mur51.terranova.mymoney.adapters.DealerBonusAdapter;
import ru.tele2.mur51.terranova.mymoney.entities.DealerBonus;
import ru.tele2.mur51.terranova.mymoney.helpers.RealmHelper;

/**
 * Created by Allteran on 24.02.2018.
 */

public class DealerBonusFragment extends Fragment {
    public static final String ARG_DB_DATE = "date_dealer_bonus";
    public static final String ARG_DB_SELLER = "user_to_pay_dealer_bonus";

    public static DealerBonusFragment newInstance(String date, String seller) {
        DealerBonusFragment fragment = new DealerBonusFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DB_DATE, date);
        args.putString(ARG_DB_SELLER, seller);
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
        return inflater.inflate(R.layout.fragment_dealer_bonus, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dealer_bonus_recycler);
        Realm realm = Realm.getDefaultInstance();
        RealmHelper realmHelper = new RealmHelper();
        List<DealerBonus> dbList = new ArrayList<>();
        if (getArguments()!= null) {
            dbList = realmHelper.getDealerBonus(realm, getArguments().getString(ARG_DB_SELLER));
        }
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView (c) Google
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        DealerBonusAdapter adapter = new DealerBonusAdapter(dbList);
        recyclerView.setAdapter(adapter);
    }


}
