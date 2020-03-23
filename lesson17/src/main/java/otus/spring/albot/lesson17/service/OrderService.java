package otus.spring.albot.lesson17.service;

import otus.spring.albot.lesson17.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();
    OrderDto getOrderById(Long id);
}