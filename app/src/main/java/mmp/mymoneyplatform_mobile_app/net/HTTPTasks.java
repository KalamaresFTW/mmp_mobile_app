package mmp.mymoneyplatform_mobile_app.net;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
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
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String partialResponse;
                while ((partialResponse = bufferedReader.readLine()) != null) {

                    response.append(partialResponse);
                }
                bufferedReader.close();
                String string = new String(response);
                System.out.println(string);
                //TODO: make this shit work
//                JSONObject JSONResponse;
//                JSONArray regionArray;
//                try {
//                    JSONResponse = new JSONObject(response.toString());
//                    regionArray = JSONResponse.getJSONArray(ServiceTags.JURISDICTION_TABLE);
//                    for (int i = 0; i < regionArray.length(); i++) {
//                        JSONObject regionObject = (JSONObject) regionArray.get(i);
//                        RegionData country = new RegionData(
//                                regionObject.getInt(ServiceTags.JURISDICTIONID_TAG),
//                                regionObject.getString(ServiceTags.JURISDICTION_TAG));
//                        countryList.add(country);
//                    }
//                } catch (JSONException ex) {
//                    ex.printStackTrace();
//                }
                return null;
                //This represents all the data contained in the JSON object from the sever.
            } catch (IOException e) {
                e.printStackTrace();
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
            return null;
        }
    }
}
