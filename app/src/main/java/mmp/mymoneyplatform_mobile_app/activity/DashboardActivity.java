package mmp.mymoneyplatform_mobile_app.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import mmp.mymoneyplatform_mobile_app.R;
import mmp.mymoneyplatform_mobile_app.adapter.CardViewDataAdapter;
import mmp.mymoneyplatform_mobile_app.pojo.CardViewData;
import mmp.mymoneyplatform_mobile_app.pojo.UserData;
import mmp.mymoneyplatform_mobile_app.util.FontsOverride;
import mmp.mymoneyplatform_mobile_app.util.ProgressBarAnimation;

@SuppressWarnings({"ConstantConditions", "finally", "ReturnInsideFinallyBlock", "deprecation"})
public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Magic numbers out of nowhere ;)
    public static final int NUMBER_OF_CARDS = 6;

    //This ia reference to the header items on the drawer
    private ImageView iv_menu_img; //Not being used atm
    private TextView tv_menu_name, tv_menu_mail;

    //Sample data to fill the cards with data and colors
    private ArrayList<CardViewData> cardData = new ArrayList<>(NUMBER_OF_CARDS);

    //We will hold a reference for every CardView in the list
    private ArrayList<LinearLayout> cardList = new ArrayList<>(NUMBER_OF_CARDS);

    //By default this is null until the onCreate callback
    private UserData user;

    //This is we are going to retrieve the user data for the dashboard
    private ArrayList<String> moneyData = new ArrayList<>(DashboardActivity.NUMBER_OF_CARDS);
    private ArrayList<Double> percentageData = new ArrayList<>(DashboardActivity.NUMBER_OF_CARDS);

    //Reference to the UI action plan section
    private TextView tv_action_plan_score;
    private ProgressBar progress_bar_action_plan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set the new font
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Raleway-Regular.ttf");
        //Set the layout of this Activity
        setContentView(R.layout.activity_dashboard);
        //Retrieve Shared preferences of the app
        SharedPreferences mPrefs = getSharedPreferences("prefs", MODE_PRIVATE);
        //Gson object used to deserialize JSON strings into Java Objects
        Gson gson = new Gson();
        //JSON String that contains the ammount of money displayed in each card
        String moneyDataJSON = mPrefs.getString("moneyData", "");
        //Type of ArrayList<String>
        Type moneyDataType = new TypeToken<ArrayList<String>>() {
        }.getType();
        //Parse the JSON String into an actual ArrayList of Strings
        moneyData = gson.fromJson(moneyDataJSON, moneyDataType);
        //JSON String that contains the Double data for the progressbar percentage
        String percentageDataJSON = mPrefs.getString("percentageData", "");
        //Type of ArrayList<Double>
        Type percentageDataType = new TypeToken<ArrayList<Double>>() {
        }.getType();
        //Parse the JSON String into an ArrayList<Double> object
        percentageData = gson.fromJson(percentageDataJSON, percentageDataType);
        //Retrieve the user data from the SharedPrefs
        String json = mPrefs.getString("user", "");
        //Retrieve the user Data
        user = gson.fromJson(json, UserData.class);
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
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) drawer.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View navigationViewHeader = navigationView.getHeaderView(0);
        //This are the drawer items
        iv_menu_img = (ImageView) navigationViewHeader.findViewById(R.id.iv_menu_img);
        tv_menu_name = (TextView) navigationViewHeader.findViewById(R.id.tv_menu_name);
        tv_menu_mail = (TextView) navigationViewHeader.findViewById(R.id.tv_menu_mail);
        //This are for the general health bar
        tv_action_plan_score = (TextView) findViewById(R.id.tv_action_plan_score);
        progress_bar_action_plan = (ProgressBar) findViewById(R.id.progress_bar_action_plan);
        cardList.add((LinearLayout) findViewById(R.id.cv_income));
        cardList.add((LinearLayout) findViewById(R.id.cv_pension));
        cardList.add((LinearLayout) findViewById(R.id.cv_goals));
        cardList.add((LinearLayout) findViewById(R.id.cv_assetsdebts));
        cardList.add((LinearLayout) findViewById(R.id.cv_life));
        cardList.add((LinearLayout) findViewById(R.id.cv_spending));
        loadProfileData();
        loadCardsData();
    }

    public void onCardViewClicked(View v) {
        switch (v.getId()) {
            case R.id.cv_income:
                startActivity(new Intent(getApplicationContext(), IncomeActivity.class));
                break;
            case R.id.cv_pension:
                startActivity(new Intent(getApplicationContext(), PensionActivity.class));
                break;
            case R.id.cv_goals:
                startActivity(new Intent(getApplicationContext(), GoalsActivity.class));
                break;
            case R.id.cv_assetsdebts:
                Toast.makeText(this, "Start Assets/Debts Screen", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cv_life:
                Toast.makeText(this, "Start Life Screen", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cv_spending:
                Toast.makeText(this, "Start Spending Screen", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void loadProfileData() {
        //TODO: Find a way to load the user's profile image
//        new AsyncTask<Void, Void, Void>() {
//            private Bitmap bitmap;
//
//            @Override
//            protected Void doInBackground(Void... params) {
//                String url = ServiceURL.REVIEW_SERVICE_URL + user.getProfileImage().substring(5);
//                System.out.println(url);
//                try {
//                    bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    return null;
//                }
//            }
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                iv_menu_img.setImageBitmap(bitmap);
////                iv_menu_img.setMaxWidth(10);
////                iv_menu_img.setMaxHeight(10);
//                iv_menu_img.setScaleX(0.5f);
//                iv_menu_img.setScaleY(0.5f);
//            }
//        }.execute();

        if (user.getName() != null) tv_menu_name.setText(user.getName());
        if (user.getEmail() != null) tv_menu_mail.setText(user.getEmail());
    }

    @SuppressLint("SetTextI18n")
    private void loadCardsData() {
        cardData.add(
                new CardViewData(
                        getString(R.string.cv_title_income),
                        getString(R.string.cv_subtitle_income),
                        getResources().getColor(R.color.colorTitleIncome),
                        getResources().getColor(R.color.colorBackgroundCardViewIncome),
                        0,
                        new CardViewData.HealthPanelData(
                                this,
                                getString(R.string.cv_hp_title_income),
                                0,
                                getResources().getColor(R.color.colorBackgroundHealthPanelIncome)
                        )
                )
        );
        cardData.add(
                new CardViewData(
                        getString(R.string.cv_title_pension),
                        getString(R.string.cv_subtitle_pension),
                        getResources().getColor(R.color.colorTitlePension),
                        getResources().getColor(R.color.colorBackgroundCardViewPension),
                        0,
                        new CardViewData.HealthPanelData(
                                this,
                                getString(R.string.cv_hp_title_pension),
                                0,
                                getResources().getColor(R.color.colorBackgroundHealthPanelPension)
                        )
                )
        );
        cardData.add(
                new CardViewData(
                        getString(R.string.cv_title_goals),
                        getString(R.string.cv_subtitle_goals),
                        getResources().getColor(R.color.colorTitleGoals),
                        getResources().getColor(R.color.colorBackgroundCardViewGoals),
                        0,
                        new CardViewData.HealthPanelData(
                                this,
                                getString(R.string.cv_hp_title_goals),
                                0,
                                getResources().getColor(R.color.colorBackgroundHealthPanelGoals)
                        )
                )
        );
        cardData.add(
                new CardViewData(
                        getString(R.string.cv_title_assets_debts),
                        getString(R.string.cv_subtitle_assets_debts),
                        getResources().getColor(R.color.colorTitleAssetsDebts),
                        getResources().getColor(R.color.colorBackgroundCardViewAssetsDebts),
                        0,
                        new CardViewData.HealthPanelData(
                                this,
                                getString(R.string.cv_hp_title_assets_debts),
                                0,
                                getResources().getColor(R.color.colorBackgroundHealthPanelAssetsDebts)
                        )
                )
        );
        cardData.add(
                new CardViewData(
                        getString(R.string.cv_title_life),
                        getString(R.string.cv_subtitle_life),
                        getResources().getColor(R.color.colorTitleLife),
                        getResources().getColor(R.color.colorBackgroundCardViewLife),
                        0,
                        new CardViewData.HealthPanelData(
                                this,
                                getString(R.string.cv_hp_title_life),
                                0,
                                getResources().getColor(R.color.colorBackgroundHealthPanelLife)
                        )
                )
        );
        cardData.add(
                new CardViewData(
                        getString(R.string.cv_title_spending),
                        getString(R.string.cv_subtitle_spending),
                        getResources().getColor(R.color.colorTitleSpending),
                        getResources().getColor(R.color.colorBackgroundCardViewSpending),
                        0,
                        new CardViewData.HealthPanelData(
                                this,
                                getString(R.string.cv_hp_title_spending),
                                0,
                                getResources().getColor(R.color.colorBackgroundHealthPanelSpending)
                        )
                )
        );

        //This is where we load the actual data (Money title and the progress bar))
        for (int i = 0; i < DashboardActivity.NUMBER_OF_CARDS; i++) {
            cardData.get(i).setMoney(Float.parseFloat(moneyData.get(i)));
            cardData.get(i).getHealthPanel().setHealthProgress((int) Math.abs(percentageData.get(i)));
        }
        int actionPlanValue = percentageData.get(6).intValue();
        progress_bar_action_plan.setProgress(actionPlanValue);
        tv_action_plan_score.setText(actionPlanValue + "%");
        //Animations added here
        ProgressBarAnimation anim = new ProgressBarAnimation(progress_bar_action_plan, 0, actionPlanValue);
        anim.setDuration(2000);
        // Instead of setting the final progress of the bar
        // we start the animation, and the framework does everything
        tv_action_plan_score.startAnimation(anim);
        //Finally we set all the properties of each card (mostly a e s t h e t i c properties)
        CardViewDataAdapter.getInstance().loadData(cardData, cardList);
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
        //TODO: Create a bunch of Indents for every item on the Navigator Menu
        switch (item.getItemId()) {
            case R.id.nav_profile:
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                break;
            case R.id.nav_dashboard:
                Toast.makeText(this, "Dashboard", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_income:
                startActivity(new Intent(getApplicationContext(), IncomeActivity.class));
                break;
            case R.id.nav_pension:
                startActivity(new Intent(getApplicationContext(), PensionActivity.class));
                break;
            case R.id.nav_assetsdebts:
                Toast.makeText(this, "Assets/Debts", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_goals:
                startActivity(new Intent(getApplicationContext(), GoalsActivity.class));
                break;
            case R.id.nav_life:
                Toast.makeText(this, "Life", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_spendings:
                Toast.makeText(this, "Spendings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_help:
                Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
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
}
