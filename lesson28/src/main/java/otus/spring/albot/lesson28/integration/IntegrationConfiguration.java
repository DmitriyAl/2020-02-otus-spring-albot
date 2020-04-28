package otus.spring.albot.lesson28.integration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import otus.spring.albot.lesson28.model.Food;
import otus.spring.albot.lesson28.model.Order;
import otus.spring.albot.lesson28.model.OrderType;
import otus.spring.albot.lesson28.service.BarService;
import otus.spring.albot.lesson28.service.KitchenService;
import otus.spring.albot.lesson28.service.OrderPreparer;
import otus.spring.albot.lesson28.service.PriceService;

import java.util.List;

@Configuration
@EnableIntegration
@AllArgsConstructor
public class IntegrationConfiguration {
    private PriceService priceService;
    private OrderPreparer orderPreparer;
    private KitchenService kitchenService;
    private BarService barService;

    @Bean
    public IntegrationFlow handleOrder() {
        return f -> f
                .split()
                .<String, Order>transform(s -> orderPreparer.prepareOrder(s))
                .aggregate()
                .<List<Order>, List<Order>>transform(os -> priceService.definePrice(os))
                .split()
                .<Order, OrderType>route(Order::getType, mapping -> mapping
                        .subFlowMapping(OrderType.ALCOHOL, sf -> sf.<Order, Food>transform(o -> barService.prepareDrink(o)))
                        .subFlowMapping(OrderType.DRINK, sf -> sf.<Order, Food>transform(o -> barService.prepareDrink(o)))
                        .defaultSubFlowMapping(sf -> sf.<Order, Food>transform(o -> kitchenService.prepareMeal(o)))
                        .resolutionRequired(false))
                .aggregate();
    }
}
