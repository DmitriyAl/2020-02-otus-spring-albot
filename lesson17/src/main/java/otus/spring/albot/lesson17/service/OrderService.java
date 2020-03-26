package otus.spring.albot.lesson17.service;

import otus.spring.albot.lesson17.dto.OrderDto;
import otus.spring.albot.lesson17.dto.ProductDto;
import otus.spring.albot.lesson17.exception.NoSuchOrderException;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();

    OrderDto getOrderById(Long id) throws NoSuchOrderException;

    List<ProductDto> productsByOrderId(long id) throws NoSuchOrderException;

    void removeOrder(long id) throws NoSuchOrderException;

    OrderDto createNewOrder(List<Long> productIds);
}
