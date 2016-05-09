package mmp.mymoneyplatform_mobile_app.pojo;

@SuppressWarnings("unused")
public class RegionData {

    private int regionID;
    private String region;

    public RegionData(int regionID, String region) {
        this.regionID = regionID;
        this.region = region;
    }

    public int getRegionID() {
        return regionID;
    }

    public void setRegionID(int regionID) {
        this.regionID = regionID;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return region;
    }
}
