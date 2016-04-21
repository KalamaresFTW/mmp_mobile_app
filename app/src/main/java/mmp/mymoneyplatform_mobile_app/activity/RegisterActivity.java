package mmp.mymoneyplatform_mobile_app.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

import mmp.mymoneyplatform_mobile_app.R;
import mmp.mymoneyplatform_mobile_app.pojo.FrecuencyData;
import mmp.mymoneyplatform_mobile_app.pojo.RegionData;
import mmp.mymoneyplatform_mobile_app.util.FontsOverride;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Calendar calendar;
    private EditText mBirthDate, mNameView, mPasswordView, mPasswordConfirmView;
    private AutoCompleteTextView mEmailView;
    private Button mRegisterButton;
    private Spinner mPaymentFrecuencySpinner, mRegionSpinner;

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
        setContentView(R.layout.activity_register);

        //Set the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initComponents();
    }

    private void initComponents() {
        mNameView = (EditText) findViewById(R.id.reg_name);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.reg_email);
        mPasswordView = (EditText) findViewById(R.id.reg_password);
        mPasswordConfirmView = (EditText) findViewById(R.id.reg_confirm_password);
        mRegisterButton = (Button) findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(this);
        mBirthDate = (EditText) findViewById(R.id.reg_dateofbirth);
        calendar = Calendar.getInstance();

        mRegionSpinner = (Spinner) findViewById(R.id.sp_region);
        mRegionSpinner.setAdapter(new ArrayAdapter<>(this, R.layout.my_item_spinner,regionList));

        mPaymentFrecuencySpinner = (Spinner) findViewById(R.id.sp_payment_frecuency);
        mPaymentFrecuencySpinner.setAdapter(new ArrayAdapter<>(this, R.layout.my_item_spinner, paymentFrequencyList));
    }

    @Override
    public void onClick(View v) {
        //TODO: Send the data to the server via API and wait for the response
        String name = mNameView.getText().toString();
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String passwordConfirm = mPasswordConfirmView.getText().toString();
        String birthDate = mBirthDate.getText().toString();

        System.out.println("Name: " + name +
                " - Email: " + email +
                " - Password: " + password +
                " - Password Confirm: " + passwordConfirm +
                " - Birth date: " + birthDate);
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            showDate(arg1, arg2 + 1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        mBirthDate.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
    }
}
