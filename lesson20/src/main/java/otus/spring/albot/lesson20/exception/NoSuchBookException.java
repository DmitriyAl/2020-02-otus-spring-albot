package otus.spring.albot.lesson20.exception;

public class NoSuchBookException extends ClientException {
    public NoSuchBookException(String message) {
        super(message);
    }
}
