package otus.spring.albot.lesson31.service;

import otus.spring.albot.lesson31.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductById(Long id);

    void addComment(Long productId, String comment);

    Product save(Product product);

    void deleteProduct(Long id);
}
