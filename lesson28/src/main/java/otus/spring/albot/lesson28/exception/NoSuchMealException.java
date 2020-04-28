package otus.spring.albot.lesson28.exception;

public class NoSuchMealException extends RuntimeException {
    public NoSuchMealException(String message) {
        super(message);
    }
}
