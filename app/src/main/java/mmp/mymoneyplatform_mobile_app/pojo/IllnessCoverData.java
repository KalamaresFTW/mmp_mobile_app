package mmp.mymoneyplatform_mobile_app.pojo;

public class IllnessCoverData {

    private int illnesID;
    private String illnessName;

    public IllnessCoverData(int illnesID, String illnessName) {
        this.illnesID = illnesID;
        this.illnessName = illnessName;
    }

    public int getIllnesID() {
        return illnesID;
    }

    public void setIllnesID(int illnesID) {
        this.illnesID = illnesID;
    }

    public String getIllnessName() {
        return illnessName;
    }

    public void setIllnessName(String illnessName) {
        this.illnessName = illnessName;
    }

    @Override
    public String toString() {
        return this.illnessName;
    }
}
