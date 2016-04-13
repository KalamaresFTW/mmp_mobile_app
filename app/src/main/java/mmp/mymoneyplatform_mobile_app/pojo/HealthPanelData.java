package mmp.mymoneyplatform_mobile_app.pojo;

import android.content.Context;

import mmp.mymoneyplatform_mobile_app.R;

/**
 * Created by K on 12/04/2016.
 * This class holds all the properties related to a HealthPanel that is always contained in a
 * CardViewData instance. We store everything that is not common for two different HealthPanel
 * instances.
 * We also provide a reference to the Context of the activity holding this object, so we can access
 * the resources in our XML tables (such as strings.xml and colors.xml)
 */

public class HealthPanelData {

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

    private void assignStatus(double healthProgress) {
        //Depending on the healthProgress value we assign tha value for the Status and Color
        if (healthProgress >= 0 && healthProgress <= 25) { //Less or equal to 25%
            status = c.getString(R.string.cv_hp_state_poor);
            statusColor = c.getResources().getColor(R.color.colorStatePoor);
        } else if (healthProgress > 25 && healthProgress <= 50) { //more than 25% and less or equal to 50%
            status = c.getString(R.string.cv_hp_state_medium);
            statusColor = c.getResources().getColor(R.color.colorStateMedium);
        } else if (healthProgress > 50 && healthProgress <= 75) { //more than 50% and less or equal to 75%
            status = c.getString(R.string.cv_hp_state_good);
            statusColor = c.getResources().getColor(R.color.colorStateGood);
        } else if (healthProgress > 75 && healthProgress <= 100) { //more than 75% and less or equal to 100%
            status = c.getString(R.string.cv_hp_state_excellent);
            statusColor = c.getResources().getColor(R.color.colorStateExcelent);
        } else {
            throw new IllegalArgumentException("The value of the healthProgress must be between 0 and 100");
        }
    }

    //region Getters and Setters

    public String getStatus() {
        return status;
    }

    public int getHealthProgress() {
        return healthProgress;
    }

    public int getStatusColor() {
        return statusColor;
    }

    public int getBackgroundColor(){return backgroundColor;}

    public void setHealthProgress(int healthProgress) {
        if (healthProgress<0 || healthProgress>100){

            this.healthProgress = healthProgress;
            assignStatus(this.healthProgress); //Update the status to match the new progress value
        }
    }

    public String getTitle() {
        return title;
    }

    //endregion
}
