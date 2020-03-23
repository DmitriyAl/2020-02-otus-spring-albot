package otus.spring.albot.lesson17.service;

import otus.spring.albot.lesson17.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long id);
}