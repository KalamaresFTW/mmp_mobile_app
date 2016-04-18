package mmp.mymoneyplatform_mobile_app.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import mmp.mymoneyplatform_mobile_app.R;
import mmp.mymoneyplatform_mobile_app.adapter.RegistrationSpinnerAdapter;
import mmp.mymoneyplatform_mobile_app.util.FontsOverride;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private DatePicker datePicker;
    private Calendar calendar;
    private EditText mBirthDate, mNameView, mPasswordView, mPasswordConfirmView;
    private AutoCompleteTextView mEmailView;
    private Button mRegisterButton;
    private Spinner mPaymentFrecuencySpinner, mRegionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set the new font
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Raleway-Regular.ttf");

        //Set the layout
        setContentView(R.layout.activity_register);

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

        mPaymentFrecuencySpinner = (Spinner) findViewById(R.id.sp_payment_frecuency);
        mRegionSpinner = (Spinner) findViewById(R.id.sp_region);
        //Load the dummy data for the spinners. In the future this data will be send by the database
        RegistrationSpinnerAdapter.getInstance(this).loadData(mPaymentFrecuencySpinner, mRegionSpinner);
    }

    @Override
    public void onClick(View v) {
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
        Toast.makeText(getApplicationContext(), "Calendar", Toast.LENGTH_SHORT)
                .show();
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
        mBirthDate.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
    }
}
