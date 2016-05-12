package mmp.mymoneyplatform_mobile_app.fragment.pension;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.triggertrap.seekarc.SeekArc;

import mmp.mymoneyplatform_mobile_app.R;


public class PensionInputsFragment extends Fragment {

    private Button mAddPensionButton;
    private SeekArc mAgeSeekArk, mMonthlyAmountSeekArk;
    private TextView mAgeTextView, mMonthlyAmountTextView;
    private View view;
    private LayoutInflater inflater;
    private int lay_pension_data_id;

    public PensionInputsFragment() {
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
        view = inflater.inflate(R.layout.fragment_pension_inputs, container, false);
        initComponents();
        return view;
    }

    public void initComponents() {
        lay_pension_data_id = 0;
        mAddPensionButton = (Button) view.findViewById(R.id.btn_pension_screen_add);
        mAddPensionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewPension();
            }
        });

        mAgeSeekArk = (SeekArc) view.findViewById(R.id.sa_pension_screen_age);
        mAgeTextView = (TextView) view.findViewById(R.id.tv_pension_screen_age_progress);
        mAgeSeekArk.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int i, boolean b) {
                mAgeTextView.setText(i + "");
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {
            }

            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {
            }
        });

        mMonthlyAmountSeekArk = (SeekArc) view.findViewById(R.id.sa_pension_screen_amount);
        mMonthlyAmountTextView = (TextView) view.findViewById(R.id.tv_pension_screen_monthly_amount_progress);
        mMonthlyAmountSeekArk.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int i, boolean b) {
                mMonthlyAmountTextView.setText(i + "");
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {
            }

            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {
            }
        });
    }

    private void addNewPension() {
        //TODO: add new pension data layout
        LinearLayout rel_lay = (LinearLayout) view.findViewById(R.id.lay_pension_screen_data_list);
        View lay_pension_data = inflater.inflate(R.layout.lay_pension_screen_data, null);
        rel_lay.addView(lay_pension_data, lay_pension_data_id);
        lay_pension_data_id++;
    }
}
