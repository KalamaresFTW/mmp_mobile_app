package mmp.mymoneyplatform_mobile_app.pojo;

/**
 * Created by Nacho on 16/05/2016.
 */
public class PensionData {
    private int pensionID;
    private String pensionTitle;
    private String pensionBalance;
    private String pensionMonthly;

    public PensionData(int pensionID, String pensionTitle, String pensionBalance, String pensionMonthly) {
        this.pensionID = pensionID;
        this.pensionTitle = pensionTitle;
        this.pensionBalance = pensionBalance;
        this.pensionMonthly = pensionMonthly;
    }

    public int getPensionID() {
        return pensionID;
    }

    public void setPensionID(int pensionID) {
        this.pensionID = pensionID;
    }

    public String getPensionTitle() {
        return pensionTitle;
    }

    public void setPensionTitle(String pensionTitle) {
        this.pensionTitle = pensionTitle;
    }

    public String getPensionBalance() {
        return pensionBalance;
    }

    public void setPensionBalance(String pensionBalance) {
        this.pensionBalance = pensionBalance;
    }

    public String getPensionMonthly() {
        return pensionMonthly;
    }

    public void setPensionMonthly(String pensionMonthly) {
        this.pensionMonthly = pensionMonthly;
    }

    @Override
    public String toString() {
        return "PensionData{" +
                "pensionID=" + pensionID +
                ", pensionTitle='" + pensionTitle + '\'' +
                ", pensionBalance='" + pensionBalance + '\'' +
                ", pensionMonthly='" + pensionMonthly + '\'' +
                '}';
    }
}
