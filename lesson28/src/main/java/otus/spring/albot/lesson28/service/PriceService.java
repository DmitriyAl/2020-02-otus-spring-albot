package otus.spring.albot.lesson28.service;

import otus.spring.albot.lesson28.model.Order;

import java.util.List;

public interface PriceService {
    List<Order> definePrice(List<Order> orders);
}
