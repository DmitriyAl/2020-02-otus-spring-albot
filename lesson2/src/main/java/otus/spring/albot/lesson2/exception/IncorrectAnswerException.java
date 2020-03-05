package otus.spring.albot.lesson2.exception;

/**
 * @author Dmitrii Albot
 */
public class IncorrectAnswerException extends QuizException {
    public IncorrectAnswerException(String message) {
        super(message);
    }
}
