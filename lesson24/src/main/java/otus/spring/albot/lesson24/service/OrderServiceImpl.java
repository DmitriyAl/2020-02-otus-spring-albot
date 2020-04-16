package otus.spring.albot.lesson24.service;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson24.entity.Order;
import otus.spring.albot.lesson24.entity.Product;
import otus.spring.albot.lesson24.repo.OrderRepo;
import otus.spring.albot.lesson24.repo.ProductRepo;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepo orderRepo;
    private ProductRepo productRepo;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Order getOrderById(Long id) {
        return orderRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public Order modifyOrder(Long orderId, List<Long> productIds) {
        List<Product> products;
        if (productIds != null && !productIds.isEmpty()) {
            products = productRepo.findAllById(productIds);
        } else {
            products = Collections.emptyList();
        }
        Order order = getOrder(orderId, products);
        return orderRepo.save(order);

    }

    private Order getOrder(Long orderId, List<Product> products) {
        Order order;
        if (orderId == null) {
            order = new Order();
        } else {
            order = orderRepo.findById(orderId).orElse(new Order());
        }
        order.setProducts(products);
        return order;
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void removeOrders(List<Long> orderIds) {
        orderRepo.deleteByIdIn(orderIds);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void removeOrder(Long id) {
        orderRepo.deleteById(id);
    }
}
