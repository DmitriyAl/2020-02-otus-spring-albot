package otus.spring.albot.lesson17.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import otus.spring.albot.lesson17.dto.ProductDto;
import otus.spring.albot.lesson17.entity.Product;
import otus.spring.albot.lesson17.exception.NoSuchProductException;
import otus.spring.albot.lesson17.repo.ProductRepo;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class ProductServiceImplTest {
    @Autowired
    private ProductService productService;
    @MockBean
    private ProductRepo productRepo;

    @Test
    void getAllProducts() {
        Product product = new Product(1L, "Test", "Test", null, null);
        given(productRepo.findAll()).willReturn(Collections.singletonList(product));
        assertThat(productService.getAllProducts()).hasSize(1).containsOnly(ProductDto.fromDao(product));
    }

    @Test
    void getProductById() throws NoSuchProductException {
        Product product = new Product(1L, "Test", "Test", null, null);
        given(productRepo.findById(1L)).willReturn(Optional.of(product));
        assertThat(productService.getProductById(1L)).isEqualTo(ProductDto.fromDao(product));
    }

    @Test
    void removeProduct() {
        given(productRepo.findById(1L)).willReturn(Optional.empty());
        assertThrows(NoSuchProductException.class, () -> productService.removeProduct(1L));
    }

    @Test
    void updateProduct() {
        Product initial = new Product(1L, "Test", "Test", null, null);
        Product updated = new Product(1L, "Test1", "Test1", null, null);
        given(productRepo.save(initial)).willReturn(updated);
        assertThat(productService.updateProduct(ProductDto.fromDao(initial))).isEqualTo(ProductDto.fromDao(updated));
    }
}
