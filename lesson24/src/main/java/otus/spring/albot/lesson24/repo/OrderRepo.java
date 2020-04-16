package otus.spring.albot.lesson24.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.spring.albot.lesson24.entity.Order;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    void deleteByIdIn(List<Long> orderIds);
}
