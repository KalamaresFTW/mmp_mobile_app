package mmp.mymoneyplatform_mobile_app.adapter;

import android.content.Context;
import android.widget.Spinner;

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

    }


    private void loadLegionData(Spinner mRegionSpinner) {
        //We have to fill the spinner with the data recived from de database by the API

    }
}
