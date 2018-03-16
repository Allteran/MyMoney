package ru.tele2.mur51.terranova.mymoney.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import io.realm.Realm;
import ru.tele2.mur51.terranova.mymoney.R;
import ru.tele2.mur51.terranova.mymoney.entities.SalesPlan;
import ru.tele2.mur51.terranova.mymoney.helpers.RealmHelper;

/**
 * Created by Allteran on 28.02.2018.
 */

public class SalesPlanFragment extends Fragment {
    public static final String ARG_POS_ID = "pos_id_arg";
    public static final String ARG_DATE = "month_arg";

    public static SalesPlanFragment newInstance(int posId, String date) {
        SalesPlanFragment fragment = new SalesPlanFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POS_ID, posId);
        args.putString(ARG_DATE, date);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sales_plan, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView giPlan = (TextView) view.findViewById(R.id.gi_plan);
        TextView aoPlan = (TextView) view.findViewById(R.id.ao_plan);
        TextView upSalePlan = (TextView) view.findViewById(R.id.up_sale_plan);
        TextView insurancePlan = (TextView) view.findViewById(R.id.insurance_plan);
        TextView yaddPlan = (TextView) view.findViewById(R.id.yadd_plan);
        TextView mnpPlan = (TextView) view.findViewById(R.id.mnp_plan);
        TextView servicesPlan = (TextView) view.findViewById(R.id.services_plan);
        TextView accessPlan = (TextView) view.findViewById(R.id.accessories_plan);
        TextView gsmPlan = (TextView) view.findViewById(R.id.gsm_plan);

        TextView giFact = (TextView) view.findViewById(R.id.gi_fact);
        TextView aoFact = (TextView) view.findViewById(R.id.ao_fact);
        TextView upSaleFact = (TextView) view.findViewById(R.id.up_sale_fact);
        TextView insuranceFact = (TextView) view.findViewById(R.id.insurance_fact);
        TextView yaddFact = (TextView) view.findViewById(R.id.yadd_fact);
        TextView mnpFact = (TextView) view.findViewById(R.id.mnp_fact);
        TextView servicesFact = (TextView) view.findViewById(R.id.services_fact);
        TextView accessFact = (TextView) view.findViewById(R.id.access_fact);
        TextView gsmFact = (TextView) view.findViewById(R.id.gsm_fact);

        TextView giPercent = (TextView) view.findViewById(R.id.gi_percent);
        TextView aoPercent = (TextView) view.findViewById(R.id.ao_percent);
        TextView upSalePercent = (TextView) view.findViewById(R.id.up_sale_percent);
        TextView insurancePercent = (TextView) view.findViewById(R.id.insurance_percent);
        TextView yaddPercent = (TextView) view.findViewById(R.id.yadd_percent);
        TextView mnpPercent = (TextView) view.findViewById(R.id.mnp_percent);
        TextView servicesPercent = (TextView) view.findViewById(R.id.services_percent);
        TextView accessPercent = (TextView) view.findViewById(R.id.access_percent);
        TextView gsmPercent = (TextView) view.findViewById(R.id.gsm_percent);

        Realm realm = Realm.getDefaultInstance();
        RealmHelper realmHelper = new RealmHelper();

        if (getArguments() != null) {
            SalesPlan plan = realmHelper.getSalesPlan(realm, getArguments().getInt(ARG_POS_ID),
                    getArguments().getString(ARG_DATE));
            DecimalFormat formatter = new DecimalFormat("###.#");

            double giNumber = (double) plan.getGiFact() / plan.getGiPlan() * 100;
            String gi = formatter.format(giNumber) + "%";

            double aoNumber = (double) plan.getAoFact() / plan.getAoPlan() * 100;
            String ao = formatter.format(aoNumber) + "%";

            double upSaleNumber = (double) plan.getUpSaleFact() / plan.getUpSalePlan() * 100;
            String upSale = formatter.format(upSaleNumber) + "%";

            double insuranceNumber = (double) plan.getInsuranceFact() / plan.getInsurancePlan() * 100;
            String insurance = formatter.format(insuranceNumber) + "%";

            double yaddNumber = (double) plan.getYaddFact() / plan.getYaddPlan() * 100;
            String yadd = formatter.format(yaddNumber) + "%";

            double mnpNumber = (double) plan.getMnpFact() / plan.getMnpPlan() * 100;
            String mnp = formatter.format(mnpNumber) + "%";

            double servicesNumber = (double) plan.getServicesFact() / plan.getServicesPlan() * 100;
            String services = formatter.format(servicesNumber) + "%";

            double accessNumber = (double) plan.getAccessFact() / plan.getAccessPlan() * 100;
            String accessories = formatter.format(accessNumber) + "%";

            double gsmNumber = (double) plan.getGsmFact() / plan.getGsmPlan() * 100;
            String gsm = formatter.format(gsmNumber) + "%";


            NumberFormat numberFormatter = new DecimalFormat("##,###");
            String giPlanString = numberFormatter.format(plan.getGiPlan()) + " шт.";
            giPlan.setText(giPlanString);

            String aoPlanString = numberFormatter.format(plan.getAoPlan()) + " у.е.";
            aoPlan.setText(aoPlanString);

            String upSalePlanString = numberFormatter.format(plan.getUpSalePlan()) + " у.е.";
            upSalePlan.setText(upSalePlanString);

            String insurancePlanString = numberFormatter.format(plan.getInsurancePlan()) + " р.";
            insurancePlan.setText(insurancePlanString);

            String yaddPlanString = numberFormatter.format(plan.getYaddPlan()) + " шт.";
            yaddPlan.setText(yaddPlanString);

            String mnpPlanString = numberFormatter.format(plan.getMnpPlan()) + " шт.";
            mnpPlan.setText(mnpPlanString);

            String servicesPlanString = numberFormatter.format(plan.getServicesPlan()) + " p.";
            servicesPlan.setText(servicesPlanString);

            String accessPlanString = numberFormatter.format(plan.getAccessPlan()) + " p.";
            accessPlan.setText(accessPlanString);

            String gsmPlanString = numberFormatter.format(plan.getGsmPlan()) + " p.";
            gsmPlan.setText(gsmPlanString);

            String giFactString = numberFormatter.format(plan.getGiFact()) + " шт.";
            giFact.setText(giFactString);

            String aoFactString = numberFormatter.format(plan.getAoFact()) + " у.е.";
            aoFact.setText(aoFactString);

            String upSaleFactString = numberFormatter.format(plan.getUpSaleFact()) + " y.e.";
            upSaleFact.setText(upSaleFactString);

            String insuranceFactString = numberFormatter.format(plan.getInsuranceFact()) + " р.";
            insuranceFact.setText(insuranceFactString);

            String yaddFactString = numberFormatter.format(plan.getYaddFact()) + " шт.";
            yaddFact.setText(yaddFactString);

            String mnpFactString = numberFormatter.format(plan.getMnpFact()) + " шт.";
            mnpFact.setText(mnpFactString);

            String servicesFactString = numberFormatter.format(plan.getServicesFact()) + " р.";
            servicesFact.setText(servicesFactString);

            String accessFactString = numberFormatter.format(plan.getAccessFact()) + " р.";
            accessFact.setText(accessFactString);

            String gsmFactString = numberFormatter.format(plan.getGsmFact()) + " р.";
            gsmFact.setText(gsmFactString);

            giPercent.setText(gi);
            aoPercent.setText(ao);
            upSalePercent.setText(upSale);
            insurancePercent.setText(insurance);
            yaddPercent.setText(yadd);
            mnpPercent.setText(mnp);
            servicesPercent.setText(services);
            accessPercent.setText(accessories);
            gsmPercent.setText(gsm);
        }
    }


}
