package mmp.mymoneyplatform_mobile_app.adapter;

import android.content.res.ColorStateList;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import mmp.mymoneyplatform_mobile_app.R;
import mmp.mymoneyplatform_mobile_app.activity.DashboardActivity;
import mmp.mymoneyplatform_mobile_app.pojo.CardViewData;

/**
 * Created by K on 13/04/2016.
 * Utility class made to load the data into the cards.
 * Singleton design pattern applied.
 */
public class CardViewDataAdapter {

    private static CardViewDataAdapter instance = null;

    CardViewDataAdapter() {

    }

    public static CardViewDataAdapter getInstance() {
        if (instance == null) {
            instance = new CardViewDataAdapter();
        }
        return instance;
    }

    public void loadData(ArrayList<CardViewData> cardData, ArrayList<LinearLayout> cardList) {
        //Bunch of variables we declare here instead at inside the loop to improve performance
        CardViewData cvd;
        LinearLayout ll;
        RelativeLayout healthPanel;
        CardView cardView;
        TextView cvTitle, cvSubtitle, cvMoney, cvHpTitle, cvHpState;
        Drawable progressBar;
        ProgressBar hpProgressBar;
        int statusColor;
        //Class that we use to format the money value into ###,###.00
        Locale locale = new Locale("en","UK");
        DecimalFormat df = (DecimalFormat)
                NumberFormat.getNumberInstance(locale);
        df.setMaximumFractionDigits(2);

        for (int i = 0; i < DashboardActivity.NUMBER_OF_CARDS; i++) {
            //Get the item we need to modify
            ll = cardList.get(i);
            //Get the data of the item we're going to modify
            cvd = cardData.get(i);
            //Get the CardView object
            cardView = (CardView) ll.findViewById(R.id.card_view);
            //Set the card background color
            cardView.setCardBackgroundColor(cvd.getBackgroundColor());
            //Get the card title TextView
            cvTitle = (TextView) cardView.findViewById(R.id.cv_tv_title);
            //Set the card title
            cvTitle.setText(cvd.getTitle());
            //Set the card title color
            cvTitle.setTextColor(cvd.getTitleColor());
            //Get the TextView for the subtitle
            cvSubtitle = (TextView) cardView.findViewById(R.id.cv_tv_subtitle);
            //Set the card subtitle
            cvSubtitle.setText(cvd.getSubtitle());
            //Get the card money TextView
            cvMoney = (TextView) cardView.findViewById(R.id.cv_tv_money);
            //Set the card money (formating the double value)
            //TODO: replace hardcoded "€" with strings.xml resource
            //TODO: add decimal values to the money TextView
            cvMoney.setText("€" + df.format(cvd.getMoney()));
            //Get the title of the card health panel
            cvHpTitle = (TextView) cardView.findViewById(R.id.cv_hp_tv_title);
            //Set the title od the card
            cvHpTitle.setText(cvd.getHealthPanel().getTitle());
            //Get the RelativeLayout of the health panel
            healthPanel = (RelativeLayout) cardView.findViewById(R.id.health_panel);
            //Set the background color of the health panel
            healthPanel.setBackgroundColor(cvd.getHealthPanel().getBackgroundColor());
            //Get the health panel title
            cvHpState = (TextView) cardView.findViewById(R.id.cv_hp_tv_progresstitle);
            //Set the status of the health panel
            cvHpState.setText(cvd.getHealthPanel().getStatus());
            //We store this color because we are going to use it for both status and progressbar
            statusColor = cvd.getHealthPanel().getStatusColor();
            //Set the color of the health panel status title
            cvHpState.setTextColor(statusColor);
            //Get the progress bar on the health panel
            hpProgressBar = (ProgressBar) cardView.findViewById(R.id.cv_hp_progressbar);
            //We get the progress bar for each card
            progressBar = cvd.getHealthPanel().getProgressBar();
            hpProgressBar.setProgressDrawable(progressBar);
            //Set the progress of the bar
            hpProgressBar.setProgress(cvd.getHealthPanel().getHealthProgress());
        }
    }

}