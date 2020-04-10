package otus.spring.albot.lesson22.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import otus.spring.albot.lesson22.entity.Order;
import otus.spring.albot.lesson22.service.OrderService;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderService orderService;

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = {"ROLE_ADMIN"})
    void deleteOrderByAdmin() throws Exception {
        doNothing().when(orderService).removeOrder(0L);
        mockMvc.perform(get("/order/remove").param("id", "0")).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "user", password = "user", authorities = {"ROLE_USER"})
    void deleteOrderByUser() throws Exception {
        doNothing().when(orderService).removeOrder(0L);
        mockMvc.perform(get("/order/remove").param("id", "0")).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "user", password = "user", authorities = {"ROLE_USER"})
    void openOrdersByUser() throws Exception {
        mockMvc.perform(get("/orders")).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = {"ROLE_ADMIN"})
    void openOrdersByAdmin() throws Exception {
        given(orderService.getAllOrders()).willReturn(Collections.emptyList());
        mockMvc.perform(get("/orders")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = {"ROLE_ADMIN"})
    void openOrderByAdmin() throws Exception {
        given(orderService.getOrderById(1L)).willReturn(new Order());
        mockMvc.perform(get("/orders/1")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = {"ROLE_ADMIN"})
    void modifyOrderByAdmin() throws Exception {
        given(orderService.modifyOrder(1L, Arrays.asList(1L, 2L))).willReturn(new Order());
        mockMvc.perform(post("/orders/addToOrder")
                .param("productId", "1")
                .param("productId", "2")
                .param("orderId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "user", authorities = {"ROLE_USER"})
    void modifyOrderByUser() throws Exception {
        given(orderService.modifyOrder(1L, Arrays.asList(1L, 2L))).willReturn(new Order());
        mockMvc.perform(post("/orders/addToOrder")
                .param("productId", "1")
                .param("productId", "2")
                .param("orderId", "1"))
                .andExpect(status().is3xxRedirection());
    }
}
