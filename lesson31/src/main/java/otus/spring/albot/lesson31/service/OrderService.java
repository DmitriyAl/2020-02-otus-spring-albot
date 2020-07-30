package otus.spring.albot.lesson31.service;

import otus.spring.albot.lesson31.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);

    Order modifyOrder(Long orderId, List<Long> productIds);

    void removeOrders(List<Long> orderIds);

    void removeOrder(Long id);
}
