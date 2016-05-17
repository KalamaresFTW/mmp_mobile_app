package mmp.mymoneyplatform_mobile_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mmp.mymoneyplatform_mobile_app.R;
import mmp.mymoneyplatform_mobile_app.pojo.PensionData;

/**
 * Created by Nacho on 16/05/2016.
 */
public class PensionDataAdapter {

    private static PensionDataAdapter instance = null;

    PensionDataAdapter() {
    }

    public static PensionDataAdapter getInstance() {
        if (instance == null) {
            instance = new PensionDataAdapter();
        }
        return instance;
    }

    public void loadData(ArrayList<PensionData> pensionData, LinearLayout rel_lay, LayoutInflater inflater) {
        //First, we delete all the existing childs to not have any duplication with it
        rel_lay.removeAllViewsInLayout();
        //Bunch of variables we declare here instead at inside the loop to improve performance
        View lay_pension_data;
        TextView title;
        TextView balance;
        TextView monthly;

        for (int i = 0; i < pensionData.size(); i++) {
            lay_pension_data = inflater.inflate(R.layout.lay_pension_screen_data, null);

            title = (TextView) lay_pension_data.findViewById(R.id.tv_pension_screen_item_title);
            balance = (TextView) lay_pension_data.findViewById(R.id.tv_pension_screen_item_balance_money);
            monthly = (TextView) lay_pension_data.findViewById(R.id.tv_pension_screen_item_monthly_money);

            lay_pension_data.setId(pensionData.get(i).getPensionID());
            title.setText(pensionData.get(i).getPensionTitle());
            balance.setText(pensionData.get(i).getPensionBalance());
            monthly.setText(pensionData.get(i).getPensionMonthly());

            rel_lay.addView(lay_pension_data);
        }
    }
}
