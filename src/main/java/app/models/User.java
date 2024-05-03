package app.models;

import java.time.LocalDate;
// import utility.RandomGenerator;

abstract public class User {

    private static int id = 1; // is used to generate unique usernames everytime
    private String username;
    private String userID;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private LocalDate joinDate;

    User(String username, String password, String firstName, String lastName, String email, String phone,
            LocalDate birthDate) {

        // actual length is (length + number of id digits) and for id < 100 it's 3
        // this.userID = RandomGenerator.randomIDGenerator(4, id++, 3);
        this.userID = String.valueOf(id++);
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.joinDate = LocalDate.now();
    }

    // GETTER
    public String getUsername() {
        return this.username;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public LocalDate getJoinDate() {
        return this.joinDate;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    // SETTER
    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
