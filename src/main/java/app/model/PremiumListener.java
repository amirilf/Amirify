package app.model;

import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import app.service.auth.CurrentUser;

public class PremiumListener extends Listener {

    private int leftDays;
    private ScheduledExecutorService scheduler;

    public PremiumListener(String username, String password, String firstName, String lastName, String email,
            String phone,
            LocalDate birthDate, int leftDays) {
        super(username, password, firstName, lastName, email, phone, birthDate);
        this.leftDays = leftDays;

        // start scheduler for left days
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this::decrementLeftDays, 0, 1, TimeUnit.DAYS);
    }

    // GETTER
    public int getLeftDays() {
        return this.leftDays;
    }

    // SETTER
    public void setLeftDays(int leftDays) {
        this.leftDays = leftDays;
    }

    // OTHER
    private void decrementLeftDays() {
        if (this.leftDays > 1)
            this.leftDays--;
        else {
            // time's up
            BasicListener basic = new BasicListener(this.getUsername(), this.getPassword(), this.getFirstName(),
                    this.getLastName(), this.getEmail(), this.getPhone(), this.getBirthDate());
            basic.setUserID(this.getUserID());
            basic.setJoinDate(this.getJoinDate());
            basic.setFavoriteGenres(this.getFavoriteGenres());
            basic.setFollowings(this.getFollowings());
            basic.setLikedAudios(this.getLikedAudios());
            basic.setPlayedTimes(this.getPlayedTimes());

            // end task
            scheduler.shutdown();

            // replace in db
            Database.getDB().getUsers().remove(this);
            Database.getDB().getUsers().add(basic);

            // if user is logged in should be changed to basic
            if (CurrentUser.getUser() == this)
                CurrentUser.login(basic);

        }

    }
}
