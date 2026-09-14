package logic;

public class LogicException extends RuntimeException {
    public LogicException(String message, Throwable exception) {
        super(message, exception);
    }

    public LogicException(String message) {
        super(message);
    }
}

