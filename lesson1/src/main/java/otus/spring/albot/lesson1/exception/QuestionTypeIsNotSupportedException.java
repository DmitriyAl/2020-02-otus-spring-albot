package otus.spring.albot.lesson1.exception;

/**
 * @author Dmitrii Albot
 */
public class QuestionTypeIsNotSupportedException extends Exception {
    public QuestionTypeIsNotSupportedException(String message) {
        super(message);
    }
}
