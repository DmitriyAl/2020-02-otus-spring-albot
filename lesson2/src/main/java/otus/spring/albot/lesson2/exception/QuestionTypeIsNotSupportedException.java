package otus.spring.albot.lesson2.exception;

/**
 * @author Dmitrii Albot
 */
public class QuestionTypeIsNotSupportedException extends QuizException {
    public QuestionTypeIsNotSupportedException(String message) {
        super(message);
    }
}
