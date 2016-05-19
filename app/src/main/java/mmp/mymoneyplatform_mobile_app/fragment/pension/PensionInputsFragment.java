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

import com.afollestad.materialdialogs.MaterialDialog;
import com.triggertrap.seekarc.SeekArc;

import java.util.ArrayList;

import mmp.mymoneyplatform_mobile_app.R;
import mmp.mymoneyplatform_mobile_app.adapter.PensionDataAdapter;
import mmp.mymoneyplatform_mobile_app.pojo.PensionData;


public class PensionInputsFragment extends Fragment {

    private Button mAddPensionButton;
    private SeekArc mAgeSeekArk, mMonthlyAmountSeekArk;
    private TextView mAgeTextView, mMonthlyAmountTextView;
    private View view;
    private LayoutInflater inflater;
    private LinearLayout rel_lay;
    private int newPensionID;
    private ArrayList<PensionData> pensionDataArray;
    private Button mExistingPensionInfo, mAgeRetirementInfo, mMonthlyAmountInfo;

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
        pensionDataArray = new ArrayList<>();

        newPensionID = pensionDataArray.size();

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

        //region Info Buttons
        mExistingPensionInfo = (Button) view.findViewById(R.id.btn_pension_screen_existing_pension_info);
        mExistingPensionInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(getContext())
                        .title(getString(R.string.txt_pension_screen_existing_pension_title))
                        .content(getString(R.string.txt_pension_screen_existing_pension_info))
                        .positiveText(getString(R.string.confirm))
                        .show();
            }
        });
        mAgeRetirementInfo = (Button) view.findViewById(R.id.btn_pension_screen_age_retirement_info);
        mAgeRetirementInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(getContext())
                        .title(getString(R.string.txt_pension_screen_age_retirement))
                        .content(getString(R.string.txt_pension_screen_age_retirement_info))
                        .positiveText(getString(R.string.confirm))
                        .show();
            }
        });

        mMonthlyAmountInfo = (Button) view.findViewById(R.id.btn_pension_screen_monthly_amount_info);
        mMonthlyAmountInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(getContext())
                        .title(getString(R.string.txt_pension_screen_monthly_amount))
                        .content(getString(R.string.txt_pension_screen_monthly_amount_info))
                        .positiveText(getString(R.string.confirm))
                        .show();
            }
        });
        //endregion
        rel_lay = (LinearLayout) view.findViewById(R.id.lay_pension_screen_data_list);
        loadPensionList();
    }

    public void loadPensionList() {
        //TODO: get the pension data from the API
        pensionDataArray.add(new PensionData(1, "My Last Job", "16,700.00", "0,00"));
        pensionDataArray.add(new PensionData(2, "Inheritance", "1,800.00", "150,00"));
        PensionDataAdapter.getInstance().loadData(pensionDataArray, rel_lay, inflater);
    }

    private void addNewPension() {
        //TODO: get the pension data from the popup window

        pensionDataArray.add(new PensionData(newPensionID, newPensionID + "", "", ""));

        PensionDataAdapter.getInstance().loadData(pensionDataArray, rel_lay, inflater);
        newPensionID++;
    }
}