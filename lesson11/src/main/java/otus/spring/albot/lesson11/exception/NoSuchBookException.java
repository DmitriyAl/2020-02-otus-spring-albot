package otus.spring.albot.lesson11.exception;

public class NoSuchBookException extends Exception {
    public NoSuchBookException(String message) {
        super(message);
    }
}