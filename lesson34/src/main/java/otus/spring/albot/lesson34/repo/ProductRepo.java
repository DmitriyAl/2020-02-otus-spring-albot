package otus.spring.albot.lesson34.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.spring.albot.lesson34.entity.Product;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
}
