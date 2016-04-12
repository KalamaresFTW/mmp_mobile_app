package mmp.mymoneyplatform_mobile_app.pojo;

/**
 * Created by K on 12/04/2016.
 * This class holds all the properties related to an individual CardView in the CardView list
 * It has references for every single element of the card that is not common, like the title,
 * subtitle, the color of the title, the background color, the amount of money displayed and it has
 * a reference to an instance of HealthPanelData, which represents the little board on the bottom
 * of the card, displaying the actual health on that item (see CardViewData for more information).
 */

public class CardViewData {

    private String title, subtitle;
    private int titleColor, backgroundColor;
    private double money;
    private HealthPanelData healthPanel;

    public CardViewData(String title, String subtitle, int titleColor, int backgroundColor, double money, HealthPanelData healthPanel) {
        this.title = title;
        this.subtitle = subtitle;
        this.titleColor = titleColor;
        this.backgroundColor = backgroundColor;
        this.money = money;
        this.healthPanel = healthPanel;
    }

    //region Getters and Setters

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public HealthPanelData getHealthPanel() {
        return healthPanel;
    }

    public void setHealthPanel(HealthPanelData healthPanel) {
        this.healthPanel = healthPanel;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    //endregion

    @Override
    public String toString() {
        return "CardViewData{" +
                "backgroundColor='" + backgroundColor + '\'' +
                ", healthPanel=" + healthPanel +
                ", money=" + money +
                ", subtitle='" + subtitle + '\'' +
                ", title='" + title + '\'' +
                ", titleColor='" + titleColor + '\'' +
                '}';
    }
}