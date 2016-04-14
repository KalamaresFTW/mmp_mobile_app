package mmp.mymoneyplatform_mobile_app.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import mmp.mymoneyplatform_mobile_app.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private DatePickerDialog fromDatePickerDialog;
    private AutoCompleteTextView date;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        date = (AutoCompleteTextView) findViewById(R.id.reg_dateofbirth);
        date.setOnClickListener(this);
        Calendar calendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                date.setText(dateFormatter.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onClick(View v) {
        if (v == date) {
            fromDatePickerDialog.show();
        }
    }
}
