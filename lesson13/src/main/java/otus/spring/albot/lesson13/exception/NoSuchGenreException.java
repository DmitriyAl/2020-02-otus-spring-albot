package otus.spring.albot.lesson13.exception;

public class NoSuchGenreException extends Exception {
    public NoSuchGenreException(String id) {
        super(String.format("No genre with id: %s", id));
    }
}
