package otus.spring.albot.lesson5.questionHandler;


import otus.spring.albot.lesson5.exception.QuestionTypeIsNotSupportedException;
import otus.spring.albot.lesson5.model.QuestionType;

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
public interface QuestionHandlerFactory {
    QuestionHandler getHandler(QuestionType type) throws QuestionTypeIsNotSupportedException;
}
