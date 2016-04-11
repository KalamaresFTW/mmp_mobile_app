package mmp.mymoneyplatform_mobile_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import mmp.mymoneyplatform_mobile_app.R;
import mmp.mymoneyplatform_mobile_app.pojo.User;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView iv_menu_img;
    private TextView tv_menu_name, tv_menu_mail;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        user = (User) getIntent().getSerializableExtra("user");
        System.out.println(user.toString());
        if (user == null){ //Just in case
            startActivity(new Intent(this, LoginActivity.class));
        }
        initComponents();

    }

    public void initComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Initialisation of the Navigator Menu´s components
        iv_menu_img = (ImageView) findViewById(R.id.iv_menu_img);
        tv_menu_name = (TextView) findViewById(R.id.tv_menu_name);
        tv_menu_mail = (TextView) findViewById(R.id.tv_menu_mail);

        //loadProfileData();
    }

    public void loadProfileData() {
        //TODO: find out why are we getting null pointer exception when we try to acces these TextViews
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

                break;
            case R.id.nav_settings:

                break;
            case R.id.nav_help:

                break;
            case R.id.nav_logout:
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
