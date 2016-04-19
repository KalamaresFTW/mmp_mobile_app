package mmp.mymoneyplatform_mobile_app.net;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by K on 18/04/2016.
 */


public class RetrieveUserTask extends AsyncTask<Void, Void, String> {

    private String email;
    private String password;

    //Tags to retrieve the information from the JSON object
    private static final String SUBSCRIPTIONID_TAG = "userSubscriptionID";
    private static final String REGION_TAG = "region";
    private static final String PROFILEIMAGE_TAG = "profileImage";
    private static final String COUNTRY_TAG = "country";
    private static final String NEWUSER_TAG = "newUser";
    private static final String NAME_TAG = "Name";

    public RetrieveUserTask(String email, String password) {
        this.email = email;
        this.password = password;
    }

    protected void onPreExecute() {
        //Do some stuff before retrieving the data
    }

    protected String doInBackground(Void... urls) {
        try {
            URL url = new URL(ServiceURL.ACCOUNT + "?email=" + email + "&password=" + password);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                JSONObject user = new JSONObject(stringBuilder.toString());
                System.out.println("userSubscriptionID: " + user.get(SUBSCRIPTIONID_TAG));
                System.out.println("region: " + user.get(REGION_TAG));
                System.out.println("profileImage: " + user.get(PROFILEIMAGE_TAG));
                System.out.println("country: " + user.get(COUNTRY_TAG));
                System.out.println("newUser: " + user.get(NEWUSER_TAG));
                System.out.println("name: " + user.get(NAME_TAG));
                //UserData data = new UserData();
                //TODO: end this stuff, one of this days, maybe, I guess
                return stringBuilder.toString();
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            return null;
        }
    }

    protected void onPostExecute(String response) {
        if (response == null) {
            response = "THERE WAS AN ERROR: ";
        }
        Log.i("INFO", response);
        System.out.println("INFO" + response);
    }
}