package otus.spring.albot.questionHandler;

import otus.spring.albot.exception.QuestionTypeIsNotSupportedException;
import otus.spring.albot.model.QuestionType;

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
