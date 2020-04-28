package otus.spring.albot.lesson28.service;

import org.springframework.stereotype.Service;
import otus.spring.albot.lesson28.model.Food;
import otus.spring.albot.lesson28.model.Order;

import java.util.concurrent.TimeUnit;

@Service
public class KitchenServiceImpl implements KitchenService {
    @Override
    public Food prepareMeal(Order order) {
        Food food = new Food();
        food.setName(order.getName());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("Kitchen cooked '%s'", order.getName()));
        return food;
    }
}
