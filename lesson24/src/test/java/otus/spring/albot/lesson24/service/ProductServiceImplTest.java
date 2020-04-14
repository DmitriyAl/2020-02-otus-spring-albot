package otus.spring.albot.lesson24.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import otus.spring.albot.lesson24.entity.Note;
import otus.spring.albot.lesson24.entity.Product;
import otus.spring.albot.lesson24.repo.ProductRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Autowired
    private ProductService productService;
    @MockBean
    private ProductRepo productRepo;

    @Test
    @WithMockUser
    void getAllProducts() {
        Product product = new Product();
        given(productRepo.findAll()).willReturn(Collections.singletonList(product));
        assertThat(productService.getAllProducts()).hasSize(1).contains(product);
    }

    @Test
    @WithMockUser
    void getProductById() {
        Product product = new Product();
        product.setId(1L);
        given(productRepo.findById(product.getId())).willReturn(Optional.of(product));
        assertThat(productService.getProductById(product.getId())).isEqualTo(product);
    }

    @Test
    @WithMockUser
    void addComment() {
        Product product = new Product();
        product.setId(1L);
        Note note = new Note();
        note.setComment("Comment");
        product.setNotes(new ArrayList<>(Collections.singletonList(note)));
        given(productRepo.findById(product.getId())).willReturn(Optional.of(product));
        given(productRepo.save(product)).willReturn(product);
        assertThatCode(() -> productService.addComment(product.getId(), note.getComment())).doesNotThrowAnyException();
    }

    @Test
    @WithMockUser
    void save() {
        Product product = new Product();
        given(productRepo.save(product)).willReturn(product);
        assertThat(productService.save(product)).isEqualTo(product);
    }

    @Test
    @WithMockUser
    void deleteProduct() {
        doNothing().when(productRepo).deleteById(1L);
        assertThatCode(() -> productService.deleteProduct(1L)).doesNotThrowAnyException();
    }
}
