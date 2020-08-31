package otus.spring.albot.project.exception;

public class NoSuchEntityException extends ClientException {
    public NoSuchEntityException(Code code, long id) {
        super(code, id);
    }
}
