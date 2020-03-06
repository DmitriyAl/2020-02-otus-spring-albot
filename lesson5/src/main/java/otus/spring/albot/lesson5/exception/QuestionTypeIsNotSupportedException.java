package otus.spring.albot.lesson5.exception;

/**
 * @author Dmitrii Albot
 */
public class QuestionTypeIsNotSupportedException extends QuizException {
    public QuestionTypeIsNotSupportedException(String message) {
        super(message);
    }
}
