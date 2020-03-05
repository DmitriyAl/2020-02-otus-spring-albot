package otus.spring.albot.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import otus.spring.albot.exception.IncorrectAnswerException;
import otus.spring.albot.exception.QuestionTypeIsNotSupportedException;
import otus.spring.albot.model.ParsedLine;
import otus.spring.albot.questionHandler.QuestionHandlerFactory;
import otus.spring.albot.questionHandler.QuestionHandler;
import otus.spring.albot.util.message.MessageHandler;

import java.util.List;
import java.util.Scanner;

/**
 * @author Dmitrii Albot
 */
@Service
public class GameManagerImpl implements GameManager {
    private final GamePreparer gamePreparer;
    private final QuestionHandlerFactory factory;
    private final MessageHandler messageHandler;
    private final Scanner scanner;
    private String currentPlayer;
    private int counter;
    private int correctAnswers;
    private String greetingKey;
    private String rulesKey;
    private String resultKey;
    private int amount;

    @Autowired
    public GameManagerImpl(
            GamePreparer gamePreparer, QuestionHandlerFactory factory, MessageHandler messageHandler,
            @Value("${game.greeting}") String greetingKey, @Value("${game.rules}") String rulesKey,
            @Value("${game.result}") String resultKey, @Value("${game.amount}") int amount) {
        this.gamePreparer = gamePreparer;
        this.factory = factory;
        this.messageHandler = messageHandler;
        this.scanner = new Scanner(System.in);
        this.greetingKey = greetingKey;
        this.rulesKey = rulesKey;
        this.resultKey = resultKey;
        this.amount = amount;
    }

    @Override
    public void launch() {
        startGame(gamePreparer.prepareQuestions());
    }

    private void startGame(List<ParsedLine> questions) {
        describeRules();
        askQuestions(questions);
        showResults();
    }

    private void describeRules() {
        System.out.println(messageHandler.getMessage(greetingKey));
        currentPlayer = scanner.next();
        System.out.println(messageHandler.getMessage(rulesKey, currentPlayer, String.valueOf(amount)));
    }

    private void askQuestions(List<ParsedLine> questions) {
        for (int i = 0; i < amount && i < questions.size(); i++) {
            try {
                ParsedLine question = questions.get(i);
                QuestionHandler handler = factory.getHandler(question.getType());
                counter++;
                boolean handled;
                do {
                    handler.askQuestion(counter, question);
                    String answer = waitForAnswer();
                    handled = handleAnswer(question, answer);
                } while (!handled);
            } catch (QuestionTypeIsNotSupportedException e) {
                questions.remove(i--);
            }
        }
    }

    private boolean handleAnswer(ParsedLine question, String answer) throws QuestionTypeIsNotSupportedException {
        boolean handled = true;
        QuestionHandler handler = factory.getHandler(question.getType());
        boolean correct = false;
        try {
            correct = handler.handleQuestion(question, answer);
        } catch (IncorrectAnswerException e) {
            System.out.println(e.getMessage());
            handled = false;
        }
        if (correct) {
            correctAnswers++;
        }
        return handled;
    }

    private String waitForAnswer() {
        return scanner.next();
    }

    private void showResults() {
        System.out.println(messageHandler.getMessage(resultKey, currentPlayer, String.valueOf(correctAnswers),
                                                     String.valueOf(counter)));
    }
}
