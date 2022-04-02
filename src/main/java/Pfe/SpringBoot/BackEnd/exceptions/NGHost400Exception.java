package Pfe.SpringBoot.BackEnd.exceptions;

public class NGHost400Exception extends NGHostException {
    private static final long serialVersionUID = -2944221019260698225L;

    public NGHost400Exception(final String message) {
        super(message);
    }

    public NGHost400Exception(final Throwable cause, final String message) {
        super(message, cause);
    }
}
