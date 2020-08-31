package otus.spring.albot.project.dto;

import lombok.Data;
import otus.spring.albot.project.entity.Product;

@Data
public class ProductDto {
    private long id;
    private String name;
    private String description;

    public static ProductDto fromDao(Product product) {
        if (product == null) {
            return null;
        }
        ProductDto dto = new ProductDto();
        dto.id = product.getId();
        dto.name = product.getName();
        dto.description = product.getDescription();
        return dto;
    }

    public static Product toDao(ProductDto dto) {
        Product product = new Product();
        product.setId(dto.id);
        product.setName(dto.name);
        product.setDescription(dto.description);
        return product;
    }
}
