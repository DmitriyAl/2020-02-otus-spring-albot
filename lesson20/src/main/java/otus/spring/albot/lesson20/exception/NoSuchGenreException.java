package otus.spring.albot.lesson20.exception;

public class NoSuchGenreException extends ClientException {
    public NoSuchGenreException(String id) {
        super(String.format("No genre with id: %s", id));
    }
}
