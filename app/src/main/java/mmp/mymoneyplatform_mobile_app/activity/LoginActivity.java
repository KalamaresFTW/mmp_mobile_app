package mmp.mymoneyplatform_mobile_app.activity;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.annotation.TargetApi;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mmp.mymoneyplatform_mobile_app.R;
import mmp.mymoneyplatform_mobile_app.net.ServiceTags;
import mmp.mymoneyplatform_mobile_app.net.ServiceURL;
import mmp.mymoneyplatform_mobile_app.pojo.FrecuencyData;
import mmp.mymoneyplatform_mobile_app.pojo.RegionData;
import mmp.mymoneyplatform_mobile_app.pojo.UserData;
import mmp.mymoneyplatform_mobile_app.util.FontsOverride;
import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * Regular expression to validate user email input
     */
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /**
     * References of the tasks we are using on the login.
     */
    private RetrieveUserTask mRetrieveUserData;             //Gets the user data via API
    private RetrieveDashboardData mDashboardDataLoaderTask; //Gets the user dashboard's data via API
    private PaidFrequencyLoader mPaidFrequencyLoaderTask;   //Gets the payment frequencies via API
    private RegionDataLoader mRegionDataLoaderTask;         //Gets the country list via API

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set the new font
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Raleway-Regular.ttf");

        mPaidFrequencyLoaderTask = new PaidFrequencyLoader();
        mPaidFrequencyLoaderTask.execute((Void) null);

        mRegionDataLoaderTask = new RegionDataLoader();
        mRegionDataLoaderTask.execute((Void) null);


        //Set the layout
        setContentView(R.layout.activity_login);

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailLogInButton = (Button) findViewById(R.id.email_log_in_button);
        if (mEmailLogInButton != null) {
            mEmailLogInButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    attemptLogin();
                }
            });
        }

        Button btnSign = (Button) findViewById(R.id.sign_in_button);
        if (btnSign != null) {
            btnSign.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    attemptSignIn();
                }
            });
        }

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!hasFocus) {
            finish();
        }
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mRetrieveUserData != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mRetrieveUserData = new RetrieveUserTask(email, password);
            mRetrieveUserData.execute((Void) null);
        }
    }

    public void attemptSignIn() {
        Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(i);
    }

    private boolean isEmailValid(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    //region notRelevantStuff

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
    }
    //endregion

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class RetrieveUserTask extends AsyncTask<Void, Void, Boolean> {

        private String mEmail;
        private String mPassword;

        private UserData userData;

        public RetrieveUserTask(String email, String password) {
            this.mEmail = email;
            this.mPassword = password;
        }

        protected Boolean doInBackground(Void... urls) {
            try {
                URL url = null;
                String params = "email=" + URLEncoder.encode(mEmail, "UTF-8");
                params += "&password=" + URLEncoder.encode(mPassword, "UTF-8");
                try {
                    url = new URL(ServiceURL.ACCOUNT + "?" + params);
                } catch (MalformedURLException ex) {
                    System.err.println("Error:" + ex.getMessage());
                }
                System.out.println(url);
                HttpURLConnection urlConnection;
                if (url != null) {
                    urlConnection = (HttpURLConnection) url.openConnection();
                } else {
                    return false;
                }
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String partialResponse;
                    while ((partialResponse = bufferedReader.readLine()) != null) {
                        response.append(partialResponse);
                    }
                    bufferedReader.close();
                    JSONObject JSONResponse = null;
                    try {
                        JSONResponse = new JSONObject(response.toString());
                    } catch (JSONException ex) {
                        System.err.println(ex.getMessage());
                    }
                    //This represents all the data contained in the JSON object from the sever.
                    try {
                        if (JSONResponse != null) {
                            userData = new UserData(
                                    (String) JSONResponse.get(ServiceTags.SUBSCRIPTIONID_TAG),
                                    (String) JSONResponse.get(ServiceTags.REGION_TAG),
                                    (String) JSONResponse.get(ServiceTags.PROFILEIMAGE_TAG),
                                    (String) JSONResponse.get(ServiceTags.COUNTRY_TAG),
                                    (String) JSONResponse.get(ServiceTags.NEWUSER_TAG),
                                    (String) JSONResponse.get(ServiceTags.NAME_TAG),
                                    mEmail);
                        } else {
                            return false;
                        }
                    } catch (JSONException ex) {
                        System.err.println("Error: " + ex.getMessage());
                    }
                    //UserData not being null means the mail/password combination is correct
                    return !userData.isNull();
                } catch (IOException ex) {
                    System.err.println("Error: " + ex.getMessage());
                    return false;
                } finally {
                    urlConnection.disconnect();
                }
            } catch (UnsupportedEncodingException ex) {
                System.err.println("Error: " + ex.getMessage());
                return false;
            } catch (IOException ex) {
                System.err.println("Error: " + ex.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mRetrieveUserData = null;
            showProgress(false);
            if (success) {
                //Execute a task to get all the Dashboard data for that user
                mDashboardDataLoaderTask = new RetrieveDashboardData(userData.getUserSubscriptionID());
                mDashboardDataLoaderTask.execute((Void) null);
                //Store the UserData instance into the SharedPreferences as a JSON String
                SharedPreferences mPrefs = getSharedPreferences("prefs", MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(userData);
                prefsEditor.putString("user", json);
                prefsEditor.commit();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mRetrieveUserData = null;
            showProgress(false);
        }
    }

    public class RetrieveDashboardData extends AsyncTask<Void, Void, Void> {

        private String userSubscriptionID;
        private ArrayList<String> moneyData = new ArrayList<>(DashboardActivity.NUMBER_OF_CARDS);
        private ArrayList<Double> percentageData = new ArrayList<>(DashboardActivity.NUMBER_OF_CARDS);

        public RetrieveDashboardData(String userSubscriptionID) {
            this.userSubscriptionID = userSubscriptionID;
        }

        @Override
        protected Void doInBackground(Void... params) {
            String param;
            URL url = null;
            try {
                param = ServiceURL.URL_PARAM_USERSUBSCRIPTIONID + "=" + URLEncoder.encode(userSubscriptionID, "UTF-8");
                url = new URL(ServiceURL.DASHBOARD + "?" + param);
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
                System.out.println(response);
                String json = response.toString();
                try {
                    JSONObject jsonResponse = new JSONObject(json.substring(json.indexOf("{"), json.lastIndexOf("}") + 1));
                    JSONObject salaryInfo = jsonResponse.getJSONObject(ServiceTags.SALARYINFO_TAG);
                    String incomeCover = salaryInfo.getString(ServiceTags.INCOMECOVER_TAG);
                    JSONObject pensionInfo = jsonResponse.getJSONObject(ServiceTags.PENSIONINFO_TAG);
                    String pensionAtRetirement = pensionInfo.getString(ServiceTags.PENSIONATRETIREMENT_TAG);
                    JSONObject savingsInfo = jsonResponse.getJSONObject(ServiceTags.SAVINGSINFO_TAG);
                    String totalSavingRequired = savingsInfo.getString(ServiceTags.TOTALSAVINGREQUIRED_TAG);
                    String totalSavingBalance = savingsInfo.getString(ServiceTags.TOTALSAVINGBALANCE_TAG);
                    JSONObject assetDebtInfo = jsonResponse.getJSONObject(ServiceTags.ASSETDEBTINFO_TAG);
                    String totalAsset = assetDebtInfo.getString(ServiceTags.TOTALASSET_TAG);
                    String totalDebt = assetDebtInfo.getString(ServiceTags.TOTALDEBT_TAG);
                    String assetDebtDiff = assetDebtInfo.getString(ServiceTags.ASSETDEBTDIFF_TAG);
                    JSONObject lifeCoverInfo = jsonResponse.getJSONObject(ServiceTags.LIFECOVERINFO_TAG);
                    String lifeCoverNeeded = lifeCoverInfo.getString(ServiceTags.LIFECOVERNEEDED_TAG);
                    JSONObject outgoingsInfo = jsonResponse.getJSONObject(ServiceTags.OUTGOINGSINFO_TAG);
                    String disposableIncome = outgoingsInfo.getString(ServiceTags.DISPOSABLEINCOME_TAG);
                    String healthScore = jsonResponse.getString(ServiceTags.HEALTHSCORE_TAG);
                    String salaryProtectionScore = jsonResponse.getString(ServiceTags.SALARYPROTECTIONSCORE_TAG);
                    String pensionScore = jsonResponse.getString(ServiceTags.PENSIONSCORE_TAG);
                    String savings1Score = jsonResponse.getString(ServiceTags.SAVINGS1SCORE_TAG);
                    String savings2Score = jsonResponse.getString(ServiceTags.SAVINGS2SCORE_TAG);
                    String assetDebtScore = jsonResponse.getString(ServiceTags.ASSETSDEBTSCORE_TAG);
                    String lifeAssuranceScore = jsonResponse.getString(ServiceTags.LIFEASSURANCESCORE_TAG);
                    String outgoingsScore = jsonResponse.getString(ServiceTags.OUTGOINGSSCORE_TAG);
                    moneyData.add(incomeCover);
                    moneyData.add(pensionAtRetirement);
                    moneyData.add(totalSavingRequired);
                    moneyData.add(assetDebtDiff);
                    moneyData.add(lifeCoverNeeded);
                    moneyData.add(disposableIncome);
                    percentageData.add(getPercentage(healthScore, false));
                    percentageData.add(getPercentage(salaryProtectionScore, false));
                    percentageData.add(getPercentage(pensionScore, false));
                    percentageData.add(getPercentage(assetDebtScore, true));
                    percentageData.add(getPercentage(lifeAssuranceScore, false));
                    percentageData.add(getPercentage(outgoingsScore, false));
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                return null;
            }
        }

        @NonNull
        private Double getPercentage(String strValue, boolean isAssetDebt) {
            return (isAssetDebt ? (((Double.parseDouble(strValue)) * 100) / 12) : (((Double.parseDouble(strValue)) * 100) / 6));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String moneyDataJSON = gson.toJson(moneyData);
            String percentageDataJSON = gson.toJson(percentageData);
            editor.putString("moneyData", moneyDataJSON);
            editor.putString("percentageData", percentageDataJSON);
            editor.commit();
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
        }
    }

    public class PaidFrequencyLoader extends AsyncTask<Void, Void, Void> {

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
            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(frecuencyData);
            System.out.println(json);
            editor.putString("frequencyList", json);
            editor.commit();
        }
    }

    public class RegionDataLoader extends AsyncTask<Void, Void, Void> {

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
            SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(countryList);
            System.out.println(json);
            editor.putString("countryList", json);
            editor.commit();
        }
    }


}

