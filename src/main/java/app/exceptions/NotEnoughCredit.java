package app.exceptions;

public class NotEnoughCredit extends Exception {

    public NotEnoughCredit() {
        super("There is no enough credit to buy the package!");
    }

    public NotEnoughCredit(String msg) {
        super(msg);
    }
}
