package app.exceptions;

public class FreeAccountLimitException extends Exception {

    public FreeAccountLimitException() {
        super("Listener is limited due to its premium status!");
    }

    public FreeAccountLimitException(String msg) {
        super(msg);
    }
}
