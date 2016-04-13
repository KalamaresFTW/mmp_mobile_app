package mmp.mymoneyplatform_mobile_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import mmp.mymoneyplatform_mobile_app.R;
import mmp.mymoneyplatform_mobile_app.pojo.CardViewData;
import mmp.mymoneyplatform_mobile_app.pojo.HealthPanelData;
import mmp.mymoneyplatform_mobile_app.pojo.User;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //TODO: Update this to 7 when we add the last element
    private static final int NUMBER_OF_CARDS = 6;

    //This ia reference to the header items on the drawe
    private ImageView iv_menu_img;
    private TextView tv_menu_name, tv_menu_mail;

    //Dummy data(not that dummy actually) to fill the cards with data and colors
    private ArrayList<CardViewData> CARDS_DUMMYDATA = new ArrayList<>(NUMBER_OF_CARDS);

    //We will hold a reference for every CardView in the list
    private ArrayList<CardView> cardList = new ArrayList<>(NUMBER_OF_CARDS);

    //By default this is null until the onCreate callback
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set the layout of this Activity
        setContentView(R.layout.activity_dashboard);

        //Retrieve the user object from the Intent
        user = (User) getIntent().getSerializableExtra("user");
        if (user == null) { //Just in case
            startActivity(new Intent(this, LoginActivity.class));
        }

        initComponents();
    }

    public void initComponents() {
        //Ge get a reference to the toolbar of this Activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Reference to the drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) drawer.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View navigationViewHeader = navigationView.getHeaderView(0);

        iv_menu_img = (ImageView) navigationViewHeader.findViewById(R.id.iv_menu_img);
        tv_menu_name = (TextView) navigationViewHeader.findViewById(R.id.tv_menu_name);
        tv_menu_mail = (TextView) navigationViewHeader.findViewById(R.id.tv_menu_mail);

        loadProfileData();
        //loadCardsData();
    }


    //TODO: End this stuff
    private void loadCardsData() {
        CARDS_DUMMYDATA.add(
                new CardViewData(
                        getString(R.string.cv_title_income),
                        getString(R.string.cv_subtitle_income),
                        getResources().getColor(R.color.colorTitleIncome),
                        getResources().getColor(R.color.colorBackgroundCardViewIncome),
                        34650.00,
                        new HealthPanelData(this, getString(R.string.cv_hp_title_income), 10)));
        CARDS_DUMMYDATA.add(
                new CardViewData(
                        getString(R.string.cv_title_pension),
                        getString(R.string.cv_subtitle_pension),
                        getResources().getColor(R.color.colorTitlePension),
                        getResources().getColor(R.color.colorBackgroundCardViewPension),
                        7133.00,
                        new HealthPanelData(this, getString(R.string.cv_hp_title_pension), 25)));
        CARDS_DUMMYDATA.add(
                new CardViewData(
                        getString(R.string.cv_title_goals),
                        getString(R.string.cv_subtitle_goals),
                        getResources().getColor(R.color.colorTitleGoals),
                        getResources().getColor(R.color.colorBackgroundCardViewGoals),
                        22633.00,
                        new HealthPanelData(this, getString(R.string.cv_hp_title_goals), 35)));
        CARDS_DUMMYDATA.add(
                new CardViewData(
                        getString(R.string.cv_title_assets_debts),
                        getString(R.string.cv_subtitle_assets_debts),
                        getResources().getColor(R.color.colorTitleAssetsDebts),
                        getResources().getColor(R.color.colorBackgroundCardViewAssetsDebts),
                        69050.00,
                        new HealthPanelData(this, getString(R.string.cv_hp_title_assets_debts), 50)));
        CARDS_DUMMYDATA.add(
                new CardViewData(
                        getString(R.string.cv_title_life),
                        getString(R.string.cv_subtitle_life),
                        getResources().getColor(R.color.colorTitleLife),
                        getResources().getColor(R.color.colorBackgroundCardViewLife),
                        1294090.00,
                        new HealthPanelData(this, getString(R.string.cv_hp_title_life), 65))
        );
        CARDS_DUMMYDATA.add(
                new CardViewData(
                        getString(R.string.cv_title_spending),
                        getString(R.string.cv_subtitle_spending),
                        getResources().getColor(R.color.colorTitleSpending),
                        getResources().getColor(R.color.colorBackgroundCardViewSpending),
                        4281.00,
                        new HealthPanelData(this, getString(R.string.cv_hp_title_spending), 80)));
    }

    public void loadProfileData() {
        //TODO: add the profile image into the NavigationView header
        //iv_menu_img.setImageIcon();
        tv_menu_name.setText(user.getName());
        tv_menu_mail.setText(user.getEmail());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //TODO: Create a bunch of Indents for every item on the Navigator Menu
        switch (id) {
            case R.id.nav_profile:
                Toast.makeText(DashboardActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_settings:
                Toast.makeText(DashboardActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_help:
                Toast.makeText(DashboardActivity.this, "Help", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                //In this case we start a new Login Activity
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;
            default:
                return false;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
