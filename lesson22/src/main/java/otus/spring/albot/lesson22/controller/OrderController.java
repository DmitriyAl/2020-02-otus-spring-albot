package otus.spring.albot.lesson22.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import otus.spring.albot.lesson22.entity.Order;
import otus.spring.albot.lesson22.service.OrderService;

import java.util.List;

@Controller
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @GetMapping(value = {"", "orders"})
    public String welcomePage(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }

    @GetMapping(value = "orders/{id}")
    public String order(@PathVariable("id") Long id, Model model) {
        Order orderById = orderService.getOrderById(id);
        model.addAttribute("order", orderById);
        return "order";
    }

    @PostMapping(value = "orders/remove")
    public String newOrder(@RequestParam(value = "orderToDelete", required = false) List<Long> orderIds) {
        if (orderIds != null) {
            orderService.removeOrders(orderIds);
        }
        return "redirect:/orders";
    }

    @GetMapping(value = "order/remove")
    public String deleteOrder(@RequestParam("id") Long id) {
        orderService.removeOrder(id);
        return "redirect:/orders";
    }

    @PostMapping(value = "orders/addToOrder")
    public String addToOrder(@RequestParam(value = "productId", required = false) List<Long> productIds, @RequestParam(value = "orderId", required = false) Long orderId, Model model) {
        Order order = orderService.modifyOrder(orderId, productIds);
        model.addAttribute("order", order);
        return "order";
    }

    @PostMapping(value = "order/modify")
    public String modifyOrder(@RequestParam("orderId") Long orderId, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("orderId", orderId);
        return "redirect:/products";
    }
}
