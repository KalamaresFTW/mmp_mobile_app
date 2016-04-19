package mmp.mymoneyplatform_mobile_app.pojo;

import android.graphics.drawable.Icon;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by K on 11/04/2016.
 */
public class User implements Serializable {

    protected String name, email, password;
    protected ImageView profilePicture;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setProfilePicture(ImageView profilePicture) {
        this.profilePicture = profilePicture;
    }

    public ImageView getProfilePicture() {
        return profilePicture;
    }
}
