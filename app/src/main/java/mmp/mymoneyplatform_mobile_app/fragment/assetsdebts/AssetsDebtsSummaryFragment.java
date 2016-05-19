package mmp.mymoneyplatform_mobile_app.fragment.assetsdebts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mmp.mymoneyplatform_mobile_app.R;

/**
 * Created by K on 17/05/2016.
 */
public class AssetsDebtsSummaryFragment extends Fragment {

    public AssetsDebtsSummaryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_assetsdebts_summary, container, false);


        return view;
    }

}
