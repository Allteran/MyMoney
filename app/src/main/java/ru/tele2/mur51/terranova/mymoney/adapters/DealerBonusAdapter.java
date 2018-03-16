package ru.tele2.mur51.terranova.mymoney.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import ru.tele2.mur51.terranova.mymoney.R;
import ru.tele2.mur51.terranova.mymoney.entities.DealerBonus;

/**
 * Created by Allteran on 24.02.2018.
 */

public class DealerBonusAdapter extends RecyclerView.Adapter<DealerBonusAdapter.ViewHolder> {
    private List<DealerBonus> mDealerBonusList = new ArrayList<>();

    public DealerBonusAdapter(List<DealerBonus> mDealerBonusList) {
        this.mDealerBonusList = mDealerBonusList;
    }

    @Override
    public DealerBonusAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dealer_bonus_item,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DealerBonus dealerBonus = mDealerBonusList.get(position);

        String date = String.valueOf(dealerBonus.dateParcer(dealerBonus.getPaidMonth())) +
                " " + String.valueOf(dealerBonus.getPaidYear()) + "-го года";
        holder.date.setText(date);

        NumberFormat formatter = new DecimalFormat("##,###");
        String amount = formatter.format(dealerBonus.getAmount()) + " р.";
        holder.amount.setText(amount);

        if (dealerBonus.isPaid()) {
            holder.indicator.setImageResource(R.drawable.ind_db_paid);
        } else {
            holder.indicator.setImageResource(R.drawable.ind_db_nonpaid);
        }
    }

    @Override
    public int getItemCount() {
        return mDealerBonusList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView indicator;
        private TextView date;
        private TextView amount;

        public ViewHolder(View itemView) {
            super(itemView);

            indicator = (ImageView) itemView.findViewById(R.id.ind_dealer_bonus);
            date = (TextView) itemView.findViewById(R.id.date_dealer_bonus);
            amount = (TextView) itemView.findViewById(R.id.amount_dealer_bonus);
        }
    }
}
