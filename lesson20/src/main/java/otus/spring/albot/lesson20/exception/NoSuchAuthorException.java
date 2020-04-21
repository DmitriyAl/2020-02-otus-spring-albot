package otus.spring.albot.lesson20.exception;

public class NoSuchAuthorException extends ClientException {
    public NoSuchAuthorException(String message) {
        super(message);
    }
}
