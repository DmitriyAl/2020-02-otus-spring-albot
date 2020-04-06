package otus.spring.albot.lesson17.exception;

public class NoSuchProductException extends NoSuchEntityException {
    public NoSuchProductException(long id) {
        super(Code.NO_SUCH_PRODUCT, id);
    }
}
