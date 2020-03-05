package otus.spring.albot.lesson1.game;

import otus.spring.albot.lesson1.exception.IncorrectAnswerException;
import otus.spring.albot.lesson1.exception.QuestionTypeIsNotSupportedException;
import otus.spring.albot.lesson1.handler.QuestionHandler;
import otus.spring.albot.lesson1.handler.QuestionHandlerFactory;
import otus.spring.albot.lesson1.model.ParsedLine;

import java.util.List;
import java.util.Scanner;

/**
 * @author Dmitrii Albot
 */
public class GameManager {
    private QuestionHandlerFactory factory;
    private Scanner scanner;
    private String currentPlayer;
    private int counter;
    private int correctAnswers;
    private int amount = 5;

    public GameManager(QuestionHandlerFactory factory) {
        this.factory = factory;
        this.scanner = new Scanner(System.in);
    }

    public void startGame(List<ParsedLine> questions) {
        describeRules();
        askQuestions(questions);
        showResults();
    }

    private void describeRules() {
        System.out.println("Welcome to student quiz! Please enter your name");
        currentPlayer = scanner.next();
        System.out
                .printf("Ok, %s, you have to answer on %d questions. It could be the questions with a choice, " +
                                "true/false questions or open questions. Good luck!\n",
                        currentPlayer, amount);
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
        System.out
                .printf("%s, you correctly answered on %d from %d questions\n", currentPlayer, correctAnswers, counter);
    }
}
