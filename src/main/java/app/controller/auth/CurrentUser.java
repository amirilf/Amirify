package app.controller.auth;

import app.model.Admin;
import app.model.BasicListener;
import app.model.Podcaster;
import app.model.PremiumListener;
import app.model.Singer;
import app.model.User;

/**
 * This class holds the logged in user.
 **/

public class CurrentUser {

    private static CurrentUser instance = null;
    private User currentUser = null;

    private CurrentUser() {
    };

    private static void Singleton() {
        if (instance == null)
            instance = new CurrentUser();
    }

    public static User getUser() {
        Singleton();
        return instance.currentUser;
    }

    public static UserType getUserType() {

        Singleton();

        if (instance.currentUser instanceof Singer)
            return UserType.SINGER;
        else if (instance.currentUser instanceof Podcaster)
            return UserType.PODCASTER;
        else if (instance.currentUser instanceof BasicListener)
            return UserType.BASIC;
        else if (instance.currentUser instanceof PremiumListener)
            return UserType.PREMIUM;
        else if (instance.currentUser instanceof Admin)
            return UserType.ADMIN;

        return null; // So currentUser is null (there is no logged in user)
    }

    public static boolean isThereUser() {
        Singleton();
        return instance.currentUser != null;
    }

    public static boolean logout() {
        if (instance.currentUser == null)
            return false;
        instance.currentUser = null;
        return true;
    }

    public static void login(User user) {
        Singleton();
        instance.currentUser = user;
    }
}
