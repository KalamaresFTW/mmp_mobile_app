package mmp.mymoneyplatform_mobile_app.fragment.income;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.triggertrap.seekarc.SeekArc;

import java.text.DecimalFormat;
import java.util.ArrayList;

import mmp.mymoneyplatform_mobile_app.R;
import mmp.mymoneyplatform_mobile_app.pojo.IllnessCoverData;
import mmp.mymoneyplatform_mobile_app.util.MoneyFormat;

@SuppressWarnings("FieldCanBeLocal")
public class IncomeInputsFragment extends Fragment {

    //UI references
    private SeekArc mSalarySeekArc;
    //TODO: Create that stupid salary calculator for the users that doesn't know how to multiply by 12
    private Button mSalaryCalculator, mBasicSalaryInfo, mAdditionalIncomeInfo,
            mFamilyStatusInfo, mDependentsInfo;
    private TextView mBasicSalaryTextView, mBonusTextView, mOvertimeTextView,
            mTaxableTextView, mNonTaxableTextView, mPercentageCoverTextView, mDependentsTextView;
    private SeekBar mBonusSeekBar, mOvertimeSeekBar, mTaxableSeekBar,
            mNonTaxableSeekBar, mDependentsSeekbar;
    private RadioButton mPolicyYes, mPolicyNo, mProtectionYes, mProtectionNo;
    private RelativeLayout mIncomeProtectionLayout, mIncomePercentLayout;
    private LinearLayout mDependantsTopLayout, mDependantsBottomLayout;
    private ArrayList<ImageButton> dependantsList = new ArrayList<>(10); //10 dependants max
    private int lastDependantsValue = 0; //This is 0 for now
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
        mAdditionalIncomeInfo = (Button)
                view.findViewById(R.id.btn_income_screen_additional_income_info);
        mAdditionalIncomeInfo.setOnClickListener(new View.OnClickListener() {
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
        mPercentageCoverTextView = (TextView) view.findViewById(R.id.et_income_screen_salary_percent);
        mPercentageCoverTextView.setText("0.0%");

        mPercentageCoverTextView.addTextChangedListener(new TextWatcher() {

            private String current = "";
            DecimalFormat df = new DecimalFormat("##.##");

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO: improve this shit, really messy atm
                if (!s.toString().equals(current)) {
                    mPercentageCoverTextView.removeTextChangedListener(this);
                    String cleanString = s.toString().replaceAll("[%,.]", "");
                    double parsed = 0;
                    if (!cleanString.isEmpty()) parsed = Double.parseDouble(cleanString);
                    String formated = df.format(parsed);
                    current = formated;
                    mPercentageCoverTextView.setText(current + "%");
                    Editable etext = mPercentageCoverTextView.getEditableText();
                    Selection.setSelection(etext, formated.length());
                    mPercentageCoverTextView.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

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

        //TODO: this logic is a bit weird, needs improvement
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
                // This may seem a bit weird, but its necessary, since you can go from 0 to 10
                // dependants in the worst case scenario, so deal with it bitch
                if (lastDependantsValue < progress) {
                    // If the last value is less than the new one
                    // we are increasing the number of dependants
                    refreshDependants(progress, true);
                } else {
                    // If the las value is greater than the new one
                    // we are decreasing the number of dependants
                    refreshDependants(progress, false);
                }
                // Set the text on the TextView
                mDependentsTextView.setText(String.valueOf(progress));
                //And the current value is now the last one
                lastDependantsValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // We get a reference to the layouts that contains the dependant buttons
        // because we need to hide them if they are not being used,
        // otherwise weird things happen lol
        mDependantsTopLayout = (LinearLayout) view.findViewById(R.id.btn_income_dependant_top_layout);
        mDependantsTopLayout.setVisibility(View.GONE); // We hide them by default btw
        mDependantsBottomLayout = (LinearLayout) view.findViewById(R.id.btn_income_dependant_bottom_layout);
        mDependantsBottomLayout.setVisibility(View.GONE);

        // Add all the buttons to the array, its the only reference we have to them
        // Also notice that im using an ArrayList, so the order is preserved (IMPORTANT!)
        dependantsList.add((ImageButton) view.findViewById(R.id.btn_income_dependant_1));
        dependantsList.add((ImageButton) view.findViewById(R.id.btn_income_dependant_2));
        dependantsList.add((ImageButton) view.findViewById(R.id.btn_income_dependant_3));
        dependantsList.add((ImageButton) view.findViewById(R.id.btn_income_dependant_4));
        dependantsList.add((ImageButton) view.findViewById(R.id.btn_income_dependant_5));
        dependantsList.add((ImageButton) view.findViewById(R.id.btn_income_dependant_6));
        dependantsList.add((ImageButton) view.findViewById(R.id.btn_income_dependant_7));
        dependantsList.add((ImageButton) view.findViewById(R.id.btn_income_dependant_8));
        dependantsList.add((ImageButton) view.findViewById(R.id.btn_income_dependant_9));
        dependantsList.add((ImageButton) view.findViewById(R.id.btn_income_dependant_10));

        // We hide all the dependants buttons, this is the default presentation
        for (int i = 0; i < 10; i++) {
            ImageButton dependant = dependantsList.get(i);
            final int value = i;
            // This is just for now, later we have to add a
            // pop up window to fulfill the dependant info
            dependant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: this is just for now, we need to add a popup window to add a dependant
                    Toast.makeText(getContext(), "Dependant "
                            + String.valueOf(value + 1), Toast.LENGTH_SHORT).show();
                }
            });
            dependant.setVisibility(View.GONE);
        }
    }

    /**
     * We call this function every time we need to update the number of dependants we want to show
     * You might notice that this is a fucking badass function, isn't it?
     *
     * @param numberOfDependants the number of dependants to refresh
     * @param increasing this tells if you are increasing or decreasing the number of dependants
     */
    private void refreshDependants(int numberOfDependants, boolean increasing) {
        if (numberOfDependants > 0 && numberOfDependants <= 10) {
            mDependantsTopLayout.setVisibility(View.VISIBLE);
        } else {
            mDependantsTopLayout.setVisibility(View.GONE);
        }
        if (numberOfDependants > 5 && numberOfDependants <= 10) {
            mDependantsBottomLayout.setVisibility(View.VISIBLE);
        } else {
            mDependantsBottomLayout.setVisibility(View.GONE);
        }
        if (increasing) {
            for (int i = 0; i < numberOfDependants; i++) {
                dependantsList.get(i).setVisibility(View.VISIBLE);
            }
        } else {
            for (int i = dependantsList.size() - 1; i >= numberOfDependants; i--) {
                dependantsList.get(i).setVisibility(View.GONE);
            }

        }
    }
}
