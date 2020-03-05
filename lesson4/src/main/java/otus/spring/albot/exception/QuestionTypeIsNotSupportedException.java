package otus.spring.albot.exception;

/**
 * @author Dmitrii Albot
 */
public class QuestionTypeIsNotSupportedException extends QuizException {
    public QuestionTypeIsNotSupportedException(String message) {
        super(message);
    }
}
