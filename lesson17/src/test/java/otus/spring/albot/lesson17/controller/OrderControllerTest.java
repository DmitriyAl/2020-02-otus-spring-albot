package otus.spring.albot.lesson17.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import otus.spring.albot.lesson17.dto.OrderDto;
import otus.spring.albot.lesson17.entity.Order;
import otus.spring.albot.lesson17.exception.NoSuchOrderException;
import otus.spring.albot.lesson17.service.OrderService;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private OrderService orderService;

    @Test
    void getOrders() throws Exception {
        List<OrderDto> dtos = Collections.singletonList(OrderDto.fromDao(new Order(1L, Collections.emptyList())));
        given(orderService.getAllOrders()).willReturn(dtos);
        MvcResult mvcResult = this.mvc.perform(get("/orders")).andExpect(status().isOk()).andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        assertThat(objectMapper.writeValueAsString(dtos)).isEqualToIgnoringWhitespace(result);
    }

    @Test
    void getProductsByValidOrderId() throws Exception {
        OrderDto dto = OrderDto.fromDao(new Order(1L, Collections.emptyList()));
        given(orderService.getOrderById(dto.getId())).willReturn(dto);
        MvcResult mvcResult = this.mvc.perform(get("/orders/{id}", 1))
                .andExpect(status().isOk()).andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        assertThat(objectMapper.writeValueAsString(dto)).isEqualToIgnoringWhitespace(result);
    }

    @Test
    void getProductsByInvalidOrderId() throws Exception {
        given(orderService.getOrderById(1L)).willThrow(new NoSuchOrderException(1L));
        MvcResult mvcResult = this.mvc.perform(get("/orders/{id}", 1))
                .andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void createNewOrder() throws Exception {
        OrderDto inputDto = OrderDto.fromDao(new Order(0L, Collections.emptyList()));
        OrderDto outputDto = OrderDto.fromDao(new Order(1L, Collections.emptyList()));
        given(orderService.createNewOrder(inputDto)).willReturn(outputDto);
        MvcResult mvcResult = this.mvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertThat(objectMapper.writeValueAsString(outputDto)).isEqualTo(response);
    }

    @Test
    void removeExistedOrder() throws Exception {
        this.mvc.perform(delete("/orders")
                .param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void removeNotExistedOrder() throws Exception {
        willThrow(new NoSuchOrderException(1L)).given(orderService).removeOrder(1L);
        this.mvc.perform(delete("/orders")
                .param("id", "1"))
                .andExpect(status().isNotFound());
    }
}
