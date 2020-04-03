package otus.spring.albot.lesson11.exception;

public class NoSuchNoteException extends Exception {
    public NoSuchNoteException(long id) {
        super("There is no comment with such id: " + id);
    }
}