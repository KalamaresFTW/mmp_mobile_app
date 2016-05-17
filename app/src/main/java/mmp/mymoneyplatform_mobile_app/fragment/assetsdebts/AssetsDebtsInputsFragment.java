package mmp.mymoneyplatform_mobile_app.fragment.assetsdebts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mmp.mymoneyplatform_mobile_app.R;

public class AssetsDebtsInputsFragment extends Fragment {
    public AssetsDebtsInputsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_assetsdebts_inputs, container, false);
    }


}
