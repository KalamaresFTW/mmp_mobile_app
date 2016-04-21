package mmp.mymoneyplatform_mobile_app.net;

import android.os.AsyncTask;

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

                System.out.println(response.toString());

                JSONObject json = new JSONObject(response.toString());

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
            return null;
        }
    }
}
