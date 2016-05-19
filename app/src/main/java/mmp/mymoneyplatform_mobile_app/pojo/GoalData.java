package mmp.mymoneyplatform_mobile_app.pojo;

import android.graphics.drawable.Drawable;

/**
 * Created by Nacho on 18/05/2016.
 */
public class GoalData {

    private Drawable img;
    private String title;
    private int percentage;
    private double amount;

    public GoalData(Drawable img, String title, int percentage, double amount) {
        this.img = img;
        this.title = title;
        this.percentage = percentage;
        this.amount = amount;
    }

    public GoalData(String title, int percentage, double amount) {
        this.title = title;
        this.percentage = percentage;
        this.amount = amount;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "GoalData{" +
                "img=" + img +
                ", title='" + title + '\'' +
                ", percentage=" + percentage +
                ", amount=" + amount +
                '}';
    }
}
