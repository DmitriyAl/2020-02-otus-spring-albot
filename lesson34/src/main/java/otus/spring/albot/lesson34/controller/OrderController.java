package otus.spring.albot.lesson34.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import otus.spring.albot.lesson34.entity.Order;
import otus.spring.albot.lesson34.service.OrderService;
import otus.spring.albot.lesson34.service.SleepService;

import java.util.List;

@Controller
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final SleepService sleepService;

    @GetMapping(value = "orders")
    @HystrixCommand
    public String welcomePage(Model model) {
        sleepService.sleep();
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }

    @GetMapping(value = "orders/{id}")
    @HystrixCommand
    public String order(@PathVariable("id") Long id, Model model) {
        sleepService.sleep();
        Order orderById = orderService.getOrderById(id);
        model.addAttribute("order", orderById);
        return "order";
    }

    @PostMapping(value = "orders/remove")
    @HystrixCommand
    public String newOrder(@RequestParam(value = "orderToDelete", required = false) List<Long> orderIds) {
        sleepService.sleep();
        if (orderIds != null) {
            orderService.removeOrders(orderIds);
        }
        return "redirect:/orders";
    }

    @GetMapping(value = "order/remove")
    @HystrixCommand
    public String deleteOrder(@RequestParam("id") Long id) {
        sleepService.sleep();
        orderService.removeOrder(id);
        return "redirect:/orders";
    }

    @PostMapping(value = "orders/addToOrder")
    @HystrixCommand
    public String addToOrder(@RequestParam(value = "productId", required = false) List<Long> productIds, @RequestParam(value = "orderId", required = false) Long orderId, Model model) {
        sleepService.sleep();
        Order order = orderService.modifyOrder(orderId, productIds);
        model.addAttribute("order", order);
        return "order";
    }

    @PostMapping(value = "order/modify")
    @HystrixCommand
    public String modifyOrder(@RequestParam("orderId") Long orderId, RedirectAttributes redirectAttributes) {
        sleepService.sleep();
        redirectAttributes.addFlashAttribute("orderId", orderId);
        return "redirect:/products";
    }
}
