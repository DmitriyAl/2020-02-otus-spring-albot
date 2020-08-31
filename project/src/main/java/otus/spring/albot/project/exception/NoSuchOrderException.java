package otus.spring.albot.project.exception;

public class NoSuchOrderException extends NoSuchEntityException {
    public NoSuchOrderException(long id) {
        super(Code.NO_SUCH_ORDER, id);
    }
}
