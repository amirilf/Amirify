package app.model;

import java.time.LocalDate;
import java.util.ArrayList;

abstract public class Artist extends User {

    private double income;
    private ArrayList<User> followers = new ArrayList<User>();
    private String bio = "";
    private boolean isVerified = false;
    private String backGround;

    public Artist(String username, String password, String firstName, String lastName, String email, String phone,
            LocalDate birthDate, String bg, String profile) {
        super(username, password, firstName, lastName, email, phone, birthDate);
        this.income = 0;
        this.backGround = bg;
        this.setProfile(profile);
    }

    // GETTER
    public double getIncome() {
        return this.income;
    }

    public ArrayList<User> getFollowers() {
        return this.followers;
    }

    public String getBio() {
        return this.bio;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public String getBackGround() {
        return backGround;
    }

    // SETTER
    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public void setBackGround(String backGround) {
        this.backGround = backGround;
    }

}
