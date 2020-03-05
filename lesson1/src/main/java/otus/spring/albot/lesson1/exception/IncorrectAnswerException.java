package otus.spring.albot.lesson1.exception;

/**
 * @author Dmitrii Albot
 */
public class IncorrectAnswerException extends Exception {
    public IncorrectAnswerException(String message) {
        super(message);
    }
}
