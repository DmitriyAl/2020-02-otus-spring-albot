package otus.spring.albot.exception;

/**
 * @author Dmitrii Albot
 */
public class IncorrectAnswerException extends QuizException {
    public IncorrectAnswerException(String message) {
        super(message);
    }
}
