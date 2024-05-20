package app.exceptions;

public class FailedLoginException extends Exception {
    public FailedLoginException(String msg) {
        super(msg);
    }
}
