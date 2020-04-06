package otus.spring.albot.lesson17.exception;

public class NoSuchEntityException extends ClientException {
    public NoSuchEntityException(Code code, long id) {
        super(code, id);
    }
}
