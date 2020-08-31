package otus.spring.albot.project.service;

import otus.spring.albot.project.dto.ProductDto;
import otus.spring.albot.project.exception.ClientException;
import otus.spring.albot.project.exception.NoSuchProductException;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();

    ProductDto getProductById(long id) throws NoSuchProductException;

    void removeProduct(long id) throws ClientException;

    ProductDto updateProduct(ProductDto product);
}
