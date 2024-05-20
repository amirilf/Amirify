package app.exceptions;

public class WrongPasswordException extends FailedLoginException {

    public WrongPasswordException() {
        super("Password is wrong!");
    }

    public WrongPasswordException(String msg) {
        super(msg);
    }
}
