package mmp.mymoneyplatform_mobile_app.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import mmp.mymoneyplatform_mobile_app.pojo.FrecuencyData;

/**
 * Created by Nacho on 18/04/2016.
 */
public class SpinnerAdapter {

    private static Context c;
    private static SpinnerAdapter instance = null;

    SpinnerAdapter(Context c) {
        this.c = c;
    }

    public static SpinnerAdapter getInstance(Context c) {
        if (instance == null) {
            instance = new SpinnerAdapter(c);
        }
        return instance;
    }

    public void loadData(Spinner mPaymentFrecuencySpinner, Spinner mRegionSpinner) {
        loadPaymentFrecuencyData(mPaymentFrecuencySpinner);
        loadLegionData(mRegionSpinner);
    }

    private void loadPaymentFrecuencyData(Spinner mPaymentFrecuencySpinner) {
        //We have to fill the spinner with the data recived from de database by the API
        ArrayList<FrecuencyData> frecuencies = null;
        ArrayAdapter<FrecuencyData> frecuencyAdapter = new ArrayAdapter<FrecuencyData>(c, android.R.layout.simple_list_item_1, frecuencies);
        mPaymentFrecuencySpinner.setAdapter(frecuencyAdapter);
    }

    private void loadLegionData(Spinner mRegionSpinner) {
        //We have to fill the spinner with the data recived from de database by the API
        ArrayList<FrecuencyData> regions = null;
        ArrayAdapter<FrecuencyData> regionAdapter = new ArrayAdapter<FrecuencyData>(c, android.R.layout.simple_list_item_1, regions);
        mRegionSpinner.setAdapter(regionAdapter);
    }
}
