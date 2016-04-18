package mmp.mymoneyplatform_mobile_app.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import mmp.mymoneyplatform_mobile_app.pojo.SpinnerData;

/**
 * Created by Nacho on 18/04/2016.
 */
public class RegistrationSpinnerAdapter {

    private static Context c;
    private static RegistrationSpinnerAdapter instance = null;

    RegistrationSpinnerAdapter(Context c) {
        this.c = c;
    }

    public static RegistrationSpinnerAdapter getInstance(Context c) {
        if (instance == null) {
            instance = new RegistrationSpinnerAdapter(c);
        }
        return instance;
    }

    public void loadData(Spinner mPaymentFrecuencySpinner, Spinner mRegionSpinner) {
        loadPaymentFrecuencyData(mPaymentFrecuencySpinner);
        loadLegionData(mRegionSpinner);
    }

    private void loadPaymentFrecuencyData(Spinner mPaymentFrecuencySpinner) {
        //We have to fill the spinner with the data recived from de database by the API
        //Dummy Frecuencies
        ArrayList<SpinnerData> paymentFrecuencyList = new ArrayList<>();
        paymentFrecuencyList.add(new SpinnerData("Weekly", "1"));
        paymentFrecuencyList.add(new SpinnerData("Fortnightly", "2"));
        paymentFrecuencyList.add(new SpinnerData("Four-Weekly", "3"));
        paymentFrecuencyList.add(new SpinnerData("Monthly", "4"));
        paymentFrecuencyList.add(new SpinnerData("Bi-Monthly", "5"));

        ArrayAdapter<SpinnerData> adapter = new ArrayAdapter<>(c, android.R.layout.simple_list_item_1, paymentFrecuencyList);
        mPaymentFrecuencySpinner.setAdapter(adapter);
    }


    private void loadLegionData(Spinner mRegionSpinner) {
        //We have to fill the spinner with the data recived from de database by the API
        //Dummy Regions
        ArrayList<SpinnerData> paymentRegionList = new ArrayList<>();
        paymentRegionList.add(new SpinnerData("Irland", "1"));
        paymentRegionList.add(new SpinnerData("USA", "2"));
        paymentRegionList.add(new SpinnerData("UK", "3"));

        ArrayAdapter<SpinnerData> adapter = new ArrayAdapter<>(c, android.R.layout.simple_list_item_1, paymentRegionList);
        mRegionSpinner.setAdapter(adapter);
    }
}
