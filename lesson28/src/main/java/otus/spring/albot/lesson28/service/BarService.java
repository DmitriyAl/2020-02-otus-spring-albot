package otus.spring.albot.lesson28.service;

import otus.spring.albot.lesson28.model.Food;
import otus.spring.albot.lesson28.model.Order;

public interface BarService {
    Food prepareDrink(Order order);
}
