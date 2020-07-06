package otus.spring.albot.lesson28.service;

import org.springframework.stereotype.Service;
import otus.spring.albot.lesson28.model.Food;
import otus.spring.albot.lesson28.model.Order;

import java.util.concurrent.TimeUnit;

@Service
public class BarServiceImpl implements BarService {
    @Override
    public Food prepareDrink(Order order) {
        Food food = new Food();
        food.setName(order.getName());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Bar prepared a new drink: " + order.getName());
        return food;
    }
}
