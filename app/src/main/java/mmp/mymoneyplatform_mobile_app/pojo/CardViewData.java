package mmp.mymoneyplatform_mobile_app.pojo;

import android.content.Context;
import android.graphics.drawable.Drawable;

import mmp.mymoneyplatform_mobile_app.R;

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

    public CardViewData(String title, String subtitle, int titleColor, int backgroundColor,
                        double money, HealthPanelData healthPanel) {
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

    public double getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getSubtitle() {
        return subtitle;
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


    /**
     * Created by K on 12/04/2016.
     * This class holds all the properties related to a HealthPanel that is always contained in a
     * CardViewData instance. We store everything that is not common for two different HealthPanel
     * instances.
     * We also provide a reference to the Context of the activity holding this object, so we can
     * access the resources in our XML tables (such as strings.xml and colors.xml)
     * Makes sense that this class is inside of CardVie
     */

    public static class HealthPanelData {

        /**
         * We need the context of the Application in order to get the color of the health status
         * from our resources XML files (colors.xml)
         */
        private Context c;
        private String title, status;
        private int healthProgress, statusColor, backgroundColor;

        public HealthPanelData(Context c, String title, int healthProgress, int backgroundColor) {
            this.c = c;
            this.title = title;
            this.healthProgress = healthProgress;
            assignStatus(this.healthProgress);
            this.backgroundColor = backgroundColor;
        }

        /**
         * This method assigns a status to the HealthPanel, and depending on that status it changes
         * the text of the @title and both the color of the @title and progress bar (the background
         * doesn't change thought)
         * Throws an IllegalArgumentException if the value of @healthProgress isn't between 0 and
         * 100.
         *
         * @param healthProgress
         */
        private void assignStatus(double healthProgress) {
            //Depending on the healthProgress value we assign tha value for the Status and Color
            if (healthProgress >= 0 && healthProgress <= 25) { //Less or equal to 25%
                status = c.getString(R.string.cv_hp_state_poor);
                statusColor = c.getResources().getColor(R.color.colorStatePoor);
            } else if (healthProgress > 25 && healthProgress <= 50) {
                //more than 25% and less or equal to 50%
                status = c.getString(R.string.cv_hp_state_medium);
                statusColor = c.getResources().getColor(R.color.colorStateMedium);
            } else if (healthProgress > 50 && healthProgress <= 75) {
                //more than 50% and less or equal to 75%
                status = c.getString(R.string.cv_hp_state_good);
                statusColor = c.getResources().getColor(R.color.colorStateGood);
            } else if (healthProgress > 75 && healthProgress <= 100) {
                //more than 75% and less or equal to 100%
                status = c.getString(R.string.cv_hp_state_excellent);
                statusColor = c.getResources().getColor(R.color.colorStateExcelent);
            } else {
                throw new IllegalArgumentException("The value of the healthProgress must " +
                        "be between 0 and 100");
            }
        }

        //region Getters and Setters

        /**
         * @return the String value of the title ("Poor", "Medium", etc...)
         */
        public String getTitle() {
            return title;
        }

        /**
         * @return the text that will be displayed on the status title
         */
        public String getStatus() {
            return status;
        }

        /**
         * @return the percentage value of the progress bar at any moment
         */
        public int getHealthProgress() {
            return healthProgress;
        }

        /**
         * @return the color of both title and progress bar
         */
        public int getStatusColor() {
            return statusColor;
        }

        /**
         * @return the background color of the health panel
         */
        public int getBackgroundColor() {
            return backgroundColor;
        }

        /**
         * Sets a new value for the progress bar and then reassigns the colors.
         * Throws an IllegalArgumentException if the value of @healthProgress isn't between 0 and
         * 100.
         *
         * @param healthProgress
         */
        public void setHealthProgress(int healthProgress) {
            if (healthProgress >= 0 || healthProgress <= 100) {
                this.healthProgress = healthProgress;
                //Update the status to match the new progress value
                assignStatus(this.healthProgress);
            } else {
                throw new IllegalArgumentException("The value of the healthProgress must be " +
                        "between 0 and 100");
            }
        }

        //endregion
    }

}