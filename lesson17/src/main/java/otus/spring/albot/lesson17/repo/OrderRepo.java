package otus.spring.albot.lesson17.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.spring.albot.lesson17.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {
}