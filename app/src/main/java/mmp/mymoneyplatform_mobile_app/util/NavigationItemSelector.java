package mmp.mymoneyplatform_mobile_app.util;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

import mmp.mymoneyplatform_mobile_app.R;
import mmp.mymoneyplatform_mobile_app.activity.AssetsDebtsActivity;
import mmp.mymoneyplatform_mobile_app.activity.DashboardActivity;
import mmp.mymoneyplatform_mobile_app.activity.GoalsActivity;
import mmp.mymoneyplatform_mobile_app.activity.IncomeActivity;
import mmp.mymoneyplatform_mobile_app.activity.LoginActivity;
import mmp.mymoneyplatform_mobile_app.activity.PensionActivity;
import mmp.mymoneyplatform_mobile_app.activity.ProfileActivity;

/**
 * Created by Nacho on 17/05/2016.
 * This class is used to switch between screens with the Navigation Drawer (Slider menu)
 * Singleton constructor with a unique method which recive the item selected in the onNavigationItemSelectedListener
 * and the class which calls this method
 */
public class NavigationItemSelector {

    private static NavigationItemSelector instance = null;

    NavigationItemSelector() {
    }

    public static NavigationItemSelector getInstance() {
        if (instance == null) {
            instance = new NavigationItemSelector();
        }
        return instance;
    }

    public void onNavigationItemSelected(MenuItem item, Activity activity) {
        switch (item.getItemId()) {
            case R.id.nav_profile:
                if (!(activity instanceof DashboardActivity)) {
                    activity.finish();
                }
                Toast.makeText(activity.getApplicationContext(), "Profile", Toast.LENGTH_SHORT).show();
                activity.startActivity(new Intent(activity.getApplicationContext(), ProfileActivity.class));
                break;
            case R.id.nav_dashboard:
                if (activity instanceof DashboardActivity) {
                    Toast.makeText(activity.getApplicationContext(), "Allready on Dashboard", Toast.LENGTH_SHORT).show();
                } else {
                    activity.finish();
                    Toast.makeText(activity.getApplicationContext(), "Dashboard", Toast.LENGTH_SHORT).show();
                    activity.startActivity(new Intent(activity.getApplicationContext(), DashboardActivity.class));
                }
                break;
            case R.id.nav_income:
                if (activity instanceof IncomeActivity) {
                    Toast.makeText(activity.getApplicationContext(), "Allready on Income", Toast.LENGTH_SHORT).show();
                } else {
                    if (!(activity instanceof DashboardActivity)) {
                        activity.finish();
                    }
                    Toast.makeText(activity.getApplicationContext(), "Income", Toast.LENGTH_SHORT).show();
                    activity.startActivity(new Intent(activity.getApplicationContext(), IncomeActivity.class));
                }
                break;
            case R.id.nav_pension:
                if (activity instanceof PensionActivity) {
                    Toast.makeText(activity.getApplicationContext(), "Allready on Pension", Toast.LENGTH_SHORT).show();
                } else {
                    if (!(activity instanceof DashboardActivity)) {
                        activity.finish();
                    }
                    Toast.makeText(activity.getApplicationContext(), "Pension", Toast.LENGTH_SHORT).show();
                    activity.startActivity(new Intent(activity.getApplicationContext(), PensionActivity.class));
                }
                break;
            case R.id.nav_goals:
                if (activity instanceof GoalsActivity) {
                    Toast.makeText(activity.getApplicationContext(), "Allready on Goals", Toast.LENGTH_SHORT).show();
                } else {
                    if (!(activity instanceof DashboardActivity)) {
                        activity.finish();
                    }
                    Toast.makeText(activity.getApplicationContext(), "Goals", Toast.LENGTH_SHORT).show();
                    activity.startActivity(new Intent(activity.getApplicationContext(), GoalsActivity.class));
                }
                break;
            case R.id.nav_assetsdebts:
                if (activity instanceof AssetsDebtsActivity) {
                    Toast.makeText(activity.getApplicationContext(), "Allready on AssetsDebts", Toast.LENGTH_SHORT).show();
                } else {
                    if (!(activity instanceof DashboardActivity)) {
                        activity.finish();
                    }
                    Toast.makeText(activity.getApplicationContext(), "Assets/Debts", Toast.LENGTH_SHORT).show();
                    activity.startActivity(new Intent(activity.getApplicationContext(), AssetsDebtsActivity.class));
                }
                break;
            case R.id.nav_life:
                /*if (activity instanceof Life) {
                    Toast.makeText(activity.getApplicationContext(), "Allready on Life", Toast.LENGTH_SHORT).show();
                } else {
                    if (!(activity instanceof DashboardActivity)) {
                        activity.finish();
                    }
                    Toast.makeText(activity.getApplicationContext(), "Life", Toast.LENGTH_SHORT).show();
                    //activity.startActivity(new Intent(activity.getApplicationContext(), LifeActivity.class));
                }*/
                break;
            case R.id.nav_spendings:
                /*if (activity instanceof SpendingsActivity) {
                    Toast.makeText(activity.getApplicationContext(), "Allready on Spendings", Toast.LENGTH_SHORT).show();
                } else {
                    if (!(activity instanceof DashboardActivity)) {
                        activity.finish();
                    }
                    Toast.makeText(activity.getApplicationContext(), "Spendings", Toast.LENGTH_SHORT).show();
                    //activity.startActivity(new Intent(activity.getApplicationContext(), SpendingsActivity.class));
                }*/
                break;
            case R.id.nav_settings:
                if (!(activity instanceof DashboardActivity)) {
                    activity.finish();
                }
                Toast.makeText(activity.getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
                //activity.startActivity(new Intent(activity.getApplicationContext(), SettingsActivity.class));
                break;
            case R.id.nav_help:
                if (!(activity instanceof DashboardActivity)) {
                    activity.finish();
                }
                Toast.makeText(activity.getApplicationContext(), "Help", Toast.LENGTH_SHORT).show();
                //activity.startActivity(new Intent(activity.getApplicationContext(), HelpActivity.class));
                break;
            case R.id.nav_logout:
                //In this case we start a new Login Activity
                activity.finish();
                activity.startActivity(new Intent(activity.getApplicationContext(), LoginActivity.class));
                break;
        }
    }
}
