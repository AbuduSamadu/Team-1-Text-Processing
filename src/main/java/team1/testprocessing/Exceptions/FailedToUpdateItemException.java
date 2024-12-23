package team1.testprocessing.Exceptions;

public class FailedToUpdateItemException extends  RuntimeException {
    public FailedToUpdateItemException(String message) {
        super(message);
    }
}
