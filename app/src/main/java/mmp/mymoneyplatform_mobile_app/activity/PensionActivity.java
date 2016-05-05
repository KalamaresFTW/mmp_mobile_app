package mmp.mymoneyplatform_mobile_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mmp.mymoneyplatform_mobile_app.Fragments.Pension.OneFragment;
import mmp.mymoneyplatform_mobile_app.Fragments.Pension.ThreeFragment;
import mmp.mymoneyplatform_mobile_app.Fragments.Pension.TwoFragment;
import mmp.mymoneyplatform_mobile_app.R;

public class PensionActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pension);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Reference to the drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_module_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) drawer.findViewById(R.id.nav_view_module);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "ONE");
        adapter.addFragment(new TwoFragment(), "TWO");
        adapter.addFragment(new ThreeFragment(), "THREE");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_profile:
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                break;
            case R.id.nav_income:
                Toast.makeText(PensionActivity.this, "Income", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_pension:
                Toast.makeText(this, "Pension", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_assetsdebts:
                Toast.makeText(PensionActivity.this, "Assets/Debts", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_goals:
                Toast.makeText(PensionActivity.this, "Goals", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_life:
                Toast.makeText(PensionActivity.this, "Life", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_spendings:
                Toast.makeText(PensionActivity.this, "Spendings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_settings:
                Toast.makeText(PensionActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_help:
                Toast.makeText(PensionActivity.this, "Help", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                //In this case we start a new Login Activity
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;
            default:
                return false;
        }
        //Close the drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
