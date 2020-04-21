package otus.spring.albot.lesson20.exception;

public class GetNotesException extends ClientException {
    public GetNotesException() {
        super("Specify 'bookId' or 'bookName' parameter");
    }
}
