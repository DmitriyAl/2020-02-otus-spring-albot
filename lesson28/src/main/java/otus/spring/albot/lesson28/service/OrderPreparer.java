package otus.spring.albot.lesson28.service;

import otus.spring.albot.lesson28.model.Order;

public interface OrderPreparer {
    Order prepareOrder(String name);
}
