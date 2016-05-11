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

import mmp.mymoneyplatform_mobile_app.R;
import mmp.mymoneyplatform_mobile_app.fragment.income.IncomeInputsFragment;
import mmp.mymoneyplatform_mobile_app.fragment.income.IncomeSummaryFragment;

@SuppressWarnings("ConstantConditions")
public class IncomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected TabLayout tabLayout;
    protected ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        adapter.addFragment(new IncomeInputsFragment(), "Inputs");
        adapter.addFragment(new IncomeSummaryFragment(), "Summary");
        viewPager.setAdapter(adapter);
    }

    //TODO: hacer esta mierda guay
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_profile:
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                break;
            case R.id.nav_dashboard:
                finish();
                Toast.makeText(getApplicationContext(), "Dashboard", Toast.LENGTH_SHORT).show();
            case R.id.nav_income:
                Toast.makeText(getApplicationContext(), "Income", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_pension:
                finish();
                startActivity(new Intent(getApplicationContext(), PensionActivity.class));
                Toast.makeText(getApplicationContext(), "Pension", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_assetsdebts:
                Toast.makeText(getApplicationContext(), "Assets/Debts", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_goals:
                Toast.makeText(getApplicationContext(), "Goals", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_life:
                Toast.makeText(getApplicationContext(), "Life", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_spendings:
                Toast.makeText(getApplicationContext(), "Spendings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_settings:
                Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_help:
                Toast.makeText(getApplicationContext(), "Help", Toast.LENGTH_SHORT).show();
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_module_layout);
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