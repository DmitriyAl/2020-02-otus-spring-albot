package otus.spring.albot.lesson17.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import otus.spring.albot.lesson17.dto.ProductDto;
import otus.spring.albot.lesson17.exception.ClientException;
import otus.spring.albot.lesson17.exception.DependentOrdersExistException;
import otus.spring.albot.lesson17.exception.NoSuchProductException;
import otus.spring.albot.lesson17.service.ProductService;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(value = "products")
    public List<ProductDto> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "products/{id}")
    public ProductDto product(@PathVariable("id") Long id) {
        try {
            return productService.getProductById(id);
        } catch (NoSuchProductException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getCode().getCode());
        }
    }

    @PutMapping(value = "products")
    public ProductDto updateProduct(@RequestBody ProductDto product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping(value = "products")
    public void removeProduct(@RequestParam("id") long id) {
        try {
            productService.removeProduct(id);
        } catch (DependentOrdersExistException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getCode().getCode());
        } catch (ClientException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getCode().getCode());
        }
    }
}
