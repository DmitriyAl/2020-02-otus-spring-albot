package otus.spring.albot.lesson28.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order implements Serializable {
    private static final long serialVersionUID = 4704424403044759591L;
    private String name;
    private OrderType type;
    private float price;
}
