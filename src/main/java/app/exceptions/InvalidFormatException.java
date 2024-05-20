package app.exceptions;

public class InvalidFormatException extends Exception {

    public InvalidFormatException() {
        super("Invalid format found!");
    }

    public InvalidFormatException(String msg) {
        super(msg);
    }
}
