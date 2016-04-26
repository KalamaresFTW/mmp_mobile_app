package mmp.mymoneyplatform_mobile_app.pojo;

import java.io.Serializable;

/**
 * Created by K on 18/04/2016.
 */
public class UserData implements Serializable{

    //TODO: add paymentFrecuency and date of birth

    private String userSubscriptionID, region, profileImage, country, newUser, name, email;

    public UserData(String userSubscriptionID, String region, String profileImage, String country,
                    String newUser, String name, String email) {
        this.userSubscriptionID = userSubscriptionID;
        this.region = region;
        this.profileImage = profileImage;
        this.country = country;
        this.newUser = newUser;
        this.name = name;
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNewUser() {
        return newUser;
    }

    public void setNewUser(String newUser) {
        this.newUser = newUser;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getUserSubscriptionID() {
        return userSubscriptionID;
    }

    public void setUserSubscriptionID(String userSubscriptionID) {
        this.userSubscriptionID = userSubscriptionID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return true if all the fields (except email, which is always provided) are not null
     */
    public boolean isNull(){
        return (userSubscriptionID == null) && (region == null) && (profileImage == null)
                && (country == null) && (newUser == null) && (name == null);
    }

    @Override
    public String toString() {
        return "UserData{" +
                "country='" + country + '\'' +
                ", userSubscriptionID='" + userSubscriptionID + '\'' +
                ", region='" + region + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", newUser='" + newUser + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
