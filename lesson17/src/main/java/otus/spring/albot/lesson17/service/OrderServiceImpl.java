package otus.spring.albot.lesson17.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import otus.spring.albot.lesson17.dto.OrderDto;
import otus.spring.albot.lesson17.repo.OrderRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepo orderRepo;

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepo.findAll().stream().map(OrderDto::fromDao).collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Long id) {
        return OrderDto.fromDao(orderRepo.findById(id).orElse(null));
    }
}