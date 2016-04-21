package mmp.mymoneyplatform_mobile_app.net;

import android.os.AsyncTask;

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

import mmp.mymoneyplatform_mobile_app.pojo.FrecuencyData;
import mmp.mymoneyplatform_mobile_app.pojo.RegionData;

/**
 * Created by K on 20/04/2016.
 */
public class HTTPTasks {

    private static ArrayList<RegionData> countryList = new ArrayList<>();
    private static ArrayList<FrecuencyData> frecuencyData = new ArrayList<>();

    class RegionDataLoader extends AsyncTask<Void, Void, Void> {

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

        }
    }

    public ArrayList<RegionData> loadRegionData() {
        RegionDataLoader mRegionDataLoader = new RegionDataLoader();
        mRegionDataLoader.execute((Void) null);
        return countryList;
    }

    class PaidFrequencyLoader extends AsyncTask<Void, Void, Void> {

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
                for (FrecuencyData frequency : frecuencyData) {
                    System.out.println(frequency);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {

        }
    }

    public ArrayList<FrecuencyData> loadPayFrequencyData() {
        PaidFrequencyLoader mPaidFrequencyLoader = new PaidFrequencyLoader();
        mPaidFrequencyLoader.execute((Void) null);
        return frecuencyData;
    }
}
