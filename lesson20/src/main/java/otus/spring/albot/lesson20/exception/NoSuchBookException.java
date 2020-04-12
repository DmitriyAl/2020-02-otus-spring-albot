package otus.spring.albot.lesson20.exception;

public class NoSuchBookException extends Exception {
    public NoSuchBookException(String message) {
        super(message);
    }
}
