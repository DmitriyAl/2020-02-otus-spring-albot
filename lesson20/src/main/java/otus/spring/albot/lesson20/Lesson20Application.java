package otus.spring.albot.lesson20;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class Lesson20Application {

    public static void main(String[] args) {
        SpringApplication.run(Lesson20Application.class, args);
    }

}
