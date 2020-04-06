package otus.spring.albot.lesson17.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.spring.albot.lesson17.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
