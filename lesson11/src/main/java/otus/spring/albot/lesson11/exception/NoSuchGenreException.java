package otus.spring.albot.lesson11.exception;

public class NoSuchGenreException extends Exception {
    public NoSuchGenreException(String message) {
        super(message);
    }

    public NoSuchGenreException(long id) {
        super("There is no genre with id: " + id);
    }
}