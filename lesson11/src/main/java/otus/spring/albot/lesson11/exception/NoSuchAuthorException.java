package otus.spring.albot.lesson11.exception;

public class NoSuchAuthorException extends Exception {
    public NoSuchAuthorException(String message) {
        super(message);
    }
}