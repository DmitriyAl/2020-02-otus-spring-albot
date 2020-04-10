package otus.spring.albot.lesson13.exception;

public class NoSuchBookException extends Exception {
    public NoSuchBookException(String message) {
        super(message);
    }
}
