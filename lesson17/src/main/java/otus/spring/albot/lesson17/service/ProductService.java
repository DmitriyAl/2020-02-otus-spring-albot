package otus.spring.albot.lesson17.service;

import otus.spring.albot.lesson17.dto.ProductDto;
import otus.spring.albot.lesson17.exception.ClientException;
import otus.spring.albot.lesson17.exception.NoSuchProductException;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();

    ProductDto getProductById(long id) throws NoSuchProductException;

    void removeProduct(long id) throws ClientException;

    ProductDto updateProduct(ProductDto product);
}
