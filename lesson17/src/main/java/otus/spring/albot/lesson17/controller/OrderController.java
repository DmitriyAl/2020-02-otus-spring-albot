package otus.spring.albot.lesson17.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import otus.spring.albot.lesson17.dto.OrderDto;
import otus.spring.albot.lesson17.dto.ProductDto;
import otus.spring.albot.lesson17.exception.NoSuchOrderException;
import otus.spring.albot.lesson17.service.OrderService;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping(value = "orders")
    public List<OrderDto> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping(value = "orders/{id}")
    public List<ProductDto> getProductsByOrderId(@PathVariable("id") Long id) {
        try {
            return orderService.productsByOrderId(id);
        } catch (NoSuchOrderException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getCode().getCode());
        }
    }

    @PostMapping(value = "orders")
    public OrderDto createNewOrder(@RequestBody List<Long> productIds) {
        return orderService.createNewOrder(productIds);
    }

    @DeleteMapping(value = "orders")
    public void removeOrder(@RequestParam("id") long id) {
        try {
            orderService.removeOrder(id);
        } catch (NoSuchOrderException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getCode().getCode());
        }
    }
}
