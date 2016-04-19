package mmp.mymoneyplatform_mobile_app.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import mmp.mymoneyplatform_mobile_app.R;
import mmp.mymoneyplatform_mobile_app.util.FontsOverride;

public class ProfileActivity extends AppCompatActivity {

    //References to the components of the layout
    //Pablo gilipollas como vuelvas a aceptar el tuyo le digo a la argentina que la llamaste puta
    private Button mModifySaveButton;
    private EditText mNameView, mBirthdayView;
    private Spinner mRegionSpinner, mPaymentSpinner;
    //Saving the default background Drawable
    private Drawable oldBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set the new font
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Raleway-Regular.ttf");
        //Set the layout
        setContentView(R.layout.activity_profile);
        //Set the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initComponents();
    }

    public void initComponents() {
        mModifySaveButton = (Button) findViewById(R.id.btn_profile_modify_save);
        mModifySaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onModifySaveButtonClicked();
            }
        });
        mNameView = (EditText) findViewById(R.id.et_profile_name_entry);
        oldBackground = mNameView.getBackground();
        mBirthdayView = (EditText) findViewById(R.id.et_profile_birthday_entry);

        mRegionSpinner = (Spinner) findViewById(R.id.sp_profile_region_entry);
        mPaymentSpinner = (Spinner) findViewById(R.id.sp_profile_payment_entry);

        loadProfileData();
        disableEdition();
    }

    public void loadProfileData() {
        ArrayList<String> regions = new ArrayList<>();
        regions.add(0, "Ireland");
        regions.add(1, "United Kingdom");
        mRegionSpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, regions));
        mRegionSpinner.setSelection(0);

        ArrayList<String> frecuency = new ArrayList<>();
        frecuency.add(0, "Weekly");
        frecuency.add(1, "Monthly");
        mPaymentSpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, frecuency));
        mPaymentSpinner.setSelection(0);
    }

    public void onModifySaveButtonClicked() {
        if (mModifySaveButton.getText().equals(getString(R.string.profile_button_modify))) {
            mModifySaveButton.setText(getString(R.string.profile_button_save));
            enableEdition();
        } else {
            mModifySaveButton.setText(getString(R.string.profile_button_modify));
            disableEdition();
        }
    }

    public void enableEdition() {
        mNameView.setFocusableInTouchMode(true);
        mNameView.setBackground(oldBackground);
        mBirthdayView.setFocusableInTouchMode(true);
        mBirthdayView.setBackground(oldBackground);
        mRegionSpinner.setFocusableInTouchMode(true);
        mRegionSpinner.setBackground(oldBackground);
        mPaymentSpinner.setFocusableInTouchMode(true);
        mPaymentSpinner.setBackground(oldBackground);
    }

    public void disableEdition() {
        mNameView.setFocusableInTouchMode(false);
        mNameView.setBackground(null);
        mBirthdayView.setFocusableInTouchMode(false);
        mBirthdayView.setBackground(null);
        mRegionSpinner.setFocusableInTouchMode(false);
        mRegionSpinner.setBackground(null);
        mPaymentSpinner.setFocusableInTouchMode(false);
        mPaymentSpinner.setBackground(null);
    }
}
