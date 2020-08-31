package otus.spring.albot.project.service;

import otus.spring.albot.project.dto.OrderDto;
import otus.spring.albot.project.exception.NoSuchOrderException;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();

    OrderDto getOrderById(Long id) throws NoSuchOrderException;

    void removeOrder(long id) throws NoSuchOrderException;

    OrderDto createNewOrder(OrderDto orderDto);
}
