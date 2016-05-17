package mmp.mymoneyplatform_mobile_app.fragment.goals;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mmp.mymoneyplatform_mobile_app.R;

/**
 * Created by K on 17/05/2016.
 */
public class GoalsSummaryFragment extends Fragment {

    public GoalsSummaryFragment() {
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
        return inflater.inflate(R.layout.fragment_goals_summary, container, false);
    }
}
