package mmp.mymoneyplatform_mobile_app.fragment.goals;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import mmp.mymoneyplatform_mobile_app.R;
import mmp.mymoneyplatform_mobile_app.adapter.GoalDataAdapter;
import mmp.mymoneyplatform_mobile_app.pojo.GoalData;

public class GoalsInputsFragment extends Fragment {

    private LayoutInflater inflater;
    private View view, mSecundaryGoalsStateIcon, mRainyDayStateIcon, mCollegeStateIcon;
    private RelativeLayout mSecundaryGoalsHeader, mSecundaryGoalsContent, mRainyDayContent, mRainyDayHeader, mCollegeHeader, mCollegeContent;
    private LinearLayout mSecundaryGoalsList;
    private Button mGoalsTitleInfoButton, mSecundaryGoalsInfoButton, mRainyDayInfoButton, mCollegeInfoButton, mAddGoalButton;

    private ArrayList<GoalData> goalsDataArray;

    public GoalsInputsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fragment_goals_inputs, container, false);
        initComponents();
        return view;
    }

    public void initComponents() {
        goalsDataArray = new ArrayList<>();
        //Header Layouts
        mSecundaryGoalsHeader = (RelativeLayout) view.findViewById(R.id.lay_goals_screen_secundary_goals_header);
        mSecundaryGoalsHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOpenGoalsDetails(v);
            }
        });
        mRainyDayHeader = (RelativeLayout) view.findViewById(R.id.lay_goals_screen_rainy_day_header);
        mRainyDayHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOpenGoalsDetails(v);
            }
        });
        mCollegeHeader = (RelativeLayout) view.findViewById(R.id.lay_goals_screen_college_header);
        mCollegeHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOpenGoalsDetails(v);
            }
        });

        //Content Layouts
        mSecundaryGoalsContent = (RelativeLayout) view.findViewById(R.id.lay_goals_screen_content_secundary_goals);
        mSecundaryGoalsContent.setVisibility(View.GONE);
        mRainyDayContent = (RelativeLayout) view.findViewById(R.id.lay_goals_screen_content_rainy_day);
        mRainyDayContent.setVisibility(View.GONE);
        mCollegeContent = (RelativeLayout) view.findViewById(R.id.lay_goals_screen_content_college);
        mCollegeContent.setVisibility(View.GONE);

        //Header Info Buttons
        mGoalsTitleInfoButton = (Button) view.findViewById(R.id.btn_goals_screen_header_info);
        mSecundaryGoalsInfoButton = (Button) view.findViewById(R.id.btn_goals_screen_secundary_goals_info);
        mRainyDayInfoButton = (Button) view.findViewById(R.id.btn_goals_screen_rainy_day_info);
        mCollegeInfoButton = (Button) view.findViewById(R.id.btn_goals_screen_college_info);

        //Header State Icons
        mSecundaryGoalsStateIcon = view.findViewById(R.id.v_icon_goals_screen_secundary_goals);
        mRainyDayStateIcon = view.findViewById(R.id.v_icon_goals_screen_rainy_day);
        mCollegeStateIcon = view.findViewById(R.id.v_icon_goals_screen_college);

        //Lists
        mSecundaryGoalsList = (LinearLayout) view.findViewById(R.id.lay_goals_screen_secundary_goals_list);

        //Other Components
        mAddGoalButton = (Button) view.findViewById(R.id.btn_goals_screen_secundary_goals_content_add);
        mAddGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewGoal();
            }
        });

        loadNewGoalsList();
    }

    //Method which switch between the tiles of the acordion (open one and close the others)
    public void onOpenGoalsDetails(View v) {
        //TODO: create transitions
        switch (v.getId()) {
            case R.id.lay_goals_screen_secundary_goals_header:
                //Check if the other 2 parts of the acordion are opened. If it´s true, it gets closed
                if (mRainyDayContent.getVisibility() == View.VISIBLE) {
                    mRainyDayContent.setVisibility(View.GONE);
                    mRainyDayStateIcon.setBackground(getResources().getDrawable(R.drawable.ic_plus));
                }
                if (mCollegeContent.getVisibility() == View.VISIBLE) {
                    mCollegeContent.setVisibility(View.GONE);
                    mCollegeStateIcon.setBackground(getResources().getDrawable(R.drawable.ic_plus));
                }
                //Open the selected part of the acordion
                if (mSecundaryGoalsContent.getVisibility() == View.GONE) {
                    mSecundaryGoalsContent.setVisibility(View.VISIBLE);
                    mSecundaryGoalsStateIcon.setBackground(getResources().getDrawable(R.drawable.ic_minus));
                } else {
                    mSecundaryGoalsContent.setVisibility(View.GONE);
                    mSecundaryGoalsStateIcon.setBackground(getResources().getDrawable(R.drawable.ic_plus));
                }
                break;
            case R.id.lay_goals_screen_rainy_day_header:
                //Check if the other 2 parts of the acordion are opened. If it´s true, it gets closed
                if (mSecundaryGoalsContent.getVisibility() == View.VISIBLE) {
                    mSecundaryGoalsContent.setVisibility(View.GONE);
                    mSecundaryGoalsStateIcon.setBackground(getResources().getDrawable(R.drawable.ic_plus));
                }
                if (mCollegeContent.getVisibility() == View.VISIBLE) {
                    mCollegeContent.setVisibility(View.GONE);
                    mCollegeStateIcon.setBackground(getResources().getDrawable(R.drawable.ic_plus));
                }
                //Open the selected part of the acordion
                if (mRainyDayContent.getVisibility() == View.GONE) {
                    mRainyDayContent.setVisibility(View.VISIBLE);
                    mRainyDayStateIcon.setBackground(getResources().getDrawable(R.drawable.ic_minus));
                } else {
                    mRainyDayContent.setVisibility(View.GONE);
                    mRainyDayStateIcon.setBackground(getResources().getDrawable(R.drawable.ic_plus));
                }
                break;
            case R.id.lay_goals_screen_college_header:
                //Check if the other 2 parts of the acordion are opened. If it´s true, it gets closed
                if (mSecundaryGoalsContent.getVisibility() == View.VISIBLE) {
                    mSecundaryGoalsContent.setVisibility(View.GONE);
                    mSecundaryGoalsStateIcon.setBackground(getResources().getDrawable(R.drawable.ic_plus));
                }
                if (mRainyDayContent.getVisibility() == View.VISIBLE) {
                    mRainyDayContent.setVisibility(View.GONE);
                    mRainyDayStateIcon.setBackground(getResources().getDrawable(R.drawable.ic_plus));
                }
                //Open the selected part of the acordion
                if (mCollegeContent.getVisibility() == View.GONE) {
                    mCollegeContent.setVisibility(View.VISIBLE);
                    mCollegeStateIcon.setBackground(getResources().getDrawable(R.drawable.ic_minus));
                } else {
                    mCollegeContent.setVisibility(View.GONE);
                    mCollegeStateIcon.setBackground(getResources().getDrawable(R.drawable.ic_plus));
                }
                break;
        }
    }

    //TODO: create a popup for the user to let him fill with his new goal data
    public void addNewGoal() {
        goalsDataArray.add(new GoalData("New stuff", 30, 9000));
        GoalDataAdapter.getInstance().loadData(goalsDataArray, mSecundaryGoalsList, inflater);
        Toast.makeText(getContext(), "Creating new Goal", Toast.LENGTH_SHORT).show();
    }

    public void loadNewGoalsList() {
        //TODO: get the goals data from the API
        goalsDataArray.add(new GoalData("New Car", 30, 9000));
        goalsDataArray.add(new GoalData("New Car2", 50, 10000));
        GoalDataAdapter.getInstance().loadData(goalsDataArray, mSecundaryGoalsList, inflater);
    }
}
