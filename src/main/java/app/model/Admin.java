package app.model;

import java.time.LocalDate;

public class Admin extends User {

    private static Admin admin;

    private Admin(String username, String password, String firstName, String lastName, String email, String phone,
            LocalDate birthDate) {
        super(username, password, firstName, lastName, email, phone, birthDate);
        Database.getDB().getUsers().add(this);
    }

    public static Admin getAdmin(String username, String password, String firstName, String lastName, String email,
            String phone,
            LocalDate birthDate) {
        if (admin == null) {
            admin = new Admin(username, password, firstName, lastName, email, phone, birthDate);
        }
        return admin;
    }

    public static Admin getAdmin() {
        return admin;
    }

}
