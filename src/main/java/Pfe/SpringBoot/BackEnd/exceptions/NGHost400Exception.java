package Pfe.SpringBoot.BackEnd.exceptions;

public class NGHost400Exception extends NGHostException {

    public NGHost400Exception(final String message) {
        super(message);
    }

    public NGHost400Exception(final Throwable cause, final String message) {
        super(message, cause);
    }
}
