package mmp.mymoneyplatform_mobile_app.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

import mmp.mymoneyplatform_mobile_app.R;
import mmp.mymoneyplatform_mobile_app.pojo.FrecuencyData;
import mmp.mymoneyplatform_mobile_app.pojo.RegionData;
import mmp.mymoneyplatform_mobile_app.pojo.UserData;
import mmp.mymoneyplatform_mobile_app.util.FontsOverride;

public class ProfileActivity extends AppCompatActivity {

    //References to the components of the layout
    private Button mModifySaveButton;
    private TextView mEmailView;
    private EditText mNameView, mBirthdayView;
    private Spinner mRegionSpinner, mPaymentFrecuencySpinner;
    //Saving the default background Drawable
    private Drawable oldBackgroundView, oldBackgroundSpinner;
    //User data
    private UserData user;
    //Calendar reference
    private Calendar calendar;
    //State Switcher (Modify mode or block mode)
    private boolean isModifying = false;

    private ArrayList<RegionData> regionList;
    private ArrayList<FrecuencyData> paymentFrequencyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences mPrefs = getSharedPreferences("prefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonCountryList = mPrefs.getString("countryList", "");
        Type regionDataType = new TypeToken<ArrayList<RegionData>>() {
        }.getType();
        regionList = gson.fromJson(jsonCountryList, regionDataType);

        String jsonPaymentFrequencyList = mPrefs.getString("frequencyList", "");
        Type frequencyDataType = new TypeToken<ArrayList<FrecuencyData>>() {
        }.getType();
        paymentFrequencyList = gson.fromJson(jsonPaymentFrequencyList, frequencyDataType);

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
        mEmailView = (TextView) findViewById(R.id.tv_profile_email_entry);
        mNameView = (EditText) findViewById(R.id.et_profile_name_entry);
        mBirthdayView = (EditText) findViewById(R.id.et_profile_birthday_entry);

        mRegionSpinner = (Spinner) findViewById(R.id.sp_profile_region_entry);
        mRegionSpinner.setAdapter(new ArrayAdapter<>(this, R.layout.my_item_spinner_profile,regionList));

        mPaymentFrecuencySpinner = (Spinner) findViewById(R.id.sp_profile_payment_entry);
        mPaymentFrecuencySpinner.setAdapter(new ArrayAdapter<>(this, R.layout.my_item_spinner_profile, paymentFrequencyList));

        calendar = Calendar.getInstance();

        oldBackgroundSpinner = mRegionSpinner.getBackground();
        oldBackgroundView = mNameView.getBackground();

        loadProfileData();
        disableEdition();
    }

    private void loadProfileData() {
        SharedPreferences mPrefs = getSharedPreferences("prefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("user", "");
        user = gson.fromJson(json, UserData.class);     //Load the data on an userData object

        mEmailView.setText(user.getEmail());            //Setting the email data on the email editText
        mNameView.setText(user.getName());              //Setting the name data on the name editText
        mBirthdayView.setText("26/04/1996");            //Setting the birthday data on the birthday editText
    }

    public void onModifySaveButtonClicked() {
        if (!isModifying) {
            isModifying = true;
            enableEdition();
        } else {
            isModifying = false;
            disableEdition();
            sendDataToTheApi();
        }
    }

    public void enableEdition() {
        mModifySaveButton.setText(getString(R.string.profile_button_save));

        mNameView.setFocusableInTouchMode(true);
        mNameView.setBackground(oldBackgroundView);

        mBirthdayView.setFocusableInTouchMode(true);
        mBirthdayView.setBackground(oldBackgroundView);
        mBirthdayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(v);
            }
        });
        mBirthdayView.setFocusable(true);

        mRegionSpinner.setFocusableInTouchMode(true);
        mRegionSpinner.setFocusable(true);
    }

    public void disableEdition() {
        mModifySaveButton.setText(getString(R.string.profile_button_modify));

        mNameView.setFocusableInTouchMode(false);
        mNameView.setBackground(null);

        mBirthdayView.setFocusableInTouchMode(false);
        mBirthdayView.setBackground(null);
        mBirthdayView.setOnClickListener(null);
        mBirthdayView.setFocusable(false);

        mRegionSpinner.setFocusableInTouchMode(false);
        mRegionSpinner.setFocusable(false);
    }

    //region CalendarRegion
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2 + 1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        mBirthdayView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    public void sendDataToTheApi() {
        Toast.makeText(getApplicationContext(), "Sending data to the API", Toast.LENGTH_LONG).show();
    }
}
