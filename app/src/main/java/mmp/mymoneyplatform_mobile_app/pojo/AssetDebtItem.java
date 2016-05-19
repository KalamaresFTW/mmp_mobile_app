package mmp.mymoneyplatform_mobile_app.pojo;

public class AssetDebtItem {

    protected String title, assetValue, debtValue, netEqualityValue;


    public AssetDebtItem(String title, String assetValue, String debtValue, String netEqualityValue) {
        this.title = title;
        this.assetValue = assetValue;
        this.debtValue = debtValue;
        this.netEqualityValue = netEqualityValue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNetEqualityValue() {
        return netEqualityValue;
    }

    public void setNetEqualityValue(String netEqualityValue) {
        this.netEqualityValue = netEqualityValue;
    }

    public String getDebtValue() {
        return debtValue;
    }

    public void setDebtValue(String debtValue) {
        this.debtValue = debtValue;
    }

    public String getAssetValue() {
        return assetValue;
    }

    public void setAssetValue(String assetValue) {
        this.assetValue = assetValue;
    }
}
