package otus.spring.albot.lesson17.exception;

import static otus.spring.albot.lesson17.exception.Code.DEPENDENT_ORDERS_EXIST;

public class DependentOrdersExistException extends ClientException {
    public DependentOrdersExistException(Object... ids) {
        super(DEPENDENT_ORDERS_EXIST, ids);
    }
}
