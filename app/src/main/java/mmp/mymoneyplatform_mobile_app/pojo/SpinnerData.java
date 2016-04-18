package mmp.mymoneyplatform_mobile_app.pojo;

/**
 * Created by Nacho on 18/04/2016.
 */
public class SpinnerData {

    private String name;
    private String id;

    public SpinnerData(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}
