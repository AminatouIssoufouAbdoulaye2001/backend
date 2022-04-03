package Pfe.SpringBoot.BackEnd.exceptions;

public class NGHost401Exception extends NGHostException{
    private static final long serialVersionUID = -2944221019260698225L;

    public NGHost401Exception(final String message) {
        super(message);
    }

    public NGHost401Exception(final Throwable cause, final String message) {
        super(message, cause);
    }

}
