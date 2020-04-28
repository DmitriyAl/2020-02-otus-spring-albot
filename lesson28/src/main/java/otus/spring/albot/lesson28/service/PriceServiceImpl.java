package otus.spring.albot.lesson28.service;

import org.springframework.stereotype.Service;
import otus.spring.albot.lesson28.model.Order;

import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {
    @Override
    public List<Order> definePrice(List<Order> orders) {
        System.out.println(String.format("Total price is: %f$", orders.stream().map(Order::getPrice).reduce(0f, Float::sum)));
        return orders;
    }
}
