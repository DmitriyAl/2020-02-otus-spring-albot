package otus.spring.albot.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.spring.albot.project.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {
}