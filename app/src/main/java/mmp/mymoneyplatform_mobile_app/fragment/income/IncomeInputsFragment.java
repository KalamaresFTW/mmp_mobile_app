package mmp.mymoneyplatform_mobile_app.fragment.income;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.triggertrap.seekarc.SeekArc;

import mmp.mymoneyplatform_mobile_app.R;
import mmp.mymoneyplatform_mobile_app.util.MoneyFormat;

@SuppressWarnings("FieldCanBeLocal")
public class IncomeInputsFragment extends Fragment {

    //References for the IU widgets
    private SeekArc mSalarySeekArc;
    private Button mSalaryCalculator, mBasicSalaryInfo, mAditionalIncomeInfo, mFamilyStatusInfo, mDependentsInfo;
    private TextView mBonusTextView, mOvertimeTextView, mTaxableTextView, mNonTaxableTextView;
    private SeekBar mBonusSeekBar, mOvertimeSeekBar, mTaxableSeekBar, mNonTaxableSeekBar;

    public IncomeInputsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the fragment view
        View fragmentView = inflater.inflate(R.layout.fragment_income_inputs, container, false);

        mSalarySeekArc = (SeekArc) fragmentView.findViewById(R.id.sa_income_screen_salary);
        mSalarySeekArc.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int i, boolean b) {
                System.out.println(i * 500);
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {
            }

            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {
            }
        });


        mSalaryCalculator = (Button) fragmentView.findViewById(R.id.btn_income_screen_basic_salary_calculator);
        mBasicSalaryInfo = (Button) fragmentView.findViewById(R.id.btn_income_screen_basic_salary_info);
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
        mAditionalIncomeInfo = (Button) fragmentView.findViewById(R.id.btn_income_screen_additional_income_info);
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
        mFamilyStatusInfo = (Button) fragmentView.findViewById(R.id.btn_income_screen_family_status_info);
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
        mDependentsInfo = (Button) fragmentView.findViewById(R.id.btn_income_screen_dependents_info);
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

        mBonusTextView = (TextView) fragmentView.findViewById(R.id.tv_income_screen_money_bonus);
        mOvertimeTextView = (TextView) fragmentView.findViewById(R.id.tv_income_screen_money_overtime);
        mTaxableTextView = (TextView) fragmentView.findViewById(R.id.tv_income_screen_money_taxable);
        mNonTaxableTextView = (TextView) fragmentView.findViewById(R.id.tv_income_screen_money_not_taxable);

        mBonusSeekBar = (SeekBar) fragmentView.findViewById(R.id.income_seekbar_bonus);
        mBonusSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBonusTextView.setText(MoneyFormat.getInstance().format(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mOvertimeSeekBar = (SeekBar) fragmentView.findViewById(R.id.income_seekbar_overtime);
        mOvertimeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mOvertimeTextView.setText(MoneyFormat.getInstance().format(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mTaxableSeekBar = (SeekBar) fragmentView.findViewById(R.id.income_seekbar_taxable);
        mTaxableSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTaxableTextView.setText(MoneyFormat.getInstance().format(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mNonTaxableSeekBar = (SeekBar) fragmentView.findViewById(R.id.income_seekbar_not_taxable);
        mNonTaxableSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mNonTaxableTextView.setText(MoneyFormat.getInstance().format(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return fragmentView;
    }


}
