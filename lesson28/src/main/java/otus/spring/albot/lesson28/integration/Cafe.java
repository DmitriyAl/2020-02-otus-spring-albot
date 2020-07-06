package otus.spring.albot.lesson28.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import otus.spring.albot.lesson28.model.Food;

import java.util.List;

@MessagingGateway
public interface Cafe {
    @Gateway(requestChannel = "handleOrder.input")
    List<Food> process(List<String> items);
}
