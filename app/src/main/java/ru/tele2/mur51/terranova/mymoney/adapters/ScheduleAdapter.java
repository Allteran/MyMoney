package ru.tele2.mur51.terranova.mymoney.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import ru.tele2.mur51.terranova.mymoney.R;
import ru.tele2.mur51.terranova.mymoney.entities.WorkDay;

/**
 * Created by Allteran on 03.03.2018.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<WorkDay> mSchedule;

    public ScheduleAdapter (List<WorkDay> workDays) {
        this.mSchedule = workDays;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == 0) {
            View headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item_header,
                    parent, false);
            return new HeaderViewHolder(headerView);
        } else {
            View mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item,
                    parent, false);
            return new MainViewHolder(mainView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        WorkDay workDay = mSchedule.get(position);

        if(holder.getItemViewType() == 0) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
            String headerTittle = "График на " + workDay.getCurrentMonth();
            headerHolder.header.setText(headerTittle);
        } else {
            MainViewHolder mainHolder = (MainViewHolder) holder;
            mainHolder.day.setText(workDay.getDay());
            mainHolder.date.setText(workDay.getDate());
            mainHolder.seller.setText(workDay.getSeller());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 2;
        }
    }

    @Override
    public int getItemCount() {
        return mSchedule.size();
    }


    public static class MainViewHolder extends RecyclerView.ViewHolder {
        private TextView day;
        private TextView date;
        private TextView seller;

        public MainViewHolder(View itemView) {
            super(itemView);

            day = (TextView) itemView.findViewById(R.id.date_day_of_week);
            date = (TextView) itemView.findViewById(R.id.date_number);
            seller = (TextView) itemView.findViewById(R.id.seller_name);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView header;

        public HeaderViewHolder(View itemView) {
            super(itemView);

            header = (TextView) itemView.findViewById(R.id.schedule_header);
        }
    }
}
