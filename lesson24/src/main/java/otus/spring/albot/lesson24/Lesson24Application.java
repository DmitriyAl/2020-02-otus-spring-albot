package otus.spring.albot.lesson24;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class Lesson24Application {

    public static void main(String[] args) {
        SpringApplication.run(Lesson24Application.class, args);
    }

}
