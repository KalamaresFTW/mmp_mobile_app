package mmp.mymoneyplatform_mobile_app.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
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
import mmp.mymoneyplatform_mobile_app.adapter.SpinnerAdapter;
import mmp.mymoneyplatform_mobile_app.net.ServiceTags;
import mmp.mymoneyplatform_mobile_app.net.ServiceURL;
import mmp.mymoneyplatform_mobile_app.pojo.FrecuencyData;
import mmp.mymoneyplatform_mobile_app.pojo.RegionData;
import mmp.mymoneyplatform_mobile_app.util.FontsOverride;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Calendar calendar;
    private EditText mBirthDate, mNameView, mPasswordView, mPasswordConfirmView;
    private AutoCompleteTextView mEmailView;
    private Button mRegisterButton;
    private Spinner mPaymentFrecuencySpinner, mRegionSpinner;

    private PaidFrequencyLoader mPaidFrequencyLoaderTask;
    private RegionDataLoader mRegionDataLoaderTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPaidFrequencyLoaderTask = new PaidFrequencyLoader();
        mPaidFrequencyLoaderTask.execute((Void) null);

        mRegionDataLoaderTask = new RegionDataLoader();
        mRegionDataLoaderTask.execute((Void) null);

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

        mPaymentFrecuencySpinner = (Spinner) findViewById(R.id.sp_payment_frecuency);
        mRegionSpinner = (Spinner) findViewById(R.id.sp_region);
        //Load the dummy data for the spinners. In the future this data will be send by the database
        SpinnerAdapter.getInstance(this).loadData(mPaymentFrecuencySpinner, mRegionSpinner);
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

    class PaidFrequencyLoader extends AsyncTask<Void, Void, Void> {

        private ArrayList<FrecuencyData> frecuencyData = new ArrayList<>();

        @Override
        protected Void doInBackground(Void... params) {
            String param;
            URL url = null;
            try {
                param = "entityName=" + URLEncoder.encode(ServiceURL.URL_PARAM_PAYPERIOD, "UTF-8");
                url = new URL(ServiceURL.DEFAULT + "?" + param);
            } catch (MalformedURLException | UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            System.out.println(url);

            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException ex) {
                System.err.println("Error: " + ex.getMessage());
            }
            try {
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder response = new StringBuilder();
                String partialResponse;
                while ((partialResponse = bufferedReader.readLine()) != null) {
                    String cleanString = partialResponse.replaceAll("(\\\\r\\\\n|\\\\n|\\\\)", "");
                    response.append(cleanString);
                }
                bufferedReader.close();
                String json = response.toString();
                JSONObject JSONResponse;
                JSONArray JSONArray;
                try {
                    JSONArray = new JSONArray(json.substring(json.indexOf("["), json.lastIndexOf("]") + 1));
                    for (int i = 0; i < JSONArray.length(); i++) {
                        JSONResponse = JSONArray.getJSONObject(i);
                        frecuencyData.add(new FrecuencyData(
                                JSONResponse.getInt(ServiceTags.PAYPERIODID_TAG),
                                JSONResponse.getString(ServiceTags.PAYPERIOD_TAG)
                        ));
                    }
                } catch (JSONException e) {
                    System.err.println("Error: " + e.getMessage());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            ArrayAdapter<FrecuencyData> adapter = new ArrayAdapter<>(getApplicationContext(),
                    R.layout.my_item_spinner, frecuencyData);
            mPaymentFrecuencySpinner.setAdapter(adapter);
        }
    }

    class RegionDataLoader extends AsyncTask<Void, Void, Void> {

        private ArrayList<RegionData> countryList = new ArrayList<>();

        @Override
        protected Void doInBackground(Void... params) {
            String param;
            URL url = null;
            try {
                param = "entityName=" + URLEncoder.encode(ServiceURL.URL_PARAM_COUNTRY, "UTF-8");
                url = new URL(ServiceURL.DEFAULT + "?" + param);
            } catch (MalformedURLException | UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            System.out.println(url);

            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException ex) {
                System.err.println("Error: " + ex.getMessage());
            }
            try {
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder response = new StringBuilder();
                String partialResponse;
                while ((partialResponse = bufferedReader.readLine()) != null) {
                    String cleanString = partialResponse.replaceAll("(\\\\r\\\\n|\\\\n|\\\\)", "");
                    response.append(cleanString);
                }
                bufferedReader.close();
                String json = response.toString();
                JSONObject JSONResponse;
                JSONArray JSONArray;
                try {
                    JSONArray = new JSONArray(json.substring(json.indexOf("["), json.lastIndexOf("]") + 1));
                    for (int i = 0; i < JSONArray.length(); i++) {
                        JSONResponse = JSONArray.getJSONObject(i);
                        countryList.add(new RegionData(
                                JSONResponse.getInt(ServiceTags.JURISDICTIONID_TAG),
                                JSONResponse.getString(ServiceTags.JURISDICTION_TAG)
                        ));
                    }
                } catch (JSONException e) {
                    System.err.println("Error: " + e.getMessage());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            ArrayAdapter<RegionData> adapter = new ArrayAdapter<>(getApplicationContext(),
                    R.layout.my_item_spinner, countryList);
            mRegionSpinner.setAdapter(adapter);
        }
    }
}
