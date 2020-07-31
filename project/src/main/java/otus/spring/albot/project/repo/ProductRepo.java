package otus.spring.albot.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.spring.albot.project.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
