package otus.spring.albot.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import otus.spring.albot.project.dto.OrderDto;
import otus.spring.albot.project.entity.Order;
import otus.spring.albot.project.exception.NoSuchOrderException;
import otus.spring.albot.project.repo.OrderRepo;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;
    @MockBean
    private OrderRepo orderRepo;

    @Test
    void getAllOrders() {
        Order order = new Order(1L, Collections.emptyList());
        given(orderRepo.findAll()).willReturn(Collections.singletonList(order));
        assertThat(orderService.getAllOrders()).hasSize(1).containsOnly(OrderDto.fromDao(order));
    }

    @Test
    void getOrderById() throws Exception {
        Order order = new Order(1L, Collections.emptyList());
        given(orderRepo.findById(1L)).willReturn(Optional.of(order));
        assertThat(orderService.getOrderById(1L)).isEqualTo(OrderDto.fromDao(order));
    }

    @Test
    void removeOrder() throws NoSuchOrderException {
        given(orderRepo.findById(1L)).willReturn(Optional.empty());
        assertThrows(NoSuchOrderException.class, () -> orderService.removeOrder(1L));
    }

    @Test
    void createNewOrder() {
        Order inputOrder = new Order(0L, Collections.emptyList());
        Order outputOrder = new Order(1L, Collections.emptyList());
        given(orderRepo.save(inputOrder)).willReturn(outputOrder);
        assertThat(orderService.createNewOrder(OrderDto.fromDao(inputOrder))).isEqualTo(OrderDto.fromDao(outputOrder));
    }
}
