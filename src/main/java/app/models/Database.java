package app.models;

import java.util.ArrayList;

public class Database {

    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Audio> audios = new ArrayList<Audio>();
    private ArrayList<Report> reports = new ArrayList<Report>();

    private static Database db;

    public static Database getDB() {
        if (db == null) {
            db = new Database();
        }
        return db;
    }

    // GETTER
    public ArrayList<Audio> getAudios() {
        return this.audios;
    }

    public ArrayList<Report> getReports() {
        return this.reports;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }
}