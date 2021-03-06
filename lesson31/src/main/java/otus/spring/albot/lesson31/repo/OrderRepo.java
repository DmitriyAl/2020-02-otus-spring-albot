package otus.spring.albot.lesson31.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.spring.albot.lesson31.entity.Order;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    void deleteByIdIn(List<Long> orderIds);
}
