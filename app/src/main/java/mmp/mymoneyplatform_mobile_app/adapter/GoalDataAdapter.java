package mmp.mymoneyplatform_mobile_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import mmp.mymoneyplatform_mobile_app.R;
import mmp.mymoneyplatform_mobile_app.pojo.GoalData;

/**
 * Created by Nacho on 18/05/2016.
 */
public class GoalDataAdapter {

    private static GoalDataAdapter instance = null;

    GoalDataAdapter() {
    }

    public static GoalDataAdapter getInstance() {
        if (instance == null) {
            instance = new GoalDataAdapter();
        }
        return instance;
    }

    public void loadData(ArrayList<GoalData> goaldData, LinearLayout lin_lay, LayoutInflater inflater) {
        //First, we delete all the existing childs to not have any duplication with it
        lin_lay.removeAllViewsInLayout();
        //Bunch of variables we declare here instead at inside the loop to improve performance
        View lay_goal_data; //Layout Parent
        ImageView img;
        TextView title;
        TextView percentage;
        TextView amount;
        ProgressBar progressBar;

        for (int i = 0; i < goaldData.size(); i++) {
            lay_goal_data = inflater.inflate(R.layout.goal_layout, null);

            img = (ImageView) lay_goal_data.findViewById(R.id.iv_menu_img);
            title = (TextView) lay_goal_data.findViewById(R.id.tv_goal_title);
            percentage = (TextView) lay_goal_data.findViewById(R.id.tv_goal_progress);
            progressBar = (ProgressBar) lay_goal_data.findViewById(R.id.progress_bar_goal);
            amount = (TextView) lay_goal_data.findViewById(R.id.tv_goal_progress_amount);

            img.setBackground(goaldData.get(i).getImg());
            title.setText(goaldData.get(i).getTitle());
            percentage.setText(goaldData.get(i).getPercentage() + "%");
            amount.setText("€" + goaldData.get(i).getAmount());
            progressBar.setProgress(goaldData.get(i).getPercentage());

            lin_lay.addView(lay_goal_data);
        }
    }

}
