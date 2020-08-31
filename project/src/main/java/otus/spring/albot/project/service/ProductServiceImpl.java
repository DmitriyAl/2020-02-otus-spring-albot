package otus.spring.albot.project.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.project.dto.ProductDto;
import otus.spring.albot.project.entity.Order;
import otus.spring.albot.project.entity.Product;
import otus.spring.albot.project.exception.ClientException;
import otus.spring.albot.project.exception.DependentOrdersExistException;
import otus.spring.albot.project.exception.NoSuchProductException;
import otus.spring.albot.project.repo.ProductRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepo productRepo;

    @Override
    @Transactional
    public List<ProductDto> getAllProducts() {
        return productRepo.findAll().stream().map(ProductDto::fromDao).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductDto getProductById(long id) throws NoSuchProductException {
        return ProductDto.fromDao(productRepo.findById(id).orElseThrow(() -> new NoSuchProductException(id)));
    }

    @Override
    @Transactional
    public void removeProduct(long id) throws ClientException {
        Product product = productRepo.findById(id).orElseThrow(() -> new NoSuchProductException(id));
        if (product.getOrders().size() != 0) {
            throw new DependentOrdersExistException(product.getOrders().stream().map(Order::getId)
                    .collect(Collectors.toList()).toArray());
        }
        productRepo.delete(product);
    }

    @Override
    @Transactional
    public ProductDto updateProduct(ProductDto product) {
        return ProductDto.fromDao(productRepo.save(ProductDto.toDao(product)));
    }
}
