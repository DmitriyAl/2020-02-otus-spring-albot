package otus.spring.albot.exception;

/**
 * <pre>
 * $Id: $
 * $LastChangedBy: $
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * </pre>
 *
 * @author Dmitrii Albot
 */
public abstract class QuizException extends Exception {
    public QuizException(String message) {
        super(message);
    }
}
