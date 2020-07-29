package otus.spring.albot.lesson31.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson31.entity.Note;
import otus.spring.albot.lesson31.entity.Product;
import otus.spring.albot.lesson31.repo.ProductRepo;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepo productRepo;

    @Override
    @Transactional
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
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
    public Product save(Product product) {
        return productRepo.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}
