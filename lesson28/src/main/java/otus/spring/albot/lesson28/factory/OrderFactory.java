package otus.spring.albot.lesson28.factory;

import otus.spring.albot.lesson28.exception.NoSuchMealException;
import otus.spring.albot.lesson28.model.Order;
import otus.spring.albot.lesson28.model.OrderType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OrderFactory {
    public static List<String> menu() {
        return Collections.unmodifiableList(Arrays.asList("milk", "pork", "beer", "pancake", "juice", "tea", "beefsteak", "borscht"));
    }

    public static Order getByName(String name) {
        switch (name) {
            case "milk":
                return new Order("milk", OrderType.DRINK, 1.3f);
            case "pork":
                return new Order("pork", OrderType.MAIN_MEAL, 2.5f);
            case "beer":
                return new Order("beer", OrderType.ALCOHOL, 3.2f);
            case "pancake":
                return new Order("pancake", OrderType.DESERT, 2.7f);
            case "juice":
                return new Order("juice", OrderType.DRINK, 1.2f);
            case "tea":
                return new Order("tea", OrderType.DRINK, 1.5f);
            case "beefsteak":
                return new Order("tea", OrderType.MAIN_MEAL, 5.5f);
            case "borscht":
                return new Order("borscht", OrderType.SOUP, 3.5f);
            default:
                throw new NoSuchMealException(String.format("The '%s' is not exists in the menu", name));
        }
    }
}
