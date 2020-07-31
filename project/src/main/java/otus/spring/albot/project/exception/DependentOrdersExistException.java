package otus.spring.albot.project.exception;

import static otus.spring.albot.project.exception.Code.DEPENDENT_ORDERS_EXIST;

public class DependentOrdersExistException extends ClientException {
    public DependentOrdersExistException(Object... ids) {
        super(DEPENDENT_ORDERS_EXIST, ids);
    }
}
