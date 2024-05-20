package app.exceptions;

public class UserNotFoundException extends FailedLoginException {
    public UserNotFoundException() {
        super("Username is not found!");
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
