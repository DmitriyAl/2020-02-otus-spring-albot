package otus.spring.albot.lesson17.controller;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import otus.spring.albot.lesson17.dto.OrderDto;
import otus.spring.albot.lesson17.service.OrderService;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @GetMapping(value = {"orders"})
    public List<OrderDto> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping(value = "orders/{id}")
    public String order(@PathVariable("id") Long id, Model model) {
        OrderDto orderById = orderService.getOrderById(id);
        model.addAttribute("order", orderById);
        return "pages/order";
    }
}
