package otus.spring.albot.lesson2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import otus.spring.albot.lesson2.game.GameManager;
import otus.spring.albot.lesson2.game.GameManagerImpl;

/**
 * @author Dmitrii Albot
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        GameManager gameManager = context.getBean(GameManagerImpl.class);
        gameManager.launch();
    }
}
