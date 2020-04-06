package otus.spring.albot.lesson17.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson17.dto.OrderDto;
import otus.spring.albot.lesson17.entity.Order;
import otus.spring.albot.lesson17.exception.NoSuchOrderException;
import otus.spring.albot.lesson17.repo.OrderRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepo orderRepo;

    @Override
    @Transactional
    public List<OrderDto> getAllOrders() {
        return orderRepo.findAll().stream().map(OrderDto::fromDao).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderDto getOrderById(Long id) throws NoSuchOrderException {
        return OrderDto.fromDao(orderRepo.findById(id).orElseThrow(() -> new NoSuchOrderException(id)));
    }

    @Override
    @Transactional
    public void removeOrder(long id) throws NoSuchOrderException {
        Order order = orderRepo.findById(id).orElseThrow(() -> new NoSuchOrderException(id));
        orderRepo.delete(order);
    }

    @Override
    @Transactional
    public OrderDto createNewOrder(OrderDto orderDto) {
        return OrderDto.fromDao(orderRepo.save(OrderDto.toDao(orderDto)));
    }
}
