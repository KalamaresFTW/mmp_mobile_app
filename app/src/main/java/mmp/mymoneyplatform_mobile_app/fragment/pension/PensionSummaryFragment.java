package mmp.mymoneyplatform_mobile_app.fragment.pension;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;

import mmp.mymoneyplatform_mobile_app.R;


public class PensionSummaryFragment extends Fragment {
    private Button mSalaryInfo;
    private LayoutInflater inflater;
    private View view;

    public PensionSummaryFragment() {
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
        view = inflater.inflate(R.layout.fragment_pension_summary, container, false);
        initComponents();
        return view;

    }

    public void initComponents() {
        mSalaryInfo = (Button) view.findViewById(R.id.btn_pension_screen_summary_salary_info);
        mSalaryInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(getContext())
                        .title(getString(R.string.txt_pension_screen_summary_salary_title))
                        .content(getString(R.string.txt_pension_screen_summary_salary_info))
                        .positiveText(getString(R.string.confirm))
                        .show();
            }
        });
    }
}
