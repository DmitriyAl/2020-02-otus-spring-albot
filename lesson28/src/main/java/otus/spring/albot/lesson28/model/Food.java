package otus.spring.albot.lesson28.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Food implements Serializable {
    private static final long serialVersionUID = 832109594940826290L;
    private String name;
}
