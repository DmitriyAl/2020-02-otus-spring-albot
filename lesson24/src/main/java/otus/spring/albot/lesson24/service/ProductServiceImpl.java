package otus.spring.albot.lesson24.service;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson24.entity.Note;
import otus.spring.albot.lesson24.entity.Product;
import otus.spring.albot.lesson24.repo.ProductRepo;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepo productRepo;

    @Override
    @Transactional
    @PreAuthorize("isFullyAuthenticated()")
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    @PreAuthorize("isFullyAuthenticated()")
    public Product getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    @PreAuthorize("isFullyAuthenticated()")
    public void addComment(Long productId, String comment) {
        productRepo.findById(productId).ifPresent(p -> addComment(p, comment));
    }

    private void addComment(Product product, String comment) {
        Note note = new Note();
        note.setComment(comment);
        note.setProduct(product);
        product.getNotes().add(note);
        productRepo.save(product);
    }

    @Override
    @Transactional
    @PreAuthorize("isFullyAuthenticated()")
    public Product save(Product product) {
        return productRepo.save(product);
    }

    @Override
    @Transactional
    @PreAuthorize("isFullyAuthenticated()")
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}
