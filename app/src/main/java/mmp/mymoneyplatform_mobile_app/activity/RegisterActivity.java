package mmp.mymoneyplatform_mobile_app.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;

import mmp.mymoneyplatform_mobile_app.R;
import mmp.mymoneyplatform_mobile_app.net.ServiceURL;
import mmp.mymoneyplatform_mobile_app.pojo.FrecuencyData;
import mmp.mymoneyplatform_mobile_app.pojo.RegionData;
import mmp.mymoneyplatform_mobile_app.util.FontsOverride;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Calendar calendar;
    private EditText mBirthDate, mNameView, mPasswordView, mPasswordConfirmView;
    private AutoCompleteTextView mEmailView;
    private Button mRegisterButton;
    private Spinner mPaymentFrequencySpinner, mRegionSpinner;

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
        mRegionSpinner.setAdapter(new ArrayAdapter<>(this, R.layout.my_item_spinner_registration, regionList));

        mPaymentFrequencySpinner = (Spinner) findViewById(R.id.sp_payment_frecuency);
        mPaymentFrequencySpinner.setAdapter(new ArrayAdapter<>(this, R.layout.my_item_spinner_registration, paymentFrequencyList));
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
                " - Birth date: " + birthDate +
                " - Selected region: " + mRegionSpinner.getSelectedItem() +
                " -  ID: " + ((RegionData) mRegionSpinner.getSelectedItem()).getRegionID() +
                " - Selected payperiod: " + mPaymentFrequencySpinner.getSelectedItem() +
                " -  ID: " + ((FrecuencyData) mPaymentFrequencySpinner.getSelectedItem()).getFrecuencyID()
        );

        new AsyncTask<Void, Void, Void>() {

            //TODO: Avoid the doom
            private String email, password, passwordConfirm, name, birthDate;
            private int payperiodID, countryID;

            @Override
            protected void onPreExecute() {
                email = mEmailView.getText().toString();
                password = mPasswordView.getText().toString();
                passwordConfirm = mPasswordConfirmView.getText().toString();
                name = mNameView.getText().toString();
                payperiodID = ((FrecuencyData) mPaymentFrequencySpinner.getSelectedItem()).getFrecuencyID();
                countryID = ((RegionData) mRegionSpinner.getSelectedItem()).getRegionID();
                birthDate = mBirthDate.getText().toString();
            }

            @Override
            protected Void doInBackground(Void... params) {
                String param;
                URL url = null;
                try {
                    param = ServiceURL.URL_PARAM_EMAIL + "=" + URLEncoder.encode(email, "UTF-8");
                    param += "&" + ServiceURL.URL_PARAM_PASSWORD + "=" + URLEncoder.encode(password, "UTF-8");
                    param += "&" + ServiceURL.URL_PARAM_CONFIRMPASSWORD + "=" + URLEncoder.encode(passwordConfirm, "UTF-8");
                    param += "&" + ServiceURL.URL_PARAM_USERNAME + "=" + URLEncoder.encode(name, "UTF-8");
                    param += "&" + ServiceURL.URL_PARAM_PAYPERIOD + "=" + payperiodID;
                    param += "&" + ServiceURL.URL_PARAM_COUNTRY + "=" + countryID;
                    param += "&" + ServiceURL.URL_PARAM_DAYOFBIRTH + "=" + URLEncoder.encode(birthDate, "UTF-8");
                    url = new URL(ServiceURL.ACCOUNT + "?" + param);
                } catch (MalformedURLException | UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
                System.out.println(url);
//                HttpURLConnection urlConnection = null;
//                try {
//                    urlConnection = (HttpURLConnection) url.openConnection();
//                } catch (IOException ex) {
//                    System.err.println("Error: " + ex.getMessage());
//                }
//                try {
//                    InputStream inputStream = urlConnection.getInputStream();
//                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//                    StringBuilder response = new StringBuilder();
//                    String partialResponse;
//                    while ((partialResponse = bufferedReader.readLine()) != null) {
//                        String cleanString = partialResponse.replaceAll("(\\\\r\\\\n|\\\\n|\\\\)", "");
//                        response.append(cleanString);
//                    }
//                    System.out.println(response);
//                    bufferedReader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    return null;
//                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
            }
        }.execute();
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
}
