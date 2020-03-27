package otus.spring.albot.lesson17.dto;

import lombok.Data;
import otus.spring.albot.lesson17.entity.Order;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderDto {
    private long id;
    private List<ProductDto> products;

    public static OrderDto fromDao(Order order) {
        if (order == null) {
            return null;
        }
        OrderDto dto = new OrderDto();
        dto.id = order.getId();
        dto.products = order.getProducts().stream().map(ProductDto::fromDao).collect(Collectors.toList());
        return dto;
    }

    public static Order toDao(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.id);
        order.setProducts(orderDto.products.stream().map(ProductDto::toDao).collect(Collectors.toList()));
        return order;
    }
}
