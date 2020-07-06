package otus.spring.albot.lesson28.service;

import org.springframework.stereotype.Service;
import otus.spring.albot.lesson28.factory.OrderFactory;
import otus.spring.albot.lesson28.model.Order;

import java.util.concurrent.TimeUnit;

@Service
public class OrderPreparerImpl implements OrderPreparer {
    @Override
    public Order prepareOrder(String name) {
        Order order = OrderFactory.getByName(name);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("Order from '%s' was done", name));
        return order;
    }
}
