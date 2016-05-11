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

    //UI references
    private SeekArc mSalarySeekArc;
    private Button mSalaryCalculator, mBasicSalaryInfo, mAditionalIncomeInfo,
            mFamilyStatusInfo, mDependentsInfo;
    private TextView mBasicSalaryTextView, mBonusTextView, mOvertimeTextView,
            mTaxableTextView, mNonTaxableTextView, mDependentsTextView;
    private SeekBar mBonusSeekBar, mOvertimeSeekBar, mTaxableSeekBar,
            mNonTaxableSeekBar, mDependentsSeekbar;

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


        mSalaryCalculator = (Button) view.findViewById(R.id.btn_income_screen_basic_salary_calculator);
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
        mAditionalIncomeInfo = (Button) view.findViewById(R.id.btn_income_screen_additional_income_info);
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
        mFamilyStatusInfo = (Button) view.findViewById(R.id.btn_income_screen_family_status_info);
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
        mDependentsInfo = (Button) view.findViewById(R.id.btn_income_screen_dependents_info);
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

        mBonusTextView = (TextView) view.findViewById(R.id.tv_income_screen_money_bonus);
        mOvertimeTextView = (TextView) view.findViewById(R.id.tv_income_screen_money_overtime);
        mTaxableTextView = (TextView) view.findViewById(R.id.tv_income_screen_money_taxable);
        mNonTaxableTextView = (TextView) view.findViewById(R.id.tv_income_screen_money_not_taxable);

        mBonusSeekBar = (SeekBar) view.findViewById(R.id.income_seekbar_bonus);
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

    }


}
