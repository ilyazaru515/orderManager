package pet.ordermanager.exception;

public class DBInvocationException extends RuntimeException {
    public DBInvocationException(String message, Throwable cause) {
        super(message, cause);
    }
}
