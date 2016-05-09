package mmp.mymoneyplatform_mobile_app.fragment.income;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import mmp.mymoneyplatform_mobile_app.R;
import mmp.mymoneyplatform_mobile_app.util.MoneyFormat;

@SuppressWarnings("FieldCanBeLocal")
public class IncomeInputsFragment extends Fragment {

    //References for the IU widgets
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
