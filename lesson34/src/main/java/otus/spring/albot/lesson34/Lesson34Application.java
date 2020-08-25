package otus.spring.albot.lesson34;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class Lesson34Application {

    public static void main(String[] args) {
        SpringApplication.run(Lesson34Application.class, args);
    }

}
