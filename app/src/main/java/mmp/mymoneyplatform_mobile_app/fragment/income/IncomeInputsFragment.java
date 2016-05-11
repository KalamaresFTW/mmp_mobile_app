package mmp.mymoneyplatform_mobile_app.fragment.income;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.triggertrap.seekarc.SeekArc;

import java.util.ArrayList;

import mmp.mymoneyplatform_mobile_app.R;
import mmp.mymoneyplatform_mobile_app.pojo.IllnessCoverData;
import mmp.mymoneyplatform_mobile_app.util.MoneyFormat;

@SuppressWarnings("FieldCanBeLocal")
public class IncomeInputsFragment extends Fragment {

    //UI references
    private SeekArc mSalarySeekArc;
    private Button mSalaryCalculator, mBasicSalaryInfo, mAditionalIncomeInfo,
            mFamilyStatusInfo, mDependentsInfo;
    private TextView mBasicSalaryTextView, mBonusTextView, mOvertimeTextView,
            mTaxableTextView, mNonTaxableTextView, mDependentsTextView;
    private SeekBar mBonusSeekBar, mOvertimeSeekBar, mTaxableSeekBar,
            mNonTaxableSeekBar, mDependentsSeekbar;
    private RadioButton mPolicyYes, mPolicyNo, mProtectionYes, mProtectionNo;
    private RelativeLayout mIncomeProtectionLayout, mIncomePercentLayout;
    private Spinner mIllnessCoverSpinner;

    public IncomeInputsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income_inputs, container, false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        //TODO: Use the API to add real functionality to all this stuff
        mBasicSalaryTextView = (TextView) view.findViewById(R.id.tv_income_basic_salary);

        mSalarySeekArc = (SeekArc) view.findViewById(R.id.sa_income_screen_salary);
        mSalarySeekArc.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int i, boolean b) {
                mBasicSalaryTextView.setText(MoneyFormat.getInstance().format(i * 500));
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {
            }

            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {
            }
        });


        mSalaryCalculator = (Button)
                view.findViewById(R.id.btn_income_screen_basic_salary_calculator);
        mBasicSalaryInfo = (Button) view.findViewById(R.id.btn_income_screen_basic_salary_info);
        mBasicSalaryInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(getContext())
                        .title(getString(R.string.info_dialog_title))
                        .content(getString(R.string.income_screen_basic_salary_info))
                        .positiveText(getString(R.string.confirm))
                        .show();
            }
        });
        mAditionalIncomeInfo = (Button)
                view.findViewById(R.id.btn_income_screen_additional_income_info);
        mAditionalIncomeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(getContext())
                        .title(getString(R.string.info_dialog_title))
                        .content(getString(R.string.income_screen_additional_income_info))
                        .positiveText(getString(R.string.confirm))
                        .show();
            }
        });
        mFamilyStatusInfo = (Button)
                view.findViewById(R.id.btn_income_screen_family_status_info);
        mFamilyStatusInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(getContext())
                        .title(getString(R.string.info_dialog_title))
                        .content(getString(R.string.income_screen_family_status_info))
                        .positiveText(getString(R.string.confirm))
                        .show();
            }
        });
        mDependentsInfo = (Button)
                view.findViewById(R.id.btn_income_screen_dependents_info);
        mDependentsInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(getContext())
                        .title(getString(R.string.info_dialog_title))
                        .content(getString(R.string.income_screen_dependents_info))
                        .positiveText(getString(R.string.confirm))
                        .show();
            }
        });

        //We get a reference for each text view
        mBonusTextView = (TextView) view.findViewById(R.id.tv_income_screen_money_bonus);
        mOvertimeTextView = (TextView) view.findViewById(R.id.tv_income_screen_money_overtime);
        mTaxableTextView = (TextView) view.findViewById(R.id.tv_income_screen_money_taxable);
        mNonTaxableTextView = (TextView) view.findViewById(R.id.tv_income_screen_money_not_taxable);


        //We get a reference for each seekbar
        mBonusSeekBar = (SeekBar) view.findViewById(R.id.income_seekbar_bonus);
        //And then we add a listener to it, very straight forward
        mBonusSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBonusTextView.setText(MoneyFormat.getInstance().format(progress * 100));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mOvertimeSeekBar = (SeekBar) view.findViewById(R.id.income_seekbar_overtime);
        mOvertimeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mOvertimeTextView.setText(MoneyFormat.getInstance().format(progress * 100));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mTaxableSeekBar = (SeekBar) view.findViewById(R.id.income_seekbar_taxable);
        mTaxableSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTaxableTextView.setText(MoneyFormat.getInstance().format(progress * 100));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mNonTaxableSeekBar = (SeekBar) view.findViewById(R.id.income_seekbar_not_taxable);
        mNonTaxableSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mNonTaxableTextView.setText(MoneyFormat.getInstance().format(progress * 100));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        mIncomeProtectionLayout = (RelativeLayout) view.findViewById(R.id.lay_income_screen_protect);
        mIncomeProtectionLayout.setVisibility(View.GONE);
        mIncomePercentLayout = (RelativeLayout) view.findViewById(R.id.lay_income_screen_percent);
        mIncomePercentLayout.setVisibility(View.GONE);

        mIllnessCoverSpinner = (Spinner) view.findViewById(R.id.sp_income_screen_illness_cover);
        //For now we are going to populate the spinner with local data
        ArrayList<IllnessCoverData> illnessDataList = new ArrayList<>();
        illnessDataList.add(new IllnessCoverData(0, ""));
        illnessDataList.add(new IllnessCoverData(1, "6 Month Full Pay"));
        illnessDataList.add(new IllnessCoverData(2, "6 Month Half Pay"));
        illnessDataList.add(new IllnessCoverData(3, "6 Month Full/6 Month Half Pay"));
        illnessDataList.add(new IllnessCoverData(4, "3 Month Full/3 Month Half Pay"));
        illnessDataList.add(new IllnessCoverData(5, "3 Month Full Pay"));
        illnessDataList.add(new IllnessCoverData(6, "Unsure"));
        illnessDataList.add(new IllnessCoverData(7, "None"));
        mIllnessCoverSpinner.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, illnessDataList));
        //TODO: populate the spinner with data from the API

        mPolicyYes = (RadioButton) view.findViewById(R.id.radio_policy_yes);
        mPolicyNo = (RadioButton) view.findViewById(R.id.radio_policy_no);
        mProtectionYes = (RadioButton) view.findViewById(R.id.radio_protect_yes);
        mProtectionNo = (RadioButton) view.findViewById(R.id.radio_protect_no);

        mPolicyYes.setSelected(true);
        mPolicyYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIncomeProtectionLayout.setVisibility(View.VISIBLE);
                mProtectionYes.setSelected(true);
                mProtectionNo.setSelected(false);
            }
        });

        mPolicyNo.setSelected(false);
        mPolicyNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIncomeProtectionLayout.setVisibility(View.GONE);
                mIncomePercentLayout.setVisibility(View.GONE);
            }
        });


        mProtectionYes.setSelected(true);
        mProtectionYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIncomePercentLayout.setVisibility(View.GONE);
            }
        });

        mProtectionNo.setSelected(false);
        mProtectionNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIncomePercentLayout.setVisibility(View.VISIBLE);
            }
        });


        mDependentsTextView = (TextView) view.findViewById(R.id.tv_income_dependents_description);
        mDependentsSeekbar = (SeekBar) view.findViewById(R.id.income_seekbar_dependents);
        mDependentsSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mDependentsTextView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //TODO: Add an item for each dependant
    }


}
