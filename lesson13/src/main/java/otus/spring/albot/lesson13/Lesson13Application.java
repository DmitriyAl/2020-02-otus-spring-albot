package otus.spring.albot.lesson13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class Lesson13Application {
    public static void main(String[] args) {
        SpringApplication.run(Lesson13Application.class, args);
    }
}
