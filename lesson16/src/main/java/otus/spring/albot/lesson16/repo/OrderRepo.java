package otus.spring.albot.lesson16.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.spring.albot.lesson16.entity.Order;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    void deleteByIdIn(List<Long> orderIds);
}
