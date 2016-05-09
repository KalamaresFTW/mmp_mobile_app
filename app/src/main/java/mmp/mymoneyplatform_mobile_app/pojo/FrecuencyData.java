package mmp.mymoneyplatform_mobile_app.pojo;

@SuppressWarnings("unused")
public class FrecuencyData {

    private int frecuencyID;
    private String frecuencyName;

    public FrecuencyData(int frecuencyID, String frecuencyName) {
        this.frecuencyID = frecuencyID;
        this.frecuencyName = frecuencyName;
    }

    public int getFrecuencyID() {
        return frecuencyID;
    }

    public void setFrecuencyID(int frecuencyID) {
        this.frecuencyID = frecuencyID;
    }

    public String getFrecuencyName() {
        return frecuencyName;
    }

    public void setFrecuencyName(String frecuencyName) {
        this.frecuencyName = frecuencyName;
    }

    @Override
    public String toString() {
        return frecuencyName;
    }
}
