package otus.spring.albot.lesson24.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import otus.spring.albot.lesson24.entity.Order;
import otus.spring.albot.lesson24.entity.Product;
import otus.spring.albot.lesson24.repo.OrderRepo;
import otus.spring.albot.lesson24.repo.ProductRepo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;
    @MockBean
    private OrderRepo orderRepo;
    @MockBean
    private ProductRepo productRepo;

    @Test
    @WithMockUser(username = "admin", authorities = "ROLE_ADMIN")
    void getAllOrdersAdmin() {
        Order order = new Order();
        given(orderRepo.findAll()).willReturn(Collections.singletonList(order));
        assertThat(orderService.getAllOrders()).hasSize(1).contains(order);
    }

    @Test
    @WithMockUser(username = "user", authorities = "ROLE_USER")
    void getAllOrdersUser() {
        Order order = new Order();
        given(orderRepo.findAll()).willReturn(Collections.singletonList(order));
        assertThatThrownBy(() -> orderService.getAllOrders()).isInstanceOf(AccessDeniedException.class);
    }

    @Test
    @WithMockUser(username = "admin", authorities = "ROLE_ADMIN")
    void getOrderByIdAdmin() {
        Order order = new Order();
        given(orderRepo.findById(1L)).willReturn(Optional.of(order));
        assertThat(orderService.getOrderById(1L)).isEqualTo(order);
    }

    @Test
    @WithMockUser(username = "user", authorities = "ROLE_USER")
    void getOrderByIdUser() {
        Order order = new Order();
        given(orderRepo.findById(1L)).willReturn(Optional.of(order));
        assertThatThrownBy(() -> orderService.getOrderById(1L)).isInstanceOf(AccessDeniedException.class);
    }

    @Test
    @WithMockUser(username = "admin", authorities = "ROLE_ADMIN")
    void modifyOrderAdmin() {
        Order order = new Order();
        order.setId(1L);
        Product product1 = new Product();
        Product product2 = new Product();
        product1.setId(1L);
        product2.setId(2L);
        order.setProducts(Arrays.asList(product1, product2));
        given(productRepo.findAllById(Arrays.asList(product1.getId(), product2.getId())))
                .willReturn(Arrays.asList(product1, product2));
        given(orderRepo.save(order)).willReturn(order);
        given(orderRepo.findById(order.getId())).willReturn(Optional.of(order));
        assertThat(orderService.modifyOrder(order.getId(), Arrays.asList(product1.getId(), product2.getId())))
                .isEqualTo(order);
    }

    @Test
    @WithMockUser(username = "user", authorities = "ROLE_USER")
    void modifyOrderUser() {
        Order order = new Order();
        order.setId(1L);
        Product product1 = new Product();
        Product product2 = new Product();
        product1.setId(1L);
        product2.setId(2L);
        order.setProducts(Arrays.asList(product1, product2));
        given(productRepo.findAllById(Arrays.asList(product1.getId(), product2.getId())))
                .willReturn(Arrays.asList(product1, product2));
        given(orderRepo.save(order)).willReturn(order);
        given(orderRepo.findById(order.getId())).willReturn(Optional.of(order));
        assertThatThrownBy(() -> orderService.modifyOrder(order.getId(), Arrays.asList(product1.getId(), product2.getId())))
                .isInstanceOf(AccessDeniedException.class);
    }

    @Test
    @WithMockUser(username = "admin", authorities = "ROLE_ADMIN")
    void removeOrdersAdmin() {
        doNothing().when(orderRepo).deleteByIdIn(Arrays.asList(1L, 2L));
        assertThatCode(() -> orderService.removeOrders(Arrays.asList(1L, 2L))).doesNotThrowAnyException();
    }

    @Test
    @WithMockUser(username = "user", authorities = "ROLE_USER")
    void removeOrdersUser() {
        doNothing().when(orderRepo).deleteByIdIn(Arrays.asList(1L, 2L));
        assertThatThrownBy(() -> orderService.removeOrders((Arrays.asList(1L, 2L))))
                .isInstanceOf(AccessDeniedException.class);
    }

    @Test
    @WithMockUser(username = "admin", authorities = "ROLE_ADMIN")
    void removeOrderAdmin() {
        doNothing().when(orderRepo).deleteById(1L);
        assertThatCode(() -> orderService.removeOrder(1L)).doesNotThrowAnyException();
    }

    @Test
    @WithMockUser(username = "user", authorities = "ROLE_USER")
    void removeOrderUser() {
        doNothing().when(orderRepo).deleteById(1L);
        assertThatThrownBy(() -> orderService.removeOrder(1L)).isInstanceOf(AccessDeniedException.class);
    }
}
