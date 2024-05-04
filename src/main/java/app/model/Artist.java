package app.model;

import java.time.LocalDate;
import java.util.ArrayList;

abstract public class Artist extends User {

    private double income;
    private ArrayList<User> followers = new ArrayList<User>();
    private String bio;

    public Artist(String username, String password, String firstName, String lastName, String email, String phone,
            LocalDate birthDate,
            String bio) {
        super(username, password, firstName, lastName, email, phone, birthDate);
        this.income = 0;
        this.bio = bio;
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

    // SETTER
    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setIncome(double income) {
        this.income = income;
    }

}
