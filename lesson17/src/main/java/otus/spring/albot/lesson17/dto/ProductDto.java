package otus.spring.albot.lesson17.dto;

import lombok.Data;
import otus.spring.albot.lesson17.entity.Product;

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
}
