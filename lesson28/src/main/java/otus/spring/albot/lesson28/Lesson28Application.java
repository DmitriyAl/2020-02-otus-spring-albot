package otus.spring.albot.lesson28;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import otus.spring.albot.lesson28.factory.OrderFactory;
import otus.spring.albot.lesson28.integration.Cafe;
import otus.spring.albot.lesson28.model.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@IntegrationComponentScan
public class Lesson28Application {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(Lesson28Application.class, args);
        Cafe cafe = context.getBean(Cafe.class);

        Random random = new Random();
        do {
            System.out.println("__________________________________________");
            System.out.println("We are ready for a new order");
            int amount = random.nextInt(OrderFactory.menu().size());
            List<String> order = new ArrayList<>();
            for (int i = 0; i < amount; i++) {
                int randomIndex = random.nextInt(OrderFactory.menu().size());
                order.add(OrderFactory.menu().get(randomIndex));
            }
            System.out.println("The client ordered: " + order);
            System.out.println("We are starting the process...");
            List<Food> food = cafe.process(order);
            System.out.println("Order received. It contains: " + food);
            TimeUnit.SECONDS.sleep(10);
        } while (true);
    }
}
