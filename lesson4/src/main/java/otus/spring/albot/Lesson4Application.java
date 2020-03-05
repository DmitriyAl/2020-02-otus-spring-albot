package otus.spring.albot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import otus.spring.albot.game.GameManager;

@SpringBootApplication
public class Lesson4Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Lesson4Application.class, args);
		GameManager gameManager = context.getBean(GameManager.class);
		gameManager.launch();
	}
}
