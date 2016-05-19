package mmp.mymoneyplatform_mobile_app.fragment.assetsdebts;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import mmp.mymoneyplatform_mobile_app.R;
import mmp.mymoneyplatform_mobile_app.pojo.AssetDebtItem;

public class AssetsDebtsInputsFragment extends Fragment {


    ImageButton mLoanCalculator, mMortageCalculator; //Calculators buttons
    ImageButton mCalculatorsInfo, mAssetsDebtsListInfo; //Info buttons
    ImageButton mAddAssetDebtItem;
    RelativeLayout mBalanceFromGoals;
    LinearLayout mAssetsDebtsList;

    public AssetsDebtsInputsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
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


        mAssetsDebtsList = (LinearLayout) view.findViewById(R.id.assets_debts_list);
        mAssetsDebtsList.setOrientation(LinearLayout.VERTICAL);

        mAddAssetDebtItem = (ImageButton) view.findViewById(R.id.btn_asset_debt_add);
        mAddAssetDebtItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssetDebtItem dummyData = new AssetDebtItem("Title", "€12.345", "14.215", "€18.251");
                addAssetDebtItem(savedInstanceState, dummyData);
            }
        });

        mBalanceFromGoals = (RelativeLayout) view.findViewById(R.id.balance_from_goals);


        return view;
    }

    /**
     * Adds a new item (assetDetb) at the end of the assetDebtList
     *
     * @param bundle we need this because we are getting the layoutInflater from a fragment
     * @param item   the actual data of the item
     */
    private void addAssetDebtItem(Bundle bundle, AssetDebtItem item) {
        LayoutInflater inflater = getLayoutInflater(bundle); //this is necessary now, wtf google

        //We prepare the view first
        View assetDebtItem = inflater.inflate(R.layout.asset_debt_item, null);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(25, 20, 25, 0);

        //We get a reference to every field we are going to modify on the the new item
        TextView mTitle, mAssetValue, mDebtValue, mNetEqualityValue;
        mTitle = (TextView) assetDebtItem.findViewById(R.id.asset_debt_item_title);
        mAssetValue = (TextView) assetDebtItem.findViewById(R.id.tv_asset_debt_asset_value);
        mDebtValue = (TextView) assetDebtItem.findViewById(R.id.tv_asset_debt_debt_value);
        mNetEqualityValue = (TextView) assetDebtItem.findViewById(R.id.tv_asset_debt_net_equality);

        //We set the data on the fields
        mTitle.setText(item.getTitle());
        mAssetValue.setText(item.getAssetValue());
        mDebtValue.setText(item.getDebtValue());
        mNetEqualityValue.setText(item.getNetEqualityValue());

        //Finally we the new item into the list
        mAssetsDebtsList.addView(assetDebtItem, layoutParams);
    }


}
