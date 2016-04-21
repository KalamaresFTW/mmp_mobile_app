package mmp.mymoneyplatform_mobile_app.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;

import mmp.mymoneyplatform_mobile_app.R;
import mmp.mymoneyplatform_mobile_app.net.ServiceTags;
import mmp.mymoneyplatform_mobile_app.net.ServiceURL;
import mmp.mymoneyplatform_mobile_app.pojo.FrecuencyData;
import mmp.mymoneyplatform_mobile_app.pojo.RegionData;
import mmp.mymoneyplatform_mobile_app.pojo.UserData;
import mmp.mymoneyplatform_mobile_app.util.FontsOverride;

public class ProfileActivity extends AppCompatActivity {

    //References to the components of the layout
    private Button mModifySaveButton;
    private TextView mEmailView;
    private EditText mNameView, mBirthdayView;
    private Spinner mRegionSpinner, mPaymentSpinner;
    //Saving the default background Drawable
    private Drawable oldBackgroundView, oldBackgroundSpinner;
    //User data
    private UserData user;
    //Calendar reference
    private Calendar calendar;
    //State Switcher (Modify mode or block mode)
    private boolean isModifying = false;

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
        mEmailView = (TextView) findViewById(R.id.tv_profile_email_entry);
        mNameView = (EditText) findViewById(R.id.et_profile_name_entry);
        mBirthdayView = (EditText) findViewById(R.id.et_profile_birthday_entry);
        mRegionSpinner = (Spinner) findViewById(R.id.sp_profile_region_entry);
        mPaymentSpinner = (Spinner) findViewById(R.id.sp_profile_payment_entry);

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
            sendDataToTheApiLel();
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
        //mBirthdayView.setClickable(true);
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
        //mBirthdayView.setClickable(false);
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

    public void sendDataToTheApiLel() {
        Toast.makeText(getApplicationContext(), "Lol as sido altamente trolliado xDDDDDDDD", Toast.LENGTH_LONG).show();
    }

}
