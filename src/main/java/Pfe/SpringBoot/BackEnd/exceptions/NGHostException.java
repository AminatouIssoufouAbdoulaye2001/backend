package Pfe.SpringBoot.BackEnd.exceptions;

public abstract class NGHostException  extends Exception{
    private static final long serialVersionUID = 1L;

    public NGHostException(final String message) {
        super(message);
    }

    public NGHostException( final String message, final Throwable cause) {
        super(message, cause);
    }
}
