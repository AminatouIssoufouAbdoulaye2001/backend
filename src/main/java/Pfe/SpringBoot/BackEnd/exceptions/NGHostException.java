package Pfe.SpringBoot.BackEnd.exceptions;

public abstract class NGHostException  extends Exception{
    public NGHostException(final String message) {
        super(message);
    }

    public NGHostException( final String message, final Throwable cause) {
        super(message, cause);
    }
}
