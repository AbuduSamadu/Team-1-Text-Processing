package team1.testprocessing.Exceptions;

public class FailedToAddItemException  extends  RuntimeException {
    public FailedToAddItemException(String message) {
        super(message);
    }
}
