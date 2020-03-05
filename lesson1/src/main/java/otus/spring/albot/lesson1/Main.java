package otus.spring.albot.lesson1;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import otus.spring.albot.lesson1.game.GameManager;
import otus.spring.albot.lesson1.util.QuestionsPreparer;
import otus.spring.albot.lesson1.util.QuizParser;
import otus.spring.albot.lesson1.model.ParsedLine;

import java.util.List;

/**
 * @author Dmitrii Albot
 */
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuizParser quizParser = context.getBean(QuizParser.class);
        QuestionsPreparer questionsPreparer = context.getBean(QuestionsPreparer.class);
        GameManager gameManager = context.getBean(GameManager.class);

        List<ParsedLine> lines = quizParser.parse();
        lines = questionsPreparer.prepareQuestions(lines);
        gameManager.startGame(lines);
    }
}
