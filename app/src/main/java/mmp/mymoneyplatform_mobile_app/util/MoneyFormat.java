package mmp.mymoneyplatform_mobile_app.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MoneyFormat {

    //Util class to format the money value into ###,###.00
    private static MoneyFormat INSTANCE = null;
    private static final Locale locale = new Locale("en", "UK");
    DecimalFormat df;

    public static MoneyFormat getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MoneyFormat();
        }
        return INSTANCE;
    }

    private MoneyFormat() {
        df = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        df.setMaximumFractionDigits(2);
    }

    public String format(double numericValue) {
        return "€" + df.format(numericValue);
    }
}
