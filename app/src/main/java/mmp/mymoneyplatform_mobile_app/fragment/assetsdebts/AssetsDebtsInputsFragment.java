package mmp.mymoneyplatform_mobile_app.fragment.assetsdebts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import mmp.mymoneyplatform_mobile_app.R;

public class AssetsDebtsInputsFragment extends Fragment {


    ImageButton mLoanCalculator, mMortageCalculator; //Calculators buttons
    ImageButton mCalculatorsInfo, mAssetsDebtsListInfo; //Info buttons



    public AssetsDebtsInputsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assetsdebts_inputs, container, false);

        //Info Buttons
        mCalculatorsInfo = (ImageButton) view.findViewById(R.id.btn_assets_debts_calculators_info);
        mCalculatorsInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(getContext())
                        .title(getString(R.string.info_dialog_title))
                        .content(getString(R.string.assets_debts_screen_calculators_info))
                        .positiveText(getString(R.string.confirm))
                        .show();
            }
        });
        mAssetsDebtsListInfo = (ImageButton) view.findViewById(R.id.btn_assets_debts_list_info);
        mAssetsDebtsListInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(getContext())
                        .title(getString(R.string.info_dialog_title))
                        .content(getString(R.string.assets_debts_screen_list_info))
                        .positiveText(getString(R.string.confirm))
                        .show();
            }
        });


        mLoanCalculator = (ImageButton) view.findViewById(R.id.btn_loan_calculator);
        mLoanCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Loan Calculator", Toast.LENGTH_SHORT).show();
            }
        });
        mMortageCalculator = (ImageButton) view.findViewById(R.id.btn_mortage_calculator);
        mMortageCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Mortage Calculator", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }


}
