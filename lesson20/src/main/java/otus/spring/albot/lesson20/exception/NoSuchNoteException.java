package otus.spring.albot.lesson20.exception;

public class NoSuchNoteException extends ClientException {
    public NoSuchNoteException(String id) {
        super("There is no comment with such id: " + id);
    }
}
