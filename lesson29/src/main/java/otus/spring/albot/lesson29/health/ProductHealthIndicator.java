package otus.spring.albot.lesson29.health;

import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import otus.spring.albot.lesson29.entity.Product;
import otus.spring.albot.lesson29.service.ProductService;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductHealthIndicator implements HealthIndicator {
    private final ProductService productService;
    private static final String key = "product amount";

    @Override
    public Health health() {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            return Health.down().withDetail(key, 0).build();
        }
        return Health.up().withDetail(key, products.size()).build();
    }
}
