package otus.spring.albot.lesson17.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson17.dto.ProductDto;
import otus.spring.albot.lesson17.repo.ProductRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepo productRepo;

    @Transactional
    @Override
    public List<ProductDto> getAllProducts() {
        return productRepo.findAll().stream().map(ProductDto::fromDao).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        return ProductDto.fromDao(productRepo.findById(id).orElse(null));
    }
}